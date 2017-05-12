package device.status.controller;

import device.status.br.SystemTools;
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

    @Autowired
    SystemTools systemTools;

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
/*
    @GetMapping(value = "/getDevicePingStatus")
    @ResponseBody
    public String getDevicePingStatus(@RequestParam("deviceId") String deviceId){
        String returnVelue = deviceList.getDevice(deviceId).getPingStatus();
        return (returnVelue == null || returnVelue.isEmpty()) ? "&#10007;" : returnVelue;
    }

    @GetMapping(value = "/getDeviceHostNameStatus")
    @ResponseBody
    public String getDeviceHostNameStatus(@RequestParam("deviceId") String deviceId){
        String returnValue = deviceList.getDevice(deviceId).getHostName();
        return (returnValue == null || returnValue.isEmpty()) ? "&#10007;" : returnValue;
    }

    @GetMapping(value = "/getDeviceOsStatus")
    @ResponseBody
    public String getDeviceOsStatus(@RequestParam("deviceId") String deviceId){
        String returnValue = deviceList.getDevice(deviceId).getOsVersion();
        return (returnValue == null || returnValue.isEmpty()) ? "&#10007;" : returnValue;
    }

    @GetMapping(value = "/getDeviceUpTimeStatus")
    @ResponseBody
    public String getUpTimeStatus(@RequestParam("deviceId") String deviceId){
        String returnValue = deviceList.getDevice(deviceId).getUpTime();
        return (returnValue == null || returnValue.isEmpty()) ? "&#10007;" : returnValue;
    }
    */

    @PostMapping(value = "/saveSystem")
    @ResponseBody
    public String saveSystem(){
        return systemTools.saveDataToXml() ? "OK" : " NOT OK";
    }

    @GetMapping(value = "/getDeviceOverviewStatus")
    @ResponseBody
    public int getOverviewStatus(@RequestParam("deviceId") String deviceId){
        Device device = deviceList.getDevice(deviceId);
        int returnValue = 0;
        if (device.getOsVersion() != null){
            returnValue++;
        }

        if (device.getHostName() != null){
            returnValue++;
        }

        if(device.getUpTime() != null){
            returnValue++;
        }

        if (device.getPingStatus() != null && !device.getPingStatus().equals("&#10007;")){
            returnValue++;
        }

        return returnValue;
    }

    @GetMapping(value = "/getDevice")
    @ResponseBody
    public Device getDevice(@RequestParam("deviceId") String deviceId){
        return deviceList.getDevice(deviceId);
    }
}
