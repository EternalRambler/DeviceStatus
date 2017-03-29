package device.status.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
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
    @PostMapping(value = "/addNewDevice")
    @ResponseBody
    public Device addDevice(@RequestParam("deviceName") String deviceName, @RequestParam("deviceIp") String deviceIp){
        Device device = new Device();
        device.setName(deviceName);
        device.setIpAddress(deviceIp);
        deviceList.add(device);
        return device;
    }
}
