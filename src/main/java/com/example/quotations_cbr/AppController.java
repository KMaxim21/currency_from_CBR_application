package com.example.quotations_cbr;

import com.example.quotations_cbr.currency.Currency;
import com.example.quotations_cbr.currency.DocReceiver;
import com.example.quotations_cbr.currency.XPathMethods;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField name;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private TableColumn<TableViewContent, Double> firstColumn;
    @FXML
    private TableColumn<TableViewContent, String> secondColumn;

    @FXML
    private TableView<TableViewContent> table;
    private ObservableList<TableViewContent> tableViewContents;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
    private Alert alertChange = new Alert(Alert.AlertType.CONFIRMATION, "Может потребоваться много времени, рекомендуется уменьшить диапазон дат", ButtonType.CANCEL, ButtonType.APPLY);


    @FXML
    protected void onExecutionButtonClick() {

        if (checkFields() && checkDates()) {
            //Очищаем старую таблицу
            table.getItems().clear();

            //Получаем данные полей
            LocalDate start = dateFrom.getValue();
            LocalDate end = dateTo.getValue();
            String choiceString = adaptation(choiceBox.getValue());

            // Цикл по получению данных в заданном диапазоне
            while (start.isBefore(end)) {

                Currency currency = XPathMethods.getData(DocReceiver.getData(start), choiceString, start);
                if (currency != null) {
                    tableViewContents.add(new TableViewContent(currency.getValue(), start));

                    }
                start = start.plusDays(1);
            }

            // Присваиваем данные в таблицу
            table.setItems(tableViewContents);
        }
    }
    public static String adaptation(String currency) {
        String resuldId = "";
        switch (currency) {
            case "USD": resuldId = "R01235";
                break;
            case "EUR": resuldId = "R01239";
                break;
            case "GBP": resuldId = "R01035";
        }
        return resuldId;
    }

    private boolean checkFields() {
        // Проверяем что все поля заполнены данными
        if (choiceBox.getValue() == null) {
            alert.setContentText("Выберите валюту!");
            alert.showAndWait();
            return false;
        }
        if (dateFrom.getValue() == null) {
            alert.setContentText("Выберите начальную дату периода!");
            alert.showAndWait();
            return false;
        }
        if (dateTo.getValue() == null) {
            alert.setContentText("Выберите конечную дату периода!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    private boolean checkDates() {
        // Проверяем что даты в допустимом диапазоне
        if (dateFrom.getValue().isBefore(LocalDate.of(1992, 7, 1))) {
            alert.setContentText("Дата должна быть не ранее 01/07/1992");
            alert.showAndWait();
            return false;
        }
        if (dateTo.getValue().isAfter(LocalDate.now())) {
            alert.setContentText("Дата должна быть не ранее 01/07/1992");
            alert.showAndWait();
            return false;
        }
        // Уведомляем что может потребоваться много времения для получения данных
        if (Period.between(dateFrom.getValue(), dateTo.getValue()).getYears() >= 1) {
            alertChange.showAndWait();
            if (alertChange.getResult().getButtonData().isCancelButton()) {
                return false;
            }

        }
        return true;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Инициализация значениями для выбора валюты
        choiceBox.setItems(FXCollections.observableArrayList("USD", "EUR", "GBP"));
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "USD": this.name.setText("Доллар США");
                break;
                case "EUR": this.name.setText("Евро");
                break;
                case "GBP": this.name.setText("Фунт стерлингов Соединенного королевства");
                    }
                }
        );
        // Инициализация начальных значений для окон выбора дат
        dateFrom.setValue(LocalDate.of(1992, 7, 1));
        dateTo.setValue(LocalDate.now());
        // Инициализация таблицы
        tableViewContents = FXCollections.observableArrayList();
        firstColumn.setCellValueFactory(new PropertyValueFactory<TableViewContent, Double>("firstColumn"));
        secondColumn.setCellValueFactory(new PropertyValueFactory<TableViewContent, String>("secondColumn"));
        table.getItems().addAll(tableViewContents);
        // Задаем параметры всплывающего окна
        alert.setHeaderText("");
        alert.setTitle("Ошибка!");
    }

}