// holds the data of the product, list of products equal shopping cart

package ZivGohasi_OranTausi_LibiSpivak;

public class Product {
    private String productName;
    private double price;
    private static int serialCounter = 1;
    private int serialNumber;
    private CATEGORY category;
    public static enum CATEGORY{
        CHILDREN,
        ELECTRICITY,
        OFFICE,
        CLOTHING;

        public static void printCategories(){
            for (int i = 0; i < CATEGORY.values().length; i++) {
                System.out.println(i+1+") "+ CATEGORY.values()[i]);
            }
        }
    }



    public Product(String productName, double price , CATEGORY category) {
        this.productName = productName;
        this.price = price;
        this.serialNumber = serialCounter++;
        this.category = category;
    }

    public Product(Product other) {
        productName = other.getProductName();
        price = other.getPrice();
        this.serialNumber = other.serialNumber;
        this.category = other.category;

    }



    public String getProductName() {
        return productName;
    }

    public boolean setProductName(String productName) {
        this.productName = productName;
        return true;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        this.price = price;
        return true;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public boolean setCategory(CATEGORY category) {
        this.category = category;
        return true;
    }

    public int getSerialNumber() {
        return serialNumber;
    }



    @Override
    public String toString() {
        return "Product Name: " + productName  + " , Price: " + price + " NIS , " + "Serial Number: " + serialNumber + " , Category: " + category;
    }
}
