<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Registration Form</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="well lead">User Registration Form</div>
    <form:form method="POST" modelAttribute="registryCredentials" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Registry Name</label>

                <div class="col-md-7">
                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="name" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="url">Url</label>

                <div class="col-md-7">
                    <form:input type="text" path="url" id="url" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="url" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="sourceUrl">Source Url</label>

                <div class="col-md-7">
                    <form:input type="text" path="sourceUrl" id="sourceUrl" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="sourceUrl" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="dbName">Schema Name</label>

                <div class="col-md-7">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="dbName" id="dbName" class="form-control input-sm"
                                        disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <form:input type="text" path="dbName" id="dbName" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="dbName" class="help-inline"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="dbUserName">Database Username</label>

                <div class="col-md-7">
                    <form:input type="text" path="dbUserName" id="dbUserName" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="dbUserName" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="dbPassword">Database Password</label>

                <div class="col-md-7">
                    <form:input type="password" path="dbPassword" id="dbPassword" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="dbPassword" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="council">Generic Council</label>

                <div class="col-md-7">
                    <form:checkbox path="council" id="council" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="council" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="facilityRegistry">Facility Registry</label>

                <div class="col-md-7">
                    <form:checkbox path="facilityRegistry" id="facilityRegistry" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="facilityRegistry" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="ministry">Ministry</label>

                <div class="col-md-7">
                    <form:checkbox path="ministry" id="ministry" class="form-control"/>
                    <div class="has-error">
                        <form:errors path="ministry" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/registry/registrylist' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/registry/registrylist' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>