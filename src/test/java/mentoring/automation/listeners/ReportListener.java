package mentoring.automation.listeners;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;
import java.util.List;
import java.util.stream.Collectors;

public class ReportListener implements IReporter {
    @Override
    public void generateReport(
            List<XmlSuite> list, List<ISuite> list1, String s
    ) {
        System.out.println( "Number of suites: " + list1.size() );
        list1.forEach( this::suiteReporter );
    }

    private void suiteReporter(ISuite suite){
        System.out.println( "Suite: " + suite.getName());
        List<ISuiteResult> results = suite.getResults().values().stream().collect(Collectors.toList());
        results.forEach( result -> {
            ITestContext context = result.getTestContext();
            context.getSkippedTests().getAllResults().stream().forEach( iTestResult -> {
                System.out.println( "Skipped test: " + iTestResult.getName());
            } );
            context.getPassedTests().getAllResults().stream().forEach( iTestResult -> {
                System.out.println( "Passed test: " + iTestResult.getName());
            } );
            context.getFailedTests().getAllResults().stream().forEach( iTestResult -> {
                System.out.println( "Failed test: " + iTestResult.getName());
            } );
        } );
    }
}
