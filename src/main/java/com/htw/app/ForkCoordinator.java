package com.htw.app;

import java.util.concurrent.TimeUnit;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import spark.Service;

public class ForkCoordinator {
	int[] portPool = {8081, 8082, 8083, 8084, 8085};
	String rightToken = "Right Fork";
	String leftToken = "Left Fork";
	String emptyToken = "Nothing";
	
	public ForkCoordinator(int port) {
		Service http = Service.ignite()
                .port(port)
                .threadPool(20);
		
		http.get("/", (q, a) -> "Hello from FORK COORDINATOR from port " + port);	
	}
	
	
	public void giveLeftFork(int port) throws UnirestException {
		HttpResponse<String> stringResponse = Unirest.get("http://localhost:" + port + "/take-left-fork")
    			.asString();
		System.out.println("Passed Left Fork to PHILOSOPHER on port " + port);
	}
	
	public void giveRightFork(int port) throws UnirestException {
		HttpResponse<String> stringResponse = Unirest.get("http://localhost:" + port + "/take-right-fork")
    			.asString();
		System.out.println("Passed Right Fork to PHILOSOPHER on port " + port);
	}
	
	public void coordinate() throws UnirestException, InterruptedException {
		int lastPhilosopherIndex = this.portPool.length - 1;
		int firstEaterPort = portPool[0];
		int secondEaterPort = portPool[2];
		
		while(true) {
			TimeUnit.SECONDS.sleep(15);
			
			firstEaterPort += 1;
			if(firstEaterPort > portPool[lastPhilosopherIndex]) {
				firstEaterPort = portPool[0];
			}
			giveRightFork(firstEaterPort);
			giveLeftFork(firstEaterPort);
			
			secondEaterPort += 1;
			if(secondEaterPort > portPool[lastPhilosopherIndex]) {
				secondEaterPort = portPool[0];
			}
			giveRightFork(secondEaterPort);
			giveLeftFork(secondEaterPort);
		}
	}
	
}
