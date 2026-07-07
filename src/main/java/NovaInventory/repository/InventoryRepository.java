package com.tejas.novainventory.repository;

import com.tejas.novainventory.model.Item;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class InventoryRepository {

    private ArrayList<Item> itemList = new ArrayList<>();

    public void addItem(Item item) {
        itemList.add(item);
    }

    public ArrayList<Item> getAllItems() {
        return itemList;
    }

    public Item searchItem(String itemId) {

        for (Item item : itemList) {

            if (item.getItemId().equalsIgnoreCase(itemId)) {
                return item;
            }

        }

        return null;
    }

    public boolean deleteItem(String itemId) {

        Item item = searchItem(itemId);

        if (item != null) {
            itemList.remove(item);
            return true;
        }

        return false;
    }

    public void displayItems() {

        if (itemList.isEmpty()) {
            System.out.println("\nNo Items Found.");
            return;
        }

        System.out.println("\n=======================================================================");
        System.out.printf("%-10s %-20s %-15s %-10s%n",
                "ID", "ITEM", "PRICE", "QTY");
        System.out.println("=======================================================================");

        for (Item item : itemList) {

            System.out.printf("%-10s %-20s ₹%-14.2f %-10d%n",
                    item.getItemId(),
                    item.getItemName(),
                    item.getPrice(),
                    item.getQuantity());

        }

        System.out.println("=======================================================================");
    }

    public void saveItemsToCSV() {

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("items.csv"));

            for (Item item : itemList) {

                writer.write(
                        item.getItemId() + "," +
                                item.getItemName() + "," +
                                item.getPrice() + "," +
                                item.getQuantity()
                );

                writer.newLine();
            }

            writer.close();

            System.out.println("Items saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving items.");

        }

    }

}