package com.github.uifxctf;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;
import org.aerofx.AeroFX;
import com.github.client.InputControllerGUI;

public class ClientFX extends Application {
	
	public static Stage stage;
	private ClassLoader cl = this.getClass().getClassLoader();
	public static Scene telaHome;
	public static Scene telaGame;
	private static InputControllerGUI input;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		stage = primaryStage;
		 
		primaryStage.setTitle("CTK - Capture The Key Game :D");
		
		Parent fxmlHome = FXMLLoader.load(getClass().getResource("TelaHome.fxml"));
		telaHome = new Scene(fxmlHome,600,386);
		
		AeroFX.style();
		AeroFX.styleAllAsGroupBox(fxmlHome);
		
		Parent fxmlGame = FXMLLoader.load(getClass().getResource("TelaMatriz.fxml"));
		fxmlGame.setFocusTraversable(true);
		telaGame = new Scene(fxmlGame,360,360);
		input= new InputControllerGUI();
		telaGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
	        @Override
	        public void handle(KeyEvent t) {
	           input.setInputAtual(t.getCode().toString());
	        }
	    });
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                t.consume();

                // Coloque aqui o código a ser executado ao fechar o sistema.

                primaryStage.close();
                Platform.exit();
                System.exit(0);
            }
        });
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(telaHome);
		primaryStage.show();
	}

	public static void chageScreen(String name) {
		switch (name) {
			case "home":
				stage.setScene(telaHome);
				break;
			case "game":
				stage.setScene(telaGame);
				break;
		}
	}
	
	public static InputControllerGUI getInputController() {
		return input;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
