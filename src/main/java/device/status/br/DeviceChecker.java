package device.status.br;

import device.status.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
class DeviceChecker {
    private static final Logger log = LoggerFactory.getLogger(DeviceStatus.class);
    private PingEngine pingEngine = new PingEngine();

    synchronized void checkDevice(Device device){
        SNMPManager snmpManager;
        try {
            snmpManager = new SNMPManager(device.getIpAddress());
            device.setHostName(snmpManager.getHostName());
            device.setOsVersion(snmpManager.getOsVersion());
            device.setUpTime(snmpManager.getUpTime());
        } catch (IOException e) {
            log.error("Something goes wrong!!!", e);
            device.setHostName(null);
            device.setOsVersion(null);
            device.setUpTime(null);
        }
        device.setPingStatus(pingEngine.pingAddressBySystemPing(device.getIpAddress()) ? "&#10003;" : "&#10007;");
    }
}
