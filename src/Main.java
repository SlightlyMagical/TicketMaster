import gui.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final SceneManager sceneManager;

    public Main() throws IOException {
        sceneManager = SceneManager.getInstance();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        sceneManager.showStartScene(primaryStage);
    }
}
