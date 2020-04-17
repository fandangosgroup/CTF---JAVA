package com.github.server;

import java.util.ArrayList;
import java.util.List;

public class PlayerCollider {
	
	public String processa(String dados) {
		System.out.println(dados);
		String data = dados.substring(3, dados.length());
    	String[] colunas = null;
    	String[] player = null;
    	List<String> id = new ArrayList<String>();
    	List<String> x = new ArrayList<String>();
    	List<String> y = new ArrayList<String>();
    	List<String> team = new ArrayList<String>();
    	int i,z;
    	
    	if(dados != null) {
    		colunas = data.split(",");
    		
    		for(i=0; i < colunas.length; i++) {
        		player = colunas[i].split("-");
        		id.add(player[0]);
        		x.add(player[1]);
        		y.add(player[2]);
        		team.add(player[3]);
        	}
        	System.out.println("Chegou1");
        	for(i=0; i < id.size(); i++) {
        		for(z=0; z < id.size(); z++) {
        			System.out.println("Chegou2");
        			System.out.println(id.get(i) +"--"+ id.get(z));
        			System.out.println(x.get(i) +"--"+ x.get(z));
        			System.out.println(y.get(i) +"--"+ y.get(z));
        			System.out.println(team.get(i) +"--"+ team.get(z));
        			
        			if((id.get(i) != id.get(z)) && (x.get(i) == x.get(z)) && (y.get(i) == y.get(z)) && (team.get(i) != team.get(z)) ) {
        				System.out.println("COLIDIU!!!");
        				System.exit(0);
        			}
        		}
        	}
    	}
    	
		return dados;
		
	}
}
