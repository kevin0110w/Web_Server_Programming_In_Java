
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class HelloServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);

        while (true) {
            Socket socket = server.accept();
            Scanner lukija = new Scanner(socket.getInputStream());
            String p = lukija.nextLine();

            if (p.contains("quit")) {
                break;
            }

            PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
            kirjoittaja.println("HTTP/1.1 200 OK");
            kirjoittaja.println("");
            kirjoittaja.flush();

            try (Scanner scanner = new Scanner(Paths.get("index.html"))) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            lukija.close();
            kirjoittaja.close();
            socket.close();
            server.close();
        }

    }
}
