import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class server {
    private static final int PORT = 9888;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Server listenning...");

            byte[] inputByte = new byte[100000];
            while (true) {
                // Receive to client
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);

                String inputStr = new String(inputPack.getData(), 0, inputPack.getLength());
                System.out.println("Data receive: " + inputStr);

                // Xu ly
                char[] charArray = inputStr.toCharArray();
                int charArrayLength = charArray.length;
                String result = "";

                for (int i = 0; i < charArrayLength; i++) {
                    // 3. Nếu từ không chứa nguyên âm, giữ nguyên từ đó.
                    if (charArray[i] != 'a' && charArray[i] != 'e' && charArray[i] != 'o' && charArray[i] != 'i'
                            && charArray[i] != 'u') {
                        result = inputStr;
                    }
                    ;
                }

                // 2. Nếu một từ bắt đầu bằng một nguyên âm, thêm cụm từ “way” vào cuối từ.
                int index = 0;
                if (charArray[0] == 'a' || charArray[0] == 'e' || charArray[0] == 'o' || charArray[0] == 'i'
                        || charArray[0] == 'u' || charArray[0] == 'y') {
                    result = inputStr + "way";
                }
                for (int i = 0; i < charArrayLength; i++) {
                    if (charArray[0] == 'b') {
                        result = inputStr + charArray[0] + "ay";
                        result = result.substring(1);

                    } else {
                        if (charArray[0] == 'g' && charArray[1] == 'r') {
                            result = inputStr + charArray[0] + charArray[1] + "ay";
                            result = result.substring(2);
                        }
                    }
                }
                System.out.println(result);

                // send result to client
                byte[] outputByte = result.getBytes();
                DatagramPacket outputPack = new DatagramPacket(outputByte, outputByte.length, inputPack.getAddress(),
                        inputPack.getPort());
                socket.send(outputPack);

            }
        } catch (IOException ex) {
            System.out.println("Bug server: " + ex.toString());
        }

    }
}
