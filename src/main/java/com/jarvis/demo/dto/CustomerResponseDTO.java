package com.jarvis.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class CustomerResponseDTO {
    private String name;
    private String email;
    private String mobileNumber;
    private List<AccountDTO> accounts;
}
