package com.github.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
  
public class Server {
	private int porta = 0;
    private List<Clientes> clientes;
    private List<Positions> positions;
    public int firstPlayer = 0;
    public int first = 0;
    public boolean savekey = false;
    public static List<Respawn>ladoa = new ArrayList<Respawn>();
    public static List<Respawn>ladob = new ArrayList<Respawn>();
    public PlayerCollider collider = new PlayerCollider();
    private String hash = "";
    private String key = ""; 
    
	
	public static void main(String[] args) throws IOException {
        // inicia o servidor
        new Server(12345).executa();
    }

    public Server (int porta) {
        this.porta = porta;
        this.clientes = new ArrayList<Clientes>();
        this.positions = new ArrayList<Positions>();
        
        ladoa.add(new Respawn(25,15, "A"));
    	ladoa.add(new Respawn(25,20, "A"));
    	ladoa.add(new Respawn(25,25, "A"));
    	ladoa.add(new Respawn(27,10, "A"));
    	ladoa.add(new Respawn(27,15, "A"));
    	ladoa.add(new Respawn(23,15, "A"));
    	ladoa.add(new Respawn(23,20, "A"));
    	ladoa.add(new Respawn(23,25, "A"));
    	ladoa.add(new Respawn(23,10, "A"));
    	ladoa.add(new Respawn(23,15, "A"));
    	
    	
    	ladob.add(new Respawn(5,15, "B"));
    	ladob.add(new Respawn(5,20, "B"));
    	ladob.add(new Respawn(5,25, "B"));
    	ladob.add(new Respawn(7,10, "B"));
    	ladob.add(new Respawn(7,20, "B"));
    	ladob.add(new Respawn(2,15, "B"));
    	ladob.add(new Respawn(2,20, "B"));
    	ladob.add(new Respawn(2,25, "B"));
    	ladob.add(new Respawn(2,10, "B"));
    	ladob.add(new Respawn(2,20, "B"));
    }
    
    public void unsetCliente(int id) {
    	for(Clientes cli : this.clientes) {
    		if(cli.getID() == id) {
    			this.clientes.remove(cli);
    			break;
    		}
    	}
    }
    
    public Respawn respawn() {
    	if(this.firstPlayer == 0) {
    		this.firstPlayer = 1;
    		Respawn aux2 = ladob.get(new Random().nextInt(9));
    		return aux2;
    	}
    	
    	this.firstPlayer = 0;
    	Respawn aux2 = ladoa.get(new Random().nextInt(9));
    	return aux2;
    }
    
    public void unsetPos(int id) {
    	for(Positions pos : this.positions) {
    		if(pos.getPosID() == id) {
    			this.positions.remove(pos);
    			break;
    		}
    	}
    }

    public void executa () throws IOException {
        ServerSocket servidor = new ServerSocket(this.porta);
        System.out.println("Servidor Aberto!");
        System.out.println("Porta 12345 aberta!");
        System.out.println("Iniciado!");

        while (true) {
            // aceita um cliente
            Socket cliente = servidor.accept();
            System.out.println("Nova conex�o com o cliente " + cliente.getInetAddress().getHostAddress());
            System.out.println(cliente.getRemoteSocketAddress());
            
            // adiciona saida do cliente � lista
            PrintStream ps = new PrintStream(cliente.getOutputStream());
            
            String id1;
            String [] id2;
            id1 = cliente.getRemoteSocketAddress().toString().substring(1);
            id2 = id1.split("\\.");
            id1 = id2[3].substring( id2[3].length() -5, id2[3].length());
            id1 = id2[0] + id1;
            //id2 = cliente.getRemoteSocketAddress().toString().substring(11, 16);
            //id1 = cliente.getRemoteSocketAddress().toString().substring(1).split("\\.");
            //id2 = id1[3];
            //id1 = id1 + id2;
            int aux = Integer.parseInt(id1);
            System.out.println(aux);

            // cria tratador de cliente numa nova thread
            ps.println("idUser:"+aux); //manda o id do cliente ao conectar
            if(this.first == 0) {
            	this.first = 1;
            	ps.println("PrimeiroPlayer");
            }
            TrataCliente tc = new TrataCliente(ps, cliente.getInputStream(), aux, this);
            
            Clientes cl = tc.getCliente();
            this.clientes.add(cl);
            
            Positions pos = tc.getPos();
            this.positions.add(pos);
            
            new Thread(tc).start();
        }
        

    }

    public void distribuiMensagem(String msg, int id) {
        // envia msg para todo mundo
    	if(this.savekey == false) {
    		System.out.println("Entrou no send");
    		for (Clientes clientes : this.clientes) {
    			if(clientes.getCliente() != this.clientes.get(0).getCliente()) {
    				clientes.getCliente().println("Esperando Chave...");
    			}
    		}
    	}
    	
    	if(msg.substring(0,3).equals("K3Y")) {
    		System.out.println("AQUI CHEGOU PORRA");
    		String[] hash = msg.split("-");
			
			System.out.println(hash[1]);
			System.out.println(hash[2]);
			this.hash = hash[1]; 
			this.key = hash[2];
			
			Database db = new Database();
    		
    		Connection teste = db.getConnection();
    		try {
				Statement st = teste.createStatement();
				//st.execute("SELECT * FROM USUARIOS");
				st.execute("USE heroku_b481894670aeac7");
				String sql = "INSERT INTO CRIPTO VALUES(null,'"+hash[1]+"','"+hash[2]+"')";
				System.out.println(sql);
				
				int resul = st.executeUpdate(sql);
				
				//resul.next();
				//System.out.println("chegouAQUI");
				//String tete = resul.getString("Nome");
				//System.out.print(tete);
				this.savekey = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   
    	}
    	
    	if(this.savekey == true) {
    		String location = "M4X";
        	int aux;
        	String[] loc = null;
        	
        	for (Positions Pos : this.positions) {//12 10 20
        		if(msg.substring(0, 3).equals("M4X")) {
        			loc = msg.split("-");
        			//System.out.println(id+" Player Updated");
            		if(Pos.getPosID() == id) {
            			Pos.setPosX(Integer.parseInt(loc[1]));
            			Pos.setPosY(Integer.parseInt(loc[2]));
            		}
            	}
        		
        		aux = Pos.getPosID();
        		location = location + Integer.toString(aux);
        		location = location + '-';
        		
        		aux = Pos.getPosX();
        		location = location + Integer.toString(aux);
        		location = location + '-';
        		
        		aux = Pos.getPosY();
        		location = location + Integer.toString(aux);
        		location = location + '-';
        		
        		location = location + Pos.getTeam();
        		location = location + ',';
        	}
        	
        	location = collider.processa(location);
        	System.out.println(location);
        	boolean status = collider.getStatus();
        	System.out.println("Game:"+status);
        	
        	if(status) {
        		for (Clientes cliente : this.clientes) {
        			System.out.println("Entrou no gameover");
            		String GameOver = "GameOver-"+ this.hash + "-" + this.key;
            		System.out.println(GameOver);
            		cliente.getCliente().println(GameOver);
        		}
        		System.out.print("Fim de Jogo - Dan�a do Caix�o");
        		System.exit(0);
        	}
        	
            for (Clientes cliente : this.clientes) {
            	System.out.println("Cl:"+cliente.getID());
            	
            	
            	//cliente.getCliente().println("Quantidade:"+this.clientes.size());
            	cliente.getCliente().println(location);
            	
//            	if(cliente.getID() == id) {
//            		cliente.getCliente().println(msg);
//            	}
    			
            }
    	}
    	
//    	if(this.savekey == false) {
//    		while(!this.savekey) {
//    			//System.out.println("Esperando Chave !");
////    			for (Clientes cliente : this.clientes) {
////    				cliente.getCliente().println("Esperando Chave...");
////    	        }
//    			if(msg.substring(0,3).equals("K3Y")) {
//    				System.out.println("Chave Recebida!!");
//    				this.savekey = true;
//    				
//    				String[] hash = msg.split("-");
//    				
//    				System.out.println(hash[1]);
//    				System.out.println(hash[2]);
//    				System.exit(0);
//    				Database db = new Database();
//    	    		
//    	    		Connection teste = db.getConnection();
//    	    		try {
//    					Statement st = teste.createStatement();
//    					//st.execute("SELECT * FROM USUARIOS");
//    					st.execute("USE heroku_b481894670aeac7");
//    					ResultSet resul = st.executeQuery("INSERT INTO CRIPTO keyy,hash VALUES("+hash[1]+","+hash[2]+")");
//    					
//    					//resul.next();
//    					//System.out.println("chegouAQUI");
//    					//String tete = resul.getString("Nome");
//    					//System.out.print(tete);
//    					System.exit(0);
//    				} catch (SQLException e) {
//    					// TODO Auto-generated catch block
//    					e.printStackTrace();
//    				}
//    			}
//    		}
//    	}
    	
    }
}
     
