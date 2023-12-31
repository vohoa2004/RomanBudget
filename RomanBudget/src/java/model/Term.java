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
public class Term {
    private Region region;
    private Consul consul;
    private int startYear;
    private boolean status;

    public Term(Region region, Consul consul, int stratYear, boolean status) {
        this.region = region;
        this.consul = consul;
        this.startYear = stratYear;
        this.status = status;
    }

    public Term() {
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Consul getConsul() {
        return consul;
    }

    public void setConsul(Consul consul) {
        this.consul = consul;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Term{" + "region=" + region + ", consul=" + consul + ", startYear=" + startYear + ", status=" + status + '}';
    }
    
   
}
