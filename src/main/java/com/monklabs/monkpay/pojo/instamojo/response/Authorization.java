package com.monklabs.monkpay.pojo.instamojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Authorization response from Instamojo POJO.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Authorization {

    @JsonProperty("access_token")
    @SerializedName("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    @SerializedName("expires_in")
    private Integer expiresIn;

    @JsonProperty("scope")
    @SerializedName("scope")
    private String scope;

    @JsonProperty("token_type")
    @SerializedName("token_type")
    private String tokenType;

}
