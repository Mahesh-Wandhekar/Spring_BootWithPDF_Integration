package com.nt.Services; // Follow Java naming conventions for package names

import java.io.IOException;

import com.nt.Model.Order;

public interface InvoiceService {

    /**
     * Generates an invoice in PDF format based on the provided Order.
     * 
     * @param order The order for which the invoice should be generated.
     * @return Byte array representing the PDF content of the invoice.
     * @throws IOException If there is an error generating the invoice.
     */
    byte[] generateInvoice(Order order) throws IOException;
}
