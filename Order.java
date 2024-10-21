package ZivGohasi_OranTausi_LibiSpivak;

import java.util.Date;

public class Order {
    private ProductsList shoppingCart;
    private Date date;




    public Order(ProductsList shoppingCart)  {
        this.shoppingCart = shoppingCart;
        this.date = new Date();
    }



    public ProductsList getShoppingCart() {
        return shoppingCart;
    }


    public Date getDate() {
        return date;
    }



    @Override
    public String toString() {
        return "Date of this order: " + date.toString();
    }
}
