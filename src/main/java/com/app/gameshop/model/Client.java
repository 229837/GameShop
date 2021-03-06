package com.app.gameshop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter

public class Client {
    private UUID id;
    private String login;
    private String password;
    private int Day, Month, Year;
    private Basket basket;
    private Account account;

    public Client(UUID id, String login, String password, int day, int month, int year) {
        this.id = id;
        this.login = login;
        this.password = password;
        Day = day;
        Month = month;
        Year = year;
        this.basket = new Basket();
        this.account = new Account();
    }

    public Period AgeCalc(){
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(Year, Month, Day);
        return Period.between(birthDate, today);
    }

    public void BuyGames(){
        if(getAccount().getWallet().getBalance() - getBasket().calculateBasketPrice() < 0){
            return;
        }
        if(getAccount().getWallet().getBalance() - getBasket().calculateBasketPrice() >= 0){
            getAccount().getWallet().setBalance(getAccount().getWallet().getBalance() - getBasket().calculateBasketPrice());
            for (Product product : getBasket().getProductList().getAll()) {
                getAccount().getMyGames().add(product);
            }
            for (Product product : getAccount().getMyGames().getAll()){
                getBasket().getProductList().remove(product);
            }
        }
    }
}
