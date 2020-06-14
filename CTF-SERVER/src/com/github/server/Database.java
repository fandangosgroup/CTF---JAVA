package com.github.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection con = null;
	 
	 private String hostName = null;
	 private String userName = null;
	 private String password = null;
	 private String url = null;
	 private String jdbcDriver = null;
	 private String dataBaseName = null;
	 private String dataBasePrefix = null;
	 private String dabaBasePort = null;

	private BufferedReader arquivo;
	 
	 public Database() {
		  super();
		 
		  /**
		  *Os dados setados abaixo servem para uma conexão em MySQL.
		  *Altere de acordo com seu BD.
		  *Aconselhamos carregar estes dados de um arquivo. 
		  */
		 
		  //"jdbc: mysql:/localhost:3306/meu_bd";
		  hostName = "us-cdbr-iron-east-05.cleardb.net";
		  userName = "baaf8787ff1507";
		  //password = "7ec630fc";    
		  jdbcDriver = "com.mysql.jdbc.Driver";
		  dataBaseName = "heroku_b481894670aeac7";
		  dataBasePrefix = "jdbc: mysql:/";
		  dabaBasePort = "3306";
		  
		  try {
			arquivo = new BufferedReader(new FileReader("database.txt"));
			password = arquivo.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}   
		  
		  
		  
		  
		  url = dataBasePrefix + hostName + ":"+dabaBasePort+"/" + dataBaseName + "/";
		  url = "jdbc:mysql://us-cdbr-iron-east-05.cleardb.net:3306/heroku_b481894670aeac7?useTimezone=true&serverTimezone=UTC";
		 
		    /**
		    *Exemplo de um URL completo para MySQL:    
		    *a concatenação acima deve ficar algo como:
		    *jdbc:'mysql:/localhost:3306/meu_bd'
		    */
		 
		}
		 
		 
		  /**
		  *Retorna uma java.sql.Connection.
		  *@return con 
		  */
		 
		 
		public Connection getConnection() {
		  try {
		    if (con == null) {
		      Class.forName(jdbcDriver);
		      con = DriverManager.getConnection(url, userName, password);
		    } else if (con.isClosed()) {
		      con = null;
		      return getConnection();
		    }
		  } catch (ClassNotFoundException e) {
		 
		    //TODO: use um sistema de log apropriado.
		 
		    e.printStackTrace();
		  } catch (SQLException e) {
		 
		    //TODO: use um sistema de log apropriado.
		 
		    e.printStackTrace();
		  }
		  return con;
		}
		
		public void closeConnection() {
			  if (con != null) {
			    try {
			      con.close();
			    } catch (SQLException e) {
			      //TODO: use um sistema de log apropriado.
			      e.printStackTrace();
			    }
			  }
			}

}
