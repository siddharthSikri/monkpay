package com.monklabs.monkpay.enums;

/**
 * Payment status enum class.
 */
public enum PaymentStatus {

    /**
     * Payment for item yet to begin.
     */
    PENDING,

    /**
     * Payment for item in progress.
     */
    INITIATED,

    /**
     * Payment for item completed.
     */
    COMPLETED
}
