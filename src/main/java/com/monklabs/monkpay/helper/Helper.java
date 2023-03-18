package com.monklabs.monkpay.helper;

import com.monklabs.monkpay.entity.Invoice;
import com.monklabs.monkpay.enums.PaymentStatus;
import com.monklabs.monkpay.pojo.response.GenericResponse;
import com.monklabs.monkpay.repository.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Helper functions class.
 */
@Service
public class Helper {

    @Autowired
    InvoiceRepo invoiceRepo;

    /**
     * Create invoices based on input count parameter and save to the invoice collection.
     * Default count = 1.
     *
     * @param count Number of invoices to be created.
     */
    public void createInvoicesAndSaveToDb(Integer count) {
        try {
            if (Objects.nonNull(count)) {
                for (int i = 0; i < count; i++) {
                    String[] itemAndPrice = getItemAndPrice();
                    Double price = Double.valueOf(itemAndPrice[1]);
                    Long currentTime = System.currentTimeMillis();
                    Invoice invoice = Invoice.builder()
                            .item(itemAndPrice[0])
                            .amount(price)
                            .paymentStatus(PaymentStatus.PENDING)
                            .created(currentTime)
                            .updated(currentTime)
                            .build();
                    invoiceRepo.save(invoice);
                }
            } else {
                createInvoicesAndSaveToDb(1);
            }
        } catch (Exception e) {
            // Log exception or return appropriate response.
        }
    }

    /**
     * Fetch a random item, and it's price from a static map.
     *
     * @return Random item and it's price.
     */
    private String[] getItemAndPrice() {
        String[] itemPriceArr = new String[2];
        Map<String, Integer> itemPriceMap = new HashMap<>();
        itemPriceMap.put("Bottle", 99);
        itemPriceMap.put("Lunchbox", 199);
        itemPriceMap.put("Shirt", 299);
        itemPriceMap.put("Bag", 399);
        itemPriceMap.put("Jacket", 499);
        itemPriceMap.put("Shorts", 249);
        itemPriceMap.put("Jeans", 599);
        itemPriceMap.put("Cap", 449);
        List<String> keyList = new ArrayList<>(itemPriceMap.keySet());
        String randomItem = keyList.get(new Random().nextInt(keyList.size()));
        Integer itemPrice = itemPriceMap.get(randomItem);
        itemPriceArr[0] = randomItem;
        itemPriceArr[1] = String.valueOf(itemPrice);
        return itemPriceArr;
    }

    /**
     * Create, populate and return generic response.
     *
     * @param success true/false.
     * @param message Message to be returned.
     * @param count   Count.
     * @return Generic response object.
     */
    public GenericResponse createAndPopulateGenericResponse(Boolean success, String message, Integer count) {
        GenericResponse genericResponse = GenericResponse.builder()
                .success(success)
                .message(message)
                .count(count)
                .build();
        return genericResponse;
    }
}
