package chartclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChartClient {

  public static void main(String[] args) throws Exception {
    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    try (DatagramSocket clientSocket = new DatagramSocket()) {
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[65508];
      byte[] receiveData = new byte[65508];
      
      System.out.println("********CLIENTE********");

      System.out.println("Ingrese un nombre de usuario: ");
      String clientUsername = inFromUser.readLine();

      System.out.println("Ahora puede empezar a enviar mensajes...");

      while (true) {
        System.out.print("Yo: ");
        String clientSentence = clientUsername + ": " + inFromUser.readLine();
        sendData = clientSentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
      }
    } catch (Exception e) {
      e.getMessage();
    }
  }
}
class EnvioRespuesta implements Runnable {

    DatagramSocket soket = null;
    BufferedReader recibo = null;

    public EnvioRespuesta(DatagramSocket soket) {
        this.soket = soket;
    }

    @Override
    public void run() {
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            byte[] receiveData = new byte[65508];
            String mensaj = null;
            while ((mensaj = inFromUser.readLine()) != null) {
                System.out.println("Esperando respuesta desde el servidor...");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                soket.receive(receivePacket);
                String serverSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(serverSentence);
            }

        } catch (Exception e) {
        }
    }
}