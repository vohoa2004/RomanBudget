/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vothimaihoa
 */
public class VersailleSituation {
    private Versaille versaille;
    private int quarter;
    private int year;
    private double marketValue;
    private double growthRate;
    private double revoltRate;
    private int foodSupply;

    public VersailleSituation() {
    }

    public VersailleSituation(Versaille versaille, int month, int year, 
            double marketValue, double growthRate, double revoltRate, int foodSupply) {
        this.versaille = versaille;
        this.quarter = month;
        this.year = year;
        this.marketValue = marketValue;
        this.growthRate = growthRate;
        this.revoltRate = revoltRate;
        this.foodSupply = foodSupply;
    }

    public Versaille getVersaille() {
        return versaille;
    }

    public void setVersaille(Versaille versaille) {
        this.versaille = versaille;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    public double getRevoltRate() {
        return revoltRate;
    }

    public void setRevoltRate(double revoltRate) {
        this.revoltRate = revoltRate;
    }

    public int getFoodSupply() {
        return foodSupply;
    }

    public void setFoodSupply(int foodSupply) {
        this.foodSupply = foodSupply;
    }

    @Override
    public String toString() {
        return "VersailleSituation{" + "versaille=" + versaille + ", quarter=" + quarter + ", year=" + year + ", marketValue=" + marketValue + ", growthRate=" + growthRate + ", revoltRate=" + revoltRate + ", foodSupply=" + foodSupply + '}';
    }
    
}
