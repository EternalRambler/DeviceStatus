package device.status.model;

public class Device {
    private String ipAddress;
    private String name;
    private String description;

    private boolean isPingAvailable;
    private boolean isSnmpAvailable;

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
}
