package com.demo.upimesh.service;

import com.demo.upimesh.model.Transaction;
import com.demo.upimesh.model.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportService {

    @Autowired
    private TransactionRepository transactionRepository;

    public String exportTransactionsAsCsv() {

        List<Transaction> transactions = transactionRepository.findAll();

        StringBuilder csv = new StringBuilder();

        csv.append("ID,Sender,Receiver,Amount,Status,Bridge,Hop Count,Settled At\n");

        for (Transaction t : transactions) {

            csv.append(t.getId()).append(",");
            csv.append(t.getSenderVpa()).append(",");
            csv.append(t.getReceiverVpa()).append(",");
            csv.append(t.getAmount()).append(",");
            csv.append(t.getStatus()).append(",");
            csv.append(t.getBridgeNodeId()).append(",");
            csv.append(t.getHopCount()).append(",");
            csv.append(t.getSettledAt()).append("\n");

        }

        return csv.toString();

    }

}