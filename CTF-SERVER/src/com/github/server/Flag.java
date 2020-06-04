package com.github.server;

public class Flag {
	private String idplayer;
	private int x;
	private int y;
	
	public Flag(String id, int x, int y) {
		this.idplayer = id;
		this.x = x;
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setPlayer(String id) {
		this.idplayer = id;
	}
	
	public String getID() {
		return this.idplayer;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
