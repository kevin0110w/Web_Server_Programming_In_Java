
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloRedirectLoop {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        int counter = 1;
        while (true) {
            Socket socket = server.accept();
            System.out.println("Request: " + counter);
            Scanner lukija = new Scanner(socket.getInputStream());
            String p = lukija.nextLine();
            counter++;
            if (p.contains("quit")) {
                break;
            }

            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 302 OK");
            kirjoittaja.println("Location: http://localhost:8080/");

            kirjoittaja.flush();

            lukija.close();
            kirjoittaja.close();
            socket.close();
            server.close();
        }

    }
}
