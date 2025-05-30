/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spring.data.spanner.core.convert;

import com.google.cloud.ByteArray;
import com.google.cloud.Date;
import com.google.cloud.Timestamp;
import com.google.cloud.spanner.Interval;
import com.google.cloud.spanner.Key;
import com.google.cloud.spanner.Mutation.WriteBuilder;
import com.google.cloud.spanner.Struct;
import com.google.cloud.spanner.Type;
import com.google.cloud.spanner.Value;
import com.google.cloud.spanner.ValueBinder;
import com.google.cloud.spring.data.spanner.core.mapping.SpannerDataException;
import com.google.cloud.spring.data.spanner.core.mapping.SpannerMappingContext;
import com.google.cloud.spring.data.spanner.core.mapping.SpannerPersistentEntity;
import com.google.cloud.spring.data.spanner.core.mapping.SpannerPersistentProperty;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import org.springframework.data.mapping.PersistentPropertyAccessor;
import org.springframework.util.Assert;

/**
 * The primary class for adding values from entity objects to {@link WriteBuilder} for the purpose
 * of creating mutations for Spanner.
 *
 * @since 1.1
 */
public class ConverterAwareMappingSpannerEntityWriter implements SpannerEntityWriter {

  private static final Set<Class> SPANNER_KEY_COMPATIBLE_TYPES =
      Collections.unmodifiableSet(
          new HashSet<Class>(
              Arrays.asList(
                  Boolean.class,
                  Integer.class,
                  Long.class,
                  Float.class,
                  Double.class,
                  String.class,
                  ByteArray.class,
                  Timestamp.class,
                  com.google.cloud.Date.class,
                  BigDecimal.class,
                  UUID.class)));

  /** A map of types to functions that binds them to `ValueBinder` objects. */
  public static final Map<Class<?>, BiFunction<ValueBinder, ?, ?>>
      singleItemTypeValueBinderMethodMap;

  static final Map<Class<?>, BiConsumer<ValueBinder<?>, Iterable>> iterablePropertyTypeToMethodMap =
      createIterableTypeMapping();

  @SuppressWarnings("unchecked")
  private static Map<Class<?>, BiConsumer<ValueBinder<?>, Iterable>> createIterableTypeMapping() {
    Map<Class<?>, BiConsumer<ValueBinder<?>, Iterable>> map = new LinkedHashMap<>();
    map.put(Boolean.class, ValueBinder::toBoolArray);
    map.put(Long.class, ValueBinder::toInt64Array);
    map.put(Double.class, ValueBinder::toFloat64Array);
    map.put(Float.class, ValueBinder::toFloat32Array);
    map.put(BigDecimal.class, ValueBinder::toNumericArray);
    map.put(Timestamp.class, ValueBinder::toTimestampArray);
    map.put(Date.class, ValueBinder::toDateArray);
    map.put(ByteArray.class, ValueBinder::toBytesArray);
    map.put(String.class, ValueBinder::toStringArray);
    map.put(Interval.class, ValueBinder::toIntervalArray);
    map.put(UUID.class, ValueBinder::toUuidArray);

    return Collections.unmodifiableMap(map);
  }

  static {
    Map<Class<?>, BiFunction<ValueBinder, ?, ?>> map = new LinkedHashMap<>();

    map.put(Boolean.class, (BiFunction<ValueBinder, Boolean, ?>) ValueBinder::to);
    map.put(Long.class, (BiFunction<ValueBinder, Long, ?>) ValueBinder::to);
    map.put(long.class, (BiFunction<ValueBinder, Long, ?>) ValueBinder::to);
    map.put(Double.class, (BiFunction<ValueBinder, Double, ?>) ValueBinder::to);
    map.put(double.class, (BiFunction<ValueBinder, Double, ?>) ValueBinder::to);
    map.put(Float.class, (BiFunction<ValueBinder, Float, ?>) ValueBinder::to);
    map.put(float.class, (BiFunction<ValueBinder, Float, ?>) ValueBinder::to);
    map.put(BigDecimal.class, (BiFunction<ValueBinder, BigDecimal, ?>) ValueBinder::to);
    map.put(Timestamp.class, (BiFunction<ValueBinder, Timestamp, ?>) ValueBinder::to);
    map.put(Date.class, (BiFunction<ValueBinder, Date, ?>) ValueBinder::to);
    map.put(ByteArray.class, (BiFunction<ValueBinder, ByteArray, ?>) ValueBinder::to);
    map.put(String.class, (BiFunction<ValueBinder, String, ?>) ValueBinder::to);
    map.put(double[].class, (BiFunction<ValueBinder, double[], ?>) ValueBinder::toFloat64Array);
    map.put(float[].class, (BiFunction<ValueBinder, float[], ?>) ValueBinder::toFloat32Array);
    map.put(boolean[].class, (BiFunction<ValueBinder, boolean[], ?>) ValueBinder::toBoolArray);
    map.put(long[].class, (BiFunction<ValueBinder, long[], ?>) ValueBinder::toInt64Array);
    map.put(Struct.class, (BiFunction<ValueBinder, Struct, ?>) ValueBinder::to);
    map.put(Interval.class, (BiFunction<ValueBinder, Interval, ?>) ValueBinder::to);
    map.put(UUID.class, (BiFunction<ValueBinder, UUID, ?>) ValueBinder::to);
    singleItemTypeValueBinderMethodMap = Collections.unmodifiableMap(map);
  }

