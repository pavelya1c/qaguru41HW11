import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
        Configuration.headless = false;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        //  Configuration.holdBrowserOpen = false;

    }
}