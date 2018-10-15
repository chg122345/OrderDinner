<%--
 /**
 * @author (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @date 2018-10-15  上午9:55
 *
 * <p>
 * Find a way for success and not make excuses for failure.
 * </p>
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath }/app/detail/style/js/jquery.js"></script>
    <title>登录</title>
</head>
<body>
<div style="margin-top: 100px; margin-left: 45%;align-content: center;">
    <h3>登录信息</h3>
    <form>
        <label for="email">邮箱</label>
        <input id="email" name="email" type="text">
        <br>  <br>
        <label for="password">密码</label>
        <input id="password" name="password" type="password">
        <br>  <br>
        <button id="login" type="button" onclick="userLogin();">登录</button>
        <button type="reset" id="reset" style="margin-left: 120px;">重置</button>
    </form>
</div>
<script type="text/javascript">
    function check() {
        var email = $('#email').val();
        var password = $('#password').val();
        var emailTest = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!emailTest.test(email)){
            alert("邮箱格式不正确");
            return false;
        }
        if (password.length < 5) {
            alert("密码长度不能小于5");
            return false;
        }
        return true;
    }
    function userLogin() {
       if (check()){
           var email = $('#email').val();
           var password = $('#password').val();
           $.ajax({
               url:'${pageContext.request.contextPath }/login',
               data:{email:email,password:password},
               type:'POST',
               success:function (res) {
                   if (res == 200){
                       window.location.href='${pageContext.request.contextPath }/admin'
                   }
               }
           });
       }
    }
    
    
</script>
</body>
</html>
