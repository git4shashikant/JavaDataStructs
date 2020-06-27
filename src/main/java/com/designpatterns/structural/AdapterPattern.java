package com.designpatterns.structural;

/*
* Adaptor pattern allows you to make 2 separate APIs compatible, by transforming the non compatible data
* from one API to another API in a compatible data format.
* */
public class AdapterPattern {
	public void chargeMobile(){
		MainLine mainLine = new MainLine();
		Adapter adapter = new Adapter();
		Mobile mobile = new Mobile();
		mobile.getCharge(adapter.supply(mainLine.supplyVoltage()));
	}
	
	public static void main (String [] args){
		AdapterPattern ap = new AdapterPattern();
		ap.chargeMobile();
	}
}

class MainLine{
	public int supplyVoltage(){
		return 220;
	}
}

class Mobile{
	public void getCharge(int voltage){
		if (voltage==10){
			System.out.println("getting charged");
		} else if (voltage<10){
			System.out.println("not charging");
		} else {
			System.out.println("BLAST!!!!");
		}
	}
}

class Adapter {
	public int supply(int voltage){
		if (voltage>=10){
			voltage = 10;
		}
		return voltage;
	}
}