package device.status.br;

import device.status.model.DeviceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeviceStatus {

    @Autowired
    DeviceCollection deviceList;

    @Autowired
    DeviceChecker deviceChecker;

    @Scheduled(fixedRate = 6000)
    public void checkDevices(){
        deviceList.getDeviceList().forEach(device -> deviceChecker.checkDevice(device));
    }
}
