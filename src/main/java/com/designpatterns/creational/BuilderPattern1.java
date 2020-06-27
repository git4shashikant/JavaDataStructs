package com.designpatterns.creational;

import java.io.Serializable;

public class BuilderPattern1 {
	
	public static void main (String ...strings ){
		House br = House.builder()
				.prepareFoundation()
				.preparePillars()
				.prepareCeiling()
				.prepareWall()
				.prepareDoors()
				.prepareFinal()
				.build();
		System.out.println(br);
	}
}

/**
 * This class uses a nested builder state machine to provide a clear building path with
 * compile-time safety and coding guidance.
 */
final class House implements Serializable {
    private static final long serialVersionUID = 1L;
   
    

    public static FoundationStep builder() {
        return new Builder();
    }

    interface FoundationStep {
        PillarStep prepareFoundation();
    }

    interface PillarStep {
        CeilingStep preparePillars();
    }

    interface CeilingStep {
        WallStep prepareCeiling();
    }

    interface WallStep {
    	DoorsStep prepareWall();
    }

    interface DoorsStep {
        FinalStep prepareDoors();
    }
    
    interface FinalStep {
    	BuildStep prepareFinal();
    }
    
    interface BuildStep {
        House build();
    }

    private static class Builder implements FoundationStep,
			PillarStep,
			CeilingStep,
			WallStep,
			DoorsStep,
			FinalStep,
			BuildStep {
        private final House request = new House();

        @Override
		public PillarStep prepareFoundation() {
        	System.out.println("logic to prepare foundation"); 
        	return this;
		}
        
        @Override
		public CeilingStep preparePillars() {
        	System.out.println("logic to prepare pillers"); 
			return this;
		}
        
        @Override
		public WallStep prepareCeiling() {
        	System.out.println("logic to prepare Ceiling");  
			return this;
		}
        
        @Override
		public DoorsStep prepareWall() {
        	System.out.println("logic to prepare Wall");  
			return this;
		}
        
        @Override
		public FinalStep prepareDoors() {
        	System.out.println("logic to prepare doors"); 
			return this;
		}
        
        @Override
		public BuildStep prepareFinal() {
        	System.out.println("logic to prepare final finishing"); 
			return this;
		}
        
		@Override
		public House build() {
			return request;
		}

    }

}