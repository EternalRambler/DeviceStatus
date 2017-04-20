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

function getStatusMark(tableData) {
        var value;
        $.ajax({
            url: 'getDeviceOverviewStatus',
            type: 'GET',
            data:{
                    deviceId : tableData.id
                },
        success : function(data){
            switch(data) {
                case 4:
                    value = "success";
                    break;
                case 3:
                case 2:
                case 1:
                    value = "warning";
                    break;
                default:
                    value = "danger";
            }
        }
        })

        return value;
};

function removeNulls(data){
    if(data == null || data == false){
        return "&#10007;";
    }
    return data;

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
            + "<td>" + removeNulls(data.pingStatus) + "</td>"
            + "<td>" + removeNulls(data.hostName) + "</td>"
            + "<td>" + removeNulls(data.osVersion) + "</td>"
            + "<td>" + removeNulls(data.upTime) + "</td></tr>");
        }
     });
}

var t = setInterval(updateRow, 1000);

function updateRow(){
    var trs = document.getElementById("tableOfDevices").getElementsByTagName("tr");

     for(var i=0; i<trs.length; i++){
        updateDevicePingStatus(trs[i]);
        updateDeviceHostNameStatus(trs[i]);
        updateDeviceOsStatus(trs[i]);
        updateDeviceUpTimeStatus(trs[i]);
        updateTableClass(trs[i]);
     }
};

function updateTableClass(tableData){
console.log(getStatusMark(tableData.className));
    tableData.className = getStatusMark(tableData);
}

function updateDevicePingStatus(tableData){
     $.ajax({
            url: 'getDevicePingStatus',
            type: 'GET',
            data:{
                        deviceId : tableData.id
                   },
            success : function(data) {
                tableData.getElementsByTagName("td")[2].innerHTML = data;
            }});
};

function updateDeviceHostNameStatus(tableData){
     $.ajax({
            url: 'getDeviceHostNameStatus',
            type: 'GET',
            data:{
                        deviceId : tableData.id
                   },
            success : function(data) {
                tableData.getElementsByTagName("td")[3].innerHTML = data;
            }});
};

function updateDeviceOsStatus(tableData){
     $.ajax({
            url: 'getDeviceOsStatus',
            type: 'GET',
            data:{
                        deviceId : tableData.id
                   },
            success : function(data) {
                tableData.getElementsByTagName("td")[4].innerHTML = data;
            }});
};

function updateDeviceUpTimeStatus(tableData){
     $.ajax({
            url: 'getDeviceUpTimeStatus',
            type: 'GET',
            data:{
                        deviceId : tableData.id
                   },
            success : function(data) {
                tableData.getElementsByTagName("td")[5].innerHTML = data;
            }});
};
