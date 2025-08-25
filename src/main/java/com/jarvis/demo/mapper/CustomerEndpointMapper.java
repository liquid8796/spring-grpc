package com.jarvis.demo.mapper;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.grpc.endpoint.Account;
import com.jarvis.grpc.endpoint.CustomerRequest;
import com.jarvis.grpc.endpoint.CustomerResponse;
import com.jarvis.grpc.service.CustomerInfoResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        , collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE
        , unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CustomerEndpointMapper {
    RequestDTO<CustomerRequestDTO> toCustomerRequestDTO(CustomerRequest customerRequest);

    @Mapping(target = "allFields", ignore = true)
    @Mapping(target = "data.allFields", ignore = true)
    @Mapping(target = "data.accountsOrBuilderList", ignore = true)
    @Mapping(target = "data.accountsList", source = "data.accountsList", qualifiedByName = "toAccountsResponse")
    CustomerResponse toCustomerEndpointResponse(CustomerInfoResponse customerInfoResponse);

    @Named("toAccountsResponse")
    @Mapping(target = "allFields", ignore = true)
    Account toAccountsResponse(com.jarvis.grpc.service.Account account);
}
