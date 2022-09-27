package api_learning.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.test_data.computer.ComputerData;
import test.test_data.DataObjectBuilder;

public class DataProviderComputerData {

    @Test(dataProvider = "computerData")
    public void testDataProvider(ComputerData computerData) {
        System.out.println(computerData);
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "/src/main/java/test_data/computer/CheapComputerData.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
