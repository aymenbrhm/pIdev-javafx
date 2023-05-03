package Gui;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class Signature extends Application {

    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vbox = new VBox();
        
        canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        canvas.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.setOnMouseDragged(e -> {
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

//        BorderPane root = new BorderPane();
//        root.setCenter(canvas);
//
//        Scene scene = new Scene(root, 400, 400);
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Signature App");
//        primaryStage.show();
        
        
        canvas.setOnMouseReleased(e -> {
    gc.closePath();
});


Button saveButton = new Button("Sauvegarder");
saveButton.setOnAction(e -> {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Sélectionner un emplacement pour enregistrer l'image");
    File file = fileChooser.showSaveDialog(primaryStage);
    if (file != null) {
        try {
            WritableImage writableImage = new WritableImage(400, 400);
            canvas.snapshot(null, writableImage);
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
            System.out.println("Dessin sauvegardé sous " + file.getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});

GridPane gridPane = new GridPane();
gridPane.add(canvas, 0, 0);
//gridPane.add(saveButton, 1, 0);
gridPane.setHgap(100);

//vbox.getChildren().add(gridPane);
vbox.getChildren().add(saveButton);


BorderPane root = new BorderPane();
root.setCenter(vbox);

Scene scene = new Scene(root, 500, 500);

primaryStage.setScene(scene);
primaryStage.setTitle("Signature App");
primaryStage.show();
        
        
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
