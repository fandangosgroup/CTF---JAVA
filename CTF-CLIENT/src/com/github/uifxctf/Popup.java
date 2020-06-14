package com.github.uifxctf;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class Popup {
	
	private static String a;
	private static String b;
    
	public static void display() {
		Stage popupwindow = new Stage();
		      
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("POP UP MODAL - CTK");
		      
		      
		Label label1 = new Label("CAPTURE DE FLAG by BRUNO SAMPAIO, FABRICIO GALUDO, HOBITO e MESTRE CABELO");
		Label label2 = new Label("DIGITE A FRASE QUE DESEJA ESCONDER");
		
		TextField textkey = new TextField("Digite a key");
		TextField texthash = new TextField("Digite a hash");
		      
		     
		Button button1 = new Button("Salvar");
		     
		     
		button1.setOnAction(e -> {
			// Coloque aqui o código a ser executado ao fechar o sistema.
			System.out.println("SALVA KEY AND HASH");
			
			Popup.a = texthash.getText();
			Popup.b = textkey.getText();

			popupwindow.close();
			
		});
		
		VBox layout = new VBox(10);
		     
		      
		layout.getChildren().addAll(label1, label2, texthash, textkey, button1);
		      
		layout.setAlignment(Pos.CENTER);
		      
		Scene scene1 = new Scene(layout, 300, 250);
		      
		popupwindow.setScene(scene1);
		      
		popupwindow.showAndWait();
	}

	public static String getA() {
		// TODO Auto-generated method stub
		return a;
	}

	public static String getB() {
		// TODO Auto-generated method stub
		return b;
	}
}