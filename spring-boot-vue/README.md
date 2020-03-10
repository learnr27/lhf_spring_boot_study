#Spring Boot+Vue+Element-UI前后端分离

来源：（1）https://mp.weixin.qq.com/s/IZqEk07b25pNCbp-EgY0SA           
     （2）https://mp.weixin.qq.com/s/c1ct85FFV4hFmIxyEm5plA            


element-ui官网：https://element.eleme.io/#/zh-CN/component/quickstart


###启动前端项目工程
1. npm install --global vue-cli   //全局安装vue脚手架工具

2. npm install

3. npm run serve  //运行项目


###Spring Boot+Vue｜axios异步请求数据的12种操作

来源：(1)https://mp.weixin.qq.com/s/c1ct85FFV4hFmIxyEm5plA     
     (2)https://mp.weixin.qq.com/s/Qh3renZEsUbRex1-ApKDdg

Vue 中异步请求使用 axios 组件来完成，axios 是一个基于Promise 用于浏览器和 nodejs 的 HTTP 客户端，可以用在浏览器和 node.js 中。 

Vue 工程中使用 axios，首先要安装 axios，命令如下所示。     
npm install axios
       
然后创建 Vue 工程，手动导入 axios 组件，命令如下所示。            
vue add axios


1. GET 请求 + 普遍变量传参          
   axios 异步 GET 请求的方法为 axios.get(url,params).then()           
   url：请求的 URL。            
   params：参数，格式为 {params:{name:value,name:value}}            
   then()：请求成功的回调函数。            

2. GET请求 + JSON传参               
   axios异步GET请求的方法为：axios.get(url, params).then()            
   url: 请求的URL                 
   params：参数，格式为：{params: json}           
   then(): 请求成功的回调函数                  

3. PSOT 请求 + 普遍变量传参        
   axios 异步 POST 请求的方法为 axios.post(url,params).then()         
   url：请求的 URL          
   params：参数，POST 请求中，参数格式不再是  {params:{name:value,name:value}} ，而需要将参数封装到 URLSearchParams 对象中。                
   then()：请求成功的回调函数。            

4. PSOT 请求 + JSON传参            
   axios 异步 POST 请求的方法为 axios.post(url,params).then()          
   url：请求的 URL              
   params：参数，POST 请求中，参数格式不再是  {params:{name:value,name:value}} ，而需要将参数封装到 URLSearchParams 对象中。          
   then()：请求成功的回调函数。           

5. 基于 RESTful GET 请求 + 普遍变量传参             
   基于 RESTful 的 axios 异步 GET 请求的方法为 axios.gett(url).then()              
   url：请求的 URL，直接追加参数。      
   then()：请求成功的回调函数。          

6. 基于 RESTful GET 请求 + JSON 传参         
   基于 RESTful 的 axios 异步 GET 请求的方法为 axios.get(url,params).then()         
   url：请求的 URL。           
   params：参数，格式为  {params:{name:value,name:value}} 。           
   then()：请求成功的回调函数。          

7.  基于 RESTful POST 请求 + 普通变量传参              
    基于 RESTful 的 axios 异步 POST 请求的方法为 axios.post(url).then()            
    url：请求的 URL，直接追加参数。             
    then()：请求成功的回调函数。           

8. 基于 RESTful POST 请求 + JSON 传参            
   基于 RESTful 的 axios 异步 POST 请求的方法为 axios.post(url,params).then()         
   url：请求的 URL。            
   params：参数，需要将参数封装到 URLSearchParams 对象中。            
   then()：请求成功的回调函数。          

9.  基于 RESTful PUT 请求 + 普通变量传参        
    基于 RESTful 的 axios 异步 POST 请求的方法为 axios.put(url).then()           
    url：请求的 URL，直接追加参数。           
    then()：请求成功的回调函数。         

10.  基于 RESTful PUT 请求 + JSON 传参             
     基于 RESTful 的 axios 异步 POST 请求的方法为 axios.put(url,params).then()        
     url：请求的 URL。           
     params：参数，需要将参数封装到 URLSearchParams 对象中。             
     then()：请求成功的回调函数。             

11. 基于 RESTful DELETE 请求 + 普通变量传参             
    基于 RESTful 的 axios 异步 POST 请求的方法为 axios.delete(url).then()          
    url：请求的 URL，直接追加参数。            
    then()：请求成功的回调函数。           

12.  基于 RESTful DELETE 请求 + JSON 传参              
     基于 RESTful 的 axios 异步 POST 请求的方法为 axios.delete(url,params).then()              
     url：请求的 URL。             
     params：参数，格式为  {params:{name:value,name:value}} 。             
     then()：请求成功的回调函数。              