  private final SpannerMappingContext spannerMappingContext;

  private final SpannerWriteConverter writeConverter;

  ConverterAwareMappingSpannerEntityWriter(
      SpannerMappingContext spannerMappingContext, SpannerWriteConverter writeConverter) {
    this.spannerMappingContext = spannerMappingContext;
    this.writeConverter = writeConverter;
  }

  public static Class<?> findFirstCompatibleSpannerSingleItemNativeType(Predicate<Class> testFunc) {
    return singleItemTypeValueBinderMethodMap.keySet().stream()
        .filter(testFunc)
        .findFirst()
        .orElse(null);
  }

  public static Class<?> findFirstCompatibleSpannerMultipleItemNativeType(
      Predicate<Class> testFunc) {
    return iterablePropertyTypeToMethodMap.keySet().stream()
        .filter(testFunc)
        .findFirst()
        .orElse(null);
  }

  @Override
  public void write(Object source, MultipleValueBinder sink) {
    write(source, sink, null);
  }

  /**
   * Writes an object's properties to the sink.
   *
   * @param source the object to write
   * @param sink the sink to which to write
   * @param includeColumns the columns to write. If null, then all columns are written.
   */
  public void write(Object source, MultipleValueBinder sink, Set<String> includeColumns) {
    boolean writeAllColumns = includeColumns == null;
    SpannerPersistentEntity<?> persistentEntity =
        this.spannerMappingContext.getPersistentEntityOrFail(source.getClass());
    PersistentPropertyAccessor accessor = persistentEntity.getPropertyAccessor(source);
    persistentEntity.doWithColumnBackedProperties(
        spannerPersistentProperty -> {
          if (spannerPersistentProperty.isEmbedded()) {
            Object embeddedObject = accessor.getProperty(spannerPersistentProperty);
            if (embeddedObject != null) {
              write(embeddedObject, sink, includeColumns);
            }
          } else if (writeAllColumns
              || includeColumns.contains(spannerPersistentProperty.getColumnName())) {
            writeProperty(sink, accessor, spannerPersistentProperty);
          }
        });
  }

  @Override
  public Key convertToKey(Object key) {
    Assert.notNull(key, "Key of an entity to be written cannot be null!");

    Key k;
    boolean isIterable = Iterable.class.isAssignableFrom(key.getClass());
    boolean isArray = Object[].class.isAssignableFrom(key.getClass());
    if ((isIterable || isArray) && !ByteArray.class.isAssignableFrom(key.getClass())) {
      Key.Builder kb = Key.newBuilder();
      for (Object keyPart : (isArray ? (Arrays.asList((Object[]) key)) : ((Iterable) key))) {
        kb.appendObject(convertKeyPart(keyPart));
      }
      k = kb.build();
      if (k.size() == 0) {
        throw new SpannerDataException("A key must have at least one component, but 0 were given.");
      }
    } else {
      k = Key.class.isAssignableFrom(key.getClass()) ? (Key) key : Key.of(convertKeyPart(key));
    }
    return k;
  }

  @Override
  public SpannerWriteConverter getSpannerWriteConverter() {
    return this.writeConverter;
  }

