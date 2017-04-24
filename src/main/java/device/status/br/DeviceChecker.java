package device.status.br;

import device.status.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
class DeviceChecker {
    private static final Logger log = LoggerFactory.getLogger(DeviceStatus.class);
    private PingEngine pingEngine = new PingEngine();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;

    synchronized void checkDevice(Device device){
        now = LocalDateTime.now();
        SNMPManager snmpManager;
        try {
            snmpManager = new SNMPManager(device.getIpAddress());
            device.setHostName(snmpManager.getHostName());
            device.setOsVersion(snmpManager.getOsVersion());
            device.setUpTime(snmpManager.getUpTime());
        } catch (IOException e) {
            log.error("Something goes wrong!!!", e);
            device.setHostName("&#10007;");
            device.setOsVersion("&#10007;");
            device.setUpTime("&#10007;");
        }
        device.setPingStatus(pingEngine.pingAddressBySystemPing(device.getIpAddress()) ? "&#10003;" : "&#10007;");
        device.setLastUpdate(dtf.format(now));
    }
}
