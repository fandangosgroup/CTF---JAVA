package com.github.uifxctf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.github.client.Meteoro;
import com.github.client.SocketRequestControl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class GameController {
	
	private ClassLoader cl = this.getClass().getClassLoader();
	private ImageView flag = new ImageView(this.cl.getResource("resources/images/flag.png").toString());
	private ImageView player = new ImageView(this.cl.getResource("resources/images/player.png").toString());
	private String fenemy = this.cl.getResource("resources/images/enemy.png").toString();
	private String ffriend = this.cl.getResource("resources/images/friend.png").toString();
	private ImageView mapa = new ImageView(this.cl.getResource("resources/images/mapa.png").toString());
	private ImageView mapa2 = new ImageView(this.cl.getResource("resources/images/mapa2.png").toString());
	private int flagMap = 0;
	
	
	@FXML
	private Button Exit;
	
	@FXML
	private TextField IP;
	
	@FXML 
	private Label Resp;
	
	@FXML
	private Pane matriz;
	
	@FXML
	private Button comeca;
	
	private static SocketRequestControl game;
	
	private static Runnable t1 = new Runnable() {
		public void run() {
			try {
				GameController.game.runGame();
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	private Runnable t2 = new Runnable() {
		public void run() {
			//System.out.println("DEBUGGGGGGGGGGGGGGGGGGGG");
			//System.out.println(GameController.game.getGameStatus());
			while(GameController.game.getGameStatus() == true) {
				//System.out.println("DEBUGGGGGGGGGGGGGGGGGGGG");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				char[][] matriz = GameController.game.getMatriz();
				
				if(matriz != null) {
					this.renderMatriz(matriz);
				}
			}
			System.exit(0);
		}
		
		
		private void renderMatriz(char[][] matriz2) {
			int x,y;
			for (int _x = 0; _x < 12; _x++) {
				GameController.game.meteoros.get(_x).moveMeteor();
			}
			
			Platform.runLater(() -> {
				matriz.getChildren().clear();
				switch(flagMap) {
					case 0 : matriz.getChildren().add(mapa); flagMap = 1; break;
					case 1 : matriz.getChildren().add(mapa2); flagMap = 0; break;
				}
			for (int _x =0; _x < 12; _x++) {
				matriz.getChildren().add(GameController.game.meteoros.get(_x).getImg());
			}
			});
				
			for(x=0;x<30;x++) {
				for(y=0;y<30;y++) {
					if(matriz2[x][y] == '@') {
						flag.setLayoutX(y * 10);
						flag.setLayoutY(x * 10);
						flag.setFitHeight(30);
						flag.setFitWidth(30);
						flag.setId("flag");
						Platform.runLater(() -> {
							matriz.getChildren().add(flag);
						});
					}
					if(matriz2[x][y] == 'X') {
						player.setLayoutX(y * 10);
						player.setLayoutY(x * 10);
						player.setFitHeight(30);
						player.setFitWidth(30);
						player.setId("player");
						Platform.runLater(() -> {
							matriz.getChildren().add(player);
						});
					}
					if(matriz2[x][y] == 'O') {
						ImageView enemy = new ImageView(fenemy);
						enemy.setFitHeight(30);
						enemy.setFitWidth(30);
						enemy.setLayoutX(y * 10);
						enemy.setLayoutY(x * 10);
						enemy.setId("enemy");
						Platform.runLater(() -> {
							matriz.getChildren().add(enemy);
						});
					}
					if(matriz2[x][y] == 'Y') {
						ImageView friend = new ImageView(ffriend);
						friend.setFitHeight(30);
						friend.setFitWidth(30);
						friend.setLayoutX(y * 10);
						friend.setLayoutY(x * 10);
						friend.setId("friend");
						Platform.runLater(() -> {
							matriz.getChildren().add(friend);
						});
					}
				}
			}
		}
	};
	
	public void exitAction() {
		System.exit(0);
	}
	
	public void conectaAction() throws IOException, InterruptedException {
		
		setLabelResposta("Conectando em " +getTextFieldIP() + "....");
//		IP.textProperty().addListener((text, old_text, new_text)->{
//	        Resp.setText(new_text);
//		});
		
		//Client cliente = new Client();
		//cliente.setserverIP(getTextFieldIP());
		
		//cliente.StartGame();
		
		//this.game = cliente.game;
		
		GameController.game = new SocketRequestControl(getTextFieldIP(), 12345);
		//boolean status = this.game.executa();
		
		if(GameController.game.executa()) {
			ClientFX.chageScreen("game");
			GameController.game.setInput(ClientFX.getInputController());
			new Thread(t1).start();
		}else {
			setLabelResposta("Connection refused: connect");
		}
	} 
	
	private void setLabelResposta(String value) {
		// TODO Auto-generated method stub
		Resp.setText(value);
	}

	public void startgameAction() {
		
		for (int x = 0; x < 12; x++) {
			if (x < 6) {
				GameController.game.meteoros.add(x, new Meteoro(ThreadLocalRandom.current().nextInt(1, 231), ThreadLocalRandom.current().nextInt(1, 4), ThreadLocalRandom.current().nextInt(1, 141)));
			}else {
				GameController.game.meteoros.add(x, new Meteoro(ThreadLocalRandom.current().nextInt(1, 231), ThreadLocalRandom.current().nextInt(328, 332), ThreadLocalRandom.current().nextInt(1, 141)));
			}
		}
		
		new Thread(t2).start();
		matriz.getChildren().remove(comeca);
		boolean state = GameController.game.getGameStatusFirstPlayer();
		
		System.out.println(state);
		
		if (state) {
			Platform.runLater(() -> {
				Popup.display();
				System.out.println(Popup.getA());
				System.out.println(Popup.getB());
				GameController.game.setHash(Popup.getA());
				GameController.game.setKey(Popup.getB());
				System.out.println("Inseri as cosias");
			});
		}
	}
	
	public static String tipo(String value) {
		String var = "";
		
		switch(value) {
			case "@":
				var = "flag";
			break;
			
			case "X":
				var = "player";
			break;
		}
		return var;
	}

	public String getTextFieldIP() {
		return IP.getText();
	}
	
	public void setTextFieldIP(String value) {
		IP.setText(value);
	}
}
