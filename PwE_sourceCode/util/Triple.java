package util;

/**
 * class for defining a triple, characterized by three numbers;
 * this object is used for specifying sizes of different objects in various views
 */

public class Triple {

	private double fir;
	private double sec;
	private double thi;
	
	public Triple(double fir, double sec, double thi) {
		this.fir = fir;
		this.sec = sec;
		this.thi = thi;
	}
	
	public double getF() {
		return this.fir;
	}
	public double getS() {
		return this.sec;
	}
	public double getT() {
		return this.thi;
	}
	
}
