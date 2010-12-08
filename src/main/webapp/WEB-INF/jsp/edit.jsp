<%--
  Created by IntelliJ IDEA.
  User: mike
  Date: Dec 6, 2010
  Time: 3:25:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>

<layout:default title="Your Files">
    <jsp:attribute name="content">

  <head><title>Edit View</title></head>
  <body>Place your content here</body>
  <form method="POST" action="/edit">
    <textarea cols=80% rows="70%" name="editbox" label="editbox">${textBox}</textarea>
    <br>
    <input type="submit" value="Save"/>
      |
    <input type="button" value="Cancel"/>
  </form>

    </jsp:attribute>
</layout:default>
