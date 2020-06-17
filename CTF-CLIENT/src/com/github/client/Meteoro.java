package com.github.client;

import javafx.scene.image.ImageView;

public class Meteoro {
	
	private ClassLoader cl = this.getClass().getClassLoader();
	private ImageView meteoro = new ImageView(this.cl.getResource("resources/images/meteoro.png").toString());
	private int flag;
	private int distance;
	public Meteoro(int x, int y, int distance) {
		this.flag = 0;
		this.distance = distance;
		this.meteoro.setFitHeight(15);
		this.meteoro.setFitWidth(15);
		this.meteoro.setLayoutX(x); 
		this.meteoro.setLayoutY(y);
	}
	
	public void moveMeteor() {	
		this.flag++;
		if(this.flag <= this.distance) {
			this.meteoro.setLayoutX(this.meteoro.getLayoutX() + 0.3d);
		}
		if(this.flag > this.distance && this.flag < this.distance * 2) {
			this.meteoro.setLayoutX(this.meteoro.getLayoutX() - 0.3d);
		}
		if(this.flag >= this.distance * 2) {
			this.flag = 0;
		}
		
	}
	
	public ImageView getImg() {
		return this.meteoro;
	}
	
}
