package com.example.quotations_cbr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CurrencyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Привязываем fxml к приложению
        FXMLLoader fxmlLoader = new FXMLLoader(CurrencyApplication.class.getResource("app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Устанавливаем заголовок и иконку
        stage.setTitle("Курсы валют");
        stage.getIcons().add(new Image(Objects.requireNonNull(CurrencyApplication.class.getResourceAsStream("/images/icon.png"))));

        // Присваиваем параметры отображения приложению
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}