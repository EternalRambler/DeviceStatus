package device.status.br;

import device.status.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;

@Service
class DeviceChecker {
    private static final Logger log = LoggerFactory.getLogger(DeviceStatus.class);
    private PingEngine pingEngine = new PingEngine();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now;

    @Async
    void checkDevice(Device device){
        now = LocalDateTime.now();
        SNMPManager snmpManager;
        try {
            snmpManager = new SNMPManager(device.getIpAddress());
            device.setHostName(snmpManager.getHostName().get());
            device.setOsVersion(snmpManager.getOsVersion().get());
            device.setUpTime(snmpManager.getUpTime().get());
            device.setPingStatus(pingEngine.pingAddressBySystemPing(device.getIpAddress()).get() ? "&#10003;" : "&#10007;");
            log.info("For DEVICE: " + device.getIpAddress() + " work done - " + Thread.currentThread().getName());
        } catch (IOException | InterruptedException | ExecutionException e) {
            log.error("Something goes wrong!!!", e);
            device.setHostName("&#10007;");
            device.setOsVersion("&#10007;");
            device.setUpTime("&#10007;");
        }
        device.setLastUpdate(dtf.format(now));
    }
}
