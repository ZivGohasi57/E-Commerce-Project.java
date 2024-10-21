// Ziv Gohasi , id: 211541271 , lecturer: Keren Kalif.
// Oran Tausi id: 206655177 , lecturer: Keren Kalif.
// Libi Spivak  id: 211962485 , lecturer: Keren Kalif.

package ZivGohasi_OranTausi_LibiSpivak;


import java.util.Scanner;



public class Main {
    public static Manager manager = new Manager();


    public static void main(String[] args) {
        System.out.println("Welcome to our shop!");
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (true) {
            try {
                choiceCase(running, scan);
                break;
            } catch (Exception e) {
                System.out.println(Status.SYSTEM_ERROR.getDescription());
            }
        }
    }

    private static void choiceCase(boolean running, Scanner scan) {
        while (running) {
        menu();
        int choice = getInt();
        switch (choice) {
            case 0:
                System.out.println("Exiting the system. Goodbye!");
                running = false;
                break;
            case 1:
                handleAddSeller(scan);
                break;
            case 2:
                handleAddBuyer(scan);
                break;
            case 3:
                handleAddItemToSeller(scan);
                break;
            case 4:
                handleAddItemToBuyer(scan);
                break;
            case 5:
                handlePayChart(scan);
                break;
            case 6:
                handleReviewListOfBuyers();
                break;
            case 7:
                handleReviewListOfSellers();
                break;
            case 8:
                handleReviewListOfProductByCategory(scan);
                break;
            case 9:
                handleNewCartOutOfHistory(scan);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
        scan.close();
    }



    private static void menu() {
        System.out.println("0 - Exit");
        System.out.println("1 - Add Seller");
        System.out.println("2 - Add Buyer");
        System.out.println("3 - Add item to Seller");
        System.out.println("4 - Add item to Buyer");
        System.out.println("5 - Buyer Cart");
        System.out.println("6 - List of the buyers");
        System.out.println("7 - List of the sellers");
        System.out.println("8 - Products List By Category");
        System.out.println("9 - Create new cart from history");
        System.out.println("Enter your choice: ");

    }

    public static void handleAddSeller(Scanner scan) {
        boolean notValidName = true;
        while (notValidName) {
            System.out.println("Enter username: ");
            String userName = getString();
            if (manager.isThere(userName)) {
                System.out.println("Sorry, This username is already taken. Please try again.");
                continue;
            }
            System.out.println("Enter Password: ");
            String password = getString();
            manager.addSeller(userName, password);
            notValidName = false;
            System.out.println(userName + " has successfully added to the sellers list.");
        }
    }

    public static void handleAddBuyer(Scanner scan) {
        boolean notValidName = true;
        while (notValidName) {

            System.out.println("Enter username: ");
            String userName = getString();
            if (manager.isThere(userName)) {
                System.out.println("Sorry, This username is already taken. Please try again.");
                continue;
            }
            System.out.println("Enter Password: ");
            String password = getString();
            System.out.println("Enter your country: ");
            String state = getString();
            System.out.println("Enter your city: ");
            String city = getString();
            System.out.println("Enter your street: ");
            String street = getString();
            System.out.println("Enter your building number: ");
            int building = getInt();
            manager.addBuyer(userName, password);
            manager.makeAddressToBuyer(state, city, street, building);
            notValidName = false;
            System.out.println(userName + " has successfully added to the buyers list.");
        }
    }


    public static void handleAddItemToSeller(Scanner scan) {
        if (manager.tryCatchIndexSeller(0).equals(Status.EMPTY_SELLERS_LIST)) {
            System.out.println(manager.tryCatchIndexSeller(0).getDescription());
            return;
        }
        System.out.println("The lists of Sellers.");
        StringBuffer indexList = manager.makeSellersList();
        System.out.println(indexList.toString());
        System.out.println("Enter your choice: ");
        int indexSeller = getIndexSeller();
        System.out.println("Enter Product Name: ");
        String productName = getString();
        System.out.println("Enter Price: ");
        double price = getDouble();
        System.out.println("Enter Product Category: ");
        Product.CATEGORY.printCategories();
        Product.CATEGORY chosenCategory = getCategory();
        System.out.println("Would you like this product in special packaging in extra price (press 1 or 2) ?");
        System.out.println(manager.yesNoList());
        int yesNoIndex  = getIndexYesNo();
        boolean spacialPack = manager.yesNoMeaning(yesNoIndex);
        double specialPackPrice = 0;
        if (spacialPack) {
            System.out.println("How much for the special packaging?: ");
            specialPackPrice = getDouble();
        }
        manager.addItemToSeller(productName, price + specialPackPrice , indexSeller - 1, chosenCategory, spacialPack);
        System.out.println("The product " + productName + " has successfully added to the " + manager.receiveSellerNameByIndex(indexSeller - 1) + "'s products list.");
    }




    private static void handleAddItemToBuyer(Scanner scan) {
        if (manager.tryCatchIndexBuyer(0).equals(Status.EMPTY_BUYERS_LIST)) {
            System.out.println(manager.tryCatchIndexBuyer(0).getDescription());
            return;
        }
        if (manager.tryCatchIndexSeller(0).equals(Status.EMPTY_SELLERS_LIST)) {
            System.out.println(manager.tryCatchIndexSeller(0).getDescription());
            return;
        }
        System.out.println("The lists of Buyers: ");
        System.out.println(manager.makeBuyersList());
        System.out.println("Enter your choice: ");
        int indexBuyer = getIndexBuyer();
        System.out.println("The lists of Sellers: ");
        System.out.println(manager.makeSellersList());
        System.out.println("Enter your choice: ");
        int indexSeller = getIndexSeller();
        if (manager.tryCatchIndexItem(indexSeller - 1,0).equals(Status.EMPTY_ITEMS_LIST)) {
            System.out.println(Status.EMPTY_ITEMS_LIST.getDescription());
            return;
        }
        System.out.println("The lists of Products: ");
        System.out.println(manager.makeItemsList(indexSeller - 1));
        System.out.println("Enter your choice: ");
        int indexItem = getIndexItem(indexSeller - 1);
        manager.addProductToBuyerCart(indexBuyer - 1, indexSeller - 1, indexItem -1);
        System.out.println("You Chose to add to " + manager.receiveBuyerNameByIndex(indexBuyer - 1) + "'s cart: \n" + manager.receiveProductByIndex(indexBuyer - 1));

    }



    private static void handlePayChart(Scanner scan) {
        if (manager.tryCatchIndexBuyer(0).equals(Status.EMPTY_BUYERS_LIST)) {
            System.out.println(Status.EMPTY_BUYERS_LIST.getDescription());
            return;
        }

        System.out.println("Select the buyer you want to pay for: ");
        StringBuffer indexList = manager.makeBuyersList();
        System.out.println(indexList.toString());
        System.out.println("Enter your choice: ");
        int indexBuyer = getIndexBuyer();
        System.out.println(manager.makeProductList(indexBuyer - 1));
        if (manager.tryCatchIndexProduct(indexBuyer - 1, 0).equals(Status.EMPTY_PRODUCT_LIST)) {
            System.out.println(Status.EMPTY_PRODUCT_LIST.getDescription());
            return;
        }
        double sum = manager.totalPrice(indexBuyer - 1);
        System.out.println("The total price of the cart is " + sum + "  NIS.");
        manager.creatOrder(indexBuyer - 1);
    }



    private static void handleReviewListOfBuyers() {
        if (manager.tryCatchIndexBuyer(0).equals(Status.EMPTY_BUYERS_LIST)) {
            System.out.println(Status.EMPTY_BUYERS_LIST.getDescription());
            return;
        }
        System.out.println(manager.BuyersData());
    }


    private static void handleReviewListOfSellers() {
        if (manager.tryCatchIndexSeller(0).equals(Status.EMPTY_SELLERS_LIST)) {
            System.out.println(Status.EMPTY_SELLERS_LIST.getDescription());
            return;
        }
        System.out.println(manager.SellersData());
    }


    private static void handleReviewListOfProductByCategory(Scanner scan) {
        Product.CATEGORY.printCategories();
        Product.CATEGORY chosenCategory = getCategory();
        System.out.println(manager.categoryList(chosenCategory));
    }

    private static void handleNewCartOutOfHistory(Scanner scan) {
        System.out.println(manager.makeBuyersList());
        System.out.println("which number Buyer are you?: ");
        int indexBuyer = getIndexBuyer();
        if (!manager.tryCatchIndexProduct(indexBuyer - 1,0).equals(Status.EMPTY_PRODUCT_LIST)) {
            System.out.println("Your cart is not empty, do you want to replace it with a previous cart? (press 1 or 2): ");
            System.out.println(manager.yesNoList());
            int yesNoIndex = getIndexYesNo();
            if (manager.yesNoMeaning(yesNoIndex)) {
                manager.deleteCart(indexBuyer - 1);
            }
            else {
                return;
            }
        }
        if (manager.tryCatchIndexOrder(indexBuyer - 1,0).equals(Status.EMPTY_ORDER_LIST)) {
            System.out.println(Status.EMPTY_ORDER_LIST.getDescription());
            return;
        }
        System.out.println("Which cart from history do you want to use?");
        System.out.println(manager.showHistoryCart(indexBuyer - 1));
        int indexOrder = getIndexOrder(indexBuyer - 1);
        manager.takeCartBackFromHistory(indexBuyer - 1, indexOrder - 1);



    }




    private static int getIndexOrder(int indexBuyer) {
        int indexOrder;
        while (true) {
            indexOrder = getInt();
            Status status = manager.tryCatchIndexOrder(indexBuyer ,indexOrder - 1);
            if (status.equals(Status.SUCCESS)) {
                return indexOrder;
            }
            else {
                System.out.println(status.getDescription());
            }
            
        }
    }

    private static int getIndexYesNo() {
        int index;
        while (true) {
            index = getInt();
            Status status = manager.tryCatchIndexYesNo(index);
            if (status.equals(Status.SUCCESS)) {
                return index;
            }
            else {
                System.out.println(status.getDescription());
            }
        }
    }



    public static String getString() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String string = scan.nextLine();
            if (string.isEmpty()) {
                System.out.println(Status.NO_INPUT.getDescription());
            } else {
                return string;
            }
        }
    }

