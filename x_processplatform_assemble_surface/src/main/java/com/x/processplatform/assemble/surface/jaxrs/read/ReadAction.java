package com.x.processplatform.assemble.surface.jaxrs.read;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonElement;
import com.x.base.core.bean.NameValueCountPair;
import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.http.HttpMediaType;
import com.x.base.core.http.WrapOutCount;
import com.x.base.core.http.WrapOutId;
import com.x.base.core.http.WrapOutMap;
import com.x.base.core.http.annotation.HttpMethodDescribe;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.base.core.project.jaxrs.ResponseFactory;
import com.x.base.core.project.jaxrs.StandardJaxrsAction;
import com.x.processplatform.assemble.surface.wrapin.content.WrapInFilter;
import com.x.processplatform.assemble.surface.wrapin.content.WrapInRead;
import com.x.processplatform.assemble.surface.wrapout.content.WrapOutRead;

@Path("read")
public class ReadAction extends StandardJaxrsAction {

	private static Logger logger = LoggerFactory.getLogger(ReadAction.class);

	@HttpMethodDescribe(value = "列示当前用户的Read对象,下一页.", response = WrapOutRead.class)
	@GET
	@Path("list/{id}/next/{count}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listNext(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListNext().execute(effectivePerson, id, count);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示当前用户的Read对象,上一页.", response = WrapOutRead.class)
	@GET
	@Path("list/{id}/prev/{count}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listPrev(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListPrev().execute(effectivePerson, id, count);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示指定应用当前用户的Read对象,下一页.", response = WrapOutRead.class)
	@GET
	@Path("list/{id}/next/{count}/application/{applicationFlag}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listNextWithApplication(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count, @PathParam("applicationFlag") String applicationFlag) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListNextWithApplication().execute(effectivePerson, id, count, applicationFlag);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示指定应用当前用户的Read对象,上一页.", response = WrapOutRead.class)
	@GET
	@Path("list/{id}/prev/{count}/application/{applicationFlag}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listPrevWithApplication(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count, @PathParam("applicationFlag") String applicationFlag) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListPrevWithApplication().execute(effectivePerson, id, count, applicationFlag);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示指定流程当前用户的Read对象,下一页.", response = WrapOutRead.class)
	@GET
	@Path("list/{id}/next/{count}/process/{processFlag}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listNextWithProcess(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count, @PathParam("processFlag") String processFlag) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListNextWithProcess().execute(effectivePerson, id, count, processFlag);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示指定流程当前用户的Read对象,上一页.", response = WrapOutRead.class)
	@GET
	@Path("list/{id}/prev/{count}/process/{processFlag}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listPrevWithProcess(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count, @PathParam("processFlag") String processFlag) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListPrevWithProcess().execute(effectivePerson, id, count, processFlag);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "统计当前用户在指定应用下的待阅，按应用分类.", response = NameValueCountPair.class)
	@GET
	@Path("list/count/application")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listCountWithApplication(@Context HttpServletRequest request) {
		ActionResult<List<NameValueCountPair>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListCountWithApplication().execute(effectivePerson);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "统计当前用户在指定应用下的待办，按流程分类.", response = NameValueCountPair.class)
	@GET
	@Path("list/count/application/{applicationFlag}/process")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listCountWithProcess(@Context HttpServletRequest request,
			@PathParam("applicationFlag") String applicationFlag) {
		ActionResult<List<NameValueCountPair>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListCountWithProcess().execute(effectivePerson, applicationFlag);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "获取待阅内容,", response = WrapOutRead.class)
	@GET
	@Path("{id}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get(@Context HttpServletRequest request, @PathParam("id") String id) {
		ActionResult<WrapOutRead> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionGet().execute(effectivePerson, id);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "保存待阅并创建已阅.", request = WrapInRead.class, response = WrapOutId.class)
	@POST
	@Path("{id}/processing")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response processing(@Context HttpServletRequest request, @PathParam("id") String id,
			JsonElement jsonElement) {
		ActionResult<WrapOutId> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionProcessing().execute(effectivePerson, id, jsonElement);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, jsonElement);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "更新待阅内容,仅更新待阅意见.", request = WrapInRead.class, response = WrapOutId.class)
	@PUT
	@Path("{id}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(@Context HttpServletRequest request, @PathParam("id") String id, JsonElement jsonElement) {
		ActionResult<WrapOutId> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionUpdate().execute(effectivePerson, id, jsonElement);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, jsonElement);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "获取制定人员的待阅数量,没有权限限制.", response = WrapOutCount.class)
	@GET
	@Path("count/{credential}")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response countWithPerson(@Context HttpServletRequest request, @PathParam("credential") String credential) {
		ActionResult<WrapOutCount> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionCountWithPerson().execute(credential);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "获取可用与filter的分类值", response = Map.class)
	@GET
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("filter/attribute")
	public Response filterAttribute(@Context HttpServletRequest request) {
		ActionResult<Map<String, List<NameValueCountPair>>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionFilterAttribute().execute(effectivePerson);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示当前用户过滤的Read,下一页.", response = WrapOutRead.class, request = WrapInFilter.class)
	@POST
	@Path("list/{id}/next/{count}/filter")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listNextWithFilter(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count, JsonElement jsonElement) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListNextFilter().execute(effectivePerson, id, count, jsonElement);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, jsonElement);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示当前用户过滤的Read,上一页.", response = WrapOutRead.class, request = WrapInFilter.class)
	@POST
	@Path("list/{id}/prev/{count}/filter")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response listPrevWithFilter(@Context HttpServletRequest request, @PathParam("id") String id,
			@PathParam("count") Integer count, JsonElement jsonElement) {
		ActionResult<List<WrapOutRead>> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionListPrevFilter().execute(effectivePerson, id, count, jsonElement);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, jsonElement);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "列示指定待阅后续的所有Work和WorkCompleted.", response = WrapOutMap.class)
	@GET
	@Path("{id}/reference")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response reference(@Context HttpServletRequest request, @PathParam("id") String id) {
		ActionResult<WrapOutMap> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ActionReference().execute(effectivePerson, id);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "管理删除待阅.", response = WrapOutId.class)
	@DELETE
	@Path("{id}/manage")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response manageDelete(@Context HttpServletRequest request, @PathParam("id") String id) {
		ActionResult<WrapOutId> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ManageDelete().execute(effectivePerson, id);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, null);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "管理待阅转已阅.", response = WrapOutId.class)
	@PUT
	@Path("{id}/completed/manage")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response manageCompleted(@Context HttpServletRequest request, @PathParam("id") String id,
			JsonElement jsonElement) {
		ActionResult<WrapOutId> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ManageCompleted().execute(effectivePerson, id, jsonElement);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, jsonElement);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}

	@HttpMethodDescribe(value = "重置待阅，将之前的待办转为已办,opinion:办理意见,,identityList:新的待阅人.", response = WrapOutId.class)
	@PUT
	@Path("{id}/reset/manage")
	@Produces(HttpMediaType.APPLICATION_JSON_UTF_8)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response manageRead(@Context HttpServletRequest request, @PathParam("id") String id, JsonElement jsonElement) {
		ActionResult<WrapOutId> result = new ActionResult<>();
		EffectivePerson effectivePerson = this.effectivePerson(request);
		try {
			result = new ManageReset().execute(effectivePerson, id, jsonElement);
		} catch (Exception e) {
			logger.error(e, effectivePerson, request, jsonElement);
			result.error(e);
		}
		return ResponseFactory.getDefaultActionResultResponse(result);
	}
}