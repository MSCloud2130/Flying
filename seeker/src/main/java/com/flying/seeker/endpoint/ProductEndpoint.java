package com.flying.seeker.endpoint;

import com.flying.seeker.gen.*;
import com.flying.seeker.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductEndpoint {
    private ProductService productService;

    @Autowired
    public ProductEndpoint(ProductService productService) {
        this.productService = productService;
    }

    @PayloadRoot(namespace = "http://gen.seeker.flying.com", localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getProductsRequest(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        response.setProducts(productService.getProducts(request.getSearchString()));
        return response;
    }
}
