package com.monklabs.monkpay.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Webhook document POJO class.
 */
@Data
@Document(collection = "webhook")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Webhook {

    @Id
    private String id;

    @Field
    @SerializedName("created")
    private Long created;

    @Field
    @SerializedName("updated")
    private Long updated;

    @Field
    @JsonProperty("amount")
    private Double amount;

    @Field
    @JsonProperty("buyer")
    private String buyer;

    @Field
    @JsonProperty("buyer_name")
    @SerializedName("buyer_name")
    private String buyer_name;

    @Field
    @JsonProperty("buyer_phone")
    @SerializedName("buyer_phone")
    private String buyerPhone;

    @Field
    @SerializedName("currency")
    private String currency;

    @Field
    @SerializedName("fees")
    private Double fees;

    @Field
    @SerializedName("longurl")
    private String longUrl;

    @Field
    @SerializedName("mac")
    private String mac;

    @Field
    @SerializedName("payment_id")
    private String paymentId;

    @Field
    @SerializedName("payment_request_id")
    private String paymentRequestId;

    @Field
    @SerializedName("purpose")
    private String purpose;

    @Field
    @SerializedName("shorturl")
    private String shorturl;

    @Field("status")
    @SerializedName("status")
    private String status;

}
