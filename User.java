package ZivGohasi_OranTausi_LibiSpivak;

public class User {

    private String userName;
    private String password;



    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean setUserName(String userName) {
        this.userName = userName;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }


    @Override
    public String toString() {
        return "Seller userName: "  + userName;
    }
}
