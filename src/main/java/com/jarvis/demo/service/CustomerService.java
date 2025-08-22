package com.jarvis.demo.service;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.CustomerResponseDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.demo.dto.ResponseDTO;

public interface CustomerService {
    ResponseDTO<CustomerResponseDTO> getCustomerInfo(RequestDTO<CustomerRequestDTO> request);
}
