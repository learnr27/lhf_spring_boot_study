<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎你</title>
    <#--<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.css"/>-->
</head>
<body>
<div>
    <p>欢迎你：</p>
    ${title}
    <#list list as item>
        ${item}<br/>
    </#list>
    ${message}
</div>


</body>
</html>
