package device.status.br;

import device.status.model.Device;
import org.springframework.stereotype.Service;

public class DeviceChecker {

    public void checkDevice(Device device){
        PingEngine pingEngine= new PingEngine();
        pingEngine.pingAddress(device.getIpAddress());
    }
}
