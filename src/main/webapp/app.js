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

function getStatusMark(value){
    if(value){
        return "&#10003;";
    } else {
        return "&#10007;";
    }
}
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
        $("#tableOfDevices")
            .append("<tr class =\"success\"><td>" + data.name + "</td> "
            + "<td>" + data.ipAddress + "</td>"
            + "<td>" + "No Hostname" + "</td>"
            + "<td>" + data.pingStatus + "</td>"
            + "<td>" + data.snmpStatus + "</td></tr>");
        }
     });
}

