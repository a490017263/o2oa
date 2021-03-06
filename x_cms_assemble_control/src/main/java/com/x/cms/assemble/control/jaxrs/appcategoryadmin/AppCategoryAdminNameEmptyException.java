package com.x.cms.assemble.control.jaxrs.appcategoryadmin;

import com.x.base.core.exception.PromptException;

class AppCategoryAdminNameEmptyException extends PromptException {

	private static final long serialVersionUID = 1859164370743532895L;

	AppCategoryAdminNameEmptyException() {
		super("应用栏目分类管理员配置信息中管理员姓名为空，无法继续保存数据。" );
	}
}
