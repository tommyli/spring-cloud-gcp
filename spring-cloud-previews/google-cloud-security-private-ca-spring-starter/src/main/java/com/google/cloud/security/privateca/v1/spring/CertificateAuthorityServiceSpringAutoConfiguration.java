/*
 * Copyright 2025 Google LLC
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

package com.google.cloud.security.privateca.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceClient;
import com.google.cloud.security.privateca.v1.CertificateAuthorityServiceSettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link CertificateAuthorityServiceClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(CertificateAuthorityServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.security.privateca.v1.certificate-authority-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(CertificateAuthorityServiceSpringProperties.class)
public class CertificateAuthorityServiceSpringAutoConfiguration {
  private final CertificateAuthorityServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(CertificateAuthorityServiceSpringAutoConfiguration.class);

  protected CertificateAuthorityServiceSpringAutoConfiguration(
      CertificateAuthorityServiceSpringProperties clientProperties,
      CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from CertificateAuthorityService-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean, corresponding to the client library's
   * default transport channel provider. If the library supports both GRPC and REST transport, and
   * the useRest property is configured, the HTTP/JSON transport provider will be used instead of
   * GRPC.
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultCertificateAuthorityServiceTransportChannelProvider")
  public TransportChannelProvider defaultCertificateAuthorityServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return CertificateAuthorityServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return CertificateAuthorityServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a CertificateAuthorityServiceSettings bean configured to use a
   * DefaultCredentialsProvider and the client library's default transport channel provider
   * (defaultCertificateAuthorityServiceTransportChannelProvider()). It also configures the quota
   * project ID and executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in CertificateAuthorityServiceSpringProperties. Method-level properties will take precedence
   * over service-level properties if available, and client library defaults will be used if neither
   * are specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link CertificateAuthorityServiceSettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public CertificateAuthorityServiceSettings certificateAuthorityServiceSettings(
      @Qualifier("defaultCertificateAuthorityServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    CertificateAuthorityServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = CertificateAuthorityServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = CertificateAuthorityServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setEndpoint(CertificateAuthorityServiceSettings.getDefaultEndpoint())
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          CertificateAuthorityServiceSettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings createCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createCertificateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .createCertificateSettings()
          .setRetrySettings(createCertificateRetrySettings);

      RetrySettings getCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getCertificateSettings().setRetrySettings(getCertificateRetrySettings);

      RetrySettings listCertificatesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificatesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listCertificatesSettings()
          .setRetrySettings(listCertificatesRetrySettings);

      RetrySettings revokeCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.revokeCertificateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .revokeCertificateSettings()
          .setRetrySettings(revokeCertificateRetrySettings);

      RetrySettings updateCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateCertificateSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .updateCertificateSettings()
          .setRetrySettings(updateCertificateRetrySettings);

      RetrySettings fetchCertificateAuthorityCsrRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.fetchCertificateAuthorityCsrSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .fetchCertificateAuthorityCsrSettings()
          .setRetrySettings(fetchCertificateAuthorityCsrRetrySettings);

      RetrySettings getCertificateAuthorityRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateAuthoritySettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .getCertificateAuthoritySettings()
          .setRetrySettings(getCertificateAuthorityRetrySettings);

      RetrySettings listCertificateAuthoritiesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificateAuthoritiesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listCertificateAuthoritiesSettings()
          .setRetrySettings(listCertificateAuthoritiesRetrySettings);

      RetrySettings getCaPoolRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCaPoolSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getCaPoolSettings().setRetrySettings(getCaPoolRetrySettings);

      RetrySettings listCaPoolsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCaPoolsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listCaPoolsSettings().setRetrySettings(listCaPoolsRetrySettings);

      RetrySettings fetchCaCertsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.fetchCaCertsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.fetchCaCertsSettings().setRetrySettings(fetchCaCertsRetrySettings);

      RetrySettings getCertificateRevocationListRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateRevocationListSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .getCertificateRevocationListSettings()
          .setRetrySettings(getCertificateRevocationListRetrySettings);

      RetrySettings listCertificateRevocationListsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificateRevocationListsSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listCertificateRevocationListsSettings()
          .setRetrySettings(listCertificateRevocationListsRetrySettings);

      RetrySettings getCertificateTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateTemplateSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .getCertificateTemplateSettings()
          .setRetrySettings(getCertificateTemplateRetrySettings);

      RetrySettings listCertificateTemplatesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificateTemplatesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listCertificateTemplatesSettings()
          .setRetrySettings(listCertificateTemplatesRetrySettings);

      RetrySettings listLocationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listLocationsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listLocationsSettings().setRetrySettings(listLocationsRetrySettings);

      RetrySettings getLocationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getLocationSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getLocationSettings().setRetrySettings(getLocationRetrySettings);

      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);

      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);

      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry createCertificateRetry = clientProperties.getCreateCertificateRetry();
    if (createCertificateRetry != null) {
      RetrySettings createCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createCertificateSettings().getRetrySettings(),
              createCertificateRetry);
      clientSettingsBuilder
          .createCertificateSettings()
          .setRetrySettings(createCertificateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for createCertificate from properties.");
      }
    }
    Retry getCertificateRetry = clientProperties.getGetCertificateRetry();
    if (getCertificateRetry != null) {
      RetrySettings getCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateSettings().getRetrySettings(),
              getCertificateRetry);
      clientSettingsBuilder.getCertificateSettings().setRetrySettings(getCertificateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getCertificate from properties.");
      }
    }
    Retry listCertificatesRetry = clientProperties.getListCertificatesRetry();
    if (listCertificatesRetry != null) {
      RetrySettings listCertificatesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificatesSettings().getRetrySettings(),
              listCertificatesRetry);
      clientSettingsBuilder
          .listCertificatesSettings()
          .setRetrySettings(listCertificatesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listCertificates from properties.");
      }
    }
    Retry revokeCertificateRetry = clientProperties.getRevokeCertificateRetry();
    if (revokeCertificateRetry != null) {
      RetrySettings revokeCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.revokeCertificateSettings().getRetrySettings(),
              revokeCertificateRetry);
      clientSettingsBuilder
          .revokeCertificateSettings()
          .setRetrySettings(revokeCertificateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for revokeCertificate from properties.");
      }
    }
    Retry updateCertificateRetry = clientProperties.getUpdateCertificateRetry();
    if (updateCertificateRetry != null) {
      RetrySettings updateCertificateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateCertificateSettings().getRetrySettings(),
              updateCertificateRetry);
      clientSettingsBuilder
          .updateCertificateSettings()
          .setRetrySettings(updateCertificateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for updateCertificate from properties.");
      }
    }
    Retry fetchCertificateAuthorityCsrRetry =
        clientProperties.getFetchCertificateAuthorityCsrRetry();
    if (fetchCertificateAuthorityCsrRetry != null) {
      RetrySettings fetchCertificateAuthorityCsrRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.fetchCertificateAuthorityCsrSettings().getRetrySettings(),
              fetchCertificateAuthorityCsrRetry);
      clientSettingsBuilder
          .fetchCertificateAuthorityCsrSettings()
          .setRetrySettings(fetchCertificateAuthorityCsrRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for fetchCertificateAuthorityCsr from properties.");
      }
    }
    Retry getCertificateAuthorityRetry = clientProperties.getGetCertificateAuthorityRetry();
    if (getCertificateAuthorityRetry != null) {
      RetrySettings getCertificateAuthorityRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateAuthoritySettings().getRetrySettings(),
              getCertificateAuthorityRetry);
      clientSettingsBuilder
          .getCertificateAuthoritySettings()
          .setRetrySettings(getCertificateAuthorityRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getCertificateAuthority from properties.");
      }
    }
    Retry listCertificateAuthoritiesRetry = clientProperties.getListCertificateAuthoritiesRetry();
    if (listCertificateAuthoritiesRetry != null) {
      RetrySettings listCertificateAuthoritiesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificateAuthoritiesSettings().getRetrySettings(),
              listCertificateAuthoritiesRetry);
      clientSettingsBuilder
          .listCertificateAuthoritiesSettings()
          .setRetrySettings(listCertificateAuthoritiesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listCertificateAuthorities from properties.");
      }
    }
    Retry getCaPoolRetry = clientProperties.getGetCaPoolRetry();
    if (getCaPoolRetry != null) {
      RetrySettings getCaPoolRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCaPoolSettings().getRetrySettings(), getCaPoolRetry);
      clientSettingsBuilder.getCaPoolSettings().setRetrySettings(getCaPoolRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getCaPool from properties.");
      }
    }
    Retry listCaPoolsRetry = clientProperties.getListCaPoolsRetry();
    if (listCaPoolsRetry != null) {
      RetrySettings listCaPoolsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCaPoolsSettings().getRetrySettings(), listCaPoolsRetry);
      clientSettingsBuilder.listCaPoolsSettings().setRetrySettings(listCaPoolsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listCaPools from properties.");
      }
    }
    Retry fetchCaCertsRetry = clientProperties.getFetchCaCertsRetry();
    if (fetchCaCertsRetry != null) {
      RetrySettings fetchCaCertsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.fetchCaCertsSettings().getRetrySettings(), fetchCaCertsRetry);
      clientSettingsBuilder.fetchCaCertsSettings().setRetrySettings(fetchCaCertsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for fetchCaCerts from properties.");
      }
    }
    Retry getCertificateRevocationListRetry =
        clientProperties.getGetCertificateRevocationListRetry();
    if (getCertificateRevocationListRetry != null) {
      RetrySettings getCertificateRevocationListRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateRevocationListSettings().getRetrySettings(),
              getCertificateRevocationListRetry);
      clientSettingsBuilder
          .getCertificateRevocationListSettings()
          .setRetrySettings(getCertificateRevocationListRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getCertificateRevocationList from properties.");
      }
    }
    Retry listCertificateRevocationListsRetry =
        clientProperties.getListCertificateRevocationListsRetry();
    if (listCertificateRevocationListsRetry != null) {
      RetrySettings listCertificateRevocationListsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificateRevocationListsSettings().getRetrySettings(),
              listCertificateRevocationListsRetry);
      clientSettingsBuilder
          .listCertificateRevocationListsSettings()
          .setRetrySettings(listCertificateRevocationListsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listCertificateRevocationLists from properties.");
      }
    }
    Retry getCertificateTemplateRetry = clientProperties.getGetCertificateTemplateRetry();
    if (getCertificateTemplateRetry != null) {
      RetrySettings getCertificateTemplateRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCertificateTemplateSettings().getRetrySettings(),
              getCertificateTemplateRetry);
      clientSettingsBuilder
          .getCertificateTemplateSettings()
          .setRetrySettings(getCertificateTemplateRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getCertificateTemplate from properties.");
      }
    }
    Retry listCertificateTemplatesRetry = clientProperties.getListCertificateTemplatesRetry();
    if (listCertificateTemplatesRetry != null) {
      RetrySettings listCertificateTemplatesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCertificateTemplatesSettings().getRetrySettings(),
              listCertificateTemplatesRetry);
      clientSettingsBuilder
          .listCertificateTemplatesSettings()
          .setRetrySettings(listCertificateTemplatesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listCertificateTemplates from properties.");
      }
    }
    Retry listLocationsRetry = clientProperties.getListLocationsRetry();
    if (listLocationsRetry != null) {
      RetrySettings listLocationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listLocationsSettings().getRetrySettings(), listLocationsRetry);
      clientSettingsBuilder.listLocationsSettings().setRetrySettings(listLocationsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listLocations from properties.");
      }
    }
    Retry getLocationRetry = clientProperties.getGetLocationRetry();
    if (getLocationRetry != null) {
      RetrySettings getLocationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getLocationSettings().getRetrySettings(), getLocationRetry);
      clientSettingsBuilder.getLocationSettings().setRetrySettings(getLocationRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getLocation from properties.");
      }
    }
    Retry setIamPolicyRetry = clientProperties.getSetIamPolicyRetry();
    if (setIamPolicyRetry != null) {
      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), setIamPolicyRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for setIamPolicy from properties.");
      }
    }
    Retry getIamPolicyRetry = clientProperties.getGetIamPolicyRetry();
    if (getIamPolicyRetry != null) {
      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), getIamPolicyRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getIamPolicy from properties.");
      }
    }
    Retry testIamPermissionsRetry = clientProperties.getTestIamPermissionsRetry();
    if (testIamPermissionsRetry != null) {
      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(),
              testIamPermissionsRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for testIamPermissions from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a CertificateAuthorityServiceClient bean configured with
   * CertificateAuthorityServiceSettings.
   *
   * @param certificateAuthorityServiceSettings settings to configure an instance of client bean.
   * @return a {@link CertificateAuthorityServiceClient} bean configured with {@link
   *     CertificateAuthorityServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public CertificateAuthorityServiceClient certificateAuthorityServiceClient(
      CertificateAuthorityServiceSettings certificateAuthorityServiceSettings) throws IOException {
    return CertificateAuthorityServiceClient.create(certificateAuthorityServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-certificate-authority-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
