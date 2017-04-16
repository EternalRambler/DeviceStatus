package device.status.controller;

import device.status.model.Device;
import device.status.model.DeviceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeviceController {

    @Autowired
    DeviceCollection deviceList;

    @RequestMapping("/")
    public ModelAndView getDeviceList(ModelMap modelMap) {
        modelMap.addAttribute("devices", deviceList.getDeviceList());
        return new ModelAndView("index", modelMap);
    }

    @PostMapping(value = "/addNewDevice")
    @ResponseBody
    public Device addDevice(@RequestParam("deviceName") String deviceName, @RequestParam("deviceIp") String deviceIp){
        return deviceList.addDevice(deviceName, deviceIp);
    }

    @GetMapping(value = "/getDevicePingStatus")
    @ResponseBody
    public String getDevicePingStatus(@RequestParam("deviceId") String deviceId){
        return deviceList.getDevice(deviceId).getPingStatus();
    }
}
