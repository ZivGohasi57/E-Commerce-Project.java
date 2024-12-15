
package ZivGohasi_OranTausi_LibiSpivak;


import java.util.Arrays;

public class Manager {
    private Buyer[] buyers = new Buyer[1];
    private Seller[] sellers = new Seller[1];
    private int sizeBuyers;
    private int sizeSellers;


    public Buyer[] getBuyers() {
        return buyers;
    }

    public Seller[] getSellers() {
        return sellers;
    }

    public int getSizeBuyers() {
        return sizeBuyers;
    }

    public int getSizeSellers() {
        return sizeSellers;
    }


    public void addSeller(String userName, String password) {
        Seller seller = new Seller(userName, password);
        if (sizeSellers == sellers.length) {
            sellers = extendSellersArray(sellers);
        }
        sellers[sizeSellers] = seller;
        sizeSellers++;
    }


    private Seller[] extendSellersArray(Seller[] sellers) {
        Seller[] newSellers = new Seller[sellers.length * 2];
        for (int i = 0; i < sizeSellers; i++) {
            newSellers[i] = sellers[i];
        }
        return newSellers;
    }


    public void addBuyer(String userName, String password) {
        Buyer buyer = new Buyer(userName, password);
        if (sizeBuyers == buyers.length) {
            buyers = extendBuyersArray(buyers);
        }
        buyers[sizeBuyers] = buyer;
        sizeBuyers++;
    }

    private Buyer[] extendBuyersArray(Buyer[] buyers) {
        Buyer[] newBuyers = new Buyer[buyers.length * 2];
        for (int i = 0; i < sizeBuyers; i++) {
            newBuyers[i] = buyers[i];
        }
        return newBuyers;
    }


    public void makeAddressToBuyer(String state, String city, String street, int building) {
        Address address = new Address(state, city, street, building);
        buyers[sizeBuyers - 1].setAddress(address);
    }


    public StringBuffer makeSellersList() {
        Seller[] sortedSellers = Arrays.copyOf(sellers, sizeSellers);
        Arrays.sort(sortedSellers);
        sellers = sortedSellers;
        StringBuffer sellerIndexList = new StringBuffer();
        for (int i = 0; i < sizeSellers; i++) {
            sellerIndexList.append((i + 1) + ") " + sellers[i].getUserName() + "\n");
        }
        return sellerIndexList;
    }


    public void addItemToSeller(String productName, double price, int indexSeller, Product.CATEGORY category, boolean specialPack) {
        Product item = new Product(productName, price, category);
        if (specialPack) {
            SpecialPackProduct sp = new SpecialPackProduct(item);
            sellers[(indexSeller)].getItems().addItem(sp);
            return;
        }
        sellers[(indexSeller)].getItems().addItem(item);
    }


    public StringBuffer makeBuyersList() {
        Buyer[] sortedBuyers = Arrays.copyOf(buyers, sizeBuyers);
        Arrays.sort(sortedBuyers);
        buyers = sortedBuyers;
        StringBuffer buyerIndexList = new StringBuffer();
        for (int i = 0; i < sizeBuyers; i++) {
            buyerIndexList.append(i + 1 + ") " + buyers[i].getUserName() + "\n");
        }
        return buyerIndexList;
    }

    public StringBuffer makeItemsList(int indexSeller) {
        StringBuffer itemsIndexList = new StringBuffer();
        for (int i = 0; i < sellers[indexSeller].getItems().getSizeList(); i++) {
            itemsIndexList.append(i + 1 + ") " + sellers[indexSeller].getItems().getProducts()[i] + "\n");
        }
        return itemsIndexList;
    }

    public void addProductToBuyerCart(int buyerIndex, int sellerIndex, int productIndex) {
        buyers[buyerIndex].addProductToCart(sellers[sellerIndex].getItems().getProducts()[productIndex]);

    }


    public StringBuffer makeProductList(int indexBuyer) {
        StringBuffer productIndexList = new StringBuffer();
        for (int i = 0; i < buyers[indexBuyer].getCurrentCart().getSizeList(); i++) {
            productIndexList.append(i + 1 + ") " + buyers[indexBuyer].getCurrentCart().getProducts()[i] + "\n");
        }
        return productIndexList;
    }


    public double totalPrice(int buyerIndex) {
        return buyers[buyerIndex].getCurrentCart().calculateTotalPrice();
    }

    public void creatOrder(int buyerIndex) {
        buyers[buyerIndex].getBuyerHistoryOrder().addOrderToHistory(buyers[buyerIndex].getCurrentCart());
        buyers[buyerIndex].clearShoppingCart();
    }

    public void deleteCart(int buyerIndex) {
        buyers[buyerIndex].clearShoppingCart();
    }


    public StringBuffer BuyersData() {
        StringBuffer buyerList = new StringBuffer();
        Buyer[] sortedBuyers = Arrays.copyOf(buyers, sizeBuyers);
        Arrays.sort(sortedBuyers);
        buyers = sortedBuyers;
        for (int i = 0; i < sizeBuyers; i++) {
            buyerList.append("-------------------------------------------------------\n");
            buyerList.append(i + 1 + ") " + buyers[i].toString() + "\n");
            buyerList.append(buyers[i].getAddress());
            buyerList.append("\n  * Current cart: \n");
            for (int j = 0; j < buyers[i].getCurrentCart().getSizeList(); j++) {
                buyerList.append("    " + (j + 1) + ") " + buyers[i].getCurrentCart().getProducts()[j].toString() + "\n");
            }
            buyerList.append("\n  * History Orders: \n");
            for (int j = 0; j < buyers[i].getBuyerHistoryOrder().getSizeHistoryOrder(); j++) {
                buyerList.append(("    ") + (j + 1) + ") " + buyers[i].getBuyerHistoryOrder().getHistoryOrder()[j].toString() + "\n");
                buyerList.append("        The products of that order:\n");
                for (int k = 0; k < buyers[i].getBuyerHistoryOrder().getHistoryOrder()[j].getShoppingCart().getSizeList(); k++) {
                    buyerList.append("        " + (k + 1) + ") " + buyers[i].getBuyerHistoryOrder().getHistoryOrder()[j].getShoppingCart().getProducts()[k].toString() + "\n");
                }
            }
        }
        buyerList.append("-------------------------------------------------------");

        return buyerList;
    }


    public StringBuffer SellersData() {
        Seller[] sortedSellers = Arrays.copyOf(sellers, sizeSellers);
        Arrays.sort(sortedSellers);
        sellers = sortedSellers;
        StringBuffer sellerList = new StringBuffer();
        for (int i = 0; i < sizeSellers; i++) {
            sellerList.append("-------------------------------------------------------\n");
            sellerList.append(i + 1 + ") " + sellers[i].toString() + "\n");
            sellerList.append("\n    The items of that seller:\n");
            for (int j = 0; j < sellers[i].getItems().getSizeList(); j++) {
                sellerList.append("    " + (j + 1) + ") " + sellers[i].getItems().getProducts()[j].toString() + "\n");
            }
        }
        sellerList.append("-------------------------------------------------------");
        return sellerList;
    }


    public StringBuffer showHistoryCart(int buyerIndex) {
        Buyer[] sortedBuyers = Arrays.copyOf(buyers, sizeBuyers);
        buyers = sortedBuyers;
        StringBuffer historyList = new StringBuffer();
        for (int i = 0; i < buyers[buyerIndex].getBuyerHistoryOrder().getSizeHistoryOrder(); i++) {
            historyList.append("-------------------------------------------------------\n");
            historyList.append((i + 1) + ") " + buyers[buyerIndex].getBuyerHistoryOrder().getHistoryOrder()[i].toString() + "\n");
            for (int j = 0; j < buyers[buyerIndex].getBuyerHistoryOrder().getHistoryOrder()[i].getShoppingCart().getSizeList(); j++) {
                historyList.append("    " + (j + 1) + ") " + buyers[buyerIndex].getBuyerHistoryOrder().getHistoryOrder()[i].getShoppingCart().getProducts()[j].toString() + " \n");
            }
            historyList.append("-------------------------------------------------------\n");
        }
        return historyList;
    }

    public int countProductsInCategory(Product.CATEGORY category) {
        int count = 0;
        for (int i = 0; i < sizeSellers; i++) {
            for (int j = 0; j < sellers[i].getItems().getSizeList(); j++) {
                if (sellers[i].getItems().getProducts()[j].getCategory().equals(category)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void takeCartBackFromHistory(int indexBuyer, int indexOrder) {
        ProductsList tempProductsList;
        tempProductsList = buyers[indexBuyer].getBuyerHistoryOrder().getHistoryOrder()[indexOrder].getShoppingCart();
        buyers[indexBuyer].setCurrentCart(tempProductsList);

    }


    public boolean isThere(String userName) {
        for (int i = 0; i < sizeBuyers; i++) {
            if (userName.equals(buyers[i].getUserName())) {
                return true;
            }
        }
        for (int i = 0; i < sizeSellers; i++) {
            if (userName.equals(sellers[i].getUserName())) {
                return true;
            }
        }
        return false;
    }

    public StringBuffer yesNoList() {
        StringBuffer yesNoList = new StringBuffer();
        yesNoList.append("1) Yes");
        yesNoList.append("\n2) No");
        return yesNoList;
    }


    public void invalidIndexSeller(int indexSeller) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Seller seller = sellers[indexSeller];
        if (indexSeller >= sizeSellers && indexSeller != 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (seller == null) {
            throw new NullPointerException();
        }
    }

    public Status tryCatchIndexSeller(int index) {
        Status status = Status.SUCCESS;
        try {
            invalidIndexSeller(index);
            return status;
        } catch (NullPointerException e) {
            status = Status.EMPTY_SELLERS_LIST;
            return status;
        } catch (IndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
            return status;
        }
    }


    public void invalidIndexBuyer(int indexBuyer) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Buyer buyer = buyers[indexBuyer];
        if (indexBuyer >= sizeBuyers && indexBuyer != 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (buyer == null)
            throw new NullPointerException();
    }

    public Status tryCatchIndexBuyer(int index) {
        Status status = Status.SUCCESS;
        try {
            invalidIndexBuyer(index);
        } catch (NullPointerException e) {
            status = Status.EMPTY_BUYERS_LIST;
            return status;
        } catch (IndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
        } finally {
            return status;
        }
    }


    public void invalidIndexItem(int indexSeller, int itemIndex) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Product item = sellers[indexSeller].getItems().getProducts()[itemIndex];
        if (itemIndex >= sellers[indexSeller].getItems().getSizeList() && itemIndex != 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (item == null) {
            throw new NullPointerException();
        }
    }

    public Status tryCatchIndexItem(int indexSeller, int indexItem) {
        Status status = Status.SUCCESS;
        try {
            invalidIndexItem(indexSeller, indexItem);
            return status;
        } catch (NullPointerException e) {
            status = Status.EMPTY_ITEMS_LIST;
            return status;
        } catch (IndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
            return status;
        }
    }

    public void invalidIndexProduct(int indexBuyer, int indexProduct) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Product product = buyers[indexBuyer].getCurrentCart().getProducts()[indexProduct];
        if (indexProduct >= buyers[indexBuyer].getCurrentCart().getSizeList() && indexProduct != 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (product == null)
            throw new NullPointerException();
    }

    public Status tryCatchIndexProduct(int indexBuyer, int indexProduct) {
        Status status = Status.SUCCESS;
        try {
            invalidIndexProduct(indexBuyer, indexProduct);
            return status;
        } catch (NullPointerException e) {
            status = Status.EMPTY_PRODUCT_LIST;
            return status;
        } catch (IndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
            return status;
        }
    }


    public void invalidIndexOrder(int indexBuyer, int indexOrder) throws ArrayIndexOutOfBoundsException, NullPointerException {
        Order order = buyers[indexBuyer].getBuyerHistoryOrder().getHistoryOrder()[indexOrder];
        if (indexOrder >= buyers[indexBuyer].getCurrentCart().getSizeList() && indexOrder != 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (order == null) {
            throw new NullPointerException();
        }
    }


    public Status tryCatchIndexOrder(int indexBuyer, int indexOrder) {
        Status status = Status.SUCCESS;
        try {
            invalidIndexOrder(indexBuyer, indexOrder);
            return status;
        } catch (NullPointerException e) {
            status = Status.EMPTY_ORDER_LIST;
            return status;
        } catch (IndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
            return status;
        }
    }


    public boolean yesNoMeaning(int yesNoIndex) throws ArrayIndexOutOfBoundsException {
        if (yesNoIndex == 1) {
            return true;
        } else if (yesNoIndex == 2) {
            return false;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Status tryCatchIndexYesNo(int index) {
        Status status = Status.SUCCESS;
        try {
            yesNoMeaning(index);
            return status;
        } catch (ArrayIndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
            return status;
        }
    }


    public StringBuffer categoryList(Product.CATEGORY category) {
        StringBuffer categoryList = new StringBuffer();
        if (countProductsInCategory(category) == 0) {
            categoryList.append("There are no products in category: " + category);
            return categoryList;
        }
        int index = 0;
        categoryList.append("\n" + "All the products in the category: " + category + "\n");
        for (int i = 0; i < sizeSellers; i++) {
            for (int j = 0; j < sellers[i].getItems().getSizeList(); j++) {
                if (sellers[i].getItems().getProducts()[j].getCategory().equals(category)) {
                    categoryList.append((index + 1) + ") " + sellers[i].getItems().getProducts()[j].toString() + "\n");
                }
            }
        }
        if (categoryList.isEmpty()) {
            categoryList.append("This category is empty");
            return categoryList;
        } else {
            return categoryList;
        }
    }

    public Status tryCatchIndexCategory(int index) {
        Status status = Status.SUCCESS;
        try {
            Product.CATEGORY chosen = Product.CATEGORY.values()[index];
            return status;
        } catch (IndexOutOfBoundsException e) {
            status = Status.INVALID_RANGE;
            return status;
        }
    }


    public Status tryCatchInt(String input) {
        Status status = Status.SUCCESS;
        int num;
        try {
            num = Integer.parseInt(input);
            if (num < 0) {
                status = Status.INVALID_INTEGER;
            }
            return status;
        } catch (NumberFormatException e) {
            status = Status.INVALID_INTEGER;
            if (input.isEmpty()) {
                status = Status.NO_INPUT;
            }
            return status;
        }
    }


    public Status tryCatchDouble(String input) {
        Status status = Status.SUCCESS;
        try {
            double num = Double.parseDouble(input);
            if (num < 0) {
                status = Status.INVALID_DOUBLE;
            }
            return status;
        } catch (NumberFormatException e) {
            status = Status.INVALID_DOUBLE;
            if (input.isEmpty()) {
                status = Status.NO_INPUT;
            }
            return status;
        }
    }

    public String receiveSellerNameByIndex(int sellerIndex) {
        return sellers[sellerIndex].getUserName();
    }

    public String receiveBuyerNameByIndex(int buyerIndex) {
        return buyers[buyerIndex].getUserName();
    }

    public String receiveProductByIndex(int buyerIndex) {
        return buyers[buyerIndex].getCurrentCart().getProducts()[buyers[buyerIndex].getCurrentCart().getSizeList() - 1].toString();
    }

}
 

