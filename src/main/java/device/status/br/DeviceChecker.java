package device.status.br;

import device.status.model.Device;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DeviceChecker {

    public void checkDevice(Device device){
        PingEngine pingEngine= new PingEngine();
        pingEngine.pingAddress(device.getIpAddress());
    }
}
