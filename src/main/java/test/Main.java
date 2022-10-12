package test;

import com.google.common.reflect.ClassPath;
import driver.BrowserType;
import org.checkerframework.checker.units.qual.C;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testsClasses = new ArrayList<>();
        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            if (info.getName().startsWith("test.") && !info.getName().equalsIgnoreCase(("test.Main"))){
                testsClasses.add(info.load());
            }
        }

        // Get browser
        String browser = System.getProperty("browser");
//        String browser = System.getenv("browser");
        if (browser == null) {
            throw new RuntimeException("Please provide browser via -Dbrowser");
        }

        try {
            BrowserType.valueOf(browser);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERR] " + browser + " is not supported, " +
                    "we covered for " + Arrays.toString(BrowserType.values()));
        }

        // Create groups of test classes
        final int MAX_PARALLEL_SESSION = 4;
        List<String> testGroupNames = new ArrayList<>();
        for (int index = 0; index < MAX_PARALLEL_SESSION; index++) {
            testGroupNames.add("Group " + (index + 1));
        }

        int numberTestOfEachGroup = testsClasses.size() / testGroupNames.size();
        HashMap<String, List<Class<?>>> desiredCapabilities = new HashMap<>();
        for (int groupIndex = 0; groupIndex < testGroupNames.size(); groupIndex++) {
            int startIndex = groupIndex * numberTestOfEachGroup;
            int endIndex = groupIndex == testGroupNames.size() - 1 ? testsClasses.size() : (startIndex + numberTestOfEachGroup);
            List<Class<?>> subTestList = testsClasses.subList(startIndex, endIndex);
            desiredCapabilities.put(testGroupNames.get(groupIndex), subTestList);
        }

        // Build dynamic test suite
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        // Put test classes into groups
        List<XmlTest> allTests = new ArrayList<>();
        for (String groupName : desiredCapabilities.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(groupName);
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = desiredCapabilities.get(groupName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }

            test.setXmlClasses(xmlClasses);
            test.addParameter("browser", browser);
            allTests.add(test);
        }

        // Add tests into suite, there is only 1 session on safari
        boolean isTestingOnSafari = browser.equals("safari");
        suite.setTests(allTests);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(isTestingOnSafari ? 1 : MAX_PARALLEL_SESSION);

        // Run a group of tests
        if (isTestingOnSafari) {
            suite.addIncludedGroup("smoke");
        } else {
            String targetGroup = args.length != 0 ? args[0] : null;
            if (targetGroup != null) {
                suite.addIncludedGroup(targetGroup);
            }
        }

        System.out.println(suite.toXml());

        // Add suite to suite list
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        // Invoke run() method
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
