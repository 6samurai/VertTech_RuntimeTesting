package com.uom.cps3225;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	
	private static int maxBadLoginsCount = 3; // 3 ok, 4 blocked
	private static int maxBadLoginsPeriod = 2; //seconds

	public Queue<Long> getBadLoginTimes() {
		return badLoginTimes;
	}

	private Queue<Long> badLoginTimes = new LinkedBlockingQueue<Long>();
	
	private boolean loggedIn = false;
	private boolean blocked = false;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public boolean isBlocked() {
		return blocked;
	}

	private Clock c = new Clock();
	
	
	public void setClock(Clock c)
	{
		this.c = c;
	}
	
	public void goodLogin()
	{
		if (!blocked) {
			badLoginTimes = new LinkedBlockingQueue<Long>();
			loggedIn = true;
			System.out.println("Login successful!");
		} else
			System.out.println("Could not login as currently blocked!");
	}
	
	public void logOut()
	{
		loggedIn = false;
		System.out.println("Logged out");
	}
	
	public void badLogin()
	{
		System.out.println("Badlogin!");
		
		long now = c.getTime();
		if (badLoginTimes.size() >= maxBadLoginsCount
			&& now - badLoginTimes.poll() < maxBadLoginsPeriod*1000){
				blocked = true;
				System.out.println("Excessive badlogins... Blocked!");
			}
		
		badLoginTimes.offer(now);//add element to the queue
				
	}
	
	public void access()
	{
		if (loggedIn)
			System.out.println("Access Granted!");
		else 
			System.out.println("Access Refused! Please login first");
	}
	
	public static void main(String[] args)
	{
		Main m = new Main();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
		m.badLogin();
	}
	
}
