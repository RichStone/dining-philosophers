package com.htw.app;

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
	
	
	public void giveFork() throws UnirestException {
		int port = 8083;
		HttpResponse<String> stringResponse = Unirest.get("http://localhost:" + port + "/get-forks/")
    			.asString();
	}
}
