package device.status.br;

import device.status.model.DeviceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

@Component
public class DeviceStatus {

    private final Logger log = Logger.getLogger("DeviceStatus");

    @Autowired
    DeviceCollection deviceList;

    @Autowired
    DeviceChecker deviceChecker;

    @Scheduled(fixedRate = 60000)
    public void checkDevices(){
        try {
            deviceList.getDeviceList().parallelStream().forEach(device -> deviceChecker.checkDevice(device));
        }catch (Exception e){
            try {
                FileHandler fileHandler = new FileHandler("C:\\DEVICE_STATUS\\device_collection.log");
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                log.addHandler(fileHandler);
                log.log(Level.SEVERE, "Exception", e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
