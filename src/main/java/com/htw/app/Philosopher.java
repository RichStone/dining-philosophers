package com.htw.app;

import spark.Service;

public class Philosopher {
	
	public Philosopher(int port) {
		Service http = Service.ignite()
                .port(port)
                .threadPool(20);
		
		http.get("/", (q, a) -> "Hello from PHILOSOPHER server V4 from port " + port);	
	}	
}
