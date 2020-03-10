package com.lhf.springboot.pojo;


import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: Student
 * @Author: liuhefei
 * @Description: 学生信息
 * @Date: 2019/7/26 11:29
 */
public class Student {

	/** 学生编号*/
	private Long id;
	/** 学生姓名*/
	private String name;
	/** 学生年龄*/
	private Integer age;
	/**
	 * 获取学生编号
	 * @return  id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置学生编号
	 * @param  id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取学生姓名
	 * @return  name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置学生姓名
	 * @param  name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取学生年龄
	 * @return  age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置学生年龄
	 * @param  age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
