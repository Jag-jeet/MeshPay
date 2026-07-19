package com.demo.upimesh.service;

import com.demo.upimesh.dto.DashboardStats;
import com.demo.upimesh.model.Transaction;
import com.demo.upimesh.model.TransactionRepository;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfReportService {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private TransactionRepository transactionRepository;

    public byte[] generateReport() throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);

        document.open();

        Font titleFont = new Font(Font.HELVETICA,22,Font.BOLD,Color.BLUE);
        Font heading = new Font(Font.HELVETICA,14,Font.BOLD);
        Font normal = new Font(Font.HELVETICA,11);

        Paragraph title = new Paragraph("MeshPay Transaction Report",titleFont);
        title.setAlignment(Element.ALIGN_CENTER);

        document.add(title);
        document.add(new Paragraph(" "));

        document.add(new Paragraph(
                "Generated On : "
                        + LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                normal));

        document.add(new Paragraph(" "));

        DashboardStats stats = dashboardService.getDashboardStats();

        document.add(new Paragraph("SUMMARY",heading));

        document.add(new Paragraph(
                "Total Transactions : " + stats.getTotalTransactions(),normal));

        document.add(new Paragraph(
                "Total Amount : ₹" + stats.getTotalAmount(),normal));

        document.add(new Paragraph(
                "Average Transaction : ₹" + stats.getAverageTransaction(),normal));

        document.add(new Paragraph(
                "Successful Transactions : "
                        + stats.getSuccessfulTransactions(),normal));

        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);

        table.setWidthPercentage(100);

        table.addCell("ID");
        table.addCell("Sender");
        table.addCell("Receiver");
        table.addCell("Amount");
        table.addCell("Status");

        List<Transaction> transactions = transactionRepository.findAll();

        for(Transaction t : transactions){

            table.addCell(String.valueOf(t.getId()));
            table.addCell(t.getSenderVpa());
            table.addCell(t.getReceiverVpa());
            table.addCell("₹"+t.getAmount());
            table.addCell(t.getStatus().toString());

        }

        document.add(table);

        document.close();

        return out.toByteArray();

    }

}