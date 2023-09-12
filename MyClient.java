import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost",6666);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            DataInputStream dis = new DataInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);

            String str1 = "";
            String str2 = "";

            while(!str1.equals("stop")){
                System.out.print("Client : ");
                str2 = sc.nextLine();
                dos.writeUTF(str2);
                dos.flush();

                str1 = dis.readUTF();
                System.out.println("Server : "+str1);
            }
            dos.close();
            s.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
