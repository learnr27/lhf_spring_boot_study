package com.lhf.springboot.echarts.common;

import com.corundumstudio.socketio.SocketIOServer;
import com.lhf.springboot.echarts.config.MyProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName: ServerRunner
 * @Author: liuhefei
 * @Description: SpringBoot启动之后执行
 * @Date: 2019/7/23 14:57
 */
@Component
@Order(1)
public class ServerRunner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final SocketIOServer server;
    // 下载地址，版本2.1.1：https://bitbucket.org/ariya/phantomjs/downloads/
    private static final String PHANTOM_PATH = "phantomjs";
    @Resource
    private MyProperties p;

    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("ServerRunner 开始启动啦....");
        server.start();
        logger.info("SocketServer 启动成功啦");
        logger.info("点击打开首页：http://localhost:9075");
        //启动socker服务器后，通过phantomjs启动浏览器网页客户端
        //openHtml(p.getLoadJs());
        //logger.info("Phantomjs 启动成功！");
    }

    /*private void openHtml(String loadJs) {
        String cmdStr = PHANTOM_PATH + " " + loadJs + " " + "http://localhost:9075";
        logger.info("cmdStr=" + cmdStr);
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(cmdStr);
        } catch (IOException e) {
            logger.error("执行phantomjs的指令失败！PhantomJs详情参考这里:http://phantomjs.org", e);
        }
    }*/
}
