package starter.pageobjects.drom;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

import java.util.List;

/**
 * PageObject описывает главную страницу auto.drom.ru и фильтр поиска
 */

@DefaultUrl("https://auto.drom.ru/")
public class MainPageWithSearch extends PageObject{

        //Можно зарефакторить и сделать более универсальным, путем сбора элементов в hashmap

        public WebElementFacade searchButton;

        public WebElementFacade carBrand;

        public WebElementFacade carModel;

        public WebElementFacade currentDropDownList;
        public WebElementFacade fuel;

        public WebElementFacade notSoldButton;

        public WebElementFacade fromYear;

        private List<WebElementFacade> fromYearsList;

        public WebElementFacade extendedSearchButton;

        public WebElementFacade mileage;

        private List<WebElementFacade> mileageOfElementsAfterSearchList;

        private List<WebElementFacade> pricesOfElementsAfterSearchList;

        private List<WebElementFacade> titlesOfElementsAfterSearchList;

        public void initializeElementsOfFilter() {
                searchButton = find(By.xpath("//div[@class = 'css-tjza12 e1lm3vns0' and contains(.,'Показать')]"));
                carBrand = find(By.xpath("//input[@placeholder = 'Марка']"));
                carModel = find(By.xpath("//input[@placeholder = 'Модель']"));
                fuel = find(By.xpath("//button[contains(.,'Топливо')]"));
                notSoldButton  = find(By.xpath("//label[contains(.,'Непроданные')]"));
                fromYear = find(By.xpath("//button[contains(.,'Год от')]"));
                extendedSearchButton = find(By.xpath("//button[contains(.,'Расширенный поиск')]"));
                mileage = find(By.xpath("//input[@placeholder= 'от, км']"));
        }

        public void clickOnElementInFilterMenu (WebElementFacade webElementFacade) {
                webElementFacade.click();
        }

        public void initializeDropDownOnScreen (WebElementFacade webElementFacade) {
                String dropDownXpath = "//div[@class = 'css-u25ii9 e154wmfa0']";
                webElementFacade.findBy(By.xpath(dropDownXpath)).waitUntilVisible();
                currentDropDownList = webElementFacade.find(By.xpath(dropDownXpath));
        }

        public void chooseFromDropDown(String toChose) {
                currentDropDownList.waitUntilClickable();
                currentDropDownList.findBy("(//div[@class = 'css-u25ii9 e154wmfa0']//div[@role = 'option' and contains(.,'"+ toChose +"')])")
                        .click();
        }

        public void collectFromYears() {
                fromYearsList = findAll(By.xpath("//div[@class = 'css-u25ii9 e154wmfa0']/div[@class = 'css-xzwadh e1x0dvi10']"));
        }

        public void chooseYear(String year) {
                fromYearsList.stream()
                        .filter(o -> o.getText().contains(year))
                        .findFirst()
                        .get()
                        .click();
        }

        public void collectTitlesOfCars() {
                titlesOfElementsAfterSearchList = findAll(By.xpath(
                        "//span[@data-ftid = 'bull_title']"));
        }

        public void collectMileageOfCars() {
                mileageOfElementsAfterSearchList = findAll(By.xpath(
                        "//div[@data-ftid = 'component_inline-bull-description']/span[5]"));
        }

        public void collectPricesOfCars() {
                pricesOfElementsAfterSearchList = findAll(By.xpath("//span[@data-ftid = 'bull_price']"));
        }

        @Step("Проверка года выпуска.(>= 2007)")
        public void checkIfAllCarsYearMoreThen (Integer moreThan) {
                Assertions.assertThat(titlesOfElementsAfterSearchList
                        .stream()
                        .filter(o ->
                        {
                                Integer year = Integer.parseInt(o.getText().substring(o.getText().length() -4, o.getText().length()));
                                return year >= moreThan;

                        })
                        .count()).isEqualTo(20);

        }

        @Step("Проверка наличия у каждой позиции пробега.(в списке 20 машин, значит должно быть 20 элементов)")
        public void checkIfAllCarsHaveMileage () {
                Assertions.assertThat((long) mileageOfElementsAfterSearchList
                        .size()).isEqualTo(20);
        }

        @Step("Проверка наличия у каждой позиции пробега.(в списке 20 машин, значит должно быть 20 элементов)")
        public void checkIfCarIsNotSold () {
                Assertions.assertThat(titlesOfElementsAfterSearchList
                        .stream()
                        .filter(o -> o.getCssValue("text-decoration").contains("line-through"))
                        .count()).isLessThan(1);
        }

}


