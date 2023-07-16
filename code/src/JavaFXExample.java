import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFXExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Membuat label
        Label label = new Label("Halo, ini contoh JavaFX");

        // Membuat tombol
        Button button = new Button("Klik Saya");
        button.setOnAction(event -> label.setText("Tombol telah diklik!"));

        // Membuat layout
        VBox root = new VBox(label, button);

        // Membuat scene
        Scene scene = new Scene(root, 300, 200);

        // Mengatur scene ke stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Contoh JavaFX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
