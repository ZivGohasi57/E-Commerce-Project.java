package ZivGohasi_OranTausi_LibiSpivak;

import java.util.Arrays;

public class ProductsList {
    private Product[] products;
    private int sizeList;





    public ProductsList() {
        products = new Product[1];
        sizeList = 0;
    }




    public Product[] getProducts() {
        return products;
    }

    public int getSizeList() {
        return sizeList;
    }




    public void addItem(Product item) {
        if (sizeList == products.length) {
            products = extendItemsArray(products);
        }
        products[sizeList] = item;
        sizeList++;
    }

    private Product[] extendItemsArray(Product[] items) {
        Product[] newItems = new Product[items.length * 2];
        for (int i = 0; i < sizeList; i++) {
            newItems[i] = items[i];
        }
        return newItems;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < sizeList; i++) {
            totalPrice += products[i].getPrice();
        }
        return totalPrice;
    }




    @Override
    public String toString() {
        return "ProductsList{" +
                "products=" + Arrays.toString(products) +
                ", sizeList=" + sizeList +
                '}';
    }
}