  /**
   * Bind an iterable value to a ValueBinder.
   *
   * @param value the value to bind.
   * @param valueBinder the binder that accepts the value.
   * @param writeConverter the converter to use to convert the values.
   * @param innerType the type of the items in the iterable.
   * @return {@code true} if the binding was successful.
   */
  public static boolean attemptSetIterableValueOnBinder(
      Iterable<Object> value,
      ValueBinder valueBinder,
      SpannerCustomConverter writeConverter,
      Class innerType) {
    boolean valueSet = false;
    // attempt check if there is directly a write method that can accept the
    // property
    if (iterablePropertyTypeToMethodMap.containsKey(innerType)) {
      iterablePropertyTypeToMethodMap.get(innerType).accept(valueBinder, value);
      valueSet = true;
    }

    // Finally find any compatible conversion
    if (!valueSet) {
      for (Class<?> targetType : iterablePropertyTypeToMethodMap.keySet()) {
        valueSet =
            attemptSetIterablePropertyWithTypeConversion(
                value, valueBinder, innerType, targetType, writeConverter);
        if (valueSet) {
          break;
        }
      }
    }
    return valueSet;
  }

  /**
   * Bind a value to a ValueBinder.
   *
   * @param propertyValue the value to bind.
   * @param propertyType the type of the value to bind.
   * @param valueBinder the binder.
   * @param spannerCustomConverter the converter used to convert if necessary.
   * @return {@code true} if the value was bound successfully.
   */
  public static boolean attemptBindSingleValue(
      Object propertyValue,
      Class<?> propertyType,
      ValueBinder valueBinder,
      SpannerCustomConverter spannerCustomConverter) {
    // directly try to set using the property's original Java type
    boolean valueSet =
        attemptSetSingleItemValue(
            propertyValue, propertyType, valueBinder, propertyType, spannerCustomConverter);

    // Finally try and find any conversion that works
    if (!valueSet) {
      for (Class<?> targetType : singleItemTypeValueBinderMethodMap.keySet()) {
        valueSet =
            attemptSetSingleItemValue(
                propertyValue, propertyType, valueBinder, targetType, spannerCustomConverter);
        if (valueSet) {
          break;
        }
      }
    }
    return valueSet;
  }

  private static boolean attemptSetIterablePropertyWithTypeConversion(
      Iterable<Object> value,
      ValueBinder<WriteBuilder> valueBinder,
      Class innerType,
      Class<?> targetType,
      SpannerCustomConverter writeConverter) {
    if (writeConverter.canConvert(innerType, targetType)) {
      BiConsumer<ValueBinder<?>, Iterable> toMethod =
          iterablePropertyTypeToMethodMap.get(targetType);
      toMethod.accept(
          valueBinder,
          (value != null)
              ? ConversionUtils.convertIterable(value, targetType, writeConverter)
              : null);
      return true;
    }
    return false;
  }

  private Object convertKeyPart(Object object) {

    if (object == null || isValidSpannerKeyType(ConversionUtils.boxIfNeeded(object.getClass()))) {
      return object;
    }
    /*
     * Iterate through the supported Key component types in the same order as the write
     * converter. For example, if a type can be converted to both String and Double, we want
     * both the this key conversion and the write converter to choose the same.
     */
    Class<?> compatible =
        ConverterAwareMappingSpannerEntityWriter.findFirstCompatibleSpannerSingleItemNativeType(
            spannerType ->
                isValidSpannerKeyType(spannerType)
                    && this.writeConverter.canConvert(object.getClass(), spannerType));

    if (compatible == null) {
      throw new SpannerDataException(
          "The given object type couldn't be built into a Cloud Spanner Key: " + object.getClass());
    }
    return this.writeConverter.convert(object, compatible);
  }

  private boolean isValidSpannerKeyType(Class type) {
    return SPANNER_KEY_COMPATIBLE_TYPES.contains(type);
  }

  // @formatter:off

  private boolean attemptSetIterableValue(
      Iterable<Object> value,
      ValueBinder<WriteBuilder> valueBinder,
      SpannerPersistentProperty spannerPersistentProperty,
      SpannerCustomConverter writeConverter) {

    Class innerType = ConversionUtils.boxIfNeeded(spannerPersistentProperty.getColumnInnerType());
    if (innerType == null) {
      return false;
    }

    boolean valueSet = false;


    if (spannerPersistentProperty.getAnnotatedColumnItemType() == Type.Code.JSON) {
      // if column annotated with JSON, convert directly
      valueBinder.toJsonArray(this.convertIterableJsonToValue(value));
      valueSet = true;
    } else if (spannerPersistentProperty.getAnnotatedColumnItemType() != null) {
      // use the annotated column type if possible.
      valueSet =
          attemptSetIterablePropertyWithTypeConversion(
              value,
              valueBinder,
              innerType,
              SpannerTypeMapper.getSimpleJavaClassFor(
                  spannerPersistentProperty.getAnnotatedColumnItemType()),
              writeConverter);
    } else {
      valueSet = attemptSetIterableValueOnBinder(value, valueBinder, writeConverter, innerType);
    }
    return valueSet;
  }

