package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));// Instanciando um objeto loader do caminho MainView 
			ScrollPane scrollPane = loader.load(); //Carrega a view
			
			scrollPane.setFitToHeight(true);// deixa a scrollPane na altura do palco
			scrollPane.setFitToWidth(true);// deixa a scrollPane na altura do palco
			
			Scene mainScene = new Scene(scrollPane);// Carrega o objeto Scene com o objeto principal
			primaryStage.setScene(mainScene);// setando o palco da scena com a mainScene
			primaryStage.setTitle("Sample JavaFX application");// setando o titulo do palco
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}