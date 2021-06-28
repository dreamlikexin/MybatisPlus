package com.angshou.mpdemo01.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author adminPC--昂首灬
 * @date 2021-06-28 10:44
 * @description
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("createTime",new Date(),metaObject);
		this.setFieldValByName("updateTime",new Date(),metaObject);


		this.setFieldValByName("version",1,metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime",new Date(),metaObject);
	}
}
