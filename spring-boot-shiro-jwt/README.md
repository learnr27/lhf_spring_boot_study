#SpringBoot整合shiro和JWT

JSON Web Token（JWT）是一个非常轻巧的规范。这个规范允许我们使用 JWT 在用户和服务器之间传递安全可靠的信息。   

JWT： https://www.cnblogs.com/cjsblog/p/9277677.html

每个用户有对应的角色（user，admin），权限（normal，vip），而 user 角色默认权限为 normal， admin 角色默认权限为 vip（当然，user 也可以是 vip）    

## 权限控制注解 @RequiresRoles， @RequiresPermissions
这两个注解为我们主要的权限控制注解, 如
```
// 拥有 admin 角色可以访问
@RequiresRoles("admin")
```
```
// 拥有 user 或 admin 角色可以访问
@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
```
```
// 拥有 vip 和 normal 权限可以访问
@RequiresPermissions(logical = Logical.AND, value = {"vip", "normal"})
```
```
// 拥有 user 或 admin 角色，且拥有 vip 权限可以访问
@GetMapping("/getVipMessage")
@RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
@RequiresPermissions("vip")
public ResultMap getVipMessage() {
    return resultMap.success().code(200).message("成功获得 vip 信息！");
}
```
当我们写的接口拥有以上的注解时，如果请求没有带有 token 或者带了 token 但权限认证不通过，则会报 UnauthenticatedException 异常，但是我在 ExceptionController 类对这些异常进行了集中处理
```
@ExceptionHandler(ShiroException.class)
public ResultMap handle401() {
    return resultMap.fail().code(401).message("您没有权限访问！");
}
```
这时，出现 shiro 相关的异常时则会返回
```
{
    "result": "fail",
    "code": 401,
    "message": "您没有权限访问！"
}
```
除了以上两种，还有 @RequiresAuthentication ，@RequiresUser 等注解


参考：https://www.cnblogs.com/HowieYuan/p/9259650.html   

