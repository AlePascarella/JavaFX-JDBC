package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.service.DepartmentService;

public class DepartmentListController implements Initializable {
	
	// dependencias
	@FXML
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnNome;
	
	@FXML
	private Button btNew;
	
	// Carregar os departamentos na observablelist
	private ObservableList<Department> obsList;
	
	@FXML
	public void onBtNewAction() {
		System.out.println("onBtNewAction");
	}
	
	// Injetando a dependencia
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
	}
	
	// Metodo para iniciar algo da minha tela
	private void initializeNodes() {
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("Id")); //Iniciar o comportamento das colunas das tabelas 
		tableColumnNome.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		// Ajustar o tamanho da tableWiev ao tamanho da largura e altura da janela
		Stage stage = (Stage) Main.getMainScene().getWindow(); // pegando a referencia para o stage
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty()); // pegando a view tableViewDeártment para acompanhar a janela
		
	}
	
	
	// Metodo responsavel por acessar o servico, carregar os departamentos e jogar na observableList e depois asociar o observableList com o tableViewDepartment
	public void updateTableView() {
		if(service == null) { // serve para evitar erros
			throw new IllegalStateException("Service was null");
		}
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList); // carregar os itens e mostrar na tela
	}

}
