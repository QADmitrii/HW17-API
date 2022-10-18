package qaguru.pojodata;

public class PojoBodyData {

    private String email;
    private String password;



    public String getEmail() {
        return email;
    }

    public PojoBodyData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public PojoBodyData setPassword(String password) {
        this.password = password;
        return this;
    }
}
