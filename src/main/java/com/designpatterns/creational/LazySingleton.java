package com.designpatterns.creational;

/**
 * 
 * Example for singleton pattern loading instance lazily with double check for thread safety.
 * 
 * @author shashi1846
 *
 */
public class LazySingleton {

	// Instance reference for lazy load, keeping volatile to make sure the value from main memory
	private static volatile LazySingleton lazyInstance;
	
	// private constructor to avoid external instantiation
	private LazySingleton(){
		// to avoid instance from reflection
		if (lazyInstance != null) {
	        throw new RuntimeException("Can't instantiate singleton twice");
	    }
	}
	
	public static LazySingleton getInstance(){
		if (lazyInstance==null){
			synchronized(LazySingleton.class){
				// double checking singleton instance
				if (lazyInstance==null){
					lazyInstance = new LazySingleton();
				}
			}
		}
		return lazyInstance;
	}
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
        // to avoid instance from cloning
		throw new CloneNotSupportedException("Cloning of this class is not allowed"); 
    }
    protected Object readResolve() {
    	// to avoid instance from serialization/de-serialization
        return lazyInstance;
    }
    
}