package device.status.br;

import device.status.model.Device;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DeviceChecker {
    PingEngine pingEngine = new PingEngine();

    public void checkDevice(Device device){
        SNMPManager snmpManager = null;
        try {
            snmpManager = new SNMPManager(device.getIpAddress());
            device.setPingStatus((pingEngine.pingAddress(device.getIpAddress()) || pingEngine.pingAddressBySystemPing(device.getIpAddress())) ? "&#10003;" : "&#10007;");
            device.setSnmpStatus(snmpManager.getHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
