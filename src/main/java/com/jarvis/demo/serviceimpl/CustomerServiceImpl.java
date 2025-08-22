package com.jarvis.demo.serviceimpl;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.CustomerResponseDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.demo.dto.ResponseDTO;
import com.jarvis.demo.mapper.CustomerMapper;
import com.jarvis.demo.service.CustomerService;
import com.jarvis.grpc.service.CustomerInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;

    @Override
    public ResponseDTO<CustomerResponseDTO> getCustomerInfo(RequestDTO<CustomerRequestDTO> request) {
        CustomerInfoRequest customerInfoRequest = customerMapper.toCustomerInfoRequest(request);
        

        return null;
    }
}
