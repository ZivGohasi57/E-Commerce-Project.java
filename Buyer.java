

package ZivGohasi_OranTausi_LibiSpivak;


import java.util.Arrays;

public class Buyer extends User implements Comparable<Buyer> {
    private Address address;
    private ProductsList currentCart;
    private HistoryOrder buyerHistoryOrder;


    public Buyer(String userName, String password) {
        super(userName, password);
        this.currentCart = new ProductsList();
        this.buyerHistoryOrder = new HistoryOrder();
    }



    public Address getAddress() {
        return address;
    }

    public boolean setAddress(Address address) {
        this.address = address;
        return true;
    }

    public ProductsList getCurrentCart() {
        return currentCart;
    }

    public boolean setCurrentCart(ProductsList currentCart) {
        this.currentCart = currentCart;
        return true;
    }

    public HistoryOrder getBuyerHistoryOrder() {
        return buyerHistoryOrder;
    }


    public void clearShoppingCart() {
        this.currentCart = new ProductsList();
    }

    public void addProductToCart(Product product) {
        Product p = new Product(product);
        currentCart.addItem(p);
    }


    public int compareTo(Buyer buyer){
     return this.getUserName().compareTo(buyer.getUserName());
    }

    @Override
    public String toString() {
        return "Buyer userName: "  + getUserName();
    }


}
