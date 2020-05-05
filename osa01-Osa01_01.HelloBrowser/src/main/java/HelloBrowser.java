
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HelloBrowser {

    public static void main(String[] args) throws Exception {
        System.out.println("================");
        System.out.println("THE INTERNETS!");
        System.out.println("================");

        System.out.print("Where to? ");
        Scanner input = new Scanner(System.in);

        String website = input.nextLine();

        int port = 80;

        Socket socket = new Socket(website, port);
        System.out.println("");
        System.out.println("==========");
        System.out.println("RESPONSE!");
        System.out.println("==========");

        PrintWriter kirjoittaja = new PrintWriter(socket.getOutputStream());
        kirjoittaja.println("GET / HTTP/1.1");
        kirjoittaja.println("Host: " + website);
        kirjoittaja.println();
        kirjoittaja.flush();

        Scanner lukija = new Scanner(socket.getInputStream());
        while (lukija.hasNextLine()) {
            System.out.println(lukija.nextLine());
        }
        kirjoittaja.close();
        lukija.close();
        return;
    }
}
