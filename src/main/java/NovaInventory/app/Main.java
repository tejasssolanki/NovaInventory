package NovaInventory.app;

import com.tejas.novainventory.model.Item;
import com.tejas.novainventory.repository.InventoryRepository;
import com.tejas.novainventory.service.BillingService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryRepository repository = new InventoryRepository();
        BillingService billingService = new BillingService();

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println("      NOVA INVENTORY SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Search Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Generate Bill");
            System.out.println("6. Save Items to CSV");
            System.out.println("7. Exit");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Item ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();
                    sc.nextLine();

                    Item item = new Item(id, name, price, quantity);

                    repository.addItem(item);

                    System.out.println("\nItem Added Successfully!");
                    break;

                case 2:

                    repository.displayItems();
                    break;

                case 3:

                    System.out.print("Enter Item ID: ");
                    String searchId = sc.nextLine();

                    Item found = repository.searchItem(searchId);

                    if (found != null) {

                        System.out.println("\nItem Found");
                        System.out.println(found);

                    } else {

                        System.out.println("Item Not Found.");

                    }

                    break;

                case 4:

                    System.out.print("Enter Item ID: ");
                    String deleteId = sc.nextLine();

                    if (repository.deleteItem(deleteId)) {

                        System.out.println("Item Deleted Successfully.");

                    } else {

                        System.out.println("Item Not Found.");

                    }

                    break;

                case 5:

                    System.out.print("Enter Item ID: ");
                    String billId = sc.nextLine();

                    Item billItem = repository.searchItem(billId);

                    if (billItem != null) {

                        System.out.print("Enter Quantity to Purchase: ");
                        int qty = sc.nextInt();
                        sc.nextLine();

                        billingService.generateBill(billItem, qty);

                    } else {

                        System.out.println("Item Not Found.");

                    }

                    break;

                case 6:

                    repository.saveItemsToCSV();
                    break;

                case 7:

                    System.out.println("Thank you for using Nova Inventory.");
                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}