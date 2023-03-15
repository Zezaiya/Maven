import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class 服务器 {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器正在监听");
        //定义唯一端口
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            //创建Socket对象来接收ServerSocket->accept方法的返回值
            Socket socket = serverSocket.accept();
            //把socket通道内预接收的信息包装成流
            BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //将客户端发的信息展示到控制台
            System.out.println("客户端发来消息:" + bf.readLine());
            bf.close();
        }
    }
}
