package com.designpatterns.creational;

/**
 * Example for singleton pattern loading instance eagerly at class load time. 
 * 
 * @author shashi1846
 *
 */
public class EagerSingleton {

	// loading instance at class load time called eager loading
	private static final EagerSingleton eagerInstance = new EagerSingleton();
	
	// private constructor to avoid external instantiation
	private EagerSingleton(){};
	
	// static method to access instance in thread safe mode
	public static EagerSingleton getInstance(){
		return eagerInstance;
	}
}
