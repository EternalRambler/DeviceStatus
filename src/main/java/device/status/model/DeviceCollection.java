package device.status.model;

import java.util.ArrayList;
import java.util.List;

public class DeviceCollection {

    private List<Device> deviceList = new ArrayList<>();

    public List<Device> getDeviceList() {
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

    public boolean addDevice(Device device){
        return deviceList.add(device);
    }

}
