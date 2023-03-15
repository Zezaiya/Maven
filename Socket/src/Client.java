import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
        public static void main(String[] args) throws IOException {
            System.out.println("客户端1_start!!");
            System.out.println("====================");

            Scanner sc = new Scanner(System.in);

            while (true) {
                Socket socket = new Socket("0.0.0.0", 8090);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());

                System.out.println("Me(客户端1)：");

                String msg = sc.nextLine();
                pw.write(msg);
                pw.flush();

                String response = br.readLine();
                System.out.println("收到服务器信息: " + response);
                System.out.println("********************");
                pw.close();
                br.close();


            }
        }



}
