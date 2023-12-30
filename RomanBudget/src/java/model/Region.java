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
public class Region {
    private int id;
    private String name;
    private Consul consul;
    private double totalBudget;

    public Region() {
    }

    public Region(int id, String name, Consul consul) {
        this.id = id;
        this.name = name;
        this.consul = consul;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Consul getConsul() {
        return consul;
    }

    public void setConsul(Consul consul) {
        this.consul = consul;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(double totalBudget) {
        this.totalBudget = totalBudget;
    }
    

    @Override
    public String toString() {
        return "Region{" + "id=" + id + ", name=" + name + ", consul=" + consul + ", budget=" + totalBudget + '}';
    }    
}
