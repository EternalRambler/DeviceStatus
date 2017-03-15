package device.status.model;

public class Device {
    private String ipAddress;
    private String name;
    private String description;

    private boolean isPingAvailable;
    private boolean isSnmpAvailable;

    private boolean pingStatus;
    private boolean snmpStatus;

    public Device() {
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

    public boolean isPingStatus() {
        return pingStatus;
    }

    public void setPingStatus(boolean pingStatus) {
        this.pingStatus = pingStatus;
    }

    public boolean isSnmpStatus() {
        return snmpStatus;
    }

    public void setSnmpStatus(boolean snmpStatus) {
        this.snmpStatus = snmpStatus;
    }
}
