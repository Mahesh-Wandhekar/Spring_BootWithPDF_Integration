package com.nt.Model.Controller;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Model.Items;
import com.nt.Model.Order;
import com.nt.Services.InvoiceServiceImp;

@RestController
@RequestMapping("/pdf")
public class InvoiceController {

    @Autowired
    private InvoiceServiceImp invoiceServiceImp;

    @GetMapping(value = "/get/{orderId}", produces = "application/pdf")
    public ResponseEntity<byte[]> getInvoice(@PathVariable("orderId") int orderId) throws IOException {

        // Sample items for the order
        Items i1 = new Items("NoteBook", 10, 45.0);
        Items i2 = new Items("GelPen", 15, 15.0);
        Items i3 = new Items("Book", 10, 100.0);
        Items i4 = new Items("Cover", 5, 20.0);
        Items i5 = new Items("School Bag", 2, 450.50);

        // Create an Order object
        Order order = new Order(orderId, "Mahesh S W", "maheshwad123@gmail.com", Arrays.asList(i1, i2, i3, i4, i5));

        // Generate invoice bytes
        byte[] bytes = invoiceServiceImp.generateInvoice(order);

        // Set headers for response
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        // Return ResponseEntity with PDF bytes and headers
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

}
