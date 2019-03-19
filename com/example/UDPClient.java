package com.example;

import java.net.InetAddress;
import java.net.SocketException;

public class UDPClient {
    // 10s
    static int TIMEOUT = 10000;
    static int SERVER_PORT = 20009;
    static int SERVER_MAX_BYTES = 20;

    // TODO should be a main method -> separate program for client and server.
    public static void run() {
        String hostname = "localhost";
        int number = 10;

        try (UDPSocket udp = new UDPSocket()) {
            udp.setTimeout(TIMEOUT);
            InetAddress serverAddress = InetAddress.getByName(hostname);

            System.out.println("client request set counter to 0.");
            udp.send("reset", serverAddress, SERVER_PORT);

            String reply = udp.receive(SERVER_MAX_BYTES);
            System.out.println("counter - server reply: " + reply);

            System.out.println("client request increment counter.");
            int count = number;
            long startTime = System.currentTimeMillis();

            for (int i=0; i<count; i++) {
                udp.send("increment", serverAddress, SERVER_PORT);
                reply = udp.receive(SERVER_MAX_BYTES);
            }

            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("duration[s] server request: " + duration);

            if (count > 0) {
                System.out.println("average time[s]: " + (duration / (float)count) );
            }
            System.out.println("latest counter value: " + reply);

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
