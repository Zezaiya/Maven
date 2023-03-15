import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器 start!!");

        System.out.println("====================");

        final Scanner sc = new Scanner((System.in));

        //实现服务器套接字
        ServerSocket server = new ServerSocket(8090);
        while (true) {
            final Socket socket = server.accept();
            while (true) {

            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                String content = br.readLine();

                System.out.println("收到客户端信息:" + content);
                System.out.println("********************");
                System.out.println("Me (服务器): ");

                String msg = sc.nextLine();

                pw.write(msg);
                pw.flush();
                pw.close();
                br.close();
            } catch (IOException e) {
                //throw new RuntimeException(e);
            }
        }


        }

    }

}
