package com.lhf.springboot.pojo;


import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName: User
 * @Author: liuhefei
 * @Description: 用户信息
 * @Date: 2019/7/26 11:29
 */
public class User {
	/** 编号*/
	private Long id;
	/** 姓名*/
	private String name;
	/** 年龄*/
	private Integer age;
	/**
	 * 获取编号
	 * @return  id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置编号
	 * @param  id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取姓名
	 * @return  name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置姓名
	 * @param  name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取年龄
	 * @return  age
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置年龄
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
