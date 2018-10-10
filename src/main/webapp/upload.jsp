<%--
 /**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-06  下午2:59
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="text" name="name">
    <input type="text" name="size">
    <input type="text" name="tableName">
    <input type="text" name="tableStatus">
    <input type="file" name ="files" >
    <input type="file" name ="files" >
    <input type="file" name ="files" >
    <input type="file" name ="files" >
    <button type="submit">submit</button>
</form>

</body>
</html>
