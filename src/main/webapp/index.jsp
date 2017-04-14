<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Test System 1</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript"></script>
    <script src="/app.js"></script>

</head>
<body onload="hideOnLoad()">
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being
    enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div id="main-content" class="container">
    <div class="jumbotron">
        <h1>Test System 1</h1>
        <p>This service is use to monitored device status.</p>
    </div>
    <div class="row">
            <div class="col-md-12">
            <form class="form-inline">
                <div class="form-group" style="float: right;">
                    <button id="showPanel" class="btn btn-default">Add Device</button>
                    <button id="cancel" class="btn btn-danger">Cancel</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12" id="hide">
            <form class="form-inline" name="addNewDeviceForm">
                <div class="form-group">
                    <label for="deviceName">Device Name</label>
                    <input type="text" id="deviceName" class="form-control" placeholder="Device name..." name="deviceName">
                    <label for="deviceIp">Device IP:</label>
                    <input type="text" id="deviceIp" class="form-control" placeholder="Device IP..." name="deviceIp">
                    <button id="addDevice" class="btn btn-default" onclick="addDeviceFunctionAjax(event);">Add</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table id="conversation" class="table table-striped">
                <thead>

                <tr>
                    <th>Device Name</th>
                    <th>Ip Address</th>
                    <th>Hostname</th>
                    <th>Ping</th>
                    <th>SNMP</th>
                </tr>

                </thead>
                <tbody id="tableOfDevices">
                <c:forEach var="device" items="${devices}">
                <tr class ="success">
                <td>${device.name}</td>
                <td>${device.ipAddress}</td>
                <td>No Hostname</td>
                <td>NO Ping</td>
                <td>NO SNMP</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </form>
</div>
</body>
</html>