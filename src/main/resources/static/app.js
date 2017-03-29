$(document).ready(function(){
    $("#cancel").click(function(e){
    e.preventDefault()
    $("#hide").hide(1000);
    });
    $("#showPanel").click(function(e){
    e.preventDefault()
    $("#hide").show(1000);
    });
});

function hideOnLoad(){
    $("#hide").hide();
};

//function addDeviceFunction(){
//    $("#tableOfDevices")
//    .append("<tr class =\"success\"><td>" + "TEST" + "</td> "
//    + "<td>" + "NO IP" + "</td>"
//    + "<td>" + "No Hostname" + "</td>"
//    + "<td>" + "NO Ping" + "</td>"
//    + "<td>" + "NO SNMP" + "</td></tr>");
//    document.addNewDeviceForm.submit();
//    return false;
//}

function addDeviceFunctionAjax(event, deviceName, deviceIp){
    event.preventDefault();
    var button = document.getElementById("addDevice");
    deviceName = button.form.deviceName.value;
    deviceIp = button.form.deviceIp.value;
     $.ajax({
        url: 'addNewDevice',
        type: 'POST',
        data: {
                    deviceName : deviceName,
                    deviceIp : deviceIp
               },
        success : function(data) {
        console.log(data);
        $("#tableOfDevices")
            .append("<tr class =\"success\"><td>" + data.name + "</td> "
            + "<td>" + data.ipAddress + "</td>"
            + "<td>" + "No Hostname" + "</td>"
            + "<td>" + "NO Ping" + "</td>"
            + "<td>" + "NO SNMP" + "</td></tr>");
        }
     });
}

