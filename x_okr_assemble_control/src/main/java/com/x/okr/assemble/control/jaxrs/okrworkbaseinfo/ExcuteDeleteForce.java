package com.x.okr.assemble.control.jaxrs.okrworkbaseinfo;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.http.ActionResult;
import com.x.base.core.http.EffectivePerson;
import com.x.base.core.http.WrapOutId;
import com.x.base.core.logger.Logger;
import com.x.base.core.logger.LoggerFactory;
import com.x.okr.assemble.control.OkrUserCache;
import com.x.okr.assemble.control.jaxrs.okrworkbaseinfo.exception.GetOkrUserCacheException;
import com.x.okr.assemble.control.jaxrs.okrworkbaseinfo.exception.WorkBaseInfoProcessException;
import com.x.okr.assemble.control.jaxrs.okrworkbaseinfo.exception.WorkIdEmptyException;
import com.x.okr.assemble.control.service.OkrWorkBaseInfoOperationService;
import com.x.okr.entity.OkrWorkBaseInfo;

public class ExcuteDeleteForce extends ExcuteBase {

	private Logger logger = LoggerFactory.getLogger( ExcuteDeleteForce.class );
	private OkrWorkBaseInfoOperationService okrWorkBaseInfoOperationService = new OkrWorkBaseInfoOperationService();
	
	protected ActionResult<WrapOutId> execute( HttpServletRequest request,EffectivePerson effectivePerson, String id ) throws Exception {
		ActionResult<WrapOutId> result = new ActionResult<>();
		OkrWorkBaseInfo okrWorkBaseInfo = null;
		Boolean check = true;
		OkrUserCache  okrUserCache  = null;
		if( check ){
			try {
				okrUserCache = okrUserInfoService.getOkrUserCacheWithPersonName( effectivePerson.getName() );
			} catch ( Exception e ) {
				check = false;
				Exception exception = new GetOkrUserCacheException( e, effectivePerson.getName()  );
				result.error( exception );
				logger.error( e, effectivePerson, request, null);
			}	
		}
		if( check ){
			if( id == null || id.isEmpty() ){
				check = false;
				Exception exception = new WorkIdEmptyException();
				result.error( exception );
			}
		}
		if( check ){
			try{
				okrWorkBaseInfo = okrWorkBaseInfoService.get( id );
			}catch(Exception e){
				check = false;
				Exception exception = new WorkBaseInfoProcessException( e, "查询指定ID的具体工作信息时发生异常。ID：" + id );
				result.error( exception );
				logger.error( e, effectivePerson, request, null);
			}
		}
		if( check ){
			try{
				okrWorkBaseInfoOperationService.deleteForce( id );
			}catch(Exception e){
				check = false;
				Exception exception = new WorkBaseInfoProcessException( e, "工作删除过程中发生异常。"+id );
				result.error( exception );
				logger.error( e, effectivePerson, request, null);
			}
		}
		if( check ){
			if( okrWorkBaseInfo != null ){
				try{
					okrWorkDynamicsService.workDynamic(
							okrWorkBaseInfo.getCenterId(), 
							okrWorkBaseInfo.getId(),
							okrWorkBaseInfo.getTitle(),
							"删除具体工作", 
							effectivePerson.getName(), 
							okrUserCache.getLoginUserName(), 
							okrUserCache.getLoginIdentityName() , 
							"删除具体工作：" + okrWorkBaseInfo.getTitle(), 
							"具体工作删除成功！"
					);
				}catch(Exception e){
					logger.warn( "system save work dynamic got an exception." );
					logger.error( e );
				}
			}else{
				try{
					okrWorkDynamicsService.workDynamic(
							"0000-0000-0000-0000", 
							id,
							"未知",
							"删除具体工作", 
							effectivePerson.getName(), 
							okrUserCache.getLoginUserName(), 
							okrUserCache.getLoginIdentityName() , 
							"删除具体工作：未知", 
							"具体工作删除成功！"
					);
				}catch(Exception e){
					logger.warn( "system save work dynamic got an exception." );
					logger.error( e );
				}
			}
		}
		return result;
	}
	
}