

package ZivGohasi_OranTausi_LibiSpivak;


public class Seller extends User implements Comparable<Seller> {
    private ProductsList items;




    public Seller(String userName, String password) {
        super(userName, password);
        this.items = new ProductsList();
    }

    public ProductsList getItems() {
        return items;
    }

    public boolean setItems(ProductsList items) {
        this.items = items;
        return true;
    }

    public int compareTo(Seller seller) {
        return Integer.compare(seller.getItems().getSizeList(),this.getItems().getSizeList());
    }

    @Override
    public String toString() {
        return "Seller userName: "  + getUserName();
    }
}