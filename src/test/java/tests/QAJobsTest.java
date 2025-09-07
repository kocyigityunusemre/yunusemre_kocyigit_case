package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class QAJobsTest extends BaseTest {

    @Test
    public void verifyQAJobsAndLeverForm() {
        HomePage home = new HomePage(driver);

        // 1) Home
        home.open();
        Assert.assertTrue(home.isHomeOpened(), "Home page açılmadı!");

        // 2) Company -> Careers ve bloklar
        CareersPage careers = home.goToCareers();
        Assert.assertTrue(careers.ensureBlocksAndOpenTeams(),
                "Careers sayfasında bloklar görünmüyor!");

        // 3) QA landing -> See all QA jobs
        QAJobsPage qa = careers.goToQAJobs();
        qa.clickSeeAll();

        // 4) Filtreler
        qa.filterJobs();
        Assert.assertTrue(qa.areFiltersApplied(),
                "Filtreler uygulanmadı veya liste boş!");

        // 5) İlk ilan -> Lever formu
        JobDetailsPage details = qa.openFirstJob();
        Assert.assertTrue(details.isLeverFormOpened(),
                "Lever başvuru formu/sayfası açılmadı!");
    }
}
