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

package com.google.cloud.discoveryengine.v1.spring;

import com.google.api.core.BetaApi;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.CredentialsSupplier;
import com.google.cloud.spring.core.Retry;
import javax.annotation.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/** Provides default property values for ConversationalSearchService client bean */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@ConfigurationProperties("com.google.cloud.discoveryengine.v1.conversational-search-service")
public class ConversationalSearchServiceSpringProperties implements CredentialsSupplier {
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
   * Allow override of retry settings at method-level for converseConversation. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry converseConversationRetry;
  /**
   * Allow override of retry settings at method-level for createConversation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry createConversationRetry;
  /**
   * Allow override of retry settings at method-level for deleteConversation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry deleteConversationRetry;
  /**
   * Allow override of retry settings at method-level for updateConversation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry updateConversationRetry;
  /**
   * Allow override of retry settings at method-level for getConversation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getConversationRetry;
  /**
   * Allow override of retry settings at method-level for listConversations. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listConversationsRetry;
  /**
   * Allow override of retry settings at method-level for answerQuery. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry answerQueryRetry;
  /**
   * Allow override of retry settings at method-level for getAnswer. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getAnswerRetry;
  /**
   * Allow override of retry settings at method-level for createSession. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry createSessionRetry;
  /**
   * Allow override of retry settings at method-level for deleteSession. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry deleteSessionRetry;
  /**
   * Allow override of retry settings at method-level for updateSession. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry updateSessionRetry;
  /**
   * Allow override of retry settings at method-level for getSession. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getSessionRetry;
  /**
   * Allow override of retry settings at method-level for listSessions. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listSessionsRetry;

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

  public Retry getConverseConversationRetry() {
    return this.converseConversationRetry;
  }

  public void setConverseConversationRetry(Retry converseConversationRetry) {
    this.converseConversationRetry = converseConversationRetry;
  }

  public Retry getCreateConversationRetry() {
    return this.createConversationRetry;
  }

  public void setCreateConversationRetry(Retry createConversationRetry) {
    this.createConversationRetry = createConversationRetry;
  }

  public Retry getDeleteConversationRetry() {
    return this.deleteConversationRetry;
  }

  public void setDeleteConversationRetry(Retry deleteConversationRetry) {
    this.deleteConversationRetry = deleteConversationRetry;
  }

  public Retry getUpdateConversationRetry() {
    return this.updateConversationRetry;
  }

  public void setUpdateConversationRetry(Retry updateConversationRetry) {
    this.updateConversationRetry = updateConversationRetry;
  }

  public Retry getGetConversationRetry() {
    return this.getConversationRetry;
  }

  public void setGetConversationRetry(Retry getConversationRetry) {
    this.getConversationRetry = getConversationRetry;
  }

  public Retry getListConversationsRetry() {
    return this.listConversationsRetry;
  }

  public void setListConversationsRetry(Retry listConversationsRetry) {
    this.listConversationsRetry = listConversationsRetry;
  }

  public Retry getAnswerQueryRetry() {
    return this.answerQueryRetry;
  }

  public void setAnswerQueryRetry(Retry answerQueryRetry) {
    this.answerQueryRetry = answerQueryRetry;
  }

  public Retry getGetAnswerRetry() {
    return this.getAnswerRetry;
  }

  public void setGetAnswerRetry(Retry getAnswerRetry) {
    this.getAnswerRetry = getAnswerRetry;
  }

  public Retry getCreateSessionRetry() {
    return this.createSessionRetry;
  }

  public void setCreateSessionRetry(Retry createSessionRetry) {
    this.createSessionRetry = createSessionRetry;
  }

  public Retry getDeleteSessionRetry() {
    return this.deleteSessionRetry;
  }

  public void setDeleteSessionRetry(Retry deleteSessionRetry) {
    this.deleteSessionRetry = deleteSessionRetry;
  }

  public Retry getUpdateSessionRetry() {
    return this.updateSessionRetry;
  }

  public void setUpdateSessionRetry(Retry updateSessionRetry) {
    this.updateSessionRetry = updateSessionRetry;
  }

  public Retry getGetSessionRetry() {
    return this.getSessionRetry;
  }

  public void setGetSessionRetry(Retry getSessionRetry) {
    this.getSessionRetry = getSessionRetry;
  }

  public Retry getListSessionsRetry() {
    return this.listSessionsRetry;
  }

  public void setListSessionsRetry(Retry listSessionsRetry) {
    this.listSessionsRetry = listSessionsRetry;
  }
}
