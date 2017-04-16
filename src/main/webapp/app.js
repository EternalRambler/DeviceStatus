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

function getStatusMark(data){
    return "success";
};

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
            .append("<tr class =" + getStatusMark(data) + " id=" + data.id + "><td>" + data.name + "</td> "
            + "<td>" + data.ipAddress + "</td>"
            + "<td>" + "No Hostname" + "</td>"
            + "<td>" + data.pingStatus + "</td>"
            + "<td>" + data.snmpStatus + "</td></tr>");
        }
     });
}

var t = setInterval(updateRow, 1000);

function updateRow(){
    var trs = document.getElementById("tableOfDevices").getElementsByTagName("tr");

     for(var i=0; i<trs.length; i++){
     console.log(trs[i].getElementsByTagName("td"));
        updateDevicePingStatus(trs[i]);
     }


};

function updateDevicePingStatus(tableData){
     $.ajax({
            url: 'getDevicePingStatus',
            type: 'GET',
            data:{
                        deviceId : tableData.id
                   },
            success : function(data) {
                tableData.getElementsByTagName("td")[3].innerHTML = data;
            }});
};

