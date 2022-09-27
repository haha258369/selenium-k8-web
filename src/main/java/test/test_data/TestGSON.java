package test.test_data;

import com.google.gson.Gson;
import test.test_data.computer.ComputerData;

import java.util.Arrays;

public class TestGSON {

    public static void main(String[] args) {
//        exploreGson();
        testDataBuilder();
    }

    private static void testDataBuilder() {
        String fileLocation = "/src/main/java/test_data/computer/CheapComputerData.json";
        ComputerData[] computerData = DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
        System.out.println(Arrays.toString(computerData));
    }

    private static void exploreGson() {
        String JsonString = "  {\n" +
                "    \"processorType\": \"Fast\",\n" +
                "    \"ram\": \"8 GB\",\n" +
                "    \"hdd\": \"320 GB\",\n" +
                "    \"software\": \"Image Viewer\"\n" +
                "  }";

        Gson gson = new Gson();
        ComputerData computerData = gson.fromJson(JsonString, ComputerData.class);
        System.out.println(computerData);
        System.out.println(gson.toJson(computerData));
    }
}
