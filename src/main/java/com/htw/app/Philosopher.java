package com.htw.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import spark.Service;

public class Philosopher {
	int port;
	String status;
	String rightFork;
	String leftFork;
	
	public Philosopher(int port, String rightFork) {
		this.port = port;
		this.status = "thinking";
		this.rightFork = rightFork;
		this.leftFork = "Nothing";
		
		Service http = Service.ignite()
                .port(port)
                .threadPool(20);
		
		http.get("/", (req, res) -> 
		{
			String textRespond =
				"Hello from PHILOSOPHER from port " + this.port 
				+ "\nThe philosopher is currently " + this.status + " ... "
				+ "\nIn his right hand he holds " + this.rightFork + ", "
				+ "\nin his left hand he holds " + this.leftFork;
			return textRespond;
		});
	}
}
