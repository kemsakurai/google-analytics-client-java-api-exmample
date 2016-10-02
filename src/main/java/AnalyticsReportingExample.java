import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * AnalyticsReportingExample
 */
public class AnalyticsReportingExample {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final ResourceBundle resource = ResourceBundle.getBundle("AnalyticsReportingExample");
    private static final String APP_NAME = resource.getString("appName");
    private static final String KEY_FILE_LOCATION = resource.getString("keyFilieLocation");
    private static final String VIEW_ID = resource.getString("viewId");

    /**
     * getReportDimensionPagepathsMetrixPageviews_SessionsOrderByPageviewsDesc
     *
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void getReportDimensionPagepathsMetrixPageviews_SessionsOrderByPageviewsDesc() throws GeneralSecurityException, IOException {
        AnalyticsReporting service = initializeAnalyticsReporting();

        // --------------------------------------------------
        // Create the DateRange object.
        // -----------------------------
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("2016-09-01");
        dateRange.setEndDate("2016-09-29");
        List<DateRange> dateRanges = Arrays.asList(dateRange);

        // --------------------------------------------------
        // Create the Metrics object.
        // -----------------------------
        Metric pageviews = new Metric()
                .setExpression("ga:pageviews")
                .setAlias("PageView");

        // Create the Metrics object.
        Metric sessions = new Metric()
                .setExpression("ga:sessions")
                .setAlias("Sessions");
        List<Metric> metrics = Arrays.asList(sessions, pageviews);

        // --------------------------------------------------
        // Create the Dimensions object.
        // -----------------------------
        Dimension pagePath = new Dimension().setName("ga:pagePath");
        List<Dimension> dimensions = Arrays.asList(pagePath);

        // --------------------------------------------------
        // Create OrderBy object
        // ------------------------------
        OrderBy sort = new OrderBy().setFieldName("ga:pageviews").setSortOrder("DESCENDING");
        List<OrderBy> sortOrders = Arrays.asList(sort);

        // --------------------------------------------------
        // Create the ReportRequest object.
        // ------------------------------
        GetReportsResponse response = getReportsResponse(service, dateRanges, metrics, dimensions, sortOrders);

        // print response
        printResponse(response);
    }

    /**
     * getReportDimensionPagepathsMetrixPageviews_SessionsFilterByPagepathsOrderByPageviewsDesc
     */
    public void getReportDimensionPagepathsMetrixPageviews_SessionsFilterByPagepathsOrderByPageviewsDesc() throws GeneralSecurityException, IOException {
        AnalyticsReporting service = initializeAnalyticsReporting();

        // --------------------------------------------------
        // Create the DateRange object.
        // -----------------------------
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("2016-09-01");
        dateRange.setEndDate("2016-09-29");
        List<DateRange> dateRanges = Arrays.asList(dateRange);

        // --------------------------------------------------
        // Create the Metrics object.
        // -----------------------------
        Metric pageviews = new Metric()
                .setExpression("ga:pageviews")
                .setAlias("PageView");
        Metric sessions = new Metric()
                .setExpression("ga:sessions")
                .setAlias("Sessions");
        List<Metric> metrics = Arrays.asList(sessions, pageviews);

        // --------------------------------------------------
        // Create the Dimensions object.
        // -----------------------------
        Dimension pagePath = new Dimension().setName("ga:pagePath");
        List<Dimension> dimensions = Arrays.asList(pagePath);

        // --------------------------------------------------
        // Create OrderBy object
        // ------------------------------
        OrderBy pageViewsDesc = new OrderBy().setFieldName("ga:pageviews").setSortOrder("DESCENDING");
        List<OrderBy> sortOrders = Arrays.asList(pageViewsDesc);

        // --------------------------------------------------
        // Create filter
        // ------------------------------
        String filter = "ga:pagePath=~^/blog.*";

        // --------------------------------------------------
        // Create the ReportRequest object.
        // ------------------------------
        GetReportsResponse response = getReportsResponseWithFilter(service, dateRanges, metrics, dimensions, sortOrders, filter);
        // print response
        printResponse(response);

    }

    /**
     * getReportDimensionPageviewsMetrixPageviews_SessionsOrderByPageviewsDesc
     *
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void getReportDimensionPagepaths_DateMetrixPageviews_SessionsOrderByPageviewsDesc() throws GeneralSecurityException, IOException {
        AnalyticsReporting service = initializeAnalyticsReporting();

        // --------------------------------------------------
        // Create the DateRange object.
        // -----------------------------
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("2016-09-01");
        dateRange.setEndDate("2016-09-29");
        List<DateRange> dateRanges = Arrays.asList(dateRange);

        // --------------------------------------------------
        // Create the Metrics object.
        // -----------------------------
        Metric pageviews = new Metric()
                .setExpression("ga:pageviews")
                .setAlias("PageView");
        Metric sessions = new Metric()
                .setExpression("ga:sessions")
                .setAlias("Sessions");
        List<Metric> metrics = Arrays.asList(sessions, pageviews);

        // --------------------------------------------------
        // Create the Dimensions object.
        // -----------------------------
        Dimension pagePath = new Dimension().setName("ga:pagePath");
        Dimension date = new Dimension().setName("ga:date");
        List<Dimension> dimensions = Arrays.asList(pagePath, date);

        // --------------------------------------------------
        // Create OrderBy object
        // ------------------------------
        OrderBy dateDesc = new OrderBy().setFieldName("ga:date").setSortOrder("DESCENDING");
        OrderBy pageViewsDesc = new OrderBy().setFieldName("ga:pageviews").setSortOrder("DESCENDING");
        List<OrderBy> sortOrders = Arrays.asList(pageViewsDesc, dateDesc);

        // --------------------------------------------------
        // Create the ReportRequest object.
        // ------------------------------
        GetReportsResponse response = getReportsResponse(service, dateRanges, metrics, dimensions, sortOrders);
        // print response
        printResponse(response);
    }

    /**
     * Initializes an authorized Analytics Reporting service object.
     *
     * @return The analytics reporting service object.
     * @throws IOException
     * @throws GeneralSecurityException
     */
    private AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(KEY_FILE_LOCATION))
                .createScoped(AnalyticsReportingScopes.all());
        // Construct the Analytics Reporting service object.
        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APP_NAME).build();
    }

    /**
     * Parses and prints the Analytics Reporting API V4 response.
     *
     * @param response the Analytics Reporting API V4 response.
     */
    private void printResponse(GetReportsResponse response) {
        for (Report report : response.getReports()) {

            ColumnHeader header = report.getColumnHeader();
            List<String> dimensionHeaders = header.getDimensions();
            List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
            List<ReportRow> rows = report.getData().getRows();

            if (rows == null) {
                println("No data found for " + VIEW_ID);
                return;
            }

            println("======Print START=====");
            // print row
            for (ReportRow row : rows) {
                println("----------------------------------------------------");
                List<String> dimensions = row.getDimensions();
                List<DateRangeValues> metrics = row.getMetrics();
                for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
                    println("Dimension:" + dimensionHeaders.get(i) + ": " + dimensions.get(i));
                }
                for (DateRangeValues metric : metrics) {
                    List<String> values = metric.getValues();
                    for (int j = 0; j < values.size() && j < metricHeaders.size(); j++) {
                        println("Metrics:" + metricHeaders.get(j).getName() + ": " + values.get(j));
                    }
                }
                println("----------------------------------------------------");
            }
            println("======Print END=====");
        }
    }

    /**
     * getReportsResponse
     *
     * @param service
     * @param dateRanges
     * @param metrics
     * @param dimensions
     * @param sortOrders
     * @return
     * @throws IOException
     */
    private GetReportsResponse getReportsResponseWithFilter(AnalyticsReporting service, List<DateRange> dateRanges, List<Metric> metrics, List<Dimension> dimensions, List<OrderBy> sortOrders, String filter) throws IOException {
        ReportRequest request = new ReportRequest()
                .setViewId(VIEW_ID)
                .setDateRanges(dateRanges)
                .setDimensions(dimensions)
                .setMetrics(metrics)
                .setFiltersExpression(filter)
                .setOrderBys(sortOrders);

        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
        requests.add(request);

        // Create the GetReportsRequest object.
        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(requests);

        // Call the batchGet method.
        return service.reports().batchGet(getReport).execute();
    }

    /**
     * getReportsResponse
     *
     * @param service
     * @param dateRanges
     * @param metrics
     * @param dimensions
     * @param sortOrders
     * @return
     * @throws IOException
     */
    private GetReportsResponse getReportsResponse(AnalyticsReporting service, List<DateRange> dateRanges, List<Metric> metrics, List<Dimension> dimensions, List<OrderBy> sortOrders) throws IOException {
        ReportRequest request = new ReportRequest()
                .setViewId(VIEW_ID)
                .setDateRanges(dateRanges)
                .setDimensions(dimensions)
                .setMetrics(metrics)
                .setOrderBys(sortOrders);

        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
        requests.add(request);

        // Create the GetReportsRequest object.
        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(requests);

        // Call the batchGet method.
        return service.reports().batchGet(getReport).execute();
    }

    /**
     * println
     *
     * @param string
     */
    private void println(String string) {
        System.out.println(string);
    }
}