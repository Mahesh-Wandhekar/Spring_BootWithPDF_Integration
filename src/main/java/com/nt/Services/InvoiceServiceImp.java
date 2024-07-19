package com.nt.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import com.nt.Model.Items;
import com.nt.Model.Order;

@Service
public class InvoiceServiceImp implements InvoiceService {

    @Override
    public byte[] generateInvoice(Order order) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Add the header
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 750);
                contentStream.showText("Invoice");
                contentStream.endText();

                // Customer details
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 720);
                contentStream.showText("Customer Name: " + order.getName());
                contentStream.endText();
                
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(300, 720);
                contentStream.showText("Email : " + order.getEmail());
                contentStream.endText();

                // Add table headers
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 680);
                contentStream.showText("Item");
                contentStream.newLineAtOffset(200, 0);
                contentStream.showText("Quantity");
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText("Unit Price");
                contentStream.endText();

                // Add the items
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                int yOffset = 660;
                for (Items item : order.getItems()) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, yOffset);
                    contentStream.showText(item.getItemName());
                    contentStream.newLineAtOffset(200, 0);
                    contentStream.showText(String.valueOf(item.getQuantity()));
                    contentStream.newLineAtOffset(100, 0);
                    contentStream.showText("RS " + item.getUnitPrice());
                    contentStream.endText();
                    yOffset -= 20;
                }

                // Calculate and add total amount
                double totalAmount = calculateTotal(order.getItems());
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yOffset - 20);
                contentStream.showText("Total Amount: RS " + totalAmount);
                contentStream.endText();
            }

            document.save(baos);
        }

        return baos.toByteArray();
    }

    private double calculateTotal(List<Items> items) {
        double totalAmount = 0;
        for (Items item : items) {
            totalAmount += item.getQuantity() * item.getUnitPrice();
        }
        return totalAmount;
    }
}
