package starter.drom;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import starter.duckduckgo.NavigateActions;
import starter.pageobjects.drom.MainPageWithSearch;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class TestDrom {

    @Managed(driver = "chrome", options = "false")
    WebDriver driver;
    @Steps
    MainPageWithSearch mainPageWithSearch;
    String carBrand = "Toyota";
    String carModel = "Harrier";
    String fuel = "Гибрид";
    String startDate = "2007";
    String mileage = "1";

    @Test
    void testDrom1() {
        mainPageWithSearch.setDriver(driver);
        driver.get("https://auto.drom.ru/");
        mainPageWithSearch.waitFor(5);
        mainPageWithSearch.initializeElementsOfFilter();
        mainPageWithSearch.clickOnElementInFilterMenu(mainPageWithSearch.carBrand);
        mainPageWithSearch.initializeDropDownOnScreen(mainPageWithSearch.carBrand);
        mainPageWithSearch.chooseFromDropDown(carBrand);

        mainPageWithSearch.clickOnElementInFilterMenu(mainPageWithSearch.carModel);
        mainPageWithSearch.carModel.type(carModel);
        mainPageWithSearch.initializeDropDownOnScreen(mainPageWithSearch.currentDropDownList);
        mainPageWithSearch.chooseFromDropDown(carModel);

        mainPageWithSearch.clickOn(mainPageWithSearch.fuel);
        mainPageWithSearch.initializeDropDownOnScreen(mainPageWithSearch.fuel);
        mainPageWithSearch.chooseFromDropDown(fuel);

        mainPageWithSearch.notSoldButton.click();

        mainPageWithSearch.fromYear.click();
        mainPageWithSearch.collectFromYears();
        mainPageWithSearch.chooseYear(startDate);

        mainPageWithSearch.extendedSearchButton.click();
        mainPageWithSearch.mileage.type(mileage);

        mainPageWithSearch.searchButton.click();
        //mainPageWithSearch.waitFor(5);

        mainPageWithSearch.collectTitlesOfCars();
        mainPageWithSearch.collectMileageOfCars();
        mainPageWithSearch.collectPricesOfCars();

        mainPageWithSearch.checkIfCarIsNotSold();
        mainPageWithSearch.checkIfAllCarsYearMoreThen(2007);
        mainPageWithSearch.checkIfAllCarsHaveMileage();

    }

}
