#Springboot集成Ehcache实现缓存功能

spring-boot-starter-cache为Spring Boot应用程序提供缓存支持

ehcache提供了Ehcache的缓存实现

cache-api 提供了基于JSR-107的缓存规范


在启动类上添加注解 @EnableCaching注解开启Spring Boot应用程序缓存功能 


使用@Cacheable注解对业务方法进行注释，告诉Spring Boot该方法中产生的数据需要加入到缓存中      
@Cacheable可以标记在一个方法上，也可以标记在一个类上，当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的。对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法。Spring在缓存方法的返回值时是以键值对进行缓存的，值就是方法的返回结果。

@Cacheable可以指定三个属性，value、key和condition。         
value属性指定cache的名称（即选择ehcache.xml中哪种缓存方式存储）     
key属性是用来指定Spring缓存方法的返回结果时对应的key的。该属性支持SpringEL表达式。     
当我们没有指定该属性时，Spring将使用默认策略生成key。我们可以使用“#参数名”或者“#p参数index”。


@CacheEvict是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。     
@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation。      
其中value、key和condition的语义与@Cacheable对应的属性类似；allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。      
当指定了allEntries为true时，Spring Cache将忽略指定的key。有的时候我们需要Cache一下清除所有的元素，这比一个一个清除元素更有效率。       



参考：https://blog.csdn.net/dreamhai/article/details/80642010




