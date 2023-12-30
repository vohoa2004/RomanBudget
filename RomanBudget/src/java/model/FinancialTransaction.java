/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author vothimaihoa
 */
public class FinancialTransaction {
    private int id;
    private int regionId;
    private String transactionType;
    private Double amount;
    private String description;
    private Timestamp transactionDate;

    public FinancialTransaction() {
    }

    public FinancialTransaction(int id, int regionId, String transactionType, 
            Double amount, String description, Timestamp transactionDate) {
        this.id = id;
        this.regionId = regionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public FinancialTransaction(int regionId, String transactionType, 
            Double amount, String description, Timestamp transactionDate) {
        this.regionId = regionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "FinancialTransaction{" + "id=" + id + ", regionId=" + regionId + ", transactionType=" + transactionType + ", amount=" + amount + ", description=" + description + ", transactionDate=" + transactionDate + '}';
    }
}
