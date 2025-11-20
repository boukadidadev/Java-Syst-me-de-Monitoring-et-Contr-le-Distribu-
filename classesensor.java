import java.io.*;

public class classesensor {
    private static final String SENSOR_PATH = "C:/simu_sensors/"; 

    public float readIndicator(String indicator) {
        File file = new File(SENSOR_PATH + indicator);
        if (!file.exists()) {
            return -99999f;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return Float.parseFloat(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return -99999f;
        }
    }
}
