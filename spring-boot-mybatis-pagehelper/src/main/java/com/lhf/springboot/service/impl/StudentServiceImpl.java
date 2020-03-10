package com.lhf.springboot.service.impl;

import com.lhf.springboot.dao.BaseDao;
import com.lhf.springboot.dao.cluster.StudentDao;
import com.lhf.springboot.pojo.Student;
import com.lhf.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: StudentServiceImpl
 * @Author: liuhefei
 * @Description: 学生接口实现类
 * @Date: 2019/7/26 11:32
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student>  implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	protected BaseDao<Student> getMapper() {
		return this.studentDao;
	}

}
