package com.github.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerCollider {
	private String kill = null;
	private Flag flag = new Flag("VAZIO",5,15);
	private boolean GameStatus = false;
	
	public String processa(String dados) {
		//System.out.println(dados);
		String data = dados.substring(3, dados.length());
    	String[] colunas = null;
    	String[] player = null;
    	List<String> id = new ArrayList<String>();
    	List<String> x = new ArrayList<String>();
    	List<String> y = new ArrayList<String>();
    	List<String> team = new ArrayList<String>();
    	int i = 0;
    	int z = 0;
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
        			//System.out.println("-----------------DEBUG-------------------");
        			//System.out.println(id.get(i) +"--"+ id.get(z));
        			//System.out.println(x.get(i) +"--"+ x.get(z));
        			//System.out.println(y.get(i) +"--"+ y.get(z));
        			//System.out.println(team.get(i) +"--"+ team.get(z));
        			//System.out.println("-----------------[DEBUG]-----------------");
        			
        			if(x.get(i).equals(Integer.toString(this.flag.getX())) && y.get(i).equals(Integer.toString(this.flag.getY()))) {
    					if(team.get(i).equals("A")) {
    						//System.out.println("Jogador pegou a Bandeira");
        					this.flag.setPlayer(id.get(i));
        					this.flag.setX(Integer.parseInt(x.get(i)));
        					this.flag.setY(Integer.parseInt(y.get(i)));
    					}else {
    						//System.out.print("Fudeuuu chama a microsoft@!!!!");
    					}
    				}
        			
        			if((!id.get(i).equals(id.get(z))) && (x.get(i).equals(x.get(z))) && (y.get(i).equals(y.get(z))) && (!team.get(i).equals(team.get(z)))) {
        				
        				if(Integer.parseInt(x.get(i)) <= 15 && team.get(i).equals("B")) {
        					if(id.get(z).equals(this.flag.getID())) {
    							//System.out.print("DEIXA  APORRA DA FLAG !!!!!!!!!!!!!!!!!!");
    							this.flag.setPlayer("VAZIO");
    							//this.flag.setX(Integer.parseInt(x.get(i)));
    							//this.flag.setY(Integer.parseInt(y.get(i)));
    							//System.out.println(this.flag.getID());
    							//System.out.println(this.flag.getX());
    							//System.out.println(this.flag.getY());
    							//System.exit(0);
    						}
    						Respawn location = Kill(id.get(z), "A");
    						x.add(z, Integer.toString(location.getX()));
    						y.add(z, Integer.toString(location.getY()));
    						
    						//System.exit(0);
        				}else if(Integer.parseInt(x.get(z)) > 15 && team.get(z).equals("A")){
        					//System.out.print("FUDEUUUUUUUUUUUUUUUUUU!!!!");
        					Respawn location = Kill(id.get(i), "B");
        					x.add(i, Integer.toString(location.getX()));
        					y.add(i, Integer.toString(location.getY()));
        					//System.exit(0);
        				}else {
        					//if(id.get(z).equals(this.flag.getID())) {
    							System.out.print("se leu a msg aqui, entre em contato com a microsoft urgente!!!!!!!!!!!!!!!!!!");
    							//this.flag.setPlayer("VAZIO");
    							//this.flag.setX(Integer.parseInt(x.get(i)));
    							//this.flag.setY(Integer.parseInt(y.get(i)));
    							//System.out.println(this.flag.getID());
    							//System.out.println(this.flag.getX());
    							//System.out.println(this.flag.getY());
    							//System.exit(0);
    						//}
        				}
        			}
        			
        			//System.out.println(this.flag.getID());
        			
        			if(!this.flag.getID().equals("VAZIO")) {
        				if(id.get(i).equals(this.flag.getID())){
        					this.flag.setX(Integer.parseInt(x.get(i)));
        					this.flag.setY(Integer.parseInt(y.get(i)));
        					if(Integer.parseInt(x.get(i)) > 14) {
        						this.GameStatus = true;
        					}
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
	
	public boolean getStatus() {
		return this.GameStatus;
	}
	
	
}