package com.angshou.mpdemo01.entiy;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

/**
 * @author adminPC--昂首灬
 * @date 2021-06-28 10:03
 * @description
 */


@Data
public class User {

	@TableId(type = IdType.ID_WORKER)
	private Long id;

	private String name;
	private Integer age;
	private String email;

	@TableField(fill = FieldFill.INSERT)
	private Date createTime;
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date updateTime;


	@Version
	@TableField(fill = FieldFill.INSERT)
	private Integer version;

	@TableLogic
	private Integer deleted;
}
