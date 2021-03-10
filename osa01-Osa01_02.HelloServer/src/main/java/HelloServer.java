
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        // luodaan palvelin porttiin 8080
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            // odotetaan pyyntöä
            Socket socket = server.accept();

            // luetaan pyyntö
            Scanner lukija = new Scanner(socket.getInputStream());
            String pyyntö = lukija.nextLine();
            System.out.println(pyyntö);
            if (pyyntö.contains("/quit")) {
                System.out.println("Sammutetaan palvelin.");
                lukija.close();
                socket.close();
                server.close();
                break;
            }

            // kirjoitetaan vastaus
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 200 OK");
            kirjoittaja.println("Content-Type: text/html");
            kirjoittaja.println("\r\n");
            kirjoittaja.flush();

            Files.readAllLines(Paths.get("index.html"))
                    .forEach(rivi -> kirjoittaja.println(rivi));
            // vapautetaan resurssit
            kirjoittaja.close();
            lukija.close();
            socket.close();
        }
        server.close();
    }
}
