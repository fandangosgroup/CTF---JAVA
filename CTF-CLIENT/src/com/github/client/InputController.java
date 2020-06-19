package com.github.client;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

public class InputController extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public char dir;
	
	public InputController() {
		this.setVisible(true);
                //this.setSize(100, 100);
                this.setBackground(Color.black);
                
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'w' || e.getKeyChar() == 'a' || e.getKeyChar() == 's' || e.getKeyChar() == 'd'){
					setDir(e.getKeyChar());
				}
			}
		});
	} 
	public void dirToZero(){
            setDir(' ');
        }
	public char getDir() { 
		return dir;
	}
	
	public void setDir(char dir) {
		this.dir = dir;
	}
}