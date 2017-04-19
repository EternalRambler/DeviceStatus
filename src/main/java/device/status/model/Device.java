package device.status.model;

import device.status.br.SNMPManager;

import java.util.UUID;

public class Device {
    private String id;
    private SNMPManager snmpManager;

    private String ipAddress;
    private String name;
    private String description;

    private boolean isPingAvailable;
    private boolean isSnmpAvailable;

    private String pingStatus;
    private String snmpStatus;

    public Device() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPingAvailable() {
        return isPingAvailable;
    }

    public void setPingAvailable(boolean pingAvailable) {
        isPingAvailable = pingAvailable;
    }

    public boolean isSnmpAvailable() {
        return isSnmpAvailable;
    }

    public void setSnmpAvailable(boolean snmpAvailable) {
        isSnmpAvailable = snmpAvailable;
    }

    public String getPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(String pingStatus) {
        this.pingStatus = pingStatus;
    }

    public String getSnmpStatus() {
        return snmpStatus;
    }

    public void setSnmpStatus(String snmpStatus) {
        this.snmpStatus = snmpStatus;
    }
}
