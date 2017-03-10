package com.x.organization.assemble.personal.jaxrs.password;

import com.x.base.core.exception.PromptException;

class TwicePasswordNotMatchException extends PromptException {

	private static final long serialVersionUID = -3885997486474873786L;

	TwicePasswordNotMatchException() {
		super("两次输入的新密码不匹配.");
	}

}
