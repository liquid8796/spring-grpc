package com.jarvis.demo.controller;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.CustomerResponseDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.demo.dto.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @PostMapping("/")
    public ResponseDTO<CustomerResponseDTO> getCustomerInfo(@RequestBody RequestDTO<CustomerRequestDTO> request) {
        return null;
    }
}
