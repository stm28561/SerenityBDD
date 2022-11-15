package starter.drom;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import starter.pageobjects.drom.MainPageWithSearch;
import starter.utils.NavigateActions;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class TestDrom {

    @Managed(driver = "chrome", options = "true")
    WebDriver driver;
    @Steps
    MainPageWithSearch mainPageWithSearch;

    NavigateActions navigateActions = new NavigateActions();
    String carBrand = "Toyota";
    String carModel = "Harrier";
    String fuel = "Гибрид";
    String startDate = "2007";
    String mileage = "1";

    @Test
    void testDrom1() {
        mainPageWithSearch.setDriver(driver);
        navigateActions.openUrl(navigateActions.dromUrl, driver);
        mainPageWithSearch.setImplicitTimeout(10, ChronoUnit.SECONDS);
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

        mainPageWithSearch.collectTitlesOfCars();
        mainPageWithSearch.collectMileageOfCars();
        mainPageWithSearch.collectPricesOfCars();

        mainPageWithSearch.checkIfCarIsNotSold();
        mainPageWithSearch.checkIfAllCarsYearMoreThen(Integer.parseInt(startDate));
        mainPageWithSearch.checkIfAllCarsHaveMileage();
    }

}
