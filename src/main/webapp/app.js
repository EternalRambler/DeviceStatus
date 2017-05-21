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
        $.ajax({
            url: 'getDeviceOverviewStatus',
            type: 'GET',
            data:{
                    deviceId : tableData.id
                },
        success : function(data){
            var value;
            switch(data) {
                case 4:
                    tableData.className = "success";
                    break;
                case 3:
                case 2:
                case 1:
                    tableData.className = "warning";
                    break;
                default:
                    tableData.className = "danger";
                    break;
            }
            console.log("Value in function: " + value)
            console.log("Class name for 'tr' in function: " + tableData.className)
        }
        })
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
        async: true,
        data: {
                    deviceName : deviceName,
                    deviceIp : deviceIp
               },
        success : function(data) {
        $("#tableOfDevices")
            .append("<tr class =success id=" + data.id + "><td>" + data.name + "</td> "
            + "<td>" + data.ipAddress + "</td>"
            + "<td>" + removeNulls(data.pingStatus) + "</td>"
            + "<td>" + removeNulls(data.hostName) + "</td>"
            + "<td>" + removeNulls(data.osVersion) + "</td>"
            + "<td>" + removeNulls(data.upTime) + "</td>"
            + "<td>" + removeNulls(data.lastUpdate) + "</td></tr>");
        }
     });
}

function saveSystem(event){
    event.preventDefault();
     $.ajax({
        url: 'saveSystem',
        type: 'POST',
        success : function(data) {
            console.log(data)
        }
     });
}

function loadSystem(event){
    event.preventDefault();
     $.ajax({
        url: 'loadSystem',
        type: 'POST',
        success : function(data) {
            console.log(data)
            window.location.reload();
        }
     });
}

var t = setInterval(updateRow, 6000);

function updateRow(){
    var trs = document.getElementById("tableOfDevices").getElementsByTagName("tr");

     for(var i=0; i<trs.length; i++){
        getStatusMark(trs[i]);
        updateDeviceStatus(trs[i]);
     }
};

function updateDeviceStatus(tableData){
     $.ajax({
            url: 'getDevice',
            type: 'GET',
            async: true,
            data:{
                        deviceId : tableData.id
                   },
            success : function(data) {
                tableData.getElementsByTagName("td")[2].innerHTML = removeNulls(data.pingStatus);
                tableData.getElementsByTagName("td")[3].innerHTML = removeNulls(data.hostName);
                tableData.getElementsByTagName("td")[4].innerHTML = removeNulls(data.osVersion);
                tableData.getElementsByTagName("td")[5].innerHTML = removeNulls(data.upTime);
                tableData.getElementsByTagName("td")[6].innerHTML = removeNulls(data.lastUpdate);
            }});
};

$(document).on('show.bs.modal','#myModal', function (e) {
    var button = e.relatedTarget;
    var tit = $('.confirm-delete').data('title');

    $('#myModal .modal-body p').html("Do your realy want to delete: " + '<b>' + tit +'</b>' + ' ?');
});

/*$(document).$().on('.confirm-delete', 'click', function(e) {
    e.preventDefault();

    var id = $(this).data('id');
    $('#myModal').data('id', id).modal('show');
});*/

$(document).on("click","#btnYes", function(e) {
    // handle deletion here
    e.preventDefault();
    var id = $('#myModal').data('id');
    $('[data-id='+id+']').parents('tr').remove();
    $('#myModal').modal('hide');

});
