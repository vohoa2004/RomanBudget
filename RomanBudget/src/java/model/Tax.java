/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author vothimaihoa
 */
public class Tax {
    private int id;
    private int regionId;
    private double taxAmount;
    private double tributeAmount;
    private Date deadline;
    private Date paidDate;
    private int year;
    private TaxCategory category;

    public Tax() {
    }
    
    public Tax(int id, int regionId, double taxAmount, double tributeAmount) {
        this.id = id;
        this.regionId = regionId;
        this.taxAmount = taxAmount;
        this.tributeAmount = tributeAmount;
    }

    public Tax(int regionId, double taxAmount, double tributeAmount, Date deadline) {
        this.regionId = regionId;
        this.taxAmount = taxAmount;
        this.tributeAmount = tributeAmount;
        this.deadline = deadline;
    }

    public Tax(int regionId, double taxAmount, double tributeAmount, Date deadline, Date paidDate, int year, TaxCategory category) {
        this.regionId = regionId;
        this.taxAmount = taxAmount;
        this.tributeAmount = tributeAmount;
        this.deadline = deadline;
        this.paidDate = paidDate;
        this.year = year;
        this.category = category;
    }

    public Tax(int regionId, double taxAmount, double tributeAmount, Date deadline, int year, TaxCategory category) {
        this.regionId = regionId;
        this.taxAmount = taxAmount;
        this.tributeAmount = tributeAmount;
        this.deadline = deadline;
        this.year = year;
        this.category = category;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTributeAmount() {
        return tributeAmount;
    }

    public void setTributeAmount(double tributeAmount) {
        this.tributeAmount = tributeAmount;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public TaxCategory getCategory() {
        return category;
    }

    public void setCategory(TaxCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Tax{" + "id=" + id + ", regionId=" + regionId + ", taxAmount=" + taxAmount + ", tributeAmount=" + tributeAmount + ", deadline=" + deadline + ", paidDate=" + paidDate + ", year=" + year + ", category=" + category + '}';
    }
    
    
    
}
