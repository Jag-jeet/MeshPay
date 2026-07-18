package com.demo.upimesh.dto;

import java.math.BigDecimal;

public class DashboardStats {

    private long totalTransactions;
    private BigDecimal totalAmount;
    private long totalAccounts;
    private long successfulTransactions;
    private BigDecimal averageTransaction;

    public DashboardStats() {}

    public DashboardStats(long totalTransactions,
                          BigDecimal totalAmount,
                          long totalAccounts,
                          long successfulTransactions,
                          BigDecimal averageTransaction) {
        this.totalTransactions = totalTransactions;
        this.totalAmount = totalAmount;
        this.totalAccounts = totalAccounts;
        this.successfulTransactions = successfulTransactions;
        this.averageTransaction = averageTransaction;
    }

    public long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(long totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public long getSuccessfulTransactions() {
        return successfulTransactions;
    }

    public void setSuccessfulTransactions(long successfulTransactions) {
        this.successfulTransactions = successfulTransactions;
    }

    public BigDecimal getAverageTransaction() {
        return averageTransaction;
    }

    public void setAverageTransaction(BigDecimal averageTransaction) {
        this.averageTransaction = averageTransaction;
    }
}