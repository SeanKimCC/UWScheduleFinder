<%--<!DOCTYPE html>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<title>UW Schedule Finder</title>
<spring:url value="/resources" var="resourcesPath" />
<script src="${resourcesPath}/resources/index.js"></script>

<form>
    TERM CODE: <br><input id="term" type="text" name="term"><br>

    <input id="submit" type="submit" value="Submit">
</form>

</html>

