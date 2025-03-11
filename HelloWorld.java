import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HelloWorld {
    public static void main(String[] args) throws Exception {
        // Create an HTTP server listening on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Define a context for the "/hello" path
        server.createContext("/hello", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // HTML response for the /hello endpoint
                String response = "<html>" +
                        "<head><title>Hello Page</title></head>" +
                        "<body>" +
                        "<h1>Hello!</h1>" +
                        "<h2>This is a simple Open Source project</h2>" +
                        "<ul>" +
                        "<li>Made by Nexonm</li>" +
                        "<li>Java 21 used</li>" +
                        "<li>Only '/hello' and secret endpoint works</li>" +
                        "</ul>" +
                        "</body>" +
                        "</html>";

                // Set the response headers
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length());

                // Write the response to the output stream
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Define a context for the "/secret" path
        server.createContext("/secret", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                // Response for the /secret endpoint
                String response = "You are a smart guy! It was a pleasure to work with you! Now, let me take some rest... BIB";

                // Set the response headers
                exchange.getResponseHeaders().set("Content-Type", "text/plain");
                exchange.sendResponseHeaders(200, response.length());

                // Write the response to the output stream
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();

                // Stop the server after responding
                System.out.println("Stopping the server...");
                server.stop(0);
            }
        });

        // Start the server
        server.start();
        System.out.println("Server is running on http://localhost:8080/hello");
    }
}