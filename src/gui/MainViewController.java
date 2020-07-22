package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.service.DepartmentService;

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
	
	// jeito longo
	/*
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}*/
	
	// jeito curto
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView(); // precisamos acresentar a declaraçao do parametro lambida na funcao loadview vazio ou com declaraçao 
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", X -> {});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) { // funcao para abir outra tela
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
		
		// comando para ativar a funcao que colocar no lugar do initializingAction
		T controller = loader.getController();
		initializingAction.accept(controller);
		}
		catch(IOException e) {
			Alerts.showAlert("IOExeption", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	// Jeito mais longo para abrir outra tela
	// em vez de repitir a funcao uso a mesma criada e faço algumas alteracoes, passar a inicializaçao para o parametro loadview
	/*
	private void loadView2(String absoluteName) { // funcao para abir outra tela
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
		
		//
		// inicializador, diferenca do outro loadview 
		DepartmentListController controller = loader.getController(); // serve para carregar a view ou acessar no controller 
		controller.setDepartmentService(new DepartmentService()); // Injetando a dependecia do controller
		controller.updateTableView(); // atualizar os dados na tela tableview
		//.addAll adicionando uma colecao
		
		}
		catch(IOException e) {
			Alerts.showAlert("IOExeption", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}*/
}
