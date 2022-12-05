package com.example.quotations_cbr;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TableViewContent {
        private SimpleDoubleProperty firstColumn;
        private SimpleStringProperty secondColumn;


        public TableViewContent(Double fColumn, LocalDate sColumn) {
            this.firstColumn = new SimpleDoubleProperty(fColumn);
            this.secondColumn = new SimpleStringProperty(sColumn.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        }
        public TableViewContent(String str) {
            this.firstColumn = new SimpleDoubleProperty(0.0);
            this.secondColumn = new SimpleStringProperty(str);
        }

    public double getFirstColumn() {
        return firstColumn.get();
    }

    public SimpleDoubleProperty firstColumnProperty() {
        return firstColumn;
    }

    public void setFirstColumn(double firstColumn) {
        this.firstColumn.set(firstColumn);
    }

    public String getSecondColumn() {
        return secondColumn.get();
    }

    public SimpleStringProperty secondColumnProperty() {
        return secondColumn;
    }

    public void setSecondColumn(String secondColumn) {
        this.secondColumn.set(secondColumn);
    }
}
