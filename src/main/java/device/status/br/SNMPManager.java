package device.status.br;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;
import java.util.concurrent.Future;

public class SNMPManager {
    private static final Logger log = LoggerFactory.getLogger(DeviceStatus.class);

    Snmp snmp = null;
    String ipAddress = null;

    public SNMPManager(String ipAddress) throws IOException {
        this.ipAddress = "udp:" + ipAddress + "/161";
        start();
    }

    private void start() throws IOException {
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    public String getAsString(OID oid){
        ResponseEvent event = null;
        try {
            event = get(new OID[] { oid });
            PDU pdu = event.getResponse();
            if (pdu != null) {
                return pdu.get(0).getVariable().toString();
            } else {
                return "&#10007;";
            }
        } catch (IOException e) {
            log.error("Something goes wrong!!!", e);
            return null;
        }
    }

    @Async
    public Future<String> getHostName() {
        return new AsyncResult<>(getAsString(new OID(".1.3.6.1.2.1.1.5.0")));
    }

    @Async
    public Future<String> getOsVersion() {
        return new AsyncResult<>(getAsString(new OID(".1.3.6.1.2.1.1.1")));
    }

    @Async
    public Future<String> getUpTime() {
        return new AsyncResult<>(getAsString(new OID(".1.3.6.1.2.1.1.3.0")));
    }

    public ResponseEvent get(OID oids[]) throws IOException {
        PDU pdu = new PDU();
        for (OID oid : oids) {
            pdu.add(new VariableBinding(oid));
        }
        pdu.setType(PDU.GET);
        ResponseEvent event = snmp.send(pdu, getTarget(), null);
        if(event != null) {
            return event;
        }
        throw new RuntimeException("GET timed out");
    }

    private Target getTarget() {
        Address targetAddress = GenericAddress.parse(ipAddress);
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        target.setRetries(1);
        target.setTimeout(2000);
        target.setVersion(SnmpConstants.version2c);
        return target;
    }
}
