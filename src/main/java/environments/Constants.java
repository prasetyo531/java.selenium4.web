package environments;

public class Constants {

    public static final String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/config/config.properties";

    // Browser settings
    public static final String BROWSER = "browser";

    // Selenium Grid settings
    public static final String SELENIUM_GRID_ENABLED = "selenium.grid.enabled";
    public static final String SELENIUM_GRID_URL_FORMAT = "selenium.grid.urlFormat";
    public static final String SELENIUM_GRID_HUB_HOST = "selenium.grid.hubHost";

    // Application settings
    public static final String BASE_URL = "base.url";

    // Doc Directory
    public static String UPLOAD_PDF_ONE = "upload.pdf.one";
    public static final String UPLOAD_PDF_DRAFTCASE = "upload.pdf.draftcase";
    public static final String UPLOAD_PDF_EXISTINGCASE = "upload.pdf.existing";
}
