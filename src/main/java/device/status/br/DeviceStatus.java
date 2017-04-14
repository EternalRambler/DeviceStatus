package device.status.br;

import device.status.controller.DeviceCollection;
import device.status.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceStatus {

    private static final Logger log = LoggerFactory.getLogger(DeviceStatus.class);

    List<Device> deviceList = DeviceCollection.getDeviceList();

    @Autowired
    DeviceChecker deviceChecker;

    @Scheduled(fixedRate = 6000)
    public void checkDevices(){
        deviceList.forEach(device -> deviceChecker.checkDevice(device));
    }
}
