package device.status.br;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

@Component
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
        } catch (IOException e) {
            log.error("Something goes wrong!!!", e);
            returnValue = false;
        }

        return returnValue;
    }

    public boolean pingAddressBySystemPing(String ipAddress){
        String pingResult = "";

        String pingCmd = "ping -n 1 " + ipAddress;
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                pingResult += inputLine;
            }
            in.close();

        } catch (IOException e) {
            log.error("Something goes wrong!!!", e);
            return false;
        }

        return pingResult.contains("Reply from " + ipAddress) || pingResult.contains("Odpowied� z " + ipAddress);
    }
}
