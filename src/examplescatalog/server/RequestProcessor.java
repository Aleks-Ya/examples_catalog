package examplescatalog.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Обработчик запроса к серверу.
 */
class RequestProcessor implements Runnable {
    private final Socket socket;

    RequestProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String head = reader.readLine();
            reader.close();

            String[] strings = head.split(" ");
            String commandStr = strings[1];
            System.out.println(commandStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
