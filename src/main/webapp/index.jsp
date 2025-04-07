<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <p>Generated at: ${generatedTime}</p>
    <p>Current Directory: ${currentPath}</p>

    <c:if test="${not empty parentPath}">
        <a href="browse?path=${parentPath.replace('\\', '/')}">вернуться назад</a><br>
    </c:if>

    <ul>
        <c:forEach var="file" items="${files}">
            <li>
                <c:choose>
                    <c:when test="${file.isDirectory()}">
                        <a href="browse?path=${file.getAbsolutePath().replace('\\', '/')}">📁 ${file.getName()}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="download?path=${file.getAbsolutePath().replace('\\', '/')}">📄 ${file.getName()}</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </c:forEach>
    </ul>
</body>
</html>