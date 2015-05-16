package com.crimsonempires.RedJaVa;

public class Cuboid {
	double x1;
	double x2;
	double z1;
	double z2;
	
	public Cuboid(double x1, double z1, double x2, double z2){
		this.x1 = x1;
		this.x2 = x2;
		this.z1 = z1;
		this.z2 = z2;
	}
	
	public boolean inCuboid(double x, double y, double z){
		double px1 = this.x1;
		double px2 = this.x2;
		double pz1 = this.z1;
		double pz2 = this.z2;
		
		if(this.x1 > this.x2){
			px1 = this.x2;
			px2 = this.x1;
		}
		if(this.z1 > this.z2){
			pz1 = this.z2;
			pz2 = this.z1;
		}
		
		if(x >= px1 && x <= px2 && z >= pz1 && z <= pz2)
			return true;
		return false;
	}

}
