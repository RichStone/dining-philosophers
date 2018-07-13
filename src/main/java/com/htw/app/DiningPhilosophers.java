package com.htw.app;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Hello world!
 *
 */
public class DiningPhilosophers 
{
	
    public static void main( String[] args )
    {
    	initialize();
    }
    
    static void initialize() {
    	ForkCoordinator forksCoordinator = new ForkCoordinator(9000);
    	
    	Philosopher p1 = new Philosopher(8081, forksCoordinator.rightToken, forksCoordinator.leftToken);
    	Philosopher p2 = new Philosopher(8082, forksCoordinator.emptyToken, forksCoordinator.emptyToken);
    	Philosopher p3 = new Philosopher(8083, forksCoordinator.rightToken, forksCoordinator.leftToken);
    	Philosopher p4 = new Philosopher(8084, forksCoordinator.emptyToken, forksCoordinator.emptyToken);
    	Philosopher p5 = new Philosopher(8085, forksCoordinator.emptyToken, forksCoordinator.emptyToken);
    }
}