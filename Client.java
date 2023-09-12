import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("localhost",6666);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF("hello server");
            dos.flush();
            dos.close();

        }catch (Exception e){
            System.out.println(e);
        }


    }
}
