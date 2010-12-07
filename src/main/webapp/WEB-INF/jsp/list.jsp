<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
		
<layout:default title="Your Files">
    <jsp:attribute name="content">
        
		<h2>File Listing: Dropbox/MyFolder</h2>
		
		<ul id="file_list">
			<li class="folder"><a href="list?value=${parentPath}" class="direct">"${parentPath}" Up One Level</a></li>
			
			<c:forEach var="file" items="${files}">
				<li class="file">
                    <a href ="list?value=${file.path}" class="direct">
					<c:out value="${file.name}"/>
                    </a><br />
                    <strong><c:out value="${file.modifiedDate}"/></strong>
					<div class="buttonset">
					<a href="edit">Edit</a>
					<a href="#">Delete</a>
				</div>
				</li>
			</c:forEach>
			
		</ul

    </jsp:attribute>
</layout:default>
