package com.lhf.springboot.tags;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyTagDirective
 * @Author: liuhefei
 * @Description: 自定义tag
 * @Date: 2019/9/12 16:14
 */
@Component
public class MyTagDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Integer tagId = -1;
        String tagName = "";

        //自己的逻辑 todo
        Map<String, Object> result = new HashMap<>(2);
        if(map.containsKey("tagId")){
            tagId = Integer.parseInt(map.get("tagId").toString());
        }

        if(map.containsKey("tagName")){
            tagName = (String)map.get("tagName").toString();
        }

        result.put("tagId", tagId);
        result.put("tagName", tagName);

        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_29);
        environment.setVariable("myTag", builder.build().wrap(result));
        templateDirectiveBody.render(environment.getOut());
    }
}
