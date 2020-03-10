#SpringBoot参数校验validate
### validation:    
导入javax.validation.constraints.* 
     
注解     说明      
@Valid  开启参数验证,@Valid是javax.validation里的。        
@Validated 开启参数验证, @Validated是@Valid 的一次封装，是spring提供的校验机制使用，有分组验证功能。@Valid不提供分组功能             


@DateTimeFormat(pattern="MM/dd/yyyy")


@NotEmpty、@NotNull、@NotBlank 的区别:    
@NotEmpty 用在集合上面(不能注释枚举)    
@NotBlank用在String上面    
@NotNull用在所有类型上面      


JSR提供的校验注解：         
@Null   被注释的元素必须为 null    
@NotNull    被注释的元素必须不为 null    
@AssertTrue     被注释的元素必须为 true    
@AssertFalse    被注释的元素必须为 false    
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内    
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内    
@Past   被注释的元素必须是一个过去的日期    
@Future     被注释的元素必须是一个将来的日期    
@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式   
@Length(min, max) 验证字符串的长度，介于min与max之间       


Hibernate Validator提供的校验注解：  
@NotBlank(message =)   验证字符串非null，且长度必须大于0 ,不同于@NotEmpty，@NotBlank只应用于字符串且在比较时会去除字符串的空格        
@Email  被注释的元素必须是电子邮箱地址    
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内    
@NotEmpty   被注释的字符串的必须非空    
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内


错误信息配置文件: ValidationMessages.properties      
ValidationMessages.properties 文件的编码为ASCII。数据类型为 key value 。     
