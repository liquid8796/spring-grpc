package com.jarvis.demo.serviceimpl;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.CustomerResponseDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.demo.dto.ResponseDTO;
import com.jarvis.demo.service.CustomerClientService;
import com.jarvis.demo.mapper.CustomerMapper;
import com.jarvis.demo.service.CustomerService;
import com.jarvis.grpc.service.Account;
import com.jarvis.grpc.service.CustomerInfoRequest;
import com.jarvis.grpc.service.CustomerInfoResponse;
import com.jarvis.grpc.service.CustomerInfoResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerClientService customerClientService;
    private final CustomerMapper customerMapper;

    @Override
    public ResponseDTO<CustomerResponseDTO> getCustomerInfo(RequestDTO<CustomerRequestDTO> request) {
        CustomerInfoRequest customerInfoRequest = customerMapper.toCustomerInfoRequest(request);
        CustomerInfoResponse customerInfoResponse = customerClientService.getCustomerInfo(customerInfoRequest);

        return customerMapper.toCustomerResponseDto(customerInfoResponse);
    }

    @Override
    public CustomerInfoResponse getCustomerInfoEndpoint(RequestDTO<CustomerRequestDTO> request) {
        CustomerInfoRequest customerInfoRequest = customerMapper.toCustomerInfoRequest(request);

        return CustomerInfoResponse.newBuilder()
                .setRequestId(request.getRequestId())
                .setRequestDateTime(request.getRequestDateTime())
                .setData(
                        CustomerInfoResponseData.newBuilder()
                                .setName("Tony Stark")
                                .setEmail("tony.stark@starkindustries.com")
                                .setMobileNumber("0912345678")
                                .addAccounts(Account.newBuilder()
                                        .setAccountNumber("123456789")
                                        .setAccountType("SAVINGS")
                                        .setBranchAddress("123 Nguyen Trai, Q1, HCMC")
                                        .build())
                                .addAccounts(Account.newBuilder()
                                        .setAccountNumber("987654321")
                                        .setAccountType("CHECKING")
                                        .setBranchAddress("456 Le Loi, Q1, HCMC")
                                        .build())
                                .build()
                )
                .build();
    }
}
