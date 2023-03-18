package com.monklabs.monkpay.service;

import java.io.IOException;

/**
 * Authorization service interface.
 */
public interface AuthorizationService {

    /**
     * Generate Instamojo access token.
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    String generateAuthorizationToken() throws IOException, InterruptedException;
}
