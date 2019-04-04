package com.uom.cps3225;

import com.uom.cps3225.choices.CoffeeMachineChoices;
import com.uom.cps3225.states.CoffeeMachineStates;
import junit.framework.Assert;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.GraphListener;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import nz.ac.waikato.modeljunit.timing.Time;
import nz.ac.waikato.modeljunit.timing.TimedFsmModel;
import nz.ac.waikato.modeljunit.timing.TimedModel;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CoffeeMachineModel implements TimedFsmModel{
    private static final int PROBABILITY_TOTAL = 100;
    private static final int PROBABILITY_TEA = 40;
    private static final int PROBABILITY_COFFEE = 40;
    private static final int PROBABILITY_ADD_FIFTY = 20;

    private CoffeeMachineSystem coffeeMachineSystem = new CoffeeMachineSystem();

    private CoffeeMachineStates coffeeMachineStates = CoffeeMachineStates.NOT_ENOUGH_MONEY;
    private double currentBalance;
    private int lastServiceTime;

    @Time
    public int now;

    @Override
    public int getNextTimeIncrement(Random random) {
        return 1;
    }

    @Override
    public CoffeeMachineStates getState() {
        return coffeeMachineStates;
    }

    @Override
    public void reset(boolean b) {
        if(b) {
            coffeeMachineSystem = new CoffeeMachineSystem();
        }
        coffeeMachineStates = CoffeeMachineStates.NOT_ENOUGH_MONEY;
        currentBalance = 0;
        lastServiceTime = 0;
        now = 0;
    }

    public boolean addEuroGuard() {return getState().equals(CoffeeMachineStates.NOT_ENOUGH_MONEY);}
    public @Action void addEuro() {
        currentBalance += 1;
        coffeeMachineStates = CoffeeMachineStates.ONE_EURO;

        coffeeMachineSystem.addEuro();

        Assert.assertEquals("Current balance is not equal", coffeeMachineSystem.getCurrentBalance(), currentBalance);
    }

    public boolean confirmHotChocGuard() {return getState().equals(CoffeeMachineStates.ONE_EURO_FIFTY) && now - lastServiceTime > 10;}
    public @Action void confirmHotChoc() {
        currentBalance -= 1.5;
        coffeeMachineStates = CoffeeMachineStates.NOT_ENOUGH_MONEY;
        lastServiceTime = now;

        coffeeMachineSystem.dispenseHotChocolate();

        Assert.assertEquals("Current balance is not equal", coffeeMachineSystem.getCurrentBalance(), currentBalance);
        Assert.assertEquals("Did not dispense hot choc!", coffeeMachineSystem.getLastItemDispense(), CoffeeMachineChoices.HOT_CHOCOLATE);
    }

    public boolean confirmTeaGuard() {return getState().equals(CoffeeMachineStates.ONE_EURO) && now - lastServiceTime > 10 && ThreadLocalRandom.current().nextInt(PROBABILITY_TOTAL) < PROBABILITY_TEA;}
    public @Action void confirmTea() {
        currentBalance -= 1;
        coffeeMachineStates = CoffeeMachineStates.NOT_ENOUGH_MONEY;
        lastServiceTime = now;

        coffeeMachineSystem.dispenseTea();

        Assert.assertEquals("Current balance is not equal", coffeeMachineSystem.getCurrentBalance(), currentBalance);
        Assert.assertEquals("Did not dispense tea!", coffeeMachineSystem.getLastItemDispense(), CoffeeMachineChoices.TEA);
    }

    public boolean confirmCoffeeGuard() {return getState().equals(CoffeeMachineStates.ONE_EURO) && now - lastServiceTime > 10 && ThreadLocalRandom.current().nextInt(PROBABILITY_TOTAL) < PROBABILITY_COFFEE;}
    public @Action void confirmCoffee() {
        currentBalance -= 1;
        coffeeMachineStates = CoffeeMachineStates.NOT_ENOUGH_MONEY;
        lastServiceTime = now;

        coffeeMachineSystem.dispenseCoffee();

        Assert.assertEquals("Current balance is not equal", coffeeMachineSystem.getCurrentBalance(), currentBalance);
        Assert.assertEquals("Did not dispense coffee!", coffeeMachineSystem.getLastItemDispense(), CoffeeMachineChoices.COFFEE);
    }

    public boolean addFiftyGuard() {return getState().equals(CoffeeMachineStates.ONE_EURO) && ThreadLocalRandom.current().nextInt(PROBABILITY_TOTAL) < PROBABILITY_ADD_FIFTY;}
    public @Action void addFifty() {
        currentBalance += 0.5;
        coffeeMachineStates = CoffeeMachineStates.ONE_EURO_FIFTY;

        coffeeMachineSystem.addFifty();

        Assert.assertEquals("Current balance is not equal", coffeeMachineSystem.getCurrentBalance(), currentBalance);
    }

    public @Action void unguardedAction() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep((getNextTimeIncrement(new Random())*100)+30);
    }

    @Test
    public void main() throws FileNotFoundException {
        final TimedModel timedModel = new TimedModel(new CoffeeMachineModel());
        timedModel.setTimeoutProbability(0.5);

        final GreedyTester tester = new GreedyTester(timedModel);
        tester.setRandom(new Random());
        tester.setResetProbability(0.001);
        final GraphListener graphListener = tester.buildGraph();
        graphListener.printGraphDot("/home/niki/Desktop/output.dot");
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new TransitionCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(100);
        tester.printCoverage();
    }
}
