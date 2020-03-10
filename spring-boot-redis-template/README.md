#SpringBoot整合Redis Template实现相关功能
## spring-data-redis针对jedis提供了如下功能：
   
   1.连接池自动管理，提供了一个高度封装的“RedisTemplate”类
   
   2.针对jedis客户端中大量api进行了归类封装,将同一类型操作封装为operation接口
   
   ValueOperations：简单K-V操作
   
   SetOperations：set类型数据操作
   
   ZSetOperations：zset类型数据操作
   
   HashOperations：针对map类型的数据操作
   
   ListOperations：针对list类型的数据操作
   
   3.提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key，即BoundKeyOperations：
   
   BoundValueOperations
   
   BoundSetOperations
   
   BoundListOperations
   
   BoundSetOperations
   
   BoundHashOperations
   
   4.将事务操作封装，有容器控制。
   
   5.针对数据的“序列化/反序列化”，提供了多种可选择策略(RedisSerializer)
   
   JdkSerializationRedisSerializer：POJO对象的存取场景，使用JDK本身序列化机制，将pojo类通过ObjectInputStream/ObjectOutputStream进行序列化操作，最终redis-server中将存储字节序列。是目前最常用的序列化策略。
   
   StringRedisSerializer：Key或者value为字符串的场景，根据指定的charset对数据的字节序列编码成string，是“new String(bytes, charset)”和“string.getBytes(charset)”的直接封装。是最轻量级和高效的策略。
   
   JacksonJsonRedisSerializer：jackson-json工具提供了javabean与json之间的转换能力，可以将pojo实例序列化成json格式存储在redis中，也可以将json格式的数据转换成pojo实例。因为jackson工具在序列化和反序列化时，需要明确指定Class类型，因此此策略封装起来稍微复杂。【需要jackson-mapper-asl工具支持】
   
   OxmSerializer：提供了将javabean与xml之间的转换能力，目前可用的三方支持包括jaxb，apache-xmlbeans；redis存储的数据将是xml工具。不过使用此策略，编程将会有些难度，而且效率最低；不建议使用。【需要spring-oxm模块的支持】
   
## Redis的数据类型
   (1) String字符串       
   string是redis最基本的类型，一个key对应一个value。
   
   string类型是二进制安全的。意思是redis的string可以包含任何数据。比如jpg图片或者序列化的对象 。
   
   string类型是Redis最基本的数据类型，一个键最大能存储512MB。
   
   
   
   (2) 链表     
   redis列表是简单的字符串列表，排序为插入的顺序。列表的最大长度为2^32-1。
   
   redis的列表是使用链表实现的，这意味着，即使列表中有上百万个元素，增加一个元素到列表的头部或尾部的操作都是在常量的时间完成。
   
   可以用列表获取最新的内容（像帖子，微博等），用ltrim很容易就会获取最新的内容，并移除旧的内容。
   
   用列表可以实现生产者消费者模式，生产者调用lpush添加项到列表中，消费者调用rpop从列表中提取，如果没有元素，则轮询去获取，或者使用brpop等待生产者添加项到列表中。
   
 
   
   (3) 集合     
   redis集合是无序的字符串集合，集合中的值是唯一的，无序的。可以对集合执行很多操作，例如，测试元素是否存在，对多个集合执行交集、并集和差集等等。
   
   我们通常可以用集合存储一些无关顺序的，表达对象间关系的数据，例如用户的角色，可以用sismember很容易就判断用户是否拥有某个角色。
   
   在一些用到随机值的场合是非常适合的，可以用 srandmember/spop 获取/弹出一个随机元素。同时，使用@EnableCaching开启声明式缓存支持，这样就可以使用基于注解的缓存技术。注解缓存是一个对缓存使用的抽象，通过在代码中添加下面的一些注解，达到缓存的效果。
   
   
   
   (4) ZSet 有序集合        
   有序集合由唯一的，不重复的字符串元素组成。有序集合中的每个元素都关联了一个浮点值，称为分数。可以把有序看成hash和集合的混合体，分数即为hash的key。
   
   有序集合中的元素是按序存储的，不是请求时才排序的。
   
  
   
   (5) Hash-哈希        
   redis的哈希值是字符串字段和字符串之间的映射，是表示对象的完美数据类型。
   
   哈希中的字段数量没有限制，所以可以在你的应用程序以不同的方式来使用哈希。
   
   
   
