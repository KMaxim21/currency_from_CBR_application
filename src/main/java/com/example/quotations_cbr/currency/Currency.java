package com.example.quotations_cbr.currency;

import java.time.LocalDate;

public class Currency {
    private double value;
    LocalDate date;

    public Currency(double value, LocalDate date) {

        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Currency{" +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
