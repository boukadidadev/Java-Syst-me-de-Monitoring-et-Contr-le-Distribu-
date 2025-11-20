import java.io.*;

public class classecontrol {
    private static final String SENSOR_PATH = "C:/simu_sensors/"; 

    public String setFanSpeed(String fanId, String speedStr) {
        int speed;
        try {
            speed = Integer.parseInt(speedStr);
        } catch (NumberFormatException e) {
            return "UNSUPPORTED ROTATION SPEED";
        }

        if (speed < 0 || speed > 1200) {
            return "UNSUPPORTED ROTATION SPEED";
        }

        try {
            if (fanId.equalsIgnoreCase("ALL")) {
                for (int i = 0; i < 3; i++) {
                    writeSpeedToFile("fan" + i, speed);
                }
            } else {
                writeSpeedToFile("fan" + fanId, speed);
            }
            return "OK";
        } catch (IOException e) {
            return "INTERNAL SERVER ERROR";
        }
    }

    private void writeSpeedToFile(String fanFile, int speed) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SENSOR_PATH + fanFile))) {
            writer.write(String.valueOf(speed));
        }
    }
}
