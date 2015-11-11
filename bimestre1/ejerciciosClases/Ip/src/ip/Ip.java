
package ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author 22B
 */
public class Ip {


    public static void main(String[] args) throws UnknownHostException {
  
        InetAddress compu = InetAddress.getLocalHost();
        System.out.println(compu.getHostName());
        System.out.println(compu.getHostAddress());
        
        InetAddress dir = InetAddress.getByName("www.nltk.org");
        System.out.println(dir.getHostAddress());
    }
    
}
