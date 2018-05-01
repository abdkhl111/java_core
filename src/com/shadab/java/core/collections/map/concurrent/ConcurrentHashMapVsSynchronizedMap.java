package com.shadab.java.core.collections.map.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
/**
 * @author mshadab
 *
 */
 
public class ConcurrentHashMapVsSynchronizedMap {
 
	public final static int THREAD_POOL_SIZE = 5;
 
	public static Map<String, Integer> demoHashTableObject = null;
	public static Map<String, Integer> demoSynchronizedMapObject = null;
	public static Map<String, Integer> demoConcurrentHashMapObject = null;
 
	public static void main(String[] args) throws InterruptedException {
 
		// Test with Hashtable Object
		demoHashTableObject = new Hashtable<String, Integer>();
		demoPerformTest(demoHashTableObject);
 
		// Test with synchronizedMap Object
		demoSynchronizedMapObject = Collections.synchronizedMap(new HashMap<String, Integer>());
		demoPerformTest(demoSynchronizedMapObject);
 
		// Test with ConcurrentHashMap Object
		demoConcurrentHashMapObject = new ConcurrentHashMap<String, Integer>();
		demoPerformTest(demoConcurrentHashMapObject);
 
	}
 
	public static void demoPerformTest(final Map<String, Integer> demoThreads) throws InterruptedException {
 
		System.out.println("Test started for: " + demoThreads.getClass());
		long averageTime = 0;
		for (int i = 0; i < 9; i++) {
 
			long startTime = System.nanoTime();
			ExecutorService demoExServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
 
			for (int j = 0; j < THREAD_POOL_SIZE; j++) {
				demoExServer.execute(new Runnable() {
					@SuppressWarnings("unused")
					@Override
					public void run() {
 
						for (int i = 0; i < 500000; i++) {
							Integer demoRandomNumber = (int) Math.ceil(Math.random() * 550000);
 
							// Retrieve value. We are not using it anywhere
							Integer demoValue = demoThreads.get(String.valueOf(demoRandomNumber));
 
							// Put value
							demoThreads.put(String.valueOf(demoRandomNumber), demoRandomNumber);
						}
					}
				});
			}
 
			// Make sure executor stops
			demoExServer.shutdown();
 
			// Blocks until all tasks have completed execution after a shutdown request
			demoExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
 
			long entTime = System.nanoTime();
			long totalTime = (entTime - startTime) / 1000000L;
			averageTime += totalTime;
			System.out.println("2500K entried added/retrieved in " + totalTime + " ms");
		}
		System.out.println("For " + demoThreads.getClass() + " the average time is " + averageTime / 5 + " ms\n");
	}
}