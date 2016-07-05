<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>JPA ORM Test</title>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
  <div class="goodResult">
    <c:choose>
      <c:when test="${!empty sessionScope.products}">
        <table>
          <tr>
            <th>Id</th>
            <th>Product Name</th>
          </tr>
          <c:forEach var="item" items="${products}">
            <tr>
              <td>${item.id}</td>
              <td>${item.productName}</td>
            </tr>
          </c:forEach>
        </table>
      </c:when>
      <c:otherwise>
        <p>No products found</p>
      </c:otherwise>
    </c:choose>
    <p><a href="${pageContext.request.contextPath}/test">Back</a></p>
  </div>
</body>
</html>