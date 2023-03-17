package com.monklabs.monkpay.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.monklabs.monkpay.entity.Invoice;
import lombok.Data;

import java.util.List;

/**
 * List of invoices response POJO class.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Invoices extends GenericResponse {

    @SerializedName("invoices")
    private List<Invoice> invoices;

}
