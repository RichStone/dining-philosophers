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
	
	private void takeLeftFork(int port) throws UnirestException {
		HttpResponse<String> stringResponse = Unirest.get("http://localhost:" + port + "/give-left-fork")
    			.asString();
		System.out.println("TOOK Left Fork from PHILOSOPHER on port " + port);
	}

	private void takeRightFork(int port) throws UnirestException {
		HttpResponse<String> stringResponse = Unirest.get("http://localhost:" + port + "/give-right-fork")
    			.asString();
		System.out.println("TOOK Left Fork from PHILOSOPHER on port " + port);
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
		
		System.out.println("\nStart coordinating\n");
		
		int lastPhilosopherIndex = this.portPool.length - 1;
		int firstCurrentEaterPort = portPool[0];
		int secondCurrentEaterPort = portPool[2];
		
		while(true) {
			
			takeRightFork(firstCurrentEaterPort);
			takeLeftFork(firstCurrentEaterPort);
			firstCurrentEaterPort += 1;
			if(firstCurrentEaterPort > portPool[lastPhilosopherIndex]) {
				firstCurrentEaterPort = portPool[0];
			}
			giveRightFork(firstCurrentEaterPort);
			giveLeftFork(firstCurrentEaterPort);
			
			takeRightFork(secondCurrentEaterPort);
			takeLeftFork(secondCurrentEaterPort);
			secondCurrentEaterPort += 1;
			if(secondCurrentEaterPort > portPool[lastPhilosopherIndex]) {
				secondCurrentEaterPort = portPool[0];
			}
			giveRightFork(secondCurrentEaterPort);
			giveLeftFork(secondCurrentEaterPort);
			
			System.out.println("\nRound over\n");
			System.out.println("Next round in 30 sec ...\n");
			TimeUnit.SECONDS.sleep(30);
		}
	}
}