    public static int getInt() {
        Scanner scan = new Scanner(System.in);
        int num;
        while (true) {
            String input = scan.nextLine();
            Status status = manager.tryCatchInt(input);
            if (status.equals(Status.SUCCESS)) {
                num = Integer.parseInt(input);
                return num;
            } else {
                System.out.println(status.getDescription());
            }
        }
    }

    public static double getDouble() {
        Scanner scan = new Scanner(System.in);
        double num;
        while (true) {
            String input = scan.nextLine();
            Status status = manager.tryCatchDouble(input);
            if (status.equals(Status.SUCCESS)) {
                num = Double.parseDouble(input);
                return num;
            } else {
                System.out.println(status.getDescription());
            }
        }
    }

    public static int getIndexSeller() {
        int index;
        while (true) {
            index = getInt();
            Status status = manager.tryCatchIndexSeller(index - 1);
            if (status.equals(Status.SUCCESS)) {
                return index;
            }
            else {
                System.out.println(status.getDescription());
            }
        }
    }

    public static int getIndexBuyer() {
        int index;
        while (true) {
            index = getInt();
            Status status = manager.tryCatchIndexBuyer(index - 1);
            if (status.equals(Status.SUCCESS)) {
                return index;
            }
            else {
                System.out.println(status.getDescription());
            }
        }

    }




    public static Product.CATEGORY getCategory() {
        Product.CATEGORY chosen;
        while (true) {
            int index = getInt();
            Status status = manager.tryCatchIndexCategory(index - 1);
            if (status.equals(Status.SUCCESS)) {
                chosen = Product.CATEGORY.values()[index - 1];
                return chosen;
            } else {
                System.out.println(status.getDescription());
            }
        }


    }

    public static int getIndexItem(int indexSeller) {
        int index;
        while (true) {
            index = getInt();
            Status status = manager.tryCatchIndexItem(indexSeller, index - 1);
            if (status.equals(Status.SUCCESS)) {
                return index;
            } else {
                System.out.println(status.getDescription());
            }
        }

    }
}








