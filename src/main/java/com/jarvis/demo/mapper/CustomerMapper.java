package com.jarvis.demo.mapper;

import com.jarvis.demo.dto.*;
import com.jarvis.grpc.service.Account;
import com.jarvis.grpc.service.CustomerInfoRequest;
import com.jarvis.grpc.service.CustomerInfoResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring"
        , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        , nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
        , collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface CustomerMapper {
    CustomerInfoRequest toCustomerInfoRequest(RequestDTO<CustomerRequestDTO> request);

    @Mapping(target = "data.accounts", source = "data.accountsList", qualifiedByName = "fromAccountsListToListAccountsDto")
    ResponseDTO<CustomerResponseDTO> toCustomerResponseDto(CustomerInfoResponse customerInfoResponse);

    @Named("fromAccountsListToListAccountsDto")
    List<AccountDTO> fromAccountsListToListAccountsDto(List<Account> accounts);
}
