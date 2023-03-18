package com.monklabs.monkpay.repository;

import com.monklabs.monkpay.entity.Webhook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Webhooks collection repository class.
 */
@Repository
public interface WebhookRepo extends CrudRepository<Webhook, String> {


}
