package com.lhf.springboot.benchmark.json.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Person
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/7/23 11:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private FullName fullName;
    private int age;
    private Date birthday;
    private List<String> hobbies;
    private Map<String, String> clothes;
    private List<Person> friends;

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Person [name=" + name + ", fullName=" + fullName + ", age="
                + age + ", birthday=" + birthday + ", hobbies=" + hobbies
                + ", clothes=" + clothes + "]\n");
        if (friends != null) {
            str.append("Friends:\n");
            for (Person f : friends) {
                str.append("\t").append(f);
            }
        }
        return str.toString();
    }
}
