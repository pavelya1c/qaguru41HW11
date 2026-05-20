package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.FormFieldEasyFormComponent;
import pages.components.FormFieldHardFormComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AllPages {

    CalendarComponent calendarComponent = new CalendarComponent();
    FormFieldHardFormComponent formFieldHardFormComponent = new FormFieldHardFormComponent();
    FormFieldEasyFormComponent formFieldEasyFormComponent = new FormFieldEasyFormComponent();

    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement numberInput = $("#userNumber");
    private final SelenideElement genderInput = $("#genterWrapper");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement subjectInput = $(".subjects-auto-complete__input");
    private final SelenideElement hobbiesInput = $("#hobbiesWrapper");
    private final SelenideElement pictureInput = $("#uploadPicture");
    private final SelenideElement currentAdressInput = $("#currentAddress");
    private final SelenideElement stateInput = $(".col-md-4.col-sm-12");
    private final SelenideElement cityInput = $("#city");
    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement userFormTable = $("#userForm");


    @Step("Переход на страницу")
    public AllPages openPage(String value) {
        open(value);
        return this;
    }

    @Step("Ввод FirstName")
    public AllPages typeUserFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Ввод LastName")
    public AllPages typeUserLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Ввод email")
    public AllPages typeUserEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Ввода MobileNumber")
    public AllPages typeUserNumber(String value) {
        numberInput.setValue(value);
        return this;
    }

    @Step("Нажатие кнопки Submit")
    public AllPages submitButtonClick() {
        submitButton.click();
        return this;
    }

    @Step("Выбор Gender")
    public AllPages typeUserGender(String value) {
        genderInput.shouldBe(visible)
                .$(byText(value)).click();
        return this;
    }

    @Step("Выбор Subject")
    public AllPages typeUserSubject(String value) {
        subjectInput.shouldBe(visible)
                .setValue(value)
                .pressEnter();
        return this;
    }

    @Step("Выбор Hobbies")
    public AllPages typeUserHobbies(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    @Step("Добавление Picture")
    public AllPages typeUserPicture(String value) {
        pictureInput.uploadFromClasspath(value);
        return this;
    }

    @Step("Ввод Current Adress")
    public AllPages typeUserCurrentAddress(String value) {
        currentAdressInput.setValue(value);
        return this;
    }

    @Step("Выбор State")
    public AllPages typeUserState(String value) {
        stateInput.click();
        $(byText(value)).click();
        return this;
    }

    @Step("Выбор City")
    public AllPages typeUserCity(String value) {
        cityInput.click();
        $(byText(value)).click();
        return this;
    }

    @Step("Заполнение Date of birth")
    public AllPages setDayOfBirth(String day, String mounth, String year) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(day, mounth, year);
        return this;
    }

    @Step("Ввод UserName")
    public AllPages typeUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }

    @Step("Ввод PermanentAddress")
    public AllPages typeUserPermanentAddress(String value) {
        permanentAddressInput.setValue(value);
        return this;
    }


    @Step("Проверка формы валидации HardForm")
    public AllPages userFormWasValidatedHardForm() {
        userFormTable.shouldHave(cssClass("was-validated"));
        return this;
    }

    @Step("Проверка не валидности формы HardForm")
    public AllPages userFormWasNotValidatedHardForm() {
        userFormTable.shouldNotHave(cssClass("table-dark"));
        return this;
    }

    @Step("Проверка формы валидации EasyForm")
    public AllPages userFormWasValidatedEasyForm() {
        emailInput.shouldHave(cssClass("field-error"))
                .shouldHave(cssClass("form-control"));
        return this;
    }

    @Step("Проверка введеных данных, с данными отображаемыми в форме")
    public AllPages checkFormFieldHardForm(String key, String value) {
        formFieldHardFormComponent.FormFieldHardForm(key, value);
        return this;
    }

    @Step("Проверка заполнения формы EasyForm")
    public AllPages checkFormFieldEasyForm(String value) {
        formFieldEasyFormComponent.FormFieldEasyForm(value);
        return this;
    }


}
