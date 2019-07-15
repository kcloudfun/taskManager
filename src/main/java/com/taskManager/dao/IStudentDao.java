package com.taskManager.dao;

import org.apache.ibatis.annotations.Mapper;

import com.taskManager.vo.db.StudentVo;

@Mapper
public interface IStudentDao {

	public StudentVo selectStudentByKey(String key);
}
