package com.x.cms.assemble.control.factory.element;

import com.x.base.core.cache.ApplicationCache;
import com.x.base.core.entity.JpaObject;
import com.x.base.core.exception.ExceptionWhen;
import com.x.cms.assemble.control.AbstractFactory;
import com.x.cms.assemble.control.Business;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public abstract class ElementFactory extends AbstractFactory {

	public ElementFactory(Business abstractBusiness) throws Exception {
		super(abstractBusiness);
	}

	@SuppressWarnings("unchecked")
	protected <T extends JpaObject> T pick( String flag, Class<T> clz, ExceptionWhen exceptionWhen, String... attributes)
			throws Exception {
		Ehcache cache = ApplicationCache.instance().getCache(clz);
		T t = null;
		Element element = cache.get(flag);
		if (null != element) {
			if (null != element.getObjectValue()) {
				t = (T) element.getObjectValue();
			}
		} else {
			t = this.entityManagerContainer().flag(flag, clz, ExceptionWhen.none, false, attributes);
			if (t != null) {
				this.entityManagerContainer().get(clz).detach(t);
			}
			cache.put(new Element(flag, t));
		}
		if (null == t) {
			if ((null != exceptionWhen) && (exceptionWhen.equals(ExceptionWhen.not_found))) {
				throw new Exception("can not find entity{class:" + clz.getName() + ", flag:" + flag + "}");
			}
		}
		return t;
	}

	

}