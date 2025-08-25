package com.jarvis.demo.service;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.CustomerResponseDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.demo.dto.ResponseDTO;
import com.jarvis.grpc.service.CustomerInfoResponse;

public interface CustomerService {
    ResponseDTO<CustomerResponseDTO> getCustomerInfo(RequestDTO<CustomerRequestDTO> request);
    CustomerInfoResponse getCustomerInfoEndpoint(RequestDTO<CustomerRequestDTO> request);
}
