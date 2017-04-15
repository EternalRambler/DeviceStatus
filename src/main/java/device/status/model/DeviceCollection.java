package device.status.model;

import device.status.model.Device;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceCollection {

    public List<Device> deviceList = new ArrayList<>();

    public List<Device> getDeviceList(){
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public boolean removeDevice(Device device){
        return deviceList.remove(device);
    }

    public Device getDevice(String name){
        return deviceList.stream().filter(d -> d.getName().equals(name)).findFirst().get();
    }

    public Device addDevice(String deviceName, String deviceIp){
        Device device = new Device();
        device.setName(deviceName);
        device.setIpAddress(deviceIp);
        deviceList.add(device);
        return device;
    }
}
