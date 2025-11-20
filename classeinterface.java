import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class classeinterface extends JFrame {
    private JTextField comandeF;
    private JTextArea outputtext;
    private Socket s1;
    private PrintWriter out;
    private BufferedReader in;

    public classeinterface(String host, int port) {
        setTitle("Client");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();

        try {
            s1 = new Socket(host, port);
            out = new PrintWriter(s1.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Connexion échouée: " + e.getMessage());
            System.exit(1);
        }

        setVisible(true);
    }

    private void initComponents() {
        comandeF = new JTextField();
        outputtext = new JTextArea();
        outputtext.setEditable(false);

        comandeF.addActionListener(e -> sendCommand());

        add(comandeF, BorderLayout.NORTH);
        add(new JScrollPane(outputtext), BorderLayout.CENTER);
    }

    private void sendCommand() {
        String command = comandeF.getText();
        out.println(command);
        try {
            String response = in.readLine();
            outputtext.append("Commande: " + command + "\n");
            outputtext.append("Réponse: " + response + "\n\n");
        } catch (IOException e) {
            outputtext.append("Erreur: " + e.getMessage() + "\n");
        }
        comandeF.setText("");
    }
}
