package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ChatServer implements Runnable {

    DatagramPacket dat;
    private static DatagramSocket serverSocket = null;
    final int port = 9876;

    public ChatServer() {
        try {
            this.serverSocket = new DatagramSocket(port);
        } catch (IOException e) {
             Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public DatagramPacket getDat() {
        return dat;
    }

    public void setDat(DatagramPacket dat) {
        this.dat = dat;
    }

    public DatagramSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    

    public static void main(String[] args) throws Exception {
        byte[] receiveData = new byte[65508];
        DatagramPacket dat = new DatagramPacket(receiveData, receiveData.length);
       // serverSocket.receive(dat);
        String clientSentence = new String(dat.getData(), 0, dat.getLength());
        System.out.println(clientSentence);
        ChatServer reciv = new ChatServer();
        Thread thread = new Thread(reciv);
        thread.start();

        EscucharCliente1 escucha = new EscucharCliente1(reciv.serverSocket);
        Thread thread2 = new Thread(escucha);
        thread2.start();

    }

    @Override
    public void run() {
        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            byte[] sendData = new byte[65508];
            System.out.println("SERVIDOR: ");
            System.out.println("Ingrese un nombre de usuario: ");
            String serverUsername = inFromUser.readLine();
            System.out.println("La conversación debe empezar desde el cliente...");
            while (true) {
                System.out.println("Esperando un mensaje desde el cliente...");
                System.out.print("Yo: ");
                String serverSentence = serverUsername + ": " + inFromUser.readLine();
                sendData = serverSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, dat.getAddress(), dat.getPort());

                serverSocket.send(sendPacket);
            }

        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
class EscucharCliente1 implements Runnable {

    DatagramSocket serverSocket1 = null;

    public EscucharCliente1(DatagramSocket serverSocket) {
        this.serverSocket1 = serverSocket;
    }

    @Override
    public void run() {
        try {
            byte[] receiveData = new byte[65508];
            while (true) {
                System.out.println("Mensaje de cliente...");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket1.receive(receivePacket);
                String clientSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(clientSentence);
                InetAddress ipAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
            }
        } catch (IOException ex) {
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
