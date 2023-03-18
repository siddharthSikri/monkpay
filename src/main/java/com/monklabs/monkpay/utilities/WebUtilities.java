package com.monklabs.monkpay.utilities;

import com.monklabs.monkpay.helper.Constants;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Web utilities class.
 */
@Service
public class WebUtilities {

    /**
     * HTTP POST call method.
     *
     * @param uri
     * @param body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public String post(String uri, String body, String accessToken) {
        try {
            if (!StringUtils.hasLength(accessToken)){
                accessToken = "";
            }
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                    .header(Constants.CONTENT_TYPE, Constants.FORM_URLENCODED)
                    .header(Constants.AUTHORIZATION, accessToken)
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            // Log exception and return appropriate response.
            return "";
        }
    }
}
