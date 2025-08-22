package com.jarvis.demo.serviceimpl;

import com.google.gson.Gson;
import com.jarvis.demo.mapper.CustomerClientService;
import com.jarvis.grpc.service.CustomerInfoRequest;
import com.jarvis.grpc.service.CustomerInfoResponse;
import com.jarvis.grpc.service.CustomerServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerClientServiceImpl implements CustomerClientService {
    @Value("${grpc.request.timeout}")
    private Long timeout;

    private CustomerServiceGrpc.CustomerServiceStub asyncStub;

    private final Gson gson;

    @Override
    public CustomerInfoResponse getCustomerInfo(CustomerInfoRequest request) {
        log.info("[getCustomerInfo] request : {}", gson.toJson(request));

        CompletableFuture<CustomerInfoResponse> future = new CompletableFuture<>();

        asyncStub.withDeadlineAfter(timeout, TimeUnit.MILLISECONDS)
                .getCustomerInfo(request, new StreamObserver<>() {

                    @Override
                    public void onNext(CustomerInfoResponse customerInfoResponse) {
                        log.info("[getCustomerInfo] response : {}", gson.toJson(customerInfoResponse));
                        future.complete(customerInfoResponse);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log.error("[getCustomerInfo] error", throwable);
                        future.completeExceptionally(throwable);
                    }

                    @Override
                    public void onCompleted() {
                        // Optional: log completion
                    }
                });

        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new RuntimeException("gRPC call failed", e);
        }
    }
}
