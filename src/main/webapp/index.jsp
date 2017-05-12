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
    <button type="button" class="btn btn-default navbar-btn" onclick="saveSystem(event);">Sign in</button>
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
                    <th>Ping</th>
                    <th>Hostname</th>
                    <th>OS Version</th>
                    <th>UpTime</th>
                    <th>Last Update</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody id="tableOfDevices">
                <c:forEach var="device" items="${devices}">
                <tr class ="success" id=${device.id}>
                <td>${device.name}</td>
                <td>${device.ipAddress}</td>
                <td>${device.pingStatus}</td>
                <td>${device.hostName}</td>
                <td>${device.osVersion}</td>
                <td>${device.upTime}</td>
                <td>${device.lastUpdate}</td>
                <td><a href="#" class="confirm-delete btn mini red-stripe" role="button" data-title="kitty" data-id="2">Delete</a></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </form>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
                            <h3 id="myModalLabel">Delete</h3>
                        </div>
                        <div class="modal-body">
                            <p></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                            <button data-dismiss="modal" class="btn red" id="btnYes">Confirm</button>
                        </div>
</body>
</html>