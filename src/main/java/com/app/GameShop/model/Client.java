package com.app.GameShop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Getter
@Setter

public class Client {
    private UUID id;
    private String login;
    private String password;
    private int Day, Month, Year;

    public Client(UUID id, String login, String password, int day, int month, int year) {
        this.id = id;
        this.login = login;
        this.password = password;
        Day = day;
        Month = month;
        Year = year;
    }


    public Period AgeCalc(){
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(Year, Month, Day);
        return Period.between(birthDate, today);
    }

}