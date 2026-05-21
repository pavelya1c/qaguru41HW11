import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AllPages;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static testdata.TestData.*;


public class AllureHW2 extends TestBase {

    AllPages allPages = new AllPages();

    @Test
    @DisplayName("Успешное заполнение всех полей формы регистрации с выполнением проверки заполненных полей HardForm")
    public void successfulFormSubmissionWithAllFieldsTestHardForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открытие страницы", () -> {
            allPages.openPage("/automation-practice-form");
        });
        step("Ввод Имени/Фамилии", () -> {
            allPages.typeUserFirstName(firstName);
            allPages.typeUserLastName(lastName);
            Attach.screenshotAs("Screenshot FirstLastName" + " " + firstName + " " + lastName);
        });
        step("Ввод Email/Phone", () -> {
            allPages.typeUserEmail(userEmail);
            allPages.typeUserNumber(userPhone);
        });
        allPages.typeUserGender(userGender);
        allPages.setDayOfBirth(dayOfBirth, mounthOfBirth, yearOfBirth);
        step("Выбор Увлечений/Хобби", () -> {
            allPages.typeUserSubject(sendKey);
            allPages.typeUserHobbies(userHobbies);
        });
        sleep(1000);
        step("Выбор/Загрузка изображения", () -> {
            allPages.typeUserPicture(userPicture);
        });
        step("Ввод адресса проживания", () -> {
            allPages.typeUserCurrentAddress(userCurrentAdress);
            allPages.typeUserState(userState);
            allPages.typeUserCity(userCity);
        });
        allPages.submitButtonClick();
        step("Проверка формы", () -> {
            allPages.checkFormFieldHardForm("Student Name", (firstName + " " + lastName))
                    .checkFormFieldHardForm("Student Email", userEmail)
                    .checkFormFieldHardForm("Gender", userGender)
                    .checkFormFieldHardForm("Mobile", userPhone)
                    .checkFormFieldHardForm("Hobbies", userHobbies)
                    .checkFormFieldHardForm("Date of Birth", (dayOfBirth + " " + mounthOfBirth + "," + yearOfBirth))
                    .checkFormFieldHardForm("Subjects", userSubjects)
                    .checkFormFieldHardForm("Picture", userPicture)
                    .checkFormFieldHardForm("Address", userCurrentAdress)
                    .checkFormFieldHardForm("State and City", ((userState + " " + userCity)));
        });

    }

    @Test
    @DisplayName("Успешное заполнение всех полей формы регистрации с выполнением проверки заполненных полей EasyForm")
    void successfulFormSubmissionWithAllFieldsTestEasyForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        allPages.openPage("/text-box");
        step("Ввод Имени/Фамилии", () -> {
                    allPages.typeUserName(firstName + " " + lastName);
                    Attach.screenshotAs("Screenshot FirstLastName" + " " + firstName + " " + lastName);
                });
                allPages.typeUserEmail(userEmail)
                        .typeUserCurrentAddress(userCurrentAdress)
                        .typeUserPermanentAddress(userPermanentAdress)
                        .submitButtonClick();
        step("Проверка формы", () -> {
            allPages.checkFormFieldEasyForm(firstName + " " + lastName)
                    .checkFormFieldEasyForm(userEmail)
                    .checkFormFieldEasyForm(userCurrentAdress)
                    .checkFormFieldEasyForm(userPermanentAdress);
        });


    }

    @Test
    @DisplayName("Успешное заполнение обязательных полей формы регистрации с выполнением проверки заполненных полей HardForm")
    public void successfulFormSubmissionWithRequiredFieldsTestHardForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("/automation-practice-form");
        step("Ввод Имени/Фамилии", () -> {
            allPages.typeUserFirstName(firstName)
                    .typeUserLastName(lastName);
            Attach.screenshotAs("Screenshot FirstLastName" + " " + " " + firstName + " " + lastName);
        });
        allPages.typeUserEmail(userEmail)
                .typeUserNumber(userPhone)
                .typeUserGender(userGender)
                .submitButtonClick();
        step("Проверка формы", () -> {
            allPages.checkFormFieldHardForm("Student Name", (firstName + " " + lastName))
                    .checkFormFieldHardForm("Student Email", userEmail)
                    .checkFormFieldHardForm("Gender", userGender)
                    .checkFormFieldHardForm("Mobile", userPhone);
        });
    }

    @Test
    @DisplayName("Успешное заполнение обязательных полей формы регистрации с выполнением проверки заполненных полей EasyForm")
    public void successfulFormSubmissionWithNoTAllFieldsTestEasyForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        allPages.openPage("/text-box");
        step("Ввод Имени/Фамилии", () -> {
            allPages.typeUserName(firstName + " " + lastName)
                    .typeUserEmail(userEmail);
            Attach.screenshotAs("Screenshot FirstLastName" + " " + firstName + " " + lastName);
        });

        allPages.submitButtonClick();
        step("Проверка формы", () -> {
            allPages.checkFormFieldEasyForm(firstName + " " + lastName)
                    .checkFormFieldEasyForm(userEmail);
        });


    }

    //НЕГАТИВНЫЕ ТЕСТЫ

    @Test
    public void shouldShowValidationErrorsWhenAllRequiredFieldsAreEmptyTestHardForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        allPages.openPage("/automation-practice-form")
                .typeUserGender(userGender)
                .submitButtonClick()
                .userFormWasValidatedHardForm();
    }

    @Test
    public void shouldShowValidationErrorsWhenFirstNameAreEmptyTestHardForm() {

        SelenideLogger.addListener("allure", new AllureSelenide());

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

        SelenideLogger.addListener("allure", new AllureSelenide());

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

        SelenideLogger.addListener("allure", new AllureSelenide());

        allPages.openPage("/text-box")
                .typeUserEmail(userEmailNotValid)
                .submitButtonClick()
                .userFormWasValidatedEasyForm();
    }


}
