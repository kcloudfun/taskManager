package com.taskManager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.taskManager.vo.db.TaskDbVo;

@Mapper
public interface ITaskDao {

	public List<TaskDbVo> selectTaskList();

}
