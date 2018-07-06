package com.htw.app;

import spark.Service;

public class Fork {
	boolean inUse = false;
	
	public Fork(int port) {
		Service http = Service.ignite()
                .port(port)
                .threadPool(20);
		
		http.get("/", (q, a) -> "Hello from FORK server V4 from port " + port);	
	}
}
