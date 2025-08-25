package com.jarvis.demo.serviceimpl;

import com.jarvis.demo.dto.CustomerRequestDTO;
import com.jarvis.demo.dto.RequestDTO;
import com.jarvis.demo.mapper.CustomerEndpointMapper;
import com.jarvis.demo.service.CustomerService;
import com.jarvis.grpc.endpoint.CustomerEndpointGrpc;
import com.jarvis.grpc.endpoint.CustomerRequest;
import com.jarvis.grpc.endpoint.CustomerResponse;
import com.jarvis.grpc.service.CustomerInfoResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CustomerEndpointServiceImpl extends CustomerEndpointGrpc.CustomerEndpointImplBase {
    private final CustomerService customerService;
    private final CustomerEndpointMapper customerEndpointMapper;

    @Override
    public void getCustomerInfo(CustomerRequest request, StreamObserver<CustomerResponse> responseObserver) {
        try {
            // Map gRPC request -> DTO nội bộ
            RequestDTO<CustomerRequestDTO> dtoRequest = customerEndpointMapper.toCustomerRequestDTO(request);

            // Gọi service nội bộ
            CustomerInfoResponse customerInfoResponse = customerService.getCustomerInfoEndpoint(dtoRequest);

            // Map DTO nội bộ -> gRPC response cho endpoint
            CustomerResponse endpointResponse = customerEndpointMapper.toCustomerEndpointResponse(customerInfoResponse);

            // Trả về cho client
            responseObserver.onNext(endpointResponse);
            responseObserver.onCompleted();
        } catch (IllegalArgumentException e) {
            // Lỗi do dữ liệu đầu vào không hợp lệ
            responseObserver.onError(io.grpc.Status.INVALID_ARGUMENT
                    .withDescription(e.getMessage() != null ? e.getMessage() : "Invalid argument")
                    .augmentDescription("Method: getCustomerInfo")
                    .asRuntimeException());
        } catch (org.springframework.web.server.ResponseStatusException e) {
            // Nếu service ném theo HTTP semantics
            io.grpc.Status status = switch (e.getStatusCode().value()) {
                case 400 -> io.grpc.Status.INVALID_ARGUMENT;
                case 401 -> io.grpc.Status.UNAUTHENTICATED;
                case 403 -> io.grpc.Status.PERMISSION_DENIED;
                case 404 -> io.grpc.Status.NOT_FOUND;
                case 429 -> io.grpc.Status.RESOURCE_EXHAUSTED;
                case 504 -> io.grpc.Status.DEADLINE_EXCEEDED;
                default -> io.grpc.Status.INTERNAL;
            };
            responseObserver.onError(status
                    .withDescription(e.getReason() != null ? e.getReason() : "Service error")
                    .augmentDescription("Method: getCustomerInfo")
                    .asRuntimeException());
        } catch (Exception e) {
            // Lỗi không xác định
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("Unexpected error")
                    .augmentDescription("Method: getCustomerInfo")
                    .withCause(e)
                    .asRuntimeException());
        }
    }
}
