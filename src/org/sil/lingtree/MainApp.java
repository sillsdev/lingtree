package org.sil.lingtree;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class MainApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("LingTree.css").toExternalForm());
			Text t = new Text(0.0, 10.0, "Here is some text");
			System.out.println("t.y=" + t.getY());
			System.out.println("layout=" + t.getLayoutY());
			System.out.println("bounds, local, height:" + t.getBoundsInLocal().getHeight());
			System.out.println("bounds, parent, height:" + t.getBoundsInParent().getHeight());
			root.getChildren().add(t);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
