package device.status.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@XmlRootElement
public class DeviceCollection {
    private static final Logger log = LoggerFactory.getLogger(DeviceCollection.class);

    @XmlTransient
    public CopyOnWriteArrayList<Device> deviceList = new CopyOnWriteArrayList();

    public CopyOnWriteArrayList<Device> getDeviceList(){
        return deviceList;
    }

    @XmlElement
    public void setDeviceList(CopyOnWriteArrayList<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public boolean removeDevice(String deviceId){
        return  deviceList.removeIf(device -> device.getId().equals(deviceId));
    }

    public Device getDevice(String deviceId){
        return deviceList.parallelStream().filter(d -> d.getId().equals(deviceId)).findFirst().get();
    }

    public Device addDevice(String deviceName, String deviceIp){
        Device device = new Device();
        device.setName(deviceName);
        device.setIpAddress(deviceIp);
        deviceList.add(device);
 //       log.info("Davice was added: " + device.getName() + "(" + device.getIpAddress() + ")");
        return device;
    }
}
