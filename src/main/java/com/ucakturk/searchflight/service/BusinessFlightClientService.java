package com.ucakturk.searchflight.service;


import com.ucakturk.searchflight.entity.BusinessFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class BusinessFlightClientService {


    private final WebClient webClient;

    @Autowired
    public BusinessFlightClientService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Flux<BusinessFlight> getFlights() {

        return webClient.get().uri(uriBuilder -> uriBuilder.path("business").build())
                .retrieve().bodyToFlux(BusinessFlight.class);
    }
}
