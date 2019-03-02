package com.ucakturk.searchflight;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SearchFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchFlightApplication.class, args);
		log.info("SearchFlightApplication is started");
	}

}
