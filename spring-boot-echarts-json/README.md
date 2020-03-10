#SpringBoot集成Echarts导出图片

来源：https://www.xncoding.com/2017/08/19/spring/sb-echarts.html

过程：
1. 编写一个RESTful API接口，用来接收生成图表需要的数据，然后向页面推送导出图片的消息请求。        
2. 编写一个WebSocket接口，接收Base64格式的图片编码，然后将其转换为图片保存到磁盘上      
3. 启动应用后打开websocket服务端，并监听页面发送的Echarts图片导出消息        
4. 打开页面，自动连接上websocket服务器，并监听导出图片的消息请求，收到消息后发送Echarts图片导出消息          
5. 后面就可以调用这个RESTful API接口来讲数据可视化为Echarts图片并保存了。         

步骤：
1. 启动应用

2. 打开首页 http://localhost:9075/

3. 运行测试类ApplicationTests.java
