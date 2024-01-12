import java.io.*;
import java.net.*;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Web server started on port " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                String requestLine = in.readLine();
                System.out.println("Received request: " + requestLine);

                String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + "<html><body><h1>Hello, World!</h1></body></html>";
                out.println(httpResponse);

            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port " + port);
                System.out.println(e.getMessage());
            }
        }
    }
}