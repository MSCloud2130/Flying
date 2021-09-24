package com.flying.seekerClient;

import com.flying.seekerClient.gen.GetProductsRequest;
import com.flying.seekerClient.gen.GetProductsResponse;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class ProductClient extends WebServiceGatewaySupport {

    public GetProductsResponse getProducts(String name) {
        GetProductsRequest request = new GetProductsRequest();
        request.setName(name);
        GetProductsResponse response = (GetProductsResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }
}
