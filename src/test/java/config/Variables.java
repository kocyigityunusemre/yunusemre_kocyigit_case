package config;

public class Variables {

    // -------- Timeouts --------
    public static final int EXPLICIT_WAIT = 15;

    // -------- Urls --------
    public static final String BASE_URL        = "https://useinsider.com/";
    public static final String CAREERS_URL     = "https://useinsider.com/careers/";
    public static final String QA_LANDING_URL  = "https://useinsider.com/careers/quality-assurance/";

    // QA + Istanbul
    public static final String OPEN_POSITIONS_QA_TR_URL =
            "https://useinsider.com/careers/open-positions/?department=qualityassurance&location=istanbul-turkey";

    // sadece QA
    public static final String OPEN_POSITIONS_QA_URL =
            "https://useinsider.com/careers/open-positions/?department=qualityassurance";

    // Cookie
    public static final String ACCEPT_ALL_COOKIES_XP =
            "(" +
                    "  //a[@id='wt-cli-accept-all-btn' or contains(@class,'wt-cli-accept-all-btn')]" +
                    "  | //button[normalize-space()='Accept All' or .//span[normalize-space()='Accept All']]" +
                    "  | //a[normalize-space()='Accept All' or .//span[normalize-space()='Accept All']]" +
                    "  | //div[@role='dialog']//button[.//span[contains(normalize-space(),'Accept All')] or contains(normalize-space(),'Accept All')]" +
                    ")[1]";


    // Header Company Careers
    public static final String COMPANY_MENU_XP     = "//nav//a[normalize-space()='Company' or contains(.,'Company')]";
    public static final String CAREERS_IN_MENU_XP  = "//nav//a[contains(@href,'/careers/') and (normalize-space()='Careers' or contains(.,'Careers'))]";

    // Home
    public static final String HOME_HERO_XP =
            "((//main|//body)//h1)[1] | //*[contains(@class,'hero') or contains(@id,'hero')]//h1";

    // Careers
    public static final String SEE_ALL_TEAMS_BTN_XP = "//a[normalize-space()='See all teams']";
    public static final String QA_TEAM_TILE_XP      = "//a[contains(@href,'/careers/quality-assurance') and (contains(.,'Quality Assurance') or contains(.,'Quality'))]";

    // QA landing
    public static final String SEE_ALL_QA_XP = "//a[contains(@href,'/careers/open-positions') and contains(.,'See all QA jobs')]";

    // Open Positions – filtre başlıkları (buton/div fark etmez)
    public static final String LOC_TOGGLE_XP = "//*[self::button or self::div][.//span[contains(.,'Filter by Location')]]";
    public static final String DEP_TOGGLE_XP = "//*[self::button or self::div][.//span[contains(.,'Filter by Department')]]";

    // Secenek genel
    public static final String OPTION_BY_TEXT_FMT =
            "//*[self::li or self::button or self::div][@role='option' or @role='menuitem' or not(@role)]"
                    + "[.//*[normalize-space()='%s' or contains(normalize-space(),'%s')] or normalize-space()='%s' or contains(normalize-space(),'%s')]";

    // Liste
    public static final String JOB_VIEW_ROLE_BTNS_XP = "//a[contains(@href,'jobs.lever.co') and (normalize-space()='View Role' or contains(.,'View Role'))]";

    // Filtre yazıları
    public static final String LOCATION_VALUE   = "Istanbul, Turkey";
    public static final String DEPARTMENT_VALUE = "Quality Assurance";

    // Lever detay sayfasında kontrol edeceğimiz ogeler
    public static final String LEVER_APPLY_BTN_XP =
            "//a[normalize-space()='Apply for this job' or contains(.,'Apply')]";

    public static final String LEVER_FORM_XP =
            "//form[contains(@action,'lever.co') or .//input[@type='email' or @name='email' or @id='email']]";
}
