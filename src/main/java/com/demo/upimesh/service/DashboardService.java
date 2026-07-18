package com.demo.upimesh.service;

import com.demo.upimesh.dto.DashboardStats;
import com.demo.upimesh.model.AccountRepository;
import com.demo.upimesh.model.Transaction;
import com.demo.upimesh.model.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public DashboardStats getDashboardStats() {

        List<Transaction> transactions = transactionRepository.findAll();

        long totalTransactions = transactions.size();

        BigDecimal totalAmount = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long successfulTransactions = transactions.stream()
                .filter(t -> t.getStatus() == Transaction.Status.SETTLED)
                .count();

        long totalAccounts = accountRepository.count();

        BigDecimal averageTransaction = BigDecimal.ZERO;

        if (totalTransactions > 0) {
            averageTransaction = totalAmount.divide(
                    BigDecimal.valueOf(totalTransactions),
                    2,
                    RoundingMode.HALF_UP
            );
        }

        return new DashboardStats(
                totalTransactions,
                totalAmount,
                totalAccounts,
                successfulTransactions,
                averageTransaction
        );
    }
}