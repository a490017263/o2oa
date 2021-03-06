package com.x.okr.assemble.control.service;

import java.util.ArrayList;
import java.util.List;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.okr.assemble.control.Business;
import com.x.organization.core.express.wrap.WrapCompany;
import com.x.organization.core.express.wrap.WrapDepartment;
import com.x.organization.core.express.wrap.WrapIdentity;
import com.x.organization.core.express.wrap.WrapPerson;
import com.x.organization.core.express.wrap.WrapRole;

/**
 * 用户组织公司信息管理服务类
 * @author LIYI
 *
 */
public class OkrUserManagerService {

	/**
	 * 根据员工姓名获取部门名称，多个身份只取第一个部门
	 * @param employeeName
	 * @return
	 * @throws Exception 
	 */
	public String getDepartmentNameByEmployeeName( String employeeName ) throws Exception{
		List<WrapIdentity> identities = null;		
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			identities = business.organization().identity().listWithPerson( employeeName );
			if ( identities.size() == 0 ) {//该员工目前没有分配身份
				throw new Exception( "can not get identity of person:" + employeeName + "." );
			} else {
				return identities.get(0).getDepartment();
			}
		} catch ( Exception e ) {
			throw e;
		}
	}
	
	/**
	 * 根据身份名称获取部门名称
	 * @param identity
	 * @return
	 * @throws Exception 
	 */
	public String getDepartmentNameByIdentity( String identity ) throws Exception{	
		Business business = null;
		WrapDepartment wrapDepartment = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapDepartment = business.organization().department().getWithIdentity( identity );
			if ( wrapDepartment == null ) {//该根据身份无法查询到组织信息
				throw new Exception( "can not get organization of identity:" + identity + "." );
			} else {
				return wrapDepartment.getName();
			}
		} catch ( Exception e ) {
			throw e;
		}
	}
	
	/**
	 * 根据身份名称获取部门名称
	 * @param identity
	 * @return
	 * @throws Exception 
	 */
	public String getCompanyNameByIdentity( String identity ) throws Exception{	
		Business business = null;
		WrapCompany wrapCompany = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapCompany = business.organization().company().getWithIdentity( identity );
			if ( wrapCompany == null ) {//该根据身份无法查询到组织信息
				throw new Exception( "can not get company of identity:" + identity + "." );
			} else {
				return wrapCompany.getName();
			}
		} catch ( Exception e ) {
			throw e;
		}
	}
	
	/**
	 * 根据员工姓名获取公司名称，多个身份只取第一个身份
	 * @param employeeName
	 * @return
	 * @throws Exception 
	 */
	public String getCompanyNameByEmployeeName( String employeeName ) throws Exception{
		String identity = null;
		List<WrapIdentity> identities = null;
		WrapCompany wrapCompany = null;
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			identities = business.organization().identity().listWithPerson( employeeName );
			if ( identities.size() == 0 ) {//该员工目前没有分配身份
				throw new Exception( "can not get identity of person:" + employeeName + "." );
			} else {
				identity = identities.get(0).getName();
			}
			if( identity != null && !identity.isEmpty() ){
				wrapCompany = business.organization().company().getWithIdentity( identity );
			}
			if( wrapCompany != null ){
				return wrapCompany.getName();
			}
		} catch ( Exception e ) {
			throw e;
		}
		return null;
	}
	
	/**
	 * 根据组织名称获取公司名称
	 * @param organizationName
	 * @return
	 * @throws Exception 
	 */
	public String getCompanyNameByOrganizationName( String organizationName ) throws Exception{
		WrapDepartment wrapDepartment = null;
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapDepartment = business.organization().department().getWithName( organizationName );
			if( wrapDepartment != null ){
				return wrapDepartment.getCompany();
			}else{
				throw new Exception( "can not find company info with organization name:" + organizationName );
			}
		} catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * 根据个人姓名，获取人员的第一个身份
	 * @param personName
	 * @return
	 * @throws Exception 
	 */
	public String getFistIdentityNameByPerson( String personName ) throws Exception {
		List<WrapIdentity> identities = null;
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			identities = business.organization().identity().listWithPerson( personName );
			if ( identities.size() == 0 ) {//该员工目前没有分配身份
				throw new Exception( "can not get identity of person:" + personName + "." );
			} else {
				return identities.get(0).getName();
			}
		} catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * 根据公司名称获取所有下级公司名称列表
	 * @param companyName
	 * @return
	 * @throws Exception 
	 */
	public List<String> listSubCompanyNameList(String companyName) throws Exception {
		Business business = null;
		List<WrapCompany> companyList = null;
		List<String> nameList = new ArrayList<String>();
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			companyList = business.organization().company().listSubNested( companyName );
			if( companyList != null && companyList.size() > 0 ){
				for( WrapCompany company : companyList ){
					nameList.add( company.getName() );
				}
			}
		} catch ( Exception e ) {
			throw e;
		}
		return nameList;
	}

	/**
	 * 根据部门名称获取所有下级部门名称列表
	 * @param query_creatorOrganizationName
	 * @return
	 * @throws Exception 
	 */
	public List<String> listSubOrganizationNameList(String organizationName) throws Exception {
		Business business = null;
		List<WrapDepartment> departmentList = null;
		List<String> nameList = new ArrayList<String>();
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			departmentList = business.organization().department().listSubNested( organizationName );
			if( departmentList != null && departmentList.size() > 0 ){
				for( WrapDepartment department : departmentList ){
					nameList.add( department.getName() );
				}
			}
		} catch ( Exception e ) {
			throw e;
		}
		return nameList;
	}

	/**
	 * 查询当前用户是否有指定的身份信息
	 * @param name
	 * @param loginIdentity
	 * @return
	 * @throws Exception 
	 */
	public boolean hasIdentity( String name, String loginIdentity ) throws Exception {
		if( loginIdentity == null || loginIdentity.isEmpty() ){
			throw new Exception( "loginIdentity is null!" );
		}
		Business business = null;
		WrapPerson wrapPerson = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapPerson = business.organization().person().getWithIdentity( loginIdentity );
			if( wrapPerson != null && wrapPerson.getName().equals( name )){
				return true;
			}
		} catch ( Exception e ) {
			throw e;
		}
		return false;
	}

	/**
	 * 根据用户的身份查询用户的姓名
	 * @param identity
	 * @return
	 * @throws Exception 
	 */
	public WrapPerson getUserByIdentity( String identity ) throws Exception {
		if( identity == null || identity.isEmpty() ){
			throw new Exception( "identity is null!" );
		}
		Business business = null;
		WrapPerson wrapPerson = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapPerson = business.organization().person().getWithIdentity( identity );
		} catch ( Exception e ) {
			throw e;
		}
		return wrapPerson;
	}
	
	/**
	 * 根据用户的身份查询用户的姓名
	 * @param identity
	 * @return
	 * @throws Exception 
	 */
	public String getUserNameByIdentity( String identity ) throws Exception {
		if( identity == null || identity.isEmpty() ){
			throw new Exception( "identity is null!" );
		}
		Business business = null;
		WrapPerson wrapPerson = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapPerson = business.organization().person().getWithIdentity( identity );
		} catch ( Exception e ) {
			throw e;
		}
		if( wrapPerson != null ){
			return wrapPerson.getName();
		}
		return null;
	}
	/**
	 * 根据用户姓名查询用户所有的身份信息
	 * @param userName
	 * @return
	 * @throws Exception 
	 */
	public List<WrapIdentity> listUserIdentities(String userName) throws Exception {
		if( userName == null || userName.isEmpty() ){
			throw new Exception( "userName is null!" );
		}
		Business business = null;
		List<WrapIdentity> wrapIdentities = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			wrapIdentities = business.organization().identity().listWithPerson( userName );
		} catch ( Exception e ) {
			throw e;
		}
		return wrapIdentities;
	}

	/**
	 * 根据用户唯一标识来获取用户对象
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	public WrapPerson getUserByUserNumber( String flag ) throws Exception {
		if( flag == null || flag.isEmpty() ){
			throw new Exception( "flag is null!" );
		}
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			return business.organization().person().flag( flag );
		} catch ( Exception e ) {
			throw e;
		}
	}

	/**
	 * 判断用户是否有指定的权限
	 * @param userName
	 * @param roleName
	 * @return
	 * @throws Exception 
	 */
	public boolean isHasRole( String userName, String roleName ) throws Exception {
		if( userName == null || userName.isEmpty() ){
			throw new Exception( "userName is null!" );
		}
		if( roleName == null || roleName.isEmpty() ){
			throw new Exception( "roleName is null!" );
		}
		List<WrapRole> roleList = null;
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			roleList = business.organization().role().listWithPerson( userName );
			if( roleList != null && !roleList.isEmpty() ){
				for( WrapRole role : roleList ){
					if( role.getName().equalsIgnoreCase( roleName )){
						return true;
					}
				}
			}else{
				return false;
			}
		} catch ( Exception e ) {
			throw e;
		}
		return false;
	}
	
	/**
	 * 判断用户是否有指定的权限
	 * @param userName
	 * @param roleName
	 * @return
	 * @throws Exception 
	 */
	public boolean isCompanyWorkManager( String userIdentity ) throws Exception {
		if( userIdentity == null || userIdentity.isEmpty() ){
			throw new Exception( "userIdentity is null!" );
		}
		String[] configValues = null;
		String configValue = null;
		Business business = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			configValue = business.okrConfigSystemFactory().getValueWithConfigCode("COMPANY_WORK_ADMIN");
			if( configValue != null && !configValue.isEmpty() ){
				configValues = configValue.split( "," );
				for( String identityName : configValues ){
					if( identityName.equalsIgnoreCase( userIdentity )){
						return true;
					}
				}
			}else{
				return false;
			}
		} catch ( Exception e ) {
			throw e;
		}
		return false;
	}

	public Boolean isUserIdentityExsits( String identity ) throws Exception {
		if( identity == null || identity.isEmpty() ){
			throw new Exception( "identity is null!" );
		}
		Business business = null;
		String result = null;
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			business = new Business(emc);
			result = business.organization().identity().check( identity );
			if( result == null || result.isEmpty() ){
				return false;
			}else{
				return true;
			}
		} catch ( Exception e ) {
			throw e;
		}
	}
}