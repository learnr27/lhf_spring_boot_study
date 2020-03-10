#Springboot整合kafka
kafka与zookeeper的安装以及kafaka的使用可以参考：https://www.imooc.com/article/262427


使用kafka
1、 创建主题，进入Kafka安装目录D:\softPackage\kafka\kafka_2.12-2.0.1，按下Shift+右键，选择“打开命令窗口”选项，打开命令行，输入：
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic lhf-kafka
图片描述
注意：不要关了这个窗口
查看主题输入：
.\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181
图片描述

2、 创建生产者，进入Kafka安装目录D:\softPackage\kafka\kafka_2.12-2.0.1，按下Shift+右键，选择“打开命令窗口”选项，打开命令行，输入：
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic lhf-kafka
图片描述
注意：不要关了这个窗口

3、 创建消费者，进入Kafka安装目录D:\softPackage\kafka\kafka_2.12-2.0.1，按下Shift+右键，选择“打开命令窗口”选项，打开命令行，输入：
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic lhf-kafka --from-beginning



