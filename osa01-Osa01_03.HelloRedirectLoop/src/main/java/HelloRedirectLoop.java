
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
// odotetaan pyyntöä porttiin 8080
        ServerSocket server = new ServerSocket(8080);
        int pyyntöLaskuri = 1;
        while (true) {
            // odotetaan pyyntöä
            Socket socket = server.accept();
            System.out.println("Pyyntö numero: "+pyyntöLaskuri);
            pyyntöLaskuri++;

            // luetaan pyyntö
            Scanner lukija = new Scanner(socket.getInputStream());
            String pyyntö = lukija.nextLine();
            System.out.println(pyyntö);
            if (pyyntö.contains("/quit")) {
                server.close();
                break;
            }

            // kirjoitetaan vastaus
            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 302 Found");
            kirjoittaja.println("Location: http://localhost:8080)");
            kirjoittaja.println("");
            kirjoittaja.flush();

            // vapautetaan resurssit
            lukija.close();
            kirjoittaja.close();
            socket.close();
        }
    }
}
