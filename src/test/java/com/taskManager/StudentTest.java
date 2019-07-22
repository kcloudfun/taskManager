package com.taskManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.taskManager.dao.IStudentDao;
import com.taskManager.dao.ITemplateDao;
import com.taskManager.utils.BeanUtils;
import com.taskManager.vo.db.StudentVo;

public class StudentTest extends TaskManagerApplicationTests {

	@Autowired
	private IStudentDao studentDao;

	@Autowired
	private ITemplateDao templateDao;

	@Test
	public void test1() {
		StudentVo selectStudentByKey = studentDao.selectStudentByKey("1");
		System.out.println(JSON.toJSONString(selectStudentByKey));
	}

	@Test
	public void test2() {
		StudentVo student1 = new StudentVo();
		student1.setId("default");
		student1.setStudentName("tom");
		StudentVo student2 = new StudentVo();
		student2.setId("default");
		student2.setStudentName("tom2");
		StudentVo student3 = new StudentVo();
		student3.setId("default");
		student3.setStudentName("tom3");
		
		List<Object> list = new ArrayList<>();
		list.add(student1);
		list.add(student2);
		list.add(student3);
		int a = templateDao.batchInsert("student", BeanUtils.listBean2ListMap(list));
		System.out.println(a);
	}
}
