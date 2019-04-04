package com.uom.cps3225;

import com.uom.cps3225.choices.CoffeeMachineChoices;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.SECONDS;

public class CoffeeMachineSystem {
    private double currentBalance = 0;
    private CoffeeMachineChoices lastItemDispense = null;
    private LocalDateTime timeOfLastDispense = LocalDateTime.MIN;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public CoffeeMachineChoices getLastItemDispense() {
        return lastItemDispense;
    }

    void addEuro() {
        currentBalance += 1;
    }

    void addFifty() {
        currentBalance += 0.5;
    }

    void dispenseTea() {
        if(SECONDS.between(timeOfLastDispense, LocalDateTime.now()) < 1) {
            System.out.println("Too fast!" + SECONDS.between(timeOfLastDispense, LocalDateTime.now()));
        } else if (currentBalance < 1){
            System.out.println("Not enough money");
        } else {
            lastItemDispense = CoffeeMachineChoices.TEA;
            currentBalance -= 1;
            timeOfLastDispense = LocalDateTime.now();
        }
    }

    void dispenseCoffee() {
        if(SECONDS.between(timeOfLastDispense, LocalDateTime.now()) < 1) {
            System.out.println("Too fast!" + SECONDS.between(timeOfLastDispense, LocalDateTime.now()));
        } else if (currentBalance < 1){
            System.out.println("Not enough money");
        } else {
            lastItemDispense = CoffeeMachineChoices.COFFEE;
            currentBalance -= 1;
            timeOfLastDispense = LocalDateTime.now();
        }
    }

    void dispenseHotChocolate() {
        if(SECONDS.between(timeOfLastDispense, LocalDateTime.now()) < 1) {
            System.out.println("Too fast!" + SECONDS.between(timeOfLastDispense, LocalDateTime.now()));
        } else if (currentBalance < 1.5){
            System.out.println("Not enough money");
        } else {
            lastItemDispense = CoffeeMachineChoices.HOT_CHOCOLATE;
            currentBalance -= 1.5;
            timeOfLastDispense = LocalDateTime.now();
        }
    }
}
