package com.x.portal.assemble.surface.wrapout;

import java.util.ArrayList;
import java.util.List;

import com.x.base.core.entity.JpaObject;
import com.x.base.core.http.annotation.Wrap;
import com.x.portal.core.entity.Page;

@Wrap(Page.class)
public class WrapOutPage extends Page {

	private static final long serialVersionUID = -8067704098385000667L;

	public static List<String> Excludes = new ArrayList<>(JpaObject.FieldsInvisible);

	
}
