package mentoring.automation.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger( TestListener.class );

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info("Test '" + iTestResult.getName() + "' has finished successfully.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.error("Test '" + iTestResult.getName() + "' has failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.warn("Test '" + iTestResult.getName() + "' has been skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
