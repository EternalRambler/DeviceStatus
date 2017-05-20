package device.status.br;

import device.status.model.Device;
import device.status.model.DeviceCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class SystemTools {
    private static final Logger log = LoggerFactory.getLogger(SystemTools.class);

    @Autowired
    DeviceCollection deviceList;

    public boolean saveDataToXml(){
        File file = new File("C:\\DEVICE_STATUS\\device_collection.xml");
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(DeviceCollection.class, Device.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(deviceList, file);
        } catch (JAXBException e) {
            log.error("JAXB failure !!!", e);
            return false;
        }

        return true;
    }

    public boolean loadDataFile(){
        try {
            File file = new File("C:\\DEVICE_STATUS\\device_collection.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(DeviceCollection.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            deviceList.setDeviceList(((DeviceCollection) jaxbUnmarshaller.unmarshal(file)).getDeviceList());
            return true;

        } catch (JAXBException e) {
            log.error("JAXB failure !!!", e.getMessage());
            return false;
        }
    }
}
