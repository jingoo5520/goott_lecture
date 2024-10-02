package com.miniproject.util;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
public class SampleSchedular {
	
	@Scheduled(cron = "0/5 * * * * *")
	public void sampleSchedular() {
		
		
		log.info("======================= Scheduling =======================");
		
	}
}
