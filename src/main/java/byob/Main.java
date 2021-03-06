package byob;

import byob.controllers.PaneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view_main.fxml"));
        Parent root = loader.load();
        PaneController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("BYOB");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
