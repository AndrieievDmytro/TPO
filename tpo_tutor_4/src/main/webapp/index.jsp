<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1">
</head>
<body>

<form>
    <table>
        <tr>
            <td>First Number</td>
                <td><input type="number" size="40" name="first"></td>
        </tr>
        <tr>
            <td>Second Number</td>
            <td><input type="number" size="40" name="second"></td>
        </tr>
        <tr>
            <td>
                <button formaction="${pageContext.request.contextPath}/adding"  formmethod="get"  type="submit">GET</button>
            </td>
        </tr>
        <tr>
            <td>
                <button formaction="${pageContext.request.contextPath}/adding"  formmethod="post"  type="submit">POST</button></td>
        </tr>
    </table>
</form>

</body>
</html>

<%--${pageContext.request.contextPath}--%>
<%--${pageContext.request.contextPath}--%>
