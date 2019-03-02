package com.ucakturk.searchflight.service;


import com.ucakturk.searchflight.entity.EconomyFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class EconomyFlightClientService {


    private final WebClient webClient;

    @Autowired
    public EconomyFlightClientService(WebClient webClient) {
        this.webClient = webClient;
    }


    public Flux<EconomyFlight> getFlights() {

        return webClient.get().uri(uriBuilder -> uriBuilder.path("cheap").build())
                .retrieve().bodyToFlux(EconomyFlight.class);
    }
}
