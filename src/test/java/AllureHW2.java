import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chromium.AddHasLaunchApp;
import pages.AllPages;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static testdata.TestData.*;


public class AllureHW2 extends TestBase {
    AllPages allPages = new AllPages();


    @Test
    @DisplayName("Успешное заполнение формы регимстрации с выполнением проверки заполненных полей")
    public void successfulFormSubmissionWithAllFieldsTestHardForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открытие страницы", () -> {
            allPages.openPage("/automation-practice-form");
        });
        step("Заполнение Имени", () -> {
            allPages.typeUserFirstName(firstName);
        });
        step("Заполнение Фамилии", () -> {
            allPages.typeUserLastName(lastName);
        });
        step("Заполнение Эмаила", () -> {
            allPages.typeUserEmail(userEmail);
        });
        step("Заполнение Мобильного номера", () -> {
            allPages.typeUserNumber(userPhone);
        });
        step("Выбор пола", () -> {
            allPages.typeUserGender(userGender);
        });
        step("Заполнение даты рождения", () -> {
            allPages.setDayOfBirth(dayOfBirth, mounthOfBirth, yearOfBirth);
        });
        step("Выбор увлечений", () -> {
            allPages.typeUserSubject(sendKey);
        });
        step("Выбор хобби", () -> {
            allPages.typeUserHobbies(userHobbies);
        });
        sleep(1000);
        step("Выбор картинки", () -> {
            allPages.typeUserPicture(userPicture);
        });
        step("Заполнение адресса проживания", () -> {
            allPages.typeUserCurrentAddress(userCurrentAdress);
        });
        step("Выбор штата", () -> {
            allPages.typeUserState(userState);
        });
        step("Выбор города", () -> {
            allPages.typeUserCity(userCity);
        });
        allPages.submitButtonClick()
                .checkFormFieldHardForm("Student Name", (firstName + " " + lastName))
                .checkFormFieldHardForm("Student Email", userEmail)
                .checkFormFieldHardForm("Gender", userGender)
                .checkFormFieldHardForm("Mobile", userPhone)
                .checkFormFieldHardForm("Hobbies", userHobbies)
                .checkFormFieldHardForm("Date of Birth", (dayOfBirth + " " + mounthOfBirth + "," + yearOfBirth))
                .checkFormFieldHardForm("Subjects", userSubjects)
                .checkFormFieldHardForm("Picture", userPicture)
                .checkFormFieldHardForm("Address", userCurrentAdress)
                .checkFormFieldHardForm("State and City", ((userState + " " + userCity)));
    }








//            allPages.typeUserEmail(userEmail)
//                .typeUserNumber(userPhone)
//                .typeUserGender(userGender)
//                .setDayOfBirth(dayOfBirth, mounthOfBirth, yearOfBirth)
//                .typeUserSubject(sendKey)
//                .typeUserHobbies(userHobbies)
//                .typeUserPicture(userPicture)
//                .typeUserCurrentAddress(userCurrentAdress)
//                .typeUserState(userState)
//                .typeUserCity(userCity)
//                .submitButtonClick()
//                .checkFormFieldHardForm("Student Name", (firstName + " " + lastName))
//                .checkFormFieldHardForm("Student Email", userEmail)
//                .checkFormFieldHardForm("Gender", userGender)
//                .checkFormFieldHardForm("Mobile", userPhone)
//                .checkFormFieldHardForm("Hobbies", userHobbies)
//                .checkFormFieldHardForm("Date of Birth", (dayOfBirth + " " + mounthOfBirth + "," + yearOfBirth))
//                .checkFormFieldHardForm("Subjects", userSubjects)
//                .checkFormFieldHardForm("Picture", userPicture)
//                .checkFormFieldHardForm("Address", userCurrentAdress)
//                .checkFormFieldHardForm("State and City", ((userState + " " + userCity)));




    @Test
    void successfulFormSubmissionWithAllFieldsTestEasyForm() {

        allPages.openPage("/text-box")
                .typeUserName(firstName + " " + lastName)
                .typeUserEmail(userEmail)
                .typeUserCurrentAddress(userCurrentAdress)
                .typeUserPermanentAddress(userPermanentAdress)
                .submitButtonClick()
                .checkFormFieldEasyForm(firstName + " " + lastName)
                .checkFormFieldEasyForm(userEmail)
                .checkFormFieldEasyForm(userCurrentAdress)
                .checkFormFieldEasyForm(userPermanentAdress);


    }

    @Test
    public void successfulFormSubmissionWithRequiredFieldsTestHardForm() {
        open("/automation-practice-form");
        allPages.typeUserFirstName(firstName)
                .typeUserLastName(lastName)
                .typeUserEmail(userEmail)
                .typeUserNumber(userPhone)
                .typeUserGender(userGender)
                .submitButtonClick()
                .checkFormFieldHardForm("Student Name", (firstName + " " + lastName))
                .checkFormFieldHardForm("Student Email", userEmail)
                .checkFormFieldHardForm("Gender", userGender)
                .checkFormFieldHardForm("Mobile", userPhone);
    }

    @Test
    public void successfulFormSubmissionWithNoTAllFieldsTestEasyForm() {
        allPages.openPage("/text-box")
                .typeUserName(firstName + " " + lastName)
                .typeUserEmail(userEmail)
                .submitButtonClick()
                .checkFormFieldEasyForm(firstName + " " + lastName)
                .checkFormFieldEasyForm(userEmail);


    }

    //НЕГАТИВНЫЕ ТЕСТЫ

    @Test
    public void shouldShowValidationErrorsWhenAllRequiredFieldsAreEmptyTestHardForm() {
        allPages.openPage("/automation-practice-form")
                .typeUserGender(userGender)
                .submitButtonClick()
                .userFormWasValidatedHardForm();
    }

    @Test
    public void shouldShowValidationErrorsWhenFirstNameAreEmptyTestHardForm() {
        allPages.openPage("/automation-practice-form")
                .typeUserLastName(lastName)
                .typeUserEmail(userEmail)
                .typeUserNumber(userPhone)
                .typeUserGender(userGender)
                .submitButtonClick()
                .userFormWasValidatedHardForm();
    }

    @Test
    public void shouldNotDisplayResultTableWhenFormSubmissionIsInvalidTestHardForm() {
        allPages.openPage("/automation-practice-form")
                .typeUserLastName(lastName)
                .typeUserEmail(userEmail)
                .typeUserNumber(userPhone)
                .typeUserGender(userGender)
                .submitButtonClick()
                .userFormWasNotValidatedHardForm();
    }

    @Test
    public void shouldShowValidationErrorWhenInvalidEmailIsEnteredTestEasyForm() {
        allPages.openPage("/text-box")
                .typeUserEmail(userEmailNotValid)
                .submitButtonClick()
                .userFormWasValidatedEasyForm();
    }


}
