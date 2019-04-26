package nc.impl.pim.pimmob.prv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pm.util.db.DBAccessUtil;
import nc.itf.pim.focusproject.mob.IFocusProjectMobService;
import nc.itf.pim.pimmob.mob.IQueryContextMobService;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.pubitf.rbac.IFunctionPermissionPubService;
import nc.pubitf.rbac.IUserPubService;
import nc.vo.org.OrgVO;
import nc.vo.pim.focusproject.mob.FocusProjectMobVO;
import nc.vo.pim.project.mob.ProjectMobVO;
import nc.vo.pim.supplierpsn.SupplierRole;
import nc.vo.pm.mobile.base.MobileCommonKeyConst;
import nc.vo.pm.mobile.base.MobileResultProcessor;
import nc.vo.pm.mobile.base.PMMobVOResultSetBaseProcessor;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.pm.util.ArrayUtil;
import nc.vo.pm.util.StringUtil;
import nc.vo.pmbd.common.consts.FuncCodeConst;
import nc.vo.pmbd.mob.util.PMMobilePubUtils;
import nc.vo.pmpub.common.MappedBaseVO;
import nc.vo.pmpub.common.utils.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.sm.UserVO;
import nc.vo.sm.enumfactory.UserIdentityTypeEnumFactory;

/**
 * 移动登录应用服务实现类
 * 获取初始化信息
 * 
 * @version gh3.0
 * @author guofya
 * @time 2017-3-14 下午1:46:30
 */
public class QueryContextMobServiceImpl implements IQueryContextMobService {
	
	@Override
	public Map<String, Object> queryContext(Map<String,Object> param) {
		MobileResultProcessor resultProcessor = new MobileResultProcessor();
		Map<String,Object> result = new HashMap<String,Object>();

		String groupId = null;
		String userId = null;
		String username = PMMobilePubUtils.changeString(param.get(MobileCommonKeyConst.USERNAME));
		// 查询userid和groupid
		try {
			queryUseridByUsername(username, result);
			groupId = (String) result.get(MobileCommonKeyConst.GROUPID);
			userId = (String) result.get(MobileCommonKeyConst.USERID);
			// 预防智汇港华登录，没有初始化环境变量登录信息
			InvocationInfoProxy.getInstance().setGroupId(groupId);
			InvocationInfoProxy.getInstance().setUserId(userId);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		// 根据用户ID查询功能节点权限
		try {
			getAuthInfoByUserId(userId, groupId, result, resultProcessor);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		// 获取用户有权限的业务单元
		try {
			queryOrgs(groupId, userId, result, resultProcessor);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		// 获取用户基本信息（用户名称、参建单位）
		try {
			getPsnInfoByUserId(userId, result);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		
		// 查询关注项目
		Map<String, Object> mobileResultVO = PMProxy.lookup(
				IFocusProjectMobService.class)
				.queryFocusProjectsByUserId(param);
		// mobService间调用时，需要判断异常
		if ((Integer) mobileResultVO.get(MobileCommonKeyConst.RESULT_CODE) == 
				MobileCommonKeyConst.FAILURE) {
			resultProcessor.setErrorMSGAndResultCode((String) mobileResultVO
					.get(MobileCommonKeyConst.ERROR_MSG));
			return resultProcessor.getMobileResultVO();
		}
		result.put(MobileCommonKeyConst.FOCUSPROJECTLIST, mobileResultVO.get(MobileCommonKeyConst.RESULT));
		
		// 设置当前组织
		try {
			setDefaultOrg(result);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		resultProcessor.setResult(result);
		return resultProcessor.getMobileResultVO();
	}
	
	/**
	 * 根据用户编码查询userId和groupId
	 * 
	 * @author guofya
	 * @date 2017-3-14
	 * @param 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void queryUseridByUsername(String username, Map<String,Object> result) throws BusinessException {
		StringBuffer querySql = new StringBuffer();
		SQLParameter parameter = new SQLParameter();
		parameter.addParam(username);
		querySql.append(" select cuserid userid, pk_group groupid from sm_user where user_code = ? ");

		MappedBaseVO resultVO = new MappedBaseVO();
		resultVO.setAttributeValue("userid", "");
		resultVO.setAttributeValue("groupid", "");
		
		List<MappedBaseVO> queryResults = new ArrayList<MappedBaseVO>();
		//查询userid和groupid
		queryResults = (List<MappedBaseVO>) new DBAccessUtil().executeQuery(querySql
					.toString(), parameter, new PMMobVOResultSetBaseProcessor(resultVO));
		
		if (queryResults != null && queryResults.size() > 0) {
			MappedBaseVO vo = queryResults.get(0);

			result.put(MobileCommonKeyConst.GROUPID, (String) vo.getAttributeValue("groupid"));
			result.put(MobileCommonKeyConst.USERID, (String) vo.getAttributeValue("userid"));
		} 
	}
	
	/**
	 * 获取用户有权限的业务单元
	 * 
	 * @author guofya
	 * @date 2017-3-28
	 * @param 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private void queryOrgs(String groupId, String userId, Map<String, Object> result, MobileResultProcessor resultProcessor) throws BusinessException {
		OrgVO[] permOrgs = PMMobilePubUtils.queryUserPermOrgs(groupId, userId);
		if (ArrayUtil.isEmpty(permOrgs)) {
			resultProcessor.setErrorLevel(MobileCommonKeyConst.ERROR_LEVEL_HIGH);
			throw new BusinessException("移动功能权限对应所属角色没有分配权限组织！");
		}
		List<Map> orgListMap = new ArrayList<Map>();
		List<String> pkorgList = new ArrayList<String>();
		Map<String, Object> orgMap = null;
		for (OrgVO permOrg : permOrgs) {
			orgMap = new HashMap<String, Object>();
			orgMap.put("pk_org", permOrg.getPk_org());
			orgMap.put("name", permOrg.getName());
			orgMap.put("shortname", permOrg.getShortname());
			orgMap.put("code", permOrg.getCode());
			orgMap.put("is_default_focus", "N");
			
			pkorgList.add(permOrg.getPk_org());
			orgListMap.add(orgMap);
		}
		result.put(MobileCommonKeyConst.PERMORGS, pkorgList);
		result.put(MobileCommonKeyConst.PERMORGLISTMAP, orgListMap);
	}
	
	/**
	 * 根据用户ID获取用户基本信息
	 * 用户名称、参见单位
	 * 
	 * @author guofya
	 * @date 2017-3-14
	 * @param 
	 * @return
	 */
	private void getPsnInfoByUserId(String userId, Map<String, Object> result)
			throws BusinessException {
		UserVO[] userVOs = PMProxy.lookup(IUserPubService.class)
				.getUsersByPKs(new String[] { userId });
		// 用户身份校验
		judgeIdentity(userVOs[0], result);
		// 用户名称
		result.put(MobileCommonKeyConst.USER_NAME, userVOs[0].getUser_name());
		
	}
	
	/**
	 * 判断校验用户身份
	 * 
	 * @author guofya
	 * @date 2017-3-14
	 * @param 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void judgeIdentity(UserVO userVO, Map<String, Object> result) throws BusinessException {
		Integer identityType = userVO.getBase_doc_type();
		String cuserId = userVO.getCuserid();
		if (null == identityType || 
				!(UserIdentityTypeEnumFactory.TYPE_PERSON == identityType || 
				UserIdentityTypeEnumFactory.TYPE_PROVIDER == identityType)) {
			throw new BusinessException("用户未关联人员或关联人员类型不对！");
		}
		// 身份
		String mobIdentity = null;
		if (UserIdentityTypeEnumFactory.TYPE_PERSON == identityType) {
			mobIdentity = "甲方";
		}
		if (UserIdentityTypeEnumFactory.TYPE_PROVIDER == identityType) {
			SQLParameter parameter = new SQLParameter();
			parameter.addParam(cuserId);
			String supplierSQL = " select pk_supplier,supplier_role from pm_supplierpsn where user_code = ? and dr = 0 ";
			List resultList = (List) new DBAccessUtil().executeQuery(supplierSQL, parameter, new ArrayListProcessor());

			// 如果没有关联供应商人员
			if (ListUtil.isEmpty(resultList)) {
				throw new BusinessException("用户未关联人员或关联人员类型不对！");
			}
			
			if (SupplierRole.contraction == (Integer) ((Object[]) resultList.get(0))[1]) {
				mobIdentity = "施工方";
			} else if (SupplierRole.monitor == (Integer) ((Object[]) resultList.get(0))[1]) {
				mobIdentity = "监理";
			} else if (SupplierRole.designer == (Integer) ((Object[]) resultList.get(0))[1]) {
				mobIdentity = "设计方";
			}
			// 监理、施工人员  缓存施工单位
			String pk_supplier = (String) ((Object[]) resultList.get(0))[0];
			result.put(MobileCommonKeyConst.PK_SUPPLIER, pk_supplier);
		}
		// 移动端登录用户身份
		result.put(MobileCommonKeyConst.USER_IDENTITY, mobIdentity);
	}
	
	/**
	 * 根据用户ID查询功能节点权限
	 * 
	 * @author guofya
	 * @date 2017-3-14
	 * @param 
	 * @return
	 */
	private void getAuthInfoByUserId(String userId, String groupId, Map<String, Object> result, MobileResultProcessor resultProcessor) throws BusinessException{
		String[] funCodes = PMProxy.lookup(IFunctionPermissionPubService.class)
				.getUserPermissionFuncNode(userId, groupId);
		if (ArrayUtil.isEmpty(funCodes)
				|| ArrayUtil.indexOf(funCodes, FuncCodeConst.PMMOBILE) == -1) {
			resultProcessor.setErrorLevel(MobileCommonKeyConst.ERROR_LEVEL_HIGH);
			throw new BusinessException("此用户没有分配工程移动权限！");
		}
		
		/************************* 首页功能节点 *************************/
		Map<String,String> authInfo = new HashMap<String,String>();
		int i = 0;
		// 初始化为都没有权限
		// 质量安全问题
		authInfo.put(MobileCommonKeyConst.PROJECTISSUEMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROBLEMMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PROJECTISSUEMOB, "Y");
			authInfo.put("image" + i, "myp_xmwt_new2x.png");
			authInfo.put("text" + i, "质量安全问题");
			authInfo.put("funcode" + i, FuncCodeConst.PROBLEM);
			i++;
		}
		// 旁站记录
		authInfo.put(MobileCommonKeyConst.SIDEINSPECTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SIDEQUALITYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.SIDEINSPECTIONMOB, "Y");
			authInfo.put("image" + i, "myp_pzjc.png");
			authInfo.put("text" + i, "旁站记录");
			authInfo.put("funcode" + i, FuncCodeConst.SIDEQUALITY);
			i++;
		}
		// 平行检验
		authInfo.put(MobileCommonKeyConst.PARAINSPECTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PARAQUALITYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PARAINSPECTIONMOB, "Y");
			authInfo.put("image" + i, "myp_pxjc.png");
			authInfo.put("text" + i, "平行检验");
			authInfo.put("funcode" + i, FuncCodeConst.PARAQUALITY);
			i++;
		}
		// 质量安全巡查
		authInfo.put(MobileCommonKeyConst.QUALITYINSPECTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.QUALITYPATROLMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.QUALITYINSPECTIONMOB, "Y");
			authInfo.put("image" + i, "myp_xmjc_new2x.png");
			authInfo.put("text" + i, "质量安全巡查");
			authInfo.put("funcode" + i, FuncCodeConst.QUALITYPATROL);
			i++;
		}
		// 项目预验收
		authInfo.put(MobileCommonKeyConst.PRECHECK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PECHECKMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PRECHECK, "Y");
			authInfo.put("image" + i, "myp_xmyys.png");
			authInfo.put("text" + i, "项目预验收");
			authInfo.put("funcode" + i, FuncCodeConst.PREACCEPT);
			i++;
		}
		// 综合验收
		authInfo.put(MobileCommonKeyConst.ALLCHECK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.ALCHECKMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.ALLCHECK, "Y");
			authInfo.put("image" + i, "myp_zhys.png");
			authInfo.put("text" + i, "综合验收");
			authInfo.put("funcode" + i, FuncCodeConst.FINNALACCEPT);
			i++;
		}
		// 施工记录
		authInfo.put(MobileCommonKeyConst.CONSTRUCTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.CONSTQUALITYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.CONSTRUCTIONMOB, "Y");
			authInfo.put("image" + i, "myp_sgjl.png");
			authInfo.put("text" + i, "施工记录");
			authInfo.put("funcode" + i, FuncCodeConst.CONSTQUALITY);
			i++;
		}
		// 工序报验
		authInfo.put(MobileCommonKeyConst.PROCESSCHECK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PSCHECKMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PROCESSCHECK, "Y");
			authInfo.put("image" + i, "myp_gxby.png");
			authInfo.put("text" + i, "工序报验");
			authInfo.put("funcode" + i, FuncCodeConst.PSCHECK);
			i++;
		}
		// 隐蔽工程验收
		authInfo.put(MobileCommonKeyConst.HIDDEDPROJECTMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HIDDEDPROJECTMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.HIDDEDPROJECTMOB, "Y");
			authInfo.put("image" + i, "myp_ybgcys.png");
			authInfo.put("text" + i, "隐蔽工程验收");
			authInfo.put("funcode" + i, FuncCodeConst.HIDDEDPROJECT);
			i++;
		}
		// 电熔焊接拍照
		authInfo.put(MobileCommonKeyConst.WELDPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.ELEWELDINGPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.WELDPICMOB, "Y");
			authInfo.put("image" + i, "myp_drpz.png");
			authInfo.put("text" + i, "电熔焊接拍照");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// 热熔焊接拍照 
		authInfo.put(MobileCommonKeyConst.WELDPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HOTWELDINGPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.WELDPICMOB, "Y");
			authInfo.put("image" + i, "myp_hkpz.png");
			authInfo.put("text" + i, "热熔焊接拍照");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// 防腐补口拍照
		authInfo.put(MobileCommonKeyConst.CORROSIONPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PATCHPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.CORROSIONPICMOB, "Y");
			authInfo.put("image" + i, "myp_ffpz.png");
			authInfo.put("text" + i, "防腐补口拍照");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// 其它工序拍照
		authInfo.put(MobileCommonKeyConst.PROCESSPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.OTHERPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.PROCESSPICMOB, "Y");
			authInfo.put("image" + i, "myp_gxpz.png");
			authInfo.put("text" + i, "其他工序拍照");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// 关键进度
		authInfo.put(MobileCommonKeyConst.KEYPROGRESSMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.KEYPROGRESSMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.KEYPROGRESSMOB, "Y");
			authInfo.put("image" + i, "myp_indexmeasure.png");
			authInfo.put("text" + i, "形象进度");
			authInfo.put("funcode" + i, FuncCodeConst.KEYPROGRESS);
			i++;
		}
		// 指标进度
		authInfo.put(MobileCommonKeyConst.INDEXPROGRESSMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.INDEXPROGRESSMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.INDEXPROGRESSMOB, "Y");
			authInfo.put("image" + i, "myp_index.png");
			authInfo.put("text" + i, "指标进度");
			authInfo.put("funcode" + i, FuncCodeConst.INDEXPROGRESS);
			i++;
		}
		// 工作联系
		authInfo.put(MobileCommonKeyConst.WORKCONTACTMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.WORKCONTACTMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.WORKCONTACTMOB, "Y");
			authInfo.put("image" + i, "myp_fqlx2x.png");
			authInfo.put("text" + i, "工作联系");
			authInfo.put("funcode" + i, FuncCodeConst.WORKCONTACT);
			i++;
		}
		// 变更记录
		authInfo.put(MobileCommonKeyConst.CHANGELOG, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.CELOGMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.CHANGELOG, "Y");
			authInfo.put("image" + i, "myp_bgjl.png");
			authInfo.put("text" + i, "变更记录");
			authInfo.put("funcode" + i, FuncCodeConst.ALTER);
			i++;
		}
		// 签证记录
		authInfo.put(MobileCommonKeyConst.VISALOG, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SITEVISAMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.VISALOG, "Y");
			authInfo.put("image" + i, "myp_qzjl.png");
			authInfo.put("text" + i, "签证记录");
			authInfo.put("funcode" + i, FuncCodeConst.SITEVISA);
			i++;
		}
		// 项目日志
		authInfo.put(MobileCommonKeyConst.PROJECTLOG, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROJECTLOGMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PROJECTLOG, "Y");
			authInfo.put("image" + i, "myp_xmrz.png");
			authInfo.put("text" + i, "项目日志");
			authInfo.put("funcode" + i, FuncCodeConst.PROJECTLOG);
			i++;
		}
		// 电熔数据
		authInfo.put(MobileCommonKeyConst.WELDINGDATA, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.WELDINGDATAMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.WELDINGDATA, "Y");
			authInfo.put("image" + i, "myp_hjsjzd.png");
			authInfo.put("text" + i, "电熔上传");
			authInfo.put("funcode" + i, FuncCodeConst.WELDINGDATA);
			i++;
		}
		
		/*add by lhp begin at 21081225 新加项：热熔数据*/
		authInfo.put(MobileCommonKeyConst.WELDINGDATA, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HOTMELTDATAMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.HOTMELTDATA, "Y");
			authInfo.put("image" + i, "myp_rrsc.png");
			authInfo.put("text" + i, "热熔上传");
			authInfo.put("funcode" + i, FuncCodeConst.HOTMELTDATA);
			i++;
		}
		/*add by lhp end at 21081225*/
		
		//add by wugy on 2019-04-18 case: 新加项：工程测量 start
		authInfo.put(MobileCommonKeyConst.ENGINEERINGSURVEYMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.ENGINEERINGSURVEYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.ENGINEERINGSURVEYMOB, "Y");
			authInfo.put("image" + i, "myp_gccl.png");
			authInfo.put("text" + i, "工程测量");
			authInfo.put("funcode" + i, FuncCodeConst.ENGINEERINGSURVEY);
			i++;
		}
		//add by wugy on 2019-04-18 case: 新加项：工程测量 end
		
		// 出库申请单
		authInfo.put(MobileCommonKeyConst.OUTAPPLY, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.OTAPPLY) != -1) {
			authInfo.put(MobileCommonKeyConst.OUTAPPLY, "Y");
			authInfo.put("image" + i, "myp_cksqd.png");
			authInfo.put("text" + i, "出库申请单");
			authInfo.put("funcode" + i, FuncCodeConst.OTAPPLY);
			i++;
		}
		// 焊接记录上传
		/*authInfo.put(MobileCommonKeyConst.WDUPLOAD, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.WELDUPLOADMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.WDUPLOAD, "Y");
			authInfo.put("image" + i, "myp_sxbqd.png");
			authInfo.put("text" + i, "焊接记录上传");
			authInfo.put("funcode" + i, FuncCodeConst.);
			i++;
		}*/
		// 签到
		authInfo.put(MobileCommonKeyConst.SIGNINOUT, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SIGNMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.SIGNINOUT, "Y");
			//authInfo.put("image" + i, "myp_sxbqd.png");
		//	authInfo.put("text" + i, "上下班签到");
		//	authInfo.put("funcode" + i, FuncCodeConst.);
		//	i++;
		}
		
		/************************* 企业报表 *************************/
		Map<String,String> reportAuthInfo = new HashMap<String,String>();
		int j = 0;
		// 项目活跃度排行榜
		reportAuthInfo.put(MobileCommonKeyConst.PROJECTSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROJECTSCOREMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROJECTSCORE, "Y");
			reportAuthInfo.put("image" + j, "projectitemranking.png");
			reportAuthInfo.put("text" + j, "项目活跃度排行榜");
			j++;
		}
		// 人员活跃度排行榜
		reportAuthInfo.put(MobileCommonKeyConst.PERSONSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PERSONSCOREMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PERSONSCORE, "Y");
			reportAuthInfo.put("image" + j, "activepersonranking.png");
			reportAuthInfo.put("text" + j, "人员活跃度排行榜");
			j++;
		}
		// 单位活跃度查询
		reportAuthInfo.put(MobileCommonKeyConst.SUPPLIERSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SUPPLIERSCOREMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.SUPPLIERSCORE, "Y");
			reportAuthInfo.put("image" + j, "companyranking.png");
			reportAuthInfo.put("text" + j, "单位活跃度查询");
			j++;
		}
		// 未关闭问题统计
		reportAuthInfo.put(MobileCommonKeyConst.UNSOLVEPROBLEM, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.UNSOLVEPROBLEMMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.UNSOLVEPROBLEM, "Y");
			reportAuthInfo.put("image" + j, "notclosequestion.png");
			reportAuthInfo.put("text" + j, "未关闭问题统计");
			j++;
		}
		// 问题现象多发分析
		reportAuthInfo.put(MobileCommonKeyConst.PROREASONANALYZE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROREASONANALYZEMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROREASONANALYZE, "Y");
			reportAuthInfo.put("image" + j, "questionpin.png");
			reportAuthInfo.put("text" + j, "问题现象多发分析");
			j++;
		}
		// 新增问题月度曲线
		reportAuthInfo.put(MobileCommonKeyConst.PROBLEMMONTH, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROBLEMMONTHMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROBLEMMONTH, "Y");
			reportAuthInfo.put("image" + j, "monthquestion.png");
			reportAuthInfo.put("text" + j, "新增问题月度曲线");
			j++;
		}
		// 重点项目进度查阅
		reportAuthInfo.put(MobileCommonKeyConst.IMPORTPROJECT, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.IMPORTPROJECTMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.IMPORTPROJECT, "Y");
			reportAuthInfo.put("image" + j, "zdxmcx.png");
			reportAuthInfo.put("text" + j, "重点项目进度查阅");
			j++;
		}
		// 工商业项目到期/超期预警
		reportAuthInfo.put(MobileCommonKeyConst.PROJECTWARNING, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROJECTWARNINGMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROJECTWARNING, "Y");
			reportAuthInfo.put("image" + j, "gsyxmdqyj.png");
			reportAuthInfo.put("text" + j, "工商业项目到期/超期预警");
			j++;
		}
		// 项目物料查询
		reportAuthInfo.put(MobileCommonKeyConst.MATERIAL, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.MATERIALMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.MATERIAL, "Y");
			reportAuthInfo.put("image" + j, "xmwlcx.png");
			reportAuthInfo.put("text" + j, "项目领料查询");
			j++;
		}
		// 物料库存查询
		reportAuthInfo.put(MobileCommonKeyConst.MATERIALSTOCK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.MATERIALSTOCKMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.MATERIALSTOCK, "Y");
			reportAuthInfo.put("image" + j, "wlkccx.png");
			reportAuthInfo.put("text" + j, "物料库存查询");
			j++;
		}
		/*************************集团报表 *************************/
		Map<String,String> groupReportAuthInfo = new HashMap<String,String>();
		int k = 0;
		// 企业工程服务商活跃度
		groupReportAuthInfo.put(MobileCommonKeyConst.PROVIDERACTIVITY, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROVIDERACTIVITYMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.PROVIDERACTIVITY, "Y");
			groupReportAuthInfo.put("image" + k, "service_activity.png");
			groupReportAuthInfo.put("text" + k, "工程服务商活跃度");
			k++;
		}
		// 企业工程管理人员活跃度
		groupReportAuthInfo.put(MobileCommonKeyConst.MANAGERIALACTIVITY, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.MANAGERIALACTIVITYMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.MANAGERIALACTIVITY, "Y");
			groupReportAuthInfo.put("image" + k, "personnel_activity.png");
			groupReportAuthInfo.put("text" + k, "工程管理人员活跃度");
			k++;
		}
		// 问题现象多发分析
		groupReportAuthInfo.put(MobileCommonKeyConst.PROBLEMATIC, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROBLEMATICMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.PROBLEMATIC, "Y");
			groupReportAuthInfo.put("image" + k, "wtdffx.png");
			groupReportAuthInfo.put("text" + k, "问题现象多发分析");
			k++;
		}
		// 企业项目户均成本查询
		groupReportAuthInfo.put(MobileCommonKeyConst.HOUSEHOLDCOST, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HOUSEHOLDCOSTMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.HOUSEHOLDCOST, "Y");
			groupReportAuthInfo.put("image" + k, "worth_cost.png");
			groupReportAuthInfo.put("text" + k, "项目户均成本查询");
			k++;
		}
		// 企业当期项目预算总额
		groupReportAuthInfo.put(MobileCommonKeyConst.TOTALBUDGET, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.TOTALBUDGETMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.TOTALBUDGET, "Y");
			groupReportAuthInfo.put("image" + k, "total_budget.png");
			groupReportAuthInfo.put("text" + k, "当期项目预算总额");
			k++;
		}
		// 企业工商用户项目平均施工周期
		groupReportAuthInfo.put(MobileCommonKeyConst.CONSTRUCTIONPERIOD, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.CONSTRUCTIONPERIODMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.CONSTRUCTIONPERIOD, "Y");
			groupReportAuthInfo.put("image" + k, "construction_period.png");
			groupReportAuthInfo.put("text" + k, "工商用户项目平均施工周期");
			k++;
		}
		// 企业投资项目超预算比例
		groupReportAuthInfo.put(MobileCommonKeyConst.OVERBUDGET, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.OVERBUDGETMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.OVERBUDGET, "Y");
			groupReportAuthInfo.put("image" + k, "tzxmcysb.png");
			groupReportAuthInfo.put("text" + k, "投资项目超预算比例");
			k++;
		}
		// 企业当期项目决算总额
		groupReportAuthInfo.put(MobileCommonKeyConst.FINALACCOUNTS, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.FINALACCOUNTSMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.FINALACCOUNTS, "Y");
			groupReportAuthInfo.put("image" + k, "amount_final_accounts.png");
			groupReportAuthInfo.put("text" + k, "当期项目决算总额");
			k++;
		}
		//企业项目平均结算周期
		groupReportAuthInfo.put(MobileCommonKeyConst.BILLINGCYCLE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.BILLINGCYCLEMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.BILLINGCYCLE, "Y");
			groupReportAuthInfo.put("image" + k, "xmpjjszq.png");
			groupReportAuthInfo.put("text" + k, "项目平均结算周期");
			k++;
		}
		// 企业施工单位巡查平均得分
		groupReportAuthInfo.put(MobileCommonKeyConst.PATROLSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PATROLSCOREMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.PATROLSCORE, "Y");
			groupReportAuthInfo.put("image" + k, "sgdwdf.png");
			groupReportAuthInfo.put("text" + k, "施工单位巡查平均得分");
			k++;
		}
		
		result.put(MobileCommonKeyConst.AUTHINFO, authInfo);
		result.put(MobileCommonKeyConst.AUTHINFONUM, i);
		result.put(MobileCommonKeyConst.REPORTAUTHINFO, reportAuthInfo);
		result.put(MobileCommonKeyConst.REPORTAUTHINFONUM, j);
		result.put(MobileCommonKeyConst.GROUPREPORTAUTHINFO, groupReportAuthInfo);
		result.put(MobileCommonKeyConst.GROUPREPORTAUTHINFONUM, k);
	}
	
	/**
	 * 根据默认关注项目设置当前默认组织
	 * 
	 * @author guofya
	 * @date 2017-3-28
	 * @param 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setDefaultOrg(Map<String,Object> result) throws BusinessException{
		List<Map> orgListMap = (List<Map>) result.get(MobileCommonKeyConst.PERMORGLISTMAP);
		Map<String, Object> resultMap = (Map<String, Object>) result.get(MobileCommonKeyConst.FOCUSPROJECTLIST);
		List<Map> allMapList = (List<Map>) resultMap.get(FocusProjectMobVO.ALLLIST);
		String pk_default_org = null;
		for (Map map : allMapList) {
			if (((UFBoolean) map.get(FocusProjectMobVO.IS_DEFAULT_FOCUS)).booleanValue()) {
				pk_default_org = (String) map.get(ProjectMobVO.PK_DUTY_ORG);
			}
		}
		
		if (null != pk_default_org) {
			for (Map orgMap : orgListMap) {
				if (pk_default_org.equals(orgMap.get("pk_org"))) {
					orgMap.put("is_default_focus", "Y");
				}
			}
		}
	}

	@Override
	public Map<String, Object> checkOpenNodePermission(String funcode,String userid,String groupid) {
		MobileResultProcessor resultProcessor = new MobileResultProcessor();
		
		if(StringUtil.isEmpty(funcode)){
			resultProcessor.setErrorMSGAndResultCode("您没有该节点的操作权限，请切换当前关注项目！");
			return resultProcessor.getMobileResultVO();
		}
		
		// 查询用户在该节点上有权限的组织主键		
		String[] permOrgs = new String[]{};
		try {
			permOrgs = PMProxy.lookup(IFunctionPermissionPubService.class)
					.getUserPermissionPkOrgs(userid, funcode, groupid);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		};

		if(ArrayUtil.isEmpty(permOrgs)){
			resultProcessor.setErrorMSGAndResultCode("该用户未分配工程移动权限！");
			return resultProcessor.getMobileResultVO();
		}else{
			
			resultProcessor.setResult(ArrayUtil.changeToList(permOrgs));
	
		}
		
		return resultProcessor.getMobileResultVO();
	}
	
}
