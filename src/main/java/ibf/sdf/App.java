package ibf.sdf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// CREATE CLIENT SERVER FOR THIS GAME
public final class App {
    public static void main(String[] args) throws NumberFormatException, IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        System.out.println("Waiting for connection...");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Connected...");

                ClientHandler cH = new ClientHandler(socket);
                executorService.submit(cH);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
