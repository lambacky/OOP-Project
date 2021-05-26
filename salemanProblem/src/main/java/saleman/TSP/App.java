package saleman.TSP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import saleman.solver.*;
import saleman.model.*;
// 
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // new
        BorderPane mainRoot = new BorderPane();
        mainRoot.setPadding(new Insets(14));
        Pane root = new Pane();
        root.setBorder(new Border(
                new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        VBox buttonBox = new VBox(20);
        buttonBox.setPadding(new Insets(20));

        // end new
        // load from file:
        Solver solv = new Solver();
        Button btnLoadFile = new Button("Load from file");
        btnLoadFile.setOnAction(e -> {
            System.out.println("Loading from file");
            root.requestFocus();
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
            fc.setInitialDirectory(new File(Paths.get("").toAbsolutePath().toString()));
            File file = fc.showOpenDialog(primaryStage);
            if (file != null) {
                root.getChildren().clear();
                solv.reset(root);
                try (BufferedReader bfreader = new BufferedReader(new FileReader(file))) {
                    List<City> collect = bfreader.lines().map(line -> {
                        String[] split = line.split(" ");
                        return new City(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
                    }).collect(Collectors.toList());
                    collect.forEach(city -> { // new created city will be automatically added to List in CityManager
                        city.display(root);
                    });
                } catch (FileNotFoundException ex) {

                } catch (IOException ex) {

                }
            }
        });
        buttonBox.getChildren().add(btnLoadFile);
        // end load from file
        // add city by hand

        Label bottomLabel = new Label("Total City: 0  | Minimum distance : | Time : ");

        root.setOnMouseClicked(e -> {
            root.requestFocus();
            City city = new City(e.getX(), e.getY());
            city.display(root);
            bottomLabel.setText("Total City: " + CityManager.getInstance().numberOfCities() + " | Minimum distance : | Time : ");
        });

        Button btnUndo = new Button("Undo");
        btnUndo.setOnAction(e -> {
            if (CityManager.getInstance().numberOfCities() > 0) {
                CityManager.getInstance().clearOne(CityManager.getInstance().numberOfCities() - 1, root);
                bottomLabel.setText(
                        "Total City: " + CityManager.getInstance().numberOfCities() + " | Minimum distance : | Time : ");
            } else {
                // notify to input some city
            }
        });

        buttonBox.getChildren().add(btnUndo);
        Button btnRun = new Button("Run");
        btnRun.setOnAction(e -> {
            // run algorithm
            solv.run(root);
            bottomLabel.setText("Total City: " + CityManager.getInstance().numberOfCities() + "| Minimum distance : "
                    + solv.getMinDistance() + " | Time : " + solv.getTimeElapsed() + " seconds");
        });
        buttonBox.getChildren().add(btnRun);
        Button btnClear = new Button("Clear Line");
        btnClear.setOnAction(e -> {
            solv.clearLine(root);
        });
        buttonBox.getChildren().add(btnClear);
        Button btnReset = new Button("Reset");
        btnReset.setOnAction(e -> {
            solv.reset(root);
            bottomLabel.setText("Total City: 0  | Minimum distance : ");

        });
        buttonBox.getChildren().add(btnReset);
        Button btnExit = new Button("Quit!");
        btnExit.setOnAction(e -> {
            primaryStage.close();
        });
        buttonBox.getChildren().add(btnExit);
        mainRoot.setRight(buttonBox);
        mainRoot.setBottom(bottomLabel);
        mainRoot.setCenter(root);

        Scene scene = new Scene(mainRoot, 800, 600);

        primaryStage.setTitle("Traveling saleman problem solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}