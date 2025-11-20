import java.io.*;
import java.net.Socket;

public class classethread implements Runnable {
    private Socket s1;
    private classesensor sensor;
    private classecontrol control;

    public classethread(Socket s1) {
        this.s1 = s1;
        this.sensor = new classesensor();
        this.control = new classecontrol();
    }

 
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            PrintWriter out = new PrintWriter(s1.getOutputStream(), true)
        ) {
            String line;
            while ((line = in.readLine()) != null) {
                String response = processCommand(line);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        if (parts.length == 2 && parts[0].equalsIgnoreCase("GET")) {
            float value = sensor.readIndicator(parts[1]);
            return String.valueOf(value);
        } else if (parts.length == 3 && parts[0].equalsIgnoreCase("SET")) {
            return control.setFanSpeed(parts[1], parts[2]);
        } else {
            return "INVALID COMMAND";
        }
    }
}
