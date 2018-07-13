package com.htw.app;

import java.util.concurrent.TimeUnit;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Hello world!
 *
 */
public class DiningPhilosophers 
{
	
    public static void main( String[] args ) throws UnirestException, InterruptedException
    {
    	initialize();
    }
    
    static void initialize() throws UnirestException, InterruptedException {
    	ForkCoordinator forksCoordinator = new ForkCoordinator(9000);
    	
    	Philosopher p1 = new Philosopher(8081, forksCoordinator.rightToken, forksCoordinator.leftToken);
    	Philosopher p2 = new Philosopher(8082, forksCoordinator.emptyToken, forksCoordinator.emptyToken);
    	Philosopher p3 = new Philosopher(8083, forksCoordinator.rightToken, forksCoordinator.leftToken);
    	Philosopher p4 = new Philosopher(8084, forksCoordinator.emptyToken, forksCoordinator.emptyToken);
    	Philosopher p5 = new Philosopher(8085, forksCoordinator.emptyToken, forksCoordinator.emptyToken);
    	
    	TimeUnit.SECONDS.sleep(1);
    	
    	System.out.println("Philosopher 8081 is " + p1.status);
    	System.out.println("Philosopher 8082 is " + p2.status);
    	System.out.println("Philosopher 8083 is " + p3.status);
    	System.out.println("Philosopher 8084 is " + p4.status);
    	System.out.println("Philosopher 8085 is " + p5.status);
    	
    	System.out.println("\nNext round in 10 seconds...");
    	TimeUnit.SECONDS.sleep(10);
    	System.out.println("\nGO!\n");
    	
    	forksCoordinator.coordinate();
    }
}