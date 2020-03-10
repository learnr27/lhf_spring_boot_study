#SpringBoot整合elasticsearch
来源：https://www.cnblogs.com/xuwujing/p/8998168.html     
ElasticSearch是一个基于Lucene的搜索服务器，其实就是对Lucene进行封装，提供了 REST API 的操作接口 ElasticSearch作为一个高度可拓展的开源全文搜索和分析引擎，可用于快速地对大数据进行存储，搜索和分析。        
ElasticSearch主要特点：分布式、高可用、异步写入、多API、面向文档 。           
ElasticSearch核心概念：近实时，集群，节点（保存数据），索引，分片（将索引分片），副本（分片可设置多个副本） 。它可以快速地储存、搜索和分析海量数据。          
ElasticSearch使用案例:维基百科、Stack Overflow、Github 等等。            


spring.data.elasticsearch.repositories.enabled = true       
spring.data.elasticsearch.cluster-nodes =127.0.0.1\:9300              
注: 9300 是 Java 客户端的端口。9200 是支持 Restful HTTP 的接口。           
 
更多的配置:             
spring.data.elasticsearch.cluster-name Elasticsearch 集群名。(默认值: elasticsearch)           
spring.data.elasticsearch.cluster-nodes 集群节点地址列表，用逗号分隔。如果没有指定，就启动一个客户端节点。         
spring.data.elasticsearch.propertie 用来配置客户端的额外属性。         
spring.data.elasticsearch.repositories.enabled 开启 Elasticsearch 仓库。(默认值:true。)           






