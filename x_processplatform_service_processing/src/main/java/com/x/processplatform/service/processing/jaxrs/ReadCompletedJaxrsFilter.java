package com.x.processplatform.service.processing.jaxrs;

import javax.servlet.annotation.WebFilter;

import com.x.base.core.application.jaxrs.CipherManagerJaxrsFilter;

@WebFilter(urlPatterns = { "/jaxrs/readcompleted/*" })
public class ReadCompletedJaxrsFilter extends CipherManagerJaxrsFilter {

}
