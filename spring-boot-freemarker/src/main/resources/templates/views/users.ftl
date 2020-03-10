<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css"/>
</head>
<body>
<h1 style="text-align: center; color: forestgreen">用户列表</h1>
<table class="table table-hover">
    <tr>
        <td class="active">姓名</td>
        <td class="success">年龄</td>
        <td class="warning">性别</td>
        <td class="danger">身高</td>
        <td class="info">体重</td>
        <td class="success">手机号</td>
        <td class="warning">邮箱</td>
        <td class="danger">地址</td>
        <td class="info">爱好</td>
        <td class="active">座右铭</td>
    </tr>
    <#if users?? && (users ?size>0)>

        <#list users as user>
            <tr>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.sex}</td>
                <td>${user.height}</td>
                <td>${user.weight}</td>
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td>${user.address}</td>
                <td>${user.hobby}</td>
                <td>${user.motto}</td>
            </tr>
        </#list>
    </#if>

</table>
</body>
</html>
