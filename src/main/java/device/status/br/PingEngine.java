package device.status.br;

import com.sun.javafx.binding.StringFormatter;
import device.status.model.Device;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Futrzak on 2017-04-14
 */
public class PingEngine {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(PingEngine.class);

    public boolean pingAddress(String ipAddress){
        List<String> ipAddressOctets = Arrays.asList(ipAddress.split("\\."));
        if (ipAddressOctets.size() < 4){
            log.error("Wrong IP address!!!\n" + ipAddress);
        }
        InetAddress inet4Address;
        boolean returnValue = false;
        try {
            inet4Address = InetAddress.getByName(ipAddress);
            returnValue = inet4Address.isReachable(1000);
            log.info("Send ping to: " + ipAddress + " RESULT: " + returnValue);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnValue;
    }
}
