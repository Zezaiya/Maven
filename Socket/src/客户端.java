import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class 客户端 {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端");
        //创建客户端socket并绑定服务器端口
        Socket socket = new Socket("0.0.0.0", 9000);
        while(true) {
            //把socket通道里预发送的信息包装成流
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            //准备好预发送的信息
            Scanner sc = new Scanner(System.in);
            String message = sc.next();
            //将信息发送至服务器
           // printWriter.write(message);
            printWriter.println(message);
            printWriter.flush();
        }
    }
}
