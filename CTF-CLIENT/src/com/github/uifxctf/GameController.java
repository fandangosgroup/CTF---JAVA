package com.github.uifxctf;

import java.io.IOException;
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
			System.out.println("DEBUGGGGGGGGGGGGGGGGGGGG");
			System.out.println(GameController.game.getGameStatus());
			while(GameController.game.getGameStatus() == true) {
				System.out.println("DEBUGGGGGGGGGGGGGGGGGGGG");
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
			
			Platform.runLater(() -> {
				matriz.getChildren().clear();
				matriz.getChildren().add(mapa);
			});
				
			for(x=0;x<30;x++) {
				for(y=0;y<30;y++) {
					if(matriz2[x][y] == '@') {
						flag.setLayoutX(y * 10);
						flag.setLayoutY(x * 10);
						flag.setFitHeight(15);
						flag.setFitWidth(15);
						flag.setId("flag");
						Platform.runLater(() -> {
							matriz.getChildren().add(flag);
						});
					}
					if(matriz2[x][y] == 'X') {
						player.setLayoutX(y * 10);
						player.setLayoutY(x * 10);
						player.setFitHeight(15);
						player.setFitWidth(15);
						player.setId("player");
						Platform.runLater(() -> {
							matriz.getChildren().add(player);
						});
					}
					if(matriz2[x][y] == 'O') {
						ImageView enemy = new ImageView(fenemy);
						enemy.setFitHeight(15);
						enemy.setFitWidth(15);
						enemy.setLayoutX(y * 10);
						enemy.setLayoutY(x * 10);
						enemy.setId("enemy");
						Platform.runLater(() -> {
							matriz.getChildren().add(enemy);
						});
					}
					if(matriz2[x][y] == 'Y') {
						ImageView friend = new ImageView(ffriend);
						friend.setFitHeight(15);
						friend.setFitWidth(15);
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
