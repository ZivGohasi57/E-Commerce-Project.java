package ZivGohasi_OranTausi_LibiSpivak;

public enum Status {
    SUCCESS(""),
    INVALID_INTEGER("Invalid Integer. Please try again: "),
    INVALID_DOUBLE("Invalid Double. Please try again: "),
    NO_INPUT("Please Enter an input. Please try again: "),
    INVALID_RANGE("Invalid index. Please try again: "),
    EMPTY_BUYERS_LIST("There are no buyers yet. Please add buyer first"),
    EMPTY_SELLERS_LIST("There are no sellers yet. Please add seller first"),
    EMPTY_PRODUCT_LIST("There are no product in this buyer bag yet."),
    EMPTY_ITEMS_LIST("There are no items in this seller bag yet."),
    EMPTY_ORDER_LIST("There are no history orders yet."),
    SYSTEM_ERROR("System error. Please try again: "),
    NULL_POINTER("Null Pointer. Please try again: ");




    private final String description;


    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
