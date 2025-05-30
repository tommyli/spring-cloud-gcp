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

package com.google.cloud.datastream.v1.spring;

import com.google.api.core.BetaApi;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.CredentialsSupplier;
import com.google.cloud.spring.core.Retry;
import javax.annotation.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/** Provides default property values for Datastream client bean */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@ConfigurationProperties("com.google.cloud.datastream.v1.datastream")
public class DatastreamSpringProperties implements CredentialsSupplier {
  /** OAuth2 credentials to authenticate and authorize calls to Google Cloud Client Libraries. */
  @NestedConfigurationProperty
  private final Credentials credentials =
      new Credentials("https://www.googleapis.com/auth/cloud-platform");
  /** Quota project to use for billing. */
  private String quotaProjectId;
  /** Number of threads used for executors. */
  private Integer executorThreadCount;
  /** Allow override of default transport channel provider to use REST instead of gRPC. */
  private boolean useRest = false;
  /** Allow override of retry settings at service level, applying to all of its RPC methods. */
  @NestedConfigurationProperty private Retry retry;
  /**
   * Allow override of retry settings at method-level for listConnectionProfiles. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listConnectionProfilesRetry;
  /**
   * Allow override of retry settings at method-level for getConnectionProfile. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getConnectionProfileRetry;
  /**
   * Allow override of retry settings at method-level for discoverConnectionProfile. If defined,
   * this takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry discoverConnectionProfileRetry;
  /**
   * Allow override of retry settings at method-level for listStreams. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listStreamsRetry;
  /**
   * Allow override of retry settings at method-level for getStream. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getStreamRetry;
  /**
   * Allow override of retry settings at method-level for getStreamObject. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getStreamObjectRetry;
  /**
   * Allow override of retry settings at method-level for lookupStreamObject. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry lookupStreamObjectRetry;
  /**
   * Allow override of retry settings at method-level for listStreamObjects. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listStreamObjectsRetry;
  /**
   * Allow override of retry settings at method-level for startBackfillJob. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry startBackfillJobRetry;
  /**
   * Allow override of retry settings at method-level for stopBackfillJob. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry stopBackfillJobRetry;
  /**
   * Allow override of retry settings at method-level for fetchStaticIps. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry fetchStaticIpsRetry;
  /**
   * Allow override of retry settings at method-level for getPrivateConnection. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getPrivateConnectionRetry;
  /**
   * Allow override of retry settings at method-level for listPrivateConnections. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listPrivateConnectionsRetry;
  /**
   * Allow override of retry settings at method-level for getRoute. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getRouteRetry;
  /**
   * Allow override of retry settings at method-level for listRoutes. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listRoutesRetry;
  /**
   * Allow override of retry settings at method-level for listLocations. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listLocationsRetry;
  /**
   * Allow override of retry settings at method-level for getLocation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getLocationRetry;

  @Override
  public Credentials getCredentials() {
    return this.credentials;
  }

  public String getQuotaProjectId() {
    return this.quotaProjectId;
  }

  public void setQuotaProjectId(String quotaProjectId) {
    this.quotaProjectId = quotaProjectId;
  }

  public boolean getUseRest() {
    return this.useRest;
  }

  public void setUseRest(boolean useRest) {
    this.useRest = useRest;
  }

  public Integer getExecutorThreadCount() {
    return this.executorThreadCount;
  }

  public void setExecutorThreadCount(Integer executorThreadCount) {
    this.executorThreadCount = executorThreadCount;
  }

  public Retry getRetry() {
    return this.retry;
  }

  public void setRetry(Retry retry) {
    this.retry = retry;
  }

  public Retry getListConnectionProfilesRetry() {
    return this.listConnectionProfilesRetry;
  }

  public void setListConnectionProfilesRetry(Retry listConnectionProfilesRetry) {
    this.listConnectionProfilesRetry = listConnectionProfilesRetry;
  }

  public Retry getGetConnectionProfileRetry() {
    return this.getConnectionProfileRetry;
  }

  public void setGetConnectionProfileRetry(Retry getConnectionProfileRetry) {
    this.getConnectionProfileRetry = getConnectionProfileRetry;
  }

  public Retry getDiscoverConnectionProfileRetry() {
    return this.discoverConnectionProfileRetry;
  }

  public void setDiscoverConnectionProfileRetry(Retry discoverConnectionProfileRetry) {
    this.discoverConnectionProfileRetry = discoverConnectionProfileRetry;
  }

  public Retry getListStreamsRetry() {
    return this.listStreamsRetry;
  }

  public void setListStreamsRetry(Retry listStreamsRetry) {
    this.listStreamsRetry = listStreamsRetry;
  }

  public Retry getGetStreamRetry() {
    return this.getStreamRetry;
  }

  public void setGetStreamRetry(Retry getStreamRetry) {
    this.getStreamRetry = getStreamRetry;
  }

  public Retry getGetStreamObjectRetry() {
    return this.getStreamObjectRetry;
  }

  public void setGetStreamObjectRetry(Retry getStreamObjectRetry) {
    this.getStreamObjectRetry = getStreamObjectRetry;
  }

  public Retry getLookupStreamObjectRetry() {
    return this.lookupStreamObjectRetry;
  }

  public void setLookupStreamObjectRetry(Retry lookupStreamObjectRetry) {
    this.lookupStreamObjectRetry = lookupStreamObjectRetry;
  }

  public Retry getListStreamObjectsRetry() {
    return this.listStreamObjectsRetry;
  }

  public void setListStreamObjectsRetry(Retry listStreamObjectsRetry) {
    this.listStreamObjectsRetry = listStreamObjectsRetry;
  }

  public Retry getStartBackfillJobRetry() {
    return this.startBackfillJobRetry;
  }

  public void setStartBackfillJobRetry(Retry startBackfillJobRetry) {
    this.startBackfillJobRetry = startBackfillJobRetry;
  }

  public Retry getStopBackfillJobRetry() {
    return this.stopBackfillJobRetry;
  }

  public void setStopBackfillJobRetry(Retry stopBackfillJobRetry) {
    this.stopBackfillJobRetry = stopBackfillJobRetry;
  }

  public Retry getFetchStaticIpsRetry() {
    return this.fetchStaticIpsRetry;
  }

  public void setFetchStaticIpsRetry(Retry fetchStaticIpsRetry) {
    this.fetchStaticIpsRetry = fetchStaticIpsRetry;
  }

  public Retry getGetPrivateConnectionRetry() {
    return this.getPrivateConnectionRetry;
  }

  public void setGetPrivateConnectionRetry(Retry getPrivateConnectionRetry) {
    this.getPrivateConnectionRetry = getPrivateConnectionRetry;
  }

  public Retry getListPrivateConnectionsRetry() {
    return this.listPrivateConnectionsRetry;
  }

  public void setListPrivateConnectionsRetry(Retry listPrivateConnectionsRetry) {
    this.listPrivateConnectionsRetry = listPrivateConnectionsRetry;
  }

  public Retry getGetRouteRetry() {
    return this.getRouteRetry;
  }

  public void setGetRouteRetry(Retry getRouteRetry) {
    this.getRouteRetry = getRouteRetry;
  }

  public Retry getListRoutesRetry() {
    return this.listRoutesRetry;
  }

  public void setListRoutesRetry(Retry listRoutesRetry) {
    this.listRoutesRetry = listRoutesRetry;
  }

  public Retry getListLocationsRetry() {
    return this.listLocationsRetry;
  }

  public void setListLocationsRetry(Retry listLocationsRetry) {
    this.listLocationsRetry = listLocationsRetry;
  }

  public Retry getGetLocationRetry() {
    return this.getLocationRetry;
  }

  public void setGetLocationRetry(Retry getLocationRetry) {
    this.getLocationRetry = getLocationRetry;
  }
}
