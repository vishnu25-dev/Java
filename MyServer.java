import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {
    public static void main(String[] args) {
        try{
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);

            String str1 = "";
            String str2 = "";

            while(!str2.equals("stop")){
                str2 = dis.readUTF();
                System.out.println("Client : "+str2);

                System.out.print("Server : ");
                str1 = sc.nextLine();
                dos.writeUTF(str1);
                dos.flush();
            }
            dis.close();
            s.close();
            ss.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
