<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout" %>
		
<layout:default title="Sign In with your Dropbox Credentials">
    <jsp:attribute name="content">
        
		<div id="signin">
			<h2>Sign In with your Dropbox Username and Password</h2>
		
			<form id="auth" action="/login" method="post">
				<input type="text" placeholder="Username" name="username">
				<input type="password" placeholder="Password" name="password">
				<input type="submit" value="Go &raquo;">
			</form>
		</div>
		
		<p>Problems signing in? If you&#x27;ve forgotten your username or password, head over to <a href="http://dropbox.com/">Dropbox</a> to work it out.</p>

    </jsp:attribute>
</layout:default>
