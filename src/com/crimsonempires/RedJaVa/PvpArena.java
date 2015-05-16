package com.crimsonempires.RedJaVa;

public class PvpArena {
	Cuboid protection;
	
	public PvpArena(Cuboid protection){
		this.protection = protection;
	}
	
	public Cuboid getProtection(){
		return this.protection;
	}
	
	public void setProtection(Cuboid protection){
		this.protection = protection;
	}
}
