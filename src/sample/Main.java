package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

// https://pastebin.com/1ai8UHvR

public class Main extends Application {

    static ArrayList<String[]> andmebaas = new ArrayList<>(); // ei kasuta hetkel

    static VBox tabel = new VBox();
    static int koguSumma = 0;
    static Label summaLabel = new Label();

    @Override
    public void start(Stage stage) throws Exception{
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox, 400, 600);
        stage.setScene(scene);
        stage.show();

        // Sektsioon ülemine
        summaLabel.setText("0€");
        summaLabel.setPadding(new Insets(64));
        summaLabel.setFont(new Font(32));
        StackPane summaPane = new StackPane();
        summaPane.getChildren().add(summaLabel);
        vbox.getChildren().add(summaPane);

        // Sektsioon keskmine
        HBox keskmineBox = new HBox();
        TextField kuluPealkiri = new TextField();
        kuluPealkiri.setPromptText("Kulu tüüp");
        TextField kuluSumma = new TextField();
        Button liida = new Button("+");
        Button lahuta = new Button("-");
        keskmineBox.getChildren().addAll(kuluPealkiri, kuluSumma, liida, lahuta);
        vbox.getChildren().add(keskmineBox);

        // Sektsioon alumine
        vbox.getChildren().add(tabel);

        // Andmete sisestamine
        lahuta.setOnMouseClicked(event -> {
            lisaAndmebaasi(kuluPealkiri.getText(), kuluSumma.getText(), true);
        });

    }

    public static void lisaAndmebaasi(String tyyp, String summa, boolean lahuta) {
        koguSumma -= Integer.parseInt(summa);
        Label rida = new Label(tyyp + " " + summa + "€");
        tabel.getChildren().add(rida);
        summaLabel.setText(koguSumma + "€");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
