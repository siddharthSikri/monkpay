package com.monklabs.monkpay.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.pojo.instamojo.response.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Invoice document POJO class.
 */
@Data
@Document(collection = "invoice")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Invoice {

    @Id
    private String id;

    @Field
    @SerializedName("item")
    private String item;

    @Field
    @SerializedName("amount")
    private Double amount;

    @Field
    @JsonProperty("payment_status")
    @SerializedName("payment_status")
    private PaymentStatus paymentStatus;

    @Field
    @SerializedName("created")
    private Long created;

    @Field
    @SerializedName("updated")
    private Long updated;

    @Field
    @JsonProperty("payment_details")
    @SerializedName("payment_details")
    private Payment paymentDetails;

}
