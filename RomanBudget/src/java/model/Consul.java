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
public class Consul{
    private int id;
    private String name;
    private int age;
    private String address;
    private int termCount;
    private double salary;
    private boolean nobleStatus;

    public Consul() {
    }

    public Consul(int id, String name, int age, String address, int termCount, double salary, boolean nobleStatus) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.termCount = termCount;
        this.salary = salary;
        this.nobleStatus = nobleStatus;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTermCount() {
        return termCount;
    }

    public void setTermCount(int termCount) {
        this.termCount = termCount;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isNobleStatus() {
        return nobleStatus;
    }

    public void setNobleStatus(boolean nobleStatus) {
        this.nobleStatus = nobleStatus;
    }

    @Override
    public String toString() {
        return "Consul{" + "id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + ", termCount=" + termCount + ", salary=" + salary + ", nobleStatus=" + nobleStatus + '}';
    }
    
}
