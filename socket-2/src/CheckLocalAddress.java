import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckLocalAddress {
    public static void main(String[] args) {
        try {
            //IPアドレスからホスト名を取り出す実験
            // IP Address
            InetAddress localHost = InetAddress.getLocalHost();
            // Host name
            String localHostName = localHost.getHostName();
            String localHostAddress = localHost.getHostAddress();

            System.out.println("Local name is: " + localHostName);
            System.out.println("IP address is: " + localHostAddress);
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("Host could not be found");
        }
    }
}
