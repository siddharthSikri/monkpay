package com.monklabs.monkpay.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monklabs.monkpay.configs.ConfigProperties;
import com.monklabs.monkpay.helper.Constants;
import com.monklabs.monkpay.pojo.instamojo.response.Authorization;
import com.monklabs.monkpay.service.AuthorizationService;
import com.monklabs.monkpay.utilities.WebUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Authorization service implementation class.
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    WebUtilities webUtilities;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Generate Instamojo access token.
     *
     * @return
     */
    @Override
    public String generateAuthorizationToken() {
        try {
            String reqBodyStr = createRequestBodyString();
            String uri = ConfigProperties.IM_BASE_URL + ConfigProperties.IM_AUTH_TOKEN;
            String responseStr = webUtilities.post(uri, reqBodyStr, null);
            Authorization authorizationResponse = objectMapper.readValue(responseStr, Authorization.class);
            if (Objects.nonNull(authorizationResponse) && StringUtils.hasLength(authorizationResponse.getAccessToken())) {
                return authorizationResponse.getAccessToken();
            }
            return "";
        } catch (Exception e) {
            // Log exception or return appropriate response.
            return "";
        }
    }

    /**
     * Create request body string.
     *
     * @return Request body string.
     */
    private String createRequestBodyString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("grant_type=client_credentials");
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("client_id=");
        strBuilder.append(ConfigProperties.CLIENT_ID);
        strBuilder.append(Constants.AMPERSAND);
        strBuilder.append("client_secret=");
        strBuilder.append(ConfigProperties.CLIENT_SECRET);
        String body = strBuilder.toString();
        return body;
    }
}
