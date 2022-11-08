package starter.drom;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import starter.pageobjects.drom.MainPageWithSearch;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)

public class Test1 {
    @Managed(driver = "chrome")

    WebDriver driver;
    @Steps
    MainPageWithSearch mainPageWithSearch = new MainPageWithSearch();
    String carBrand = "Toyota";
    String carModel = "Harrier";
    String fuel = "Гибрид";
    String startDate = "2007";
    String mileage = "1";

    @Test
    void testDrom1() {

        mainPageWithSearch.open();
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
        mainPageWithSearch.waitFor(5);

        mainPageWithSearch.collectMileageOfCars();
        mainPageWithSearch.collectPricesOfCars();


        Assertions.assertThat((int) mainPageWithSearch.getMileageOfElementsAfterSearchList()
                .stream()
                .filter(o -> Integer.parseInt(o.getText().replaceAll("[^0-9]+", ""))  < 1)
                .count()).isLessThan(1);


    }

}
