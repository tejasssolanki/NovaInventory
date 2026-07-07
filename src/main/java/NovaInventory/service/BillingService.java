package com.tejas.novainventory.service;

import com.tejas.novainventory.model.Item;

public class BillingService {

    private static final double GST = 0.18;

    public void generateBill(Item item, int purchaseQuantity) {

        if (purchaseQuantity > item.getQuantity()) {
            System.out.println("Insufficient Stock!");
            return;
        }

        double total = item.getPrice() * purchaseQuantity;
        double gstAmount = total * GST;
        double grandTotal = total + gstAmount;

        System.out.println("\n=====================================");
        System.out.println("            CUSTOMER BILL");
        System.out.println("=====================================");
        System.out.println("Item ID      : " + item.getItemId());
        System.out.println("Item Name    : " + item.getItemName());
        System.out.println("Price        : ₹" + item.getPrice());
        System.out.println("Quantity     : " + purchaseQuantity);
        System.out.println("-------------------------------------");
        System.out.printf("Total        : ₹%.2f%n", total);
        System.out.printf("GST (18%%)    : ₹%.2f%n", gstAmount);
        System.out.println("-------------------------------------");
        System.out.printf("Grand Total  : ₹%.2f%n", grandTotal);
        System.out.println("=====================================");

        item.setQuantity(item.getQuantity() - purchaseQuantity);
    }
}