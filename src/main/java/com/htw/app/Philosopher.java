package com.htw.app;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import spark.Service;

public class Philosopher {
	int port;
	String status;
	String rightHand;
	String leftHand;
	
	public Philosopher(int port, String rightHand, String leftHand) {
		this.port = port;
		this.rightHand = rightHand;
		this.leftHand = leftHand;
		
		if (rightHand.equals("Right Fork") && leftHand.equals("Left Fork")) {
			this.status = "eating";
		} else {
			this.status = "thinking";
		}
		
		Service http = Service.ignite()
                .port(port)
                .threadPool(20);
		
		http.get("/", (req, res) -> 
		{
			if (rightHand.equals("Right Fork") && leftHand.equals("Left Fork")) {
				this.status = "eating";
			} else {
				this.status = "thinking";
			}
			
			String textRespond =
				"Hello from PHILOSOPHER from port " + this.port 
				+ "\nThe philosopher is currently " + this.status + " ... "
				+ "\nIn his right hand he holds " + this.rightHand + ", "
				+ "\nin his left hand he holds " + this.leftHand;
			return textRespond;
		});
		
		http.get("/take-left-fork", (req, res) -> {
			this.leftHand = "Left Fork";
			return res;
		});
		
		http.get("/take-right-fork", (req, res) -> {
			this.rightHand = "Right Fork";
			return res;
		});
	}
}
