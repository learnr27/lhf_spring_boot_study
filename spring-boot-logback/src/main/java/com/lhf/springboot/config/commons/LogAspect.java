package com.lhf.springboot.config.commons;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lhf.springboot.config.annotation.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName: LogAspect
 * @Author: liuhefei
 * @Description: AOP切面编程——日志切面
 * @Date: 2019/6/17 11:26
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String STRING_START = "\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n";
    private static final String STRING_END   = "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n";

    //切入点
    @Pointcut("execution(* com.lhf.springboot.controller..*(..))")
    public void serviceLog(){

    }

    //环绕通知
    @Around("serviceLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();

        StringBuffer sb = new StringBuffer();

        Log classAnnotation = targetClass.getAnnotation(Log.class);
        Log methodAnnotation = method.getAnnotation(Log.class);

        if(classAnnotation != null){
            if(classAnnotation.ignore()){
                return joinPoint.proceed();
            }
            sb.append(classAnnotation.value()).append("-");
        }

        if (methodAnnotation != null) {
            if (methodAnnotation.ignore()) {
                return joinPoint.proceed();
            }
            sb.append(methodAnnotation.value());
        }

        String target = targetClass.getName() + "#" + method.getName();

        String params = null;
        params = JSONObject.toJSONStringWithDateFormat(joinPoint.getArgs(), dateFormat, SerializerFeature.WriteMapNullValue);

        log.info(STRING_START + "{} 开始调用--> {} 参数:{}", sb.toString(), target, params);

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeConsuming = System.currentTimeMillis() - start;

        log.info("\n{} 调用结束<-- {} 返回值:{} 耗时:{}ms" + STRING_END, sb.toString(), target, JSONObject.toJSONStringWithDateFormat(result, dateFormat, SerializerFeature.WriteMapNullValue), timeConsuming);
        return result;
    }

}
