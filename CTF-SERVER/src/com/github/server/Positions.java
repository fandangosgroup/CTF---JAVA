package com.github.server;

public class Positions {
	private int id;
	private int x;
	private int y;
	private String team;
	
	public Positions(int id, int x, int y, String team){
		this.id = id;
		this.x = x;
		this.y = y;
		this.team = team;
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
	
	public int getPosID() {
		return this.id;
	}
	
	public String getTeam() {
		return this.team;
	}
	
	public void setPosX(int x) {
		this.x = x;
	}
	
	public void setPosY(int y) {
		this.y = y;
	}
}
