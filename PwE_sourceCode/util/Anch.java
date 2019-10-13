package util;

/**
 * class for defining an achor, characterized by left, right, up and bottom coordinate;
 * this object is used when populating different views with their child objects
 */

public class Anch {

	private double leftAnchor;
	private double rightAnchor;
	private double topAnchor;
	private double bottomAnchor;
	
	public Anch(double la, double ra, double ta, double ba) {
		this.leftAnchor = la;
		this.rightAnchor = ra;
		this.topAnchor = ta;
		this.bottomAnchor = ba;
	}
	
	public double left() {
		return this.leftAnchor;
	}
	
	public double right() {
		return this.rightAnchor;
	}
	
	public double top() {
		return this.topAnchor;
	}
	
	public double bottom() {
		return this.bottomAnchor;
	}	
}
