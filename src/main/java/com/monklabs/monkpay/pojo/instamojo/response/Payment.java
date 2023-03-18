package com.monklabs.monkpay.pojo.instamojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Payments response from Instamojo POJO.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Payment {

    @SerializedName("id")
    private String id;

    @SerializedName("user")
    private String user;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    @JsonProperty("buyer_name")
    @SerializedName("buyer_name")
    private String buyerName;

    @SerializedName("amount")
    private String amount;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("payments")
    private List<Object> payments;

    @JsonProperty("send_sms")
    @SerializedName("send_sms")
    private Boolean sendSms;

    @JsonProperty("send_email")
    @SerializedName("send_email")
    private Boolean sendEmail;

    @JsonProperty("sms_status")
    @SerializedName("sms_status")
    private String smsStatus;

    @JsonProperty("email_status")
    @SerializedName("email_status")
    private String emailStatus;

    @SerializedName("shorturl")
    private String shortUrl;

    @SerializedName("longurl")
    private String longUrl;

    @JsonProperty("redirect_url")
    @SerializedName("redirect_url")
    private String redirectRrl;

    @SerializedName("webhook")
    private String webhook;

    @JsonProperty("scheduled_at")
    @SerializedName("scheduled_at")
    private String scheduledAt;

    @JsonProperty("expires_at")
    @SerializedName("expires_at")
    private String expires_at;

    @JsonProperty("allow_repeated_payments")
    @SerializedName("allow_repeated_payments")
    private Boolean allowRepeatedPayments;

    @JsonProperty("mark_fulfilled")
    @SerializedName("mark_fulfilled")
    private Boolean markFulfilled;

    @JsonProperty("created_at")
    @SerializedName("created_at")
    private String createdAt;

    @JsonProperty("modified_at")
    @SerializedName("modified_at")
    private String modifiedAt;

    @JsonProperty("resource_uri")
    @SerializedName("resource_uri")
    private String resourceUri;

}
