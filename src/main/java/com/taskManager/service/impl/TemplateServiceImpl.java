package com.taskManager.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.taskManager.constant.ObjectSourceEnum;
import com.taskManager.dao.ITemplateDao;
import com.taskManager.service.ITemplateService;
import com.taskManager.utils.BeanUtils;

@Service
public class TemplateServiceImpl implements ITemplateService {

	private final static Logger log = LoggerFactory.getLogger(TemplateServiceImpl.class);

	@Autowired
	private ITemplateDao templateDao;

	@Override
	public Boolean insertOne(String tableName, Object obj) {
		log.info("插入一条数据开始，参数为：" + JSON.toJSONString(obj));
		Boolean flg = false;
		int a = templateDao.oneInsert(tableName, BeanUtils.bean2map(obj, ObjectSourceEnum.NORMAL.getValue()));
		if (a == 1) {
			log.info("插入一条数据成功，参数为：" + JSON.toJSONString(obj));
			flg = true;
		} else {
			log.error("插入失败，参数为：" + JSON.toJSONString(obj));
		}
		return flg;
	}

}