  @SuppressWarnings("unchecked")
  private static <T> boolean attemptSetSingleItemValue(
      Object value,
      Class<?> sourceType,
      ValueBinder<WriteBuilder> valueBinder,
      Class<T> targetType,
      SpannerCustomConverter writeConverter) {
    if (!writeConverter.canConvert(sourceType, targetType)) {
      return false;
    }
    Class innerType = ConversionUtils.boxIfNeeded(targetType);
    BiFunction<ValueBinder, T, ?> toMethod =
        (BiFunction<ValueBinder, T, ?>) singleItemTypeValueBinderMethodMap.get(innerType);
    if (toMethod == null) {
      return false;
    }
    // We're just checking for the bind to have succeeded, we don't need to chain the result.
    // Spanner allows binding of null values.
    Object ignored =
        toMethod.apply(
            valueBinder, (value != null) ? writeConverter.convert(value, targetType) : null);
    return true;
  }

  private Value convertJsonToValue(Object value) {
    if (value == null) {
      return Value.json(null);
    }
    String jsonString = this.spannerMappingContext.getGson().toJson(value);
    return Value.json(jsonString);
  }

  private Iterable<String> convertIterableJsonToValue(Iterable<Object> value) {
    List<String> result = new ArrayList<>();
    if (value == null) {
      return null;
    }
    value.forEach(item -> result.add(this.spannerMappingContext.getGson().toJson(item)));
    return result;
  }

  /**
   * For each property this method "set"s the column name and finds the corresponding "to" method on
   * the {@link ValueBinder} interface.
   *
   * <pre>
   * {
   *   &#64;code
   *
   *   long singerId = my_singer_id;
   *   Mutation.WriteBuilder writeBuilder = Mutation.newInsertBuilder("Singer")
   *       .set("SingerId")
   *       .to(singerId)
   *       .set("FirstName")
   *       .to("Billy")
   *       .set("LastName")
   *       .to("Joel");
   * }
   * </pre>
   *
   * @param accessor the accessor used to get the value to write
   * @param property the property that will be written
   * @param sink the object that will accept the value to be written
   */
  // @formatter:on
  @SuppressWarnings("unchecked")
  private void writeProperty(
      MultipleValueBinder sink,
      PersistentPropertyAccessor accessor,
      SpannerPersistentProperty property) {
    Object propertyValue = accessor.getProperty(property);

    Class<?> propertyType = property.getType();
    ValueBinder<WriteBuilder> valueBinder = sink.set(property.getColumnName());

    boolean valueSet = false;

    /*
     * Due to type erasure, binder methods for Iterable properties must be manually specified.
     * ByteArray must be excluded since it implements Iterable, but is also explicitly
     * supported by spanner.
     */
    if (ConversionUtils.isIterableNonByteArrayType(propertyType)) {
      valueSet =
          attemptSetIterableValue(
              (Iterable<Object>) propertyValue, valueBinder, property, this.writeConverter);
    } else {

      // if the property is a commit timestamp, then its Spanner column type is always TIMESTAMP
      // and only the dummy value needs to be written to trigger auto-population of the commit
      // time
      if (property.isCommitTimestamp()) {
        valueSet =
            attemptSetSingleItemValue(
                Value.COMMIT_TIMESTAMP,
                Timestamp.class,
                valueBinder,
                Timestamp.class,
                this.writeConverter);
      } else if (property.getAnnotatedColumnItemType() == Type.Code.JSON) {
        // annotated json column, bind directly
        valueBinder.to(this.convertJsonToValue(propertyValue));
        valueSet = true;
      } else if (property.getAnnotatedColumnItemType() != null) {
        // use the user's annotated column type if possible
        valueSet =
            attemptSetSingleItemValue(
                propertyValue,
                propertyType,
                valueBinder,
                SpannerTypeMapper.getSimpleJavaClassFor(property.getAnnotatedColumnItemType()),
                this.writeConverter);
      } else {
        valueSet =
            attemptBindSingleValue(propertyValue, propertyType, valueBinder, this.writeConverter);
      }
    }

    if (!valueSet) {
      throw new SpannerDataException(
          String.format("Unsupported mapping for type: %s", propertyType));
    }
  }
}
