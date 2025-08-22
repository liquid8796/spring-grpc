package com.jarvis.demo.mapper;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.grpc.service.CustomerInfoRequest;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        , collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface CustomerMapper {
    CustomerInfoRequest toCustomerInfoRequest(RequestDTO<CustomerRequestDTO> request);
}
