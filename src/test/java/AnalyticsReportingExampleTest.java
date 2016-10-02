import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.*;

/*
 * AnalyticsReportingExampleTest
 * @author Kem
 */
public class AnalyticsReportingExampleTest {

    @Test
    public void testGetReportDimensionPagepathsMetrixPageviews_SessionsOrderByPageviewsDesc() throws GeneralSecurityException, IOException {
        AnalyticsReportingExample example = new AnalyticsReportingExample();
        example.getReportDimensionPagepathsMetrixPageviews_SessionsOrderByPageviewsDesc();
        assertTrue(true);
    }

    @Test
    public void testGetReportDimensionPagepaths_DateMetrixPageviews_SessionsOrderByPageviewsDesc() throws GeneralSecurityException, IOException {
        AnalyticsReportingExample example = new AnalyticsReportingExample();
        example.getReportDimensionPagepaths_DateMetrixPageviews_SessionsOrderByPageviewsDesc();
        assertTrue(true);
    }

    @Test
    public void testGetReportDimensionPagepathsMetrixPageviews_SessionsFilterByPagepathsOrderByPageviewsDesc() throws GeneralSecurityException, IOException {
        AnalyticsReportingExample example = new AnalyticsReportingExample();
        example.getReportDimensionPagepathsMetrixPageviews_SessionsFilterByPagepathsOrderByPageviewsDesc();
        assertTrue(true);
    }
}
