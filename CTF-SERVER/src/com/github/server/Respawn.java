package com.github.server;

public class Respawn {
	private String lado;
	private int x;
	private int y;
	
	public Respawn(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public String getLado() {
		return this.lado;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLado(String lado) {
		this.lado = lado;
	}
	
}
