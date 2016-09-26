<%--<!DOCTYPE html>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<title>UW Schedule Finder</title>
<spring:url value="/resources" var="resourcesPath" />
<script src="${resourcesPath}/resources/index.js"></script>

<form>
    COURSE CODE:  <br><input id="course"type="text" name="course"><br>
    SECTION:  <br><input id="section"type="text" name="section"><br>

    <input id="submit" type="submit" value="Submit">
</form>

</html>

