package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnNome;
	
	@FXML
	private Button btNew;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
	}
	
	// Metodo para iniciar algo da minha tela
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory("id")); //Iniciar o comportamento das colunas das tabelas 
		tableColumnNome.setCellValueFactory(new PropertyValueFactory("nome"));
		
		// Ajustar o tamanho da tableWiev ao tamanho da largura e altura da janela
		Stage stage = (Stage) Main.getMainScene().getWindow(); // pegando a referencia para o stage
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // pegando a view tableViewDeártment para acompanhar a janela
		
	}

}
