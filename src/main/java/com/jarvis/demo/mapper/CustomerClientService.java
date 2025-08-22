package com.jarvis.demo.mapper;

import com.jarvis.grpc.service.CustomerInfoRequest;
import com.jarvis.grpc.service.CustomerInfoResponse;

public interface CustomerClientService {
    CustomerInfoResponse getCustomerInfo(CustomerInfoRequest request);
}
