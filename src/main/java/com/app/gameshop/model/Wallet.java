package com.app.gameshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wallet {
    private float balance;

    public Wallet() {
        this.balance = 0;
    }

    public boolean AddMoney(float value) {
        if(value < 0) {
            return false;
        }

        balance += value;

        return true;
    }

    public boolean RemoveMoney(float value) {
        if(value < 0 || value > balance) {
            return false;
        }

        balance -= value;

        return true;
    }
}
