package com.jarvis.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseDTO<T> {
    private String requestId;
    private String requestDateTime;
    private T data;
}
