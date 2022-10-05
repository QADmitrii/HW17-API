package qaGuru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestData {

    private String email;
    private String password;



    public String getEmail() {
        return email;
    }

    public TestData setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public TestData setPassword(String password) {
        this.password = password;
        return this;
    }
}
