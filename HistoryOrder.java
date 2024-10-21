package ZivGohasi_OranTausi_LibiSpivak;

public class HistoryOrder {
    private Order[] historyOrder;
    private int sizeHistoryOrder;

    public HistoryOrder() {
        historyOrder = new Order[1];
        sizeHistoryOrder = 0;
    }


    ///////////////////////////////////////#######getters#######///////////////////////////////////////


    public Order[] getHistoryOrder() {
        return historyOrder;
    }


    public int getSizeHistoryOrder() {
        return sizeHistoryOrder;
    }



    ///////////////////////////////////////#######Functions#######///////////////////////////////////////


    public void addOrderToHistory(ProductsList shoppingCart) {
        if (sizeHistoryOrder == historyOrder.length) {
            historyOrder = extendHistoryOrdersArray(historyOrder);
        }
        Order order = new Order(shoppingCart);
        historyOrder[sizeHistoryOrder] = order;
        sizeHistoryOrder++;
    }


    private Order[] extendHistoryOrdersArray(Order[] historyOrder) {
        Order[] newHistoryOrder = new Order[historyOrder.length * 2];
        for (int i = 0; i < sizeHistoryOrder; i++) {
            newHistoryOrder[i] = historyOrder[i];
        }
        return newHistoryOrder;
    }

    ///////////////////////////////////////#######toString#######///////////////////////////////////////

    @Override
    public String toString() {
        return "HistoryOrder{" +
                "historyOrder=" + historyOrder +
                ", sizeHistoryOrder=" + sizeHistoryOrder +
                '}';
    }
}


