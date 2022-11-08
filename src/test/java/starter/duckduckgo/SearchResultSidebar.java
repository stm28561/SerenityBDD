package starter.duckduckgo;

import net.serenitybdd.core.pages.PageComponent;

public class SearchResultSidebar extends PageComponent {
    public String heading() {
        return $("(//a[@data-testid = 'result-title-a'])[1]").getText();
    }
}
