package device.status.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@XmlRootElement
public class DeviceCollection {
    private static final Logger log = LoggerFactory.getLogger(DeviceCollection.class);

    @XmlTransient
    public List<Device> deviceList = Collections.synchronizedList(new ArrayList<>());

    public List<Device> getDeviceList(){
        return deviceList;
    }

    @XmlElement
    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public boolean removeDevice(Device device){
        return deviceList.remove(device);
    }

    public Device getDevice(String deviceId){
        return deviceList.stream().filter(d -> d.getId().equals(deviceId)).findFirst().get();
    }

    public Device addDevice(String deviceName, String deviceIp){
        Device device = new Device();
        device.setName(deviceName);
        device.setIpAddress(deviceIp);
        deviceList.add(device);
        log.info("Davice was added: " + device.getName() + "(" + device.getIpAddress() + ")");
        return device;
    }
}
