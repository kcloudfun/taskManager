package com.taskManager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.taskManager.dao.IStudentDao;
import com.taskManager.vo.db.StudentVo;

public class StudentTest extends TaskManagerApplicationTests {

	@Autowired
	private IStudentDao studentDao;
	
	@Test
	public void test1() {
		StudentVo selectStudentByKey = studentDao.selectStudentByKey("1");
		System.out.println(JSON.toJSONString(selectStudentByKey));
	}
}
