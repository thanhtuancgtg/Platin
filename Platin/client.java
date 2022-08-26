import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class client {
    private static final int PORT = 9888;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");
            while (true) {
                Scanner sc = new Scanner(System.in);

                System.out.print("Enter data: ");
                String request = sc.nextLine();

                // send request server
                byte[] outputByte = request.getBytes();
                DatagramPacket outputPack = new DatagramPacket(outputByte, outputByte.length, serverAddress, PORT);
                socket.send(outputPack);

                // receive data to server
                byte[] inputByte = new byte[1024];
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);

                String inputStr = new String(inputPack.getData(), 0, inputPack.getLength());
                System.out.println("Result : " + inputStr);

            }

        } catch (IOException ex) {
            System.out.println("bug Client: " + ex.toString());
        }
    }
}
