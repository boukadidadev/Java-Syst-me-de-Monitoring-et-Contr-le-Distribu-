import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class mainclasse {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en écoute sur le port " + port);
            while (true) {
                Socket s1 = serverSocket.accept();
                System.out.println("Nouveau client connecté");
                new Thread(new classethread(s1)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
