import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    public static void main(String[] args) {

        try{
            ServerSocket ss = new ServerSocket(6666);
            int counter = 0;
            System.out.println("Server started.....");

            while(true){
                counter++;
                Socket s = ss.accept();
                System.out.println(" >> " + "Client No:" + counter + " started!");
                ServerClientThread sct = new ServerClientThread(s,counter);
                sct.start();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

class ServerClientThread extends Thread{
    Socket s;
    int counter;

    ServerClientThread(Socket s, int counter){
        this.s = s;
        this.counter = counter;
    }

    @Override
    public void run() {
        try{
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            String serverMsg = "";
            String clientMsg = "";

            while(true){
                clientMsg = dis.readUTF();

                if(clientMsg.equals("stop")) {
                    dis.close();
                    break;
                }

                System.out.println("From Client-" +this.counter+ ": Number is :"+clientMsg);

                int square = Integer.parseInt(clientMsg) * Integer.parseInt(clientMsg);
                serverMsg = "From Server to Client-" + counter + " Square of " + clientMsg + " is " +square;

                dos.writeUTF(serverMsg);
                dos.flush();
            }
            System.out.println("Client-" +this.counter+ " exiting....");
            dos.close();
            s.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}