package device.status.model;

import device.status.model.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceCollection {

    private List<Device> deviceList = new ArrayList<>();

    @RequestMapping("/")
    public ModelAndView getDeviceList(ModelMap modelMap) {
        modelMap.addAttribute("devices", deviceList);
        return new ModelAndView("index", modelMap);
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
