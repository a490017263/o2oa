package com.x.organization.assemble.control.jaxrs.person;

import com.x.base.core.exception.PromptException;

 class QqDuplicateException extends PromptException {

	private static final long serialVersionUID = 4433998001143598936L;

	 QqDuplicateException(String weixin, String fieldName) {
		super("微信号错误:" + weixin + ", " + fieldName + "已有值重复.");
	}
}
