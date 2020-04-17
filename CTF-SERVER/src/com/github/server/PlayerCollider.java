package com.github.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerCollider {
	private String kill = null;
	private Flag flag = new Flag(15,10);
	
	public String processa(String dados) {
		//System.out.println(dados);
		String data = dados.substring(3, dados.length());
    	String[] colunas = null;
    	String[] player = null;
    	List<String> id = new ArrayList<String>();
    	List<String> x = new ArrayList<String>();
    	List<String> y = new ArrayList<String>();
    	List<String> team = new ArrayList<String>();
    	int i,z;
    	String matriz = "M4X";
    	
    	if(dados != null) {
    		colunas = data.split(",");
    		
    		for(i=0; i < colunas.length; i++) {
        		player = colunas[i].split("-");
        		id.add(player[0]);
        		x.add(player[1]);
        		y.add(player[2]);
        		team.add(player[3]);
        	}
    		
        	//System.out.println("Chegou1");
        	for(i=0; i < id.size(); i++) {
        		for(z=0; z < id.size(); z++) {
        			//System.out.println("Chegou2");
        			//System.out.println(id.get(i) +"--"+ id.get(z));
        			//System.out.println(x.get(i) +"--"+ x.get(z));
        			//System.out.println(y.get(i) +"--"+ y.get(z));
        			//System.out.println(team.get(i) +"--"+ team.get(z));
        			
        			if((!id.get(i).equals(id.get(z))) && (x.get(i).equals(x.get(z))) && (y.get(i).equals(y.get(z))) && (!team.get(i).equals(team.get(z)))) {
        				
        				if(Integer.parseInt(x.get(i)) <= 15 && team.get(i).equals("B")) {
    						Respawn location = Kill(id.get(z), "A");
    						x.add(z, Integer.toString(location.getX()));
    						y.add(z, Integer.toString(location.getY()));
    						//System.exit(0);
        				}else if(Integer.parseInt(x.get(z)) > 15 && team.get(z).equals("A")){
        					Respawn location = Kill(id.get(i), "B");
        					x.add(i, Integer.toString(location.getX()));
        					y.add(i, Integer.toString(location.getY()));
        					//System.exit(0);
        				}
        			}
        			
        			if(x.get(i).equals(Integer.toString(this.flag.getX())) && y.get(i).equals(Integer.toString(this.flag.getY()))) {
    					if(team.get(i).equals("A")) {
        					this.flag.setPlayer(id.get(i));
        					this.flag.setX(20);
        					this.flag.setY(Integer.parseInt(y.get(i)));
    					}
    				}
        			System.out.println(this.flag.getID());
        			
        			if(this.flag.getID() != null) {
        				if(id.get(i).equals(this.flag.getID())){
        					this.flag.setX(Integer.parseInt(x.get(i)));
        					this.flag.setY(Integer.parseInt(y.get(i)));
        					//System.exit(0);
        				}
        			}
        		}
        	}
        	
        	for(i=0; i < id.size(); i++) {
        		matriz = matriz + id.get(i);
        		matriz = matriz + "-" + x.get(i);
        		matriz = matriz + "-" + y.get(i);
        		matriz = matriz + "-" + team.get(i);
        		matriz = matriz + ",";
        	}
        	
        	matriz = matriz + "FL4G" + "-" + this.flag.getX() + "-" + this.flag.getY();
    		matriz = matriz + ",";
        	
    	}
    	
		return matriz;
	}
	
	public Respawn Kill(String id, String team) {
		Respawn spawn = null;
		if(team == "B") {
			spawn = Server.ladob.get(new Random().nextInt(9));
		}else {
			spawn = Server.ladoa.get(new Random().nextInt(9));
		}
		//this.kill = id;
		return spawn;
	}
	
	public String getKill() {
		return this.kill;
	}
	
	
}
