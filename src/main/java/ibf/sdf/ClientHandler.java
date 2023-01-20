package ibf.sdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Random rand = new Random();
        Integer guessNumber = rand.nextInt(100);
        Integer myGuess;

        try (InputStream is = socket.getInputStream()) {

            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            String commandReceived = "";
            String message = "";

            while (!commandReceived.equalsIgnoreCase("quit")) {
                commandReceived = dis.readUTF();
                myGuess = Integer.parseInt(commandReceived);
                System.out.printf("%d is the guessed number.\n", myGuess);
                while (myGuess != guessNumber) {
                    if (myGuess < guessNumber) {
                        message = "Guess a higher number.";
                    } else if (myGuess > guessNumber) {
                        message = "Guess a lower number.";
                    }
                    // NEED TO BREAK OUT OF THE WHILE LOOP
                    // OR ELSE IT IS STUCK IN HERE FOREVER
                    break;
                }
                if (myGuess == guessNumber) {
                    message = "Wow das right.";
                }
                dos.writeUTF(message);
                dos.flush();
            }
            dos.close();
            bos.close();
            os.close();
            dis.close();
            bis.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
