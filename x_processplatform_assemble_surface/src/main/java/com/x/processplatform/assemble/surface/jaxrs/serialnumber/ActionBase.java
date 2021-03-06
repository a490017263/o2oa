package com.x.processplatform.assemble.surface.jaxrs.serialnumber;

import java.util.List;

import com.x.base.core.bean.BeanCopyTools;
import com.x.base.core.bean.BeanCopyToolsBuilder;
import com.x.base.core.exception.ExceptionWhen;
import com.x.base.core.project.jaxrs.StandardJaxrsAction;
import com.x.processplatform.assemble.surface.Business;
import com.x.processplatform.assemble.surface.wrapin.content.WrapInSerialNumber;
import com.x.processplatform.assemble.surface.wrapout.content.WrapOutSerialNumber;
import com.x.processplatform.core.entity.content.SerialNumber;
import com.x.processplatform.core.entity.element.Process;

abstract class ActionBase extends StandardJaxrsAction {

	static BeanCopyTools<SerialNumber, WrapOutSerialNumber> outCopier = BeanCopyToolsBuilder.create(SerialNumber.class,
			WrapOutSerialNumber.class, WrapOutSerialNumber.Excludes);

	static BeanCopyTools<WrapInSerialNumber, SerialNumber> inCopier = BeanCopyToolsBuilder
			.create(WrapInSerialNumber.class, SerialNumber.class, WrapInSerialNumber.Excludes);

	void fillProcessName(Business business, WrapOutSerialNumber wrap) throws Exception {
		Process process = business.process().pick(wrap.getProcess(), ExceptionWhen.none);
		if (null != process) {
			wrap.setProcessName(process.getName());
		}
	}

	void fillProcessName(Business business, List<WrapOutSerialNumber> wraps) throws Exception {
		for (WrapOutSerialNumber o : wraps) {
			this.fillProcessName(business, o);
		}
	}

}
