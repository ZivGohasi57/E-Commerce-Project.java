package ZivGohasi_OranTausi_LibiSpivak;

public class SpecialPackProduct extends Product {
    private boolean specialPackage;


    public SpecialPackProduct(Product other) {
        super(other);
        specialPackage = true;

    }

    public boolean getSpecialPackage() {
        return specialPackage;
    }

    public boolean setSpecialPackage(boolean specialPackage) {
        this.specialPackage = specialPackage;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "   ------- > Special Package";
    }
}
