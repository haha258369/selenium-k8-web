package test_flows.global;

import java.util.Arrays;
import java.util.List;
import static url.Urls.baseUrl;

public class Draft {

    public static void main(String[] args) {
        List<String> expectedHrefs = Arrays.asList(
                "/sitemap", "/shipping-returns", "/privacy-policy",
                "/conditions-of-use", "/about-us", "/contactus"
        );

        expectedHrefs.replaceAll(href -> baseUrl + href);

        for (String expectedHref : expectedHrefs) {
            System.out.println(expectedHref);
        }
    }
}
