/**
 * Copyright © 2016-2024 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.common.data.tenant.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import org.thingsboard.server.common.data.ApiUsageRecordKey;
import org.thingsboard.server.common.data.TenantProfileType;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DefaultTenantProfileConfiguration.class, name = "DEFAULT")})
@Schema(discriminatorProperty = "type", discriminatorMapping = {@DiscriminatorMapping(value = "DEFAULT", schema = DefaultTenantProfileConfiguration.class)})
public interface TenantProfileConfiguration extends Serializable {

    @JsonIgnore
    TenantProfileType getType();

    @JsonIgnore
    long getProfileThreshold(ApiUsageRecordKey key);

    @JsonIgnore
    boolean getProfileFeatureEnabled(ApiUsageRecordKey key);

    @JsonIgnore
    long getWarnThreshold(ApiUsageRecordKey key);

    @JsonIgnore
    int getMaxRuleNodeExecsPerMessage();

    @JsonIgnore
    int getMaxDebugModeDurationMinutes(int systemMaxDebugModeDurationMinutes);

}
