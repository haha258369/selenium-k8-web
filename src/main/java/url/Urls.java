package url;

public interface Urls {

//    String demoBaseUrl = "https://demowebshop.tricentis.com";

    // Used by Jenkins
    String demoBaseUrl = System.getProperty("baseUrl");
    String baseUrl = "https://the-internet.herokuapp.com";
    String cheapComputer = "/build-your-cheap-own-computer";
    String standardComputer = "/build-your-own-computer";
    String registerSlug = "/register";
    String dropdownSlug = "/dropdown";
    String iFrameSlug = "/iframe";
    String hoverSlug = "/hovers";
    String alertSlug = "/javascript_alerts";
    String loginSlug = "/login";
    String dynamicControlSlug = "/dynamic_controls";
    String floatingMenuSlug = "/floating_menu";

}
