package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	private void loadView(String absoluteName) { // funcao para abir outra tela
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName)); // Carregar a tela
		VBox newVBox = loader.load(); // criando objeto VBox
		
		// MOstrar a nova View na View principal(MainView)
		Scene mainScene = Main.getMainScene(); // pegando a referencia da scena principal
		
		// Pegando os filhos (node) do VBox About e passando pros filhos do VBox no MainScene
		VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent(); // pegando referencia do VBox do MainView
		
		// getRoot = pega o primeiro elemento da view - no caso é um ScrollPane
		
		
		// Apagando todos os filhos do VBox principal e depois adicionando os filhos do Vbox principal e em seguida dos filhos do VBox que queremos abrir
		Node mainMenu = mainVBox.getChildren().get(0); // guardando referencia para um menu - pegando a referencia do primeiro filho do VBox principal
		mainVBox.getChildren().clear(); // limpando todos os filhos do MainVBox
		mainVBox.getChildren().add(mainMenu); // Adicionando todos os filhos do mainVbox
		mainVBox.getChildren().addAll(newVBox.getChildren()); // Adicionando todos os filhos do Vbox que queremos abrir
		
		//.addAll adicionando uma colecao
		
		}
		catch(IOException e) {
			Alerts.showAlert("IOExeption", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
