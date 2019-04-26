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
 * �ƶ���¼Ӧ�÷���ʵ����
 * ��ȡ��ʼ����Ϣ
 * 
 * @version gh3.0
 * @author guofya
 * @time 2017-3-14 ����1:46:30
 */
public class QueryContextMobServiceImpl implements IQueryContextMobService {
	
	@Override
	public Map<String, Object> queryContext(Map<String,Object> param) {
		MobileResultProcessor resultProcessor = new MobileResultProcessor();
		Map<String,Object> result = new HashMap<String,Object>();

		String groupId = null;
		String userId = null;
		String username = PMMobilePubUtils.changeString(param.get(MobileCommonKeyConst.USERNAME));
		// ��ѯuserid��groupid
		try {
			queryUseridByUsername(username, result);
			groupId = (String) result.get(MobileCommonKeyConst.GROUPID);
			userId = (String) result.get(MobileCommonKeyConst.USERID);
			// Ԥ���ǻ�ۻ���¼��û�г�ʼ������������¼��Ϣ
			InvocationInfoProxy.getInstance().setGroupId(groupId);
			InvocationInfoProxy.getInstance().setUserId(userId);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		// �����û�ID��ѯ���ܽڵ�Ȩ��
		try {
			getAuthInfoByUserId(userId, groupId, result, resultProcessor);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		// ��ȡ�û���Ȩ�޵�ҵ��Ԫ
		try {
			queryOrgs(groupId, userId, result, resultProcessor);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		// ��ȡ�û�������Ϣ���û����ơ��ν���λ��
		try {
			getPsnInfoByUserId(userId, result);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		}
		
		
		// ��ѯ��ע��Ŀ
		Map<String, Object> mobileResultVO = PMProxy.lookup(
				IFocusProjectMobService.class)
				.queryFocusProjectsByUserId(param);
		// mobService�����ʱ����Ҫ�ж��쳣
		if ((Integer) mobileResultVO.get(MobileCommonKeyConst.RESULT_CODE) == 
				MobileCommonKeyConst.FAILURE) {
			resultProcessor.setErrorMSGAndResultCode((String) mobileResultVO
					.get(MobileCommonKeyConst.ERROR_MSG));
			return resultProcessor.getMobileResultVO();
		}
		result.put(MobileCommonKeyConst.FOCUSPROJECTLIST, mobileResultVO.get(MobileCommonKeyConst.RESULT));
		
		// ���õ�ǰ��֯
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
	 * �����û������ѯuserId��groupId
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
		//��ѯuserid��groupid
		queryResults = (List<MappedBaseVO>) new DBAccessUtil().executeQuery(querySql
					.toString(), parameter, new PMMobVOResultSetBaseProcessor(resultVO));
		
		if (queryResults != null && queryResults.size() > 0) {
			MappedBaseVO vo = queryResults.get(0);

			result.put(MobileCommonKeyConst.GROUPID, (String) vo.getAttributeValue("groupid"));
			result.put(MobileCommonKeyConst.USERID, (String) vo.getAttributeValue("userid"));
		} 
	}
	
	/**
	 * ��ȡ�û���Ȩ�޵�ҵ��Ԫ
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
			throw new BusinessException("�ƶ�����Ȩ�޶�Ӧ������ɫû�з���Ȩ����֯��");
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
	 * �����û�ID��ȡ�û�������Ϣ
	 * �û����ơ��μ���λ
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
		// �û����У��
		judgeIdentity(userVOs[0], result);
		// �û�����
		result.put(MobileCommonKeyConst.USER_NAME, userVOs[0].getUser_name());
		
	}
	
	/**
	 * �ж�У���û����
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
			throw new BusinessException("�û�δ������Ա�������Ա���Ͳ��ԣ�");
		}
		// ���
		String mobIdentity = null;
		if (UserIdentityTypeEnumFactory.TYPE_PERSON == identityType) {
			mobIdentity = "�׷�";
		}
		if (UserIdentityTypeEnumFactory.TYPE_PROVIDER == identityType) {
			SQLParameter parameter = new SQLParameter();
			parameter.addParam(cuserId);
			String supplierSQL = " select pk_supplier,supplier_role from pm_supplierpsn where user_code = ? and dr = 0 ";
			List resultList = (List) new DBAccessUtil().executeQuery(supplierSQL, parameter, new ArrayListProcessor());

			// ���û�й�����Ӧ����Ա
			if (ListUtil.isEmpty(resultList)) {
				throw new BusinessException("�û�δ������Ա�������Ա���Ͳ��ԣ�");
			}
			
			if (SupplierRole.contraction == (Integer) ((Object[]) resultList.get(0))[1]) {
				mobIdentity = "ʩ����";
			} else if (SupplierRole.monitor == (Integer) ((Object[]) resultList.get(0))[1]) {
				mobIdentity = "����";
			} else if (SupplierRole.designer == (Integer) ((Object[]) resultList.get(0))[1]) {
				mobIdentity = "��Ʒ�";
			}
			// ����ʩ����Ա  ����ʩ����λ
			String pk_supplier = (String) ((Object[]) resultList.get(0))[0];
			result.put(MobileCommonKeyConst.PK_SUPPLIER, pk_supplier);
		}
		// �ƶ��˵�¼�û����
		result.put(MobileCommonKeyConst.USER_IDENTITY, mobIdentity);
	}
	
	/**
	 * �����û�ID��ѯ���ܽڵ�Ȩ��
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
			throw new BusinessException("���û�û�з��乤���ƶ�Ȩ�ޣ�");
		}
		
		/************************* ��ҳ���ܽڵ� *************************/
		Map<String,String> authInfo = new HashMap<String,String>();
		int i = 0;
		// ��ʼ��Ϊ��û��Ȩ��
		// ������ȫ����
		authInfo.put(MobileCommonKeyConst.PROJECTISSUEMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROBLEMMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PROJECTISSUEMOB, "Y");
			authInfo.put("image" + i, "myp_xmwt_new2x.png");
			authInfo.put("text" + i, "������ȫ����");
			authInfo.put("funcode" + i, FuncCodeConst.PROBLEM);
			i++;
		}
		// ��վ��¼
		authInfo.put(MobileCommonKeyConst.SIDEINSPECTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SIDEQUALITYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.SIDEINSPECTIONMOB, "Y");
			authInfo.put("image" + i, "myp_pzjc.png");
			authInfo.put("text" + i, "��վ��¼");
			authInfo.put("funcode" + i, FuncCodeConst.SIDEQUALITY);
			i++;
		}
		// ƽ�м���
		authInfo.put(MobileCommonKeyConst.PARAINSPECTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PARAQUALITYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PARAINSPECTIONMOB, "Y");
			authInfo.put("image" + i, "myp_pxjc.png");
			authInfo.put("text" + i, "ƽ�м���");
			authInfo.put("funcode" + i, FuncCodeConst.PARAQUALITY);
			i++;
		}
		// ������ȫѲ��
		authInfo.put(MobileCommonKeyConst.QUALITYINSPECTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.QUALITYPATROLMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.QUALITYINSPECTIONMOB, "Y");
			authInfo.put("image" + i, "myp_xmjc_new2x.png");
			authInfo.put("text" + i, "������ȫѲ��");
			authInfo.put("funcode" + i, FuncCodeConst.QUALITYPATROL);
			i++;
		}
		// ��ĿԤ����
		authInfo.put(MobileCommonKeyConst.PRECHECK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PECHECKMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PRECHECK, "Y");
			authInfo.put("image" + i, "myp_xmyys.png");
			authInfo.put("text" + i, "��ĿԤ����");
			authInfo.put("funcode" + i, FuncCodeConst.PREACCEPT);
			i++;
		}
		// �ۺ�����
		authInfo.put(MobileCommonKeyConst.ALLCHECK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.ALCHECKMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.ALLCHECK, "Y");
			authInfo.put("image" + i, "myp_zhys.png");
			authInfo.put("text" + i, "�ۺ�����");
			authInfo.put("funcode" + i, FuncCodeConst.FINNALACCEPT);
			i++;
		}
		// ʩ����¼
		authInfo.put(MobileCommonKeyConst.CONSTRUCTIONMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.CONSTQUALITYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.CONSTRUCTIONMOB, "Y");
			authInfo.put("image" + i, "myp_sgjl.png");
			authInfo.put("text" + i, "ʩ����¼");
			authInfo.put("funcode" + i, FuncCodeConst.CONSTQUALITY);
			i++;
		}
		// ������
		authInfo.put(MobileCommonKeyConst.PROCESSCHECK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PSCHECKMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PROCESSCHECK, "Y");
			authInfo.put("image" + i, "myp_gxby.png");
			authInfo.put("text" + i, "������");
			authInfo.put("funcode" + i, FuncCodeConst.PSCHECK);
			i++;
		}
		// ���ι�������
		authInfo.put(MobileCommonKeyConst.HIDDEDPROJECTMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HIDDEDPROJECTMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.HIDDEDPROJECTMOB, "Y");
			authInfo.put("image" + i, "myp_ybgcys.png");
			authInfo.put("text" + i, "���ι�������");
			authInfo.put("funcode" + i, FuncCodeConst.HIDDEDPROJECT);
			i++;
		}
		// ���ۺ�������
		authInfo.put(MobileCommonKeyConst.WELDPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.ELEWELDINGPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.WELDPICMOB, "Y");
			authInfo.put("image" + i, "myp_drpz.png");
			authInfo.put("text" + i, "���ۺ�������");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// ���ۺ������� 
		authInfo.put(MobileCommonKeyConst.WELDPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HOTWELDINGPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.WELDPICMOB, "Y");
			authInfo.put("image" + i, "myp_hkpz.png");
			authInfo.put("text" + i, "���ۺ�������");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// ������������
		authInfo.put(MobileCommonKeyConst.CORROSIONPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PATCHPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.CORROSIONPICMOB, "Y");
			authInfo.put("image" + i, "myp_ffpz.png");
			authInfo.put("text" + i, "������������");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// ������������
		authInfo.put(MobileCommonKeyConst.PROCESSPICMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.OTHERPROCESS) != -1) {
			authInfo.put(MobileCommonKeyConst.PROCESSPICMOB, "Y");
			authInfo.put("image" + i, "myp_gxpz.png");
			authInfo.put("text" + i, "������������");
			authInfo.put("funcode" + i, FuncCodeConst.PROCESSIMAGE);
			i++;
		}
		// �ؼ�����
		authInfo.put(MobileCommonKeyConst.KEYPROGRESSMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.KEYPROGRESSMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.KEYPROGRESSMOB, "Y");
			authInfo.put("image" + i, "myp_indexmeasure.png");
			authInfo.put("text" + i, "�������");
			authInfo.put("funcode" + i, FuncCodeConst.KEYPROGRESS);
			i++;
		}
		// ָ�����
		authInfo.put(MobileCommonKeyConst.INDEXPROGRESSMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.INDEXPROGRESSMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.INDEXPROGRESSMOB, "Y");
			authInfo.put("image" + i, "myp_index.png");
			authInfo.put("text" + i, "ָ�����");
			authInfo.put("funcode" + i, FuncCodeConst.INDEXPROGRESS);
			i++;
		}
		// ������ϵ
		authInfo.put(MobileCommonKeyConst.WORKCONTACTMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.WORKCONTACTMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.WORKCONTACTMOB, "Y");
			authInfo.put("image" + i, "myp_fqlx2x.png");
			authInfo.put("text" + i, "������ϵ");
			authInfo.put("funcode" + i, FuncCodeConst.WORKCONTACT);
			i++;
		}
		// �����¼
		authInfo.put(MobileCommonKeyConst.CHANGELOG, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.CELOGMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.CHANGELOG, "Y");
			authInfo.put("image" + i, "myp_bgjl.png");
			authInfo.put("text" + i, "�����¼");
			authInfo.put("funcode" + i, FuncCodeConst.ALTER);
			i++;
		}
		// ǩ֤��¼
		authInfo.put(MobileCommonKeyConst.VISALOG, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SITEVISAMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.VISALOG, "Y");
			authInfo.put("image" + i, "myp_qzjl.png");
			authInfo.put("text" + i, "ǩ֤��¼");
			authInfo.put("funcode" + i, FuncCodeConst.SITEVISA);
			i++;
		}
		// ��Ŀ��־
		authInfo.put(MobileCommonKeyConst.PROJECTLOG, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROJECTLOGMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.PROJECTLOG, "Y");
			authInfo.put("image" + i, "myp_xmrz.png");
			authInfo.put("text" + i, "��Ŀ��־");
			authInfo.put("funcode" + i, FuncCodeConst.PROJECTLOG);
			i++;
		}
		// ��������
		authInfo.put(MobileCommonKeyConst.WELDINGDATA, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.WELDINGDATAMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.WELDINGDATA, "Y");
			authInfo.put("image" + i, "myp_hjsjzd.png");
			authInfo.put("text" + i, "�����ϴ�");
			authInfo.put("funcode" + i, FuncCodeConst.WELDINGDATA);
			i++;
		}
		
		/*add by lhp begin at 21081225 �¼����������*/
		authInfo.put(MobileCommonKeyConst.WELDINGDATA, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HOTMELTDATAMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.HOTMELTDATA, "Y");
			authInfo.put("image" + i, "myp_rrsc.png");
			authInfo.put("text" + i, "�����ϴ�");
			authInfo.put("funcode" + i, FuncCodeConst.HOTMELTDATA);
			i++;
		}
		/*add by lhp end at 21081225*/
		
		//add by wugy on 2019-04-18 case: �¼�����̲��� start
		authInfo.put(MobileCommonKeyConst.ENGINEERINGSURVEYMOB, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.ENGINEERINGSURVEYMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.ENGINEERINGSURVEYMOB, "Y");
			authInfo.put("image" + i, "myp_gccl.png");
			authInfo.put("text" + i, "���̲���");
			authInfo.put("funcode" + i, FuncCodeConst.ENGINEERINGSURVEY);
			i++;
		}
		//add by wugy on 2019-04-18 case: �¼�����̲��� end
		
		// �������뵥
		authInfo.put(MobileCommonKeyConst.OUTAPPLY, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.OTAPPLY) != -1) {
			authInfo.put(MobileCommonKeyConst.OUTAPPLY, "Y");
			authInfo.put("image" + i, "myp_cksqd.png");
			authInfo.put("text" + i, "�������뵥");
			authInfo.put("funcode" + i, FuncCodeConst.OTAPPLY);
			i++;
		}
		// ���Ӽ�¼�ϴ�
		/*authInfo.put(MobileCommonKeyConst.WDUPLOAD, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.WELDUPLOADMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.WDUPLOAD, "Y");
			authInfo.put("image" + i, "myp_sxbqd.png");
			authInfo.put("text" + i, "���Ӽ�¼�ϴ�");
			authInfo.put("funcode" + i, FuncCodeConst.);
			i++;
		}*/
		// ǩ��
		authInfo.put(MobileCommonKeyConst.SIGNINOUT, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SIGNMOB) != -1) {
			authInfo.put(MobileCommonKeyConst.SIGNINOUT, "Y");
			//authInfo.put("image" + i, "myp_sxbqd.png");
		//	authInfo.put("text" + i, "���°�ǩ��");
		//	authInfo.put("funcode" + i, FuncCodeConst.);
		//	i++;
		}
		
		/************************* ��ҵ���� *************************/
		Map<String,String> reportAuthInfo = new HashMap<String,String>();
		int j = 0;
		// ��Ŀ��Ծ�����а�
		reportAuthInfo.put(MobileCommonKeyConst.PROJECTSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROJECTSCOREMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROJECTSCORE, "Y");
			reportAuthInfo.put("image" + j, "projectitemranking.png");
			reportAuthInfo.put("text" + j, "��Ŀ��Ծ�����а�");
			j++;
		}
		// ��Ա��Ծ�����а�
		reportAuthInfo.put(MobileCommonKeyConst.PERSONSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PERSONSCOREMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PERSONSCORE, "Y");
			reportAuthInfo.put("image" + j, "activepersonranking.png");
			reportAuthInfo.put("text" + j, "��Ա��Ծ�����а�");
			j++;
		}
		// ��λ��Ծ�Ȳ�ѯ
		reportAuthInfo.put(MobileCommonKeyConst.SUPPLIERSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.SUPPLIERSCOREMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.SUPPLIERSCORE, "Y");
			reportAuthInfo.put("image" + j, "companyranking.png");
			reportAuthInfo.put("text" + j, "��λ��Ծ�Ȳ�ѯ");
			j++;
		}
		// δ�ر�����ͳ��
		reportAuthInfo.put(MobileCommonKeyConst.UNSOLVEPROBLEM, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.UNSOLVEPROBLEMMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.UNSOLVEPROBLEM, "Y");
			reportAuthInfo.put("image" + j, "notclosequestion.png");
			reportAuthInfo.put("text" + j, "δ�ر�����ͳ��");
			j++;
		}
		// ��������෢����
		reportAuthInfo.put(MobileCommonKeyConst.PROREASONANALYZE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROREASONANALYZEMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROREASONANALYZE, "Y");
			reportAuthInfo.put("image" + j, "questionpin.png");
			reportAuthInfo.put("text" + j, "��������෢����");
			j++;
		}
		// ���������¶�����
		reportAuthInfo.put(MobileCommonKeyConst.PROBLEMMONTH, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROBLEMMONTHMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROBLEMMONTH, "Y");
			reportAuthInfo.put("image" + j, "monthquestion.png");
			reportAuthInfo.put("text" + j, "���������¶�����");
			j++;
		}
		// �ص���Ŀ���Ȳ���
		reportAuthInfo.put(MobileCommonKeyConst.IMPORTPROJECT, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.IMPORTPROJECTMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.IMPORTPROJECT, "Y");
			reportAuthInfo.put("image" + j, "zdxmcx.png");
			reportAuthInfo.put("text" + j, "�ص���Ŀ���Ȳ���");
			j++;
		}
		// ����ҵ��Ŀ����/����Ԥ��
		reportAuthInfo.put(MobileCommonKeyConst.PROJECTWARNING, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROJECTWARNINGMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.PROJECTWARNING, "Y");
			reportAuthInfo.put("image" + j, "gsyxmdqyj.png");
			reportAuthInfo.put("text" + j, "����ҵ��Ŀ����/����Ԥ��");
			j++;
		}
		// ��Ŀ���ϲ�ѯ
		reportAuthInfo.put(MobileCommonKeyConst.MATERIAL, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.MATERIALMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.MATERIAL, "Y");
			reportAuthInfo.put("image" + j, "xmwlcx.png");
			reportAuthInfo.put("text" + j, "��Ŀ���ϲ�ѯ");
			j++;
		}
		// ���Ͽ���ѯ
		reportAuthInfo.put(MobileCommonKeyConst.MATERIALSTOCK, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.MATERIALSTOCKMOB) != -1) {
			reportAuthInfo.put(MobileCommonKeyConst.MATERIALSTOCK, "Y");
			reportAuthInfo.put("image" + j, "wlkccx.png");
			reportAuthInfo.put("text" + j, "���Ͽ���ѯ");
			j++;
		}
		/*************************���ű��� *************************/
		Map<String,String> groupReportAuthInfo = new HashMap<String,String>();
		int k = 0;
		// ��ҵ���̷����̻�Ծ��
		groupReportAuthInfo.put(MobileCommonKeyConst.PROVIDERACTIVITY, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROVIDERACTIVITYMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.PROVIDERACTIVITY, "Y");
			groupReportAuthInfo.put("image" + k, "service_activity.png");
			groupReportAuthInfo.put("text" + k, "���̷����̻�Ծ��");
			k++;
		}
		// ��ҵ���̹�����Ա��Ծ��
		groupReportAuthInfo.put(MobileCommonKeyConst.MANAGERIALACTIVITY, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.MANAGERIALACTIVITYMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.MANAGERIALACTIVITY, "Y");
			groupReportAuthInfo.put("image" + k, "personnel_activity.png");
			groupReportAuthInfo.put("text" + k, "���̹�����Ա��Ծ��");
			k++;
		}
		// ��������෢����
		groupReportAuthInfo.put(MobileCommonKeyConst.PROBLEMATIC, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PROBLEMATICMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.PROBLEMATIC, "Y");
			groupReportAuthInfo.put("image" + k, "wtdffx.png");
			groupReportAuthInfo.put("text" + k, "��������෢����");
			k++;
		}
		// ��ҵ��Ŀ�����ɱ���ѯ
		groupReportAuthInfo.put(MobileCommonKeyConst.HOUSEHOLDCOST, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.HOUSEHOLDCOSTMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.HOUSEHOLDCOST, "Y");
			groupReportAuthInfo.put("image" + k, "worth_cost.png");
			groupReportAuthInfo.put("text" + k, "��Ŀ�����ɱ���ѯ");
			k++;
		}
		// ��ҵ������ĿԤ���ܶ�
		groupReportAuthInfo.put(MobileCommonKeyConst.TOTALBUDGET, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.TOTALBUDGETMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.TOTALBUDGET, "Y");
			groupReportAuthInfo.put("image" + k, "total_budget.png");
			groupReportAuthInfo.put("text" + k, "������ĿԤ���ܶ�");
			k++;
		}
		// ��ҵ�����û���Ŀƽ��ʩ������
		groupReportAuthInfo.put(MobileCommonKeyConst.CONSTRUCTIONPERIOD, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.CONSTRUCTIONPERIODMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.CONSTRUCTIONPERIOD, "Y");
			groupReportAuthInfo.put("image" + k, "construction_period.png");
			groupReportAuthInfo.put("text" + k, "�����û���Ŀƽ��ʩ������");
			k++;
		}
		// ��ҵͶ����Ŀ��Ԥ�����
		groupReportAuthInfo.put(MobileCommonKeyConst.OVERBUDGET, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.OVERBUDGETMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.OVERBUDGET, "Y");
			groupReportAuthInfo.put("image" + k, "tzxmcysb.png");
			groupReportAuthInfo.put("text" + k, "Ͷ����Ŀ��Ԥ�����");
			k++;
		}
		// ��ҵ������Ŀ�����ܶ�
		groupReportAuthInfo.put(MobileCommonKeyConst.FINALACCOUNTS, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.FINALACCOUNTSMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.FINALACCOUNTS, "Y");
			groupReportAuthInfo.put("image" + k, "amount_final_accounts.png");
			groupReportAuthInfo.put("text" + k, "������Ŀ�����ܶ�");
			k++;
		}
		//��ҵ��Ŀƽ����������
		groupReportAuthInfo.put(MobileCommonKeyConst.BILLINGCYCLE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.BILLINGCYCLEMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.BILLINGCYCLE, "Y");
			groupReportAuthInfo.put("image" + k, "xmpjjszq.png");
			groupReportAuthInfo.put("text" + k, "��Ŀƽ����������");
			k++;
		}
		// ��ҵʩ����λѲ��ƽ���÷�
		groupReportAuthInfo.put(MobileCommonKeyConst.PATROLSCORE, "N");
		if (ArrayUtil.indexOf(funCodes, FuncCodeConst.PATROLSCOREMOB) != -1) {
			groupReportAuthInfo.put(MobileCommonKeyConst.PATROLSCORE, "Y");
			groupReportAuthInfo.put("image" + k, "sgdwdf.png");
			groupReportAuthInfo.put("text" + k, "ʩ����λѲ��ƽ���÷�");
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
	 * ����Ĭ�Ϲ�ע��Ŀ���õ�ǰĬ����֯
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
			resultProcessor.setErrorMSGAndResultCode("��û�иýڵ�Ĳ���Ȩ�ޣ����л���ǰ��ע��Ŀ��");
			return resultProcessor.getMobileResultVO();
		}
		
		// ��ѯ�û��ڸýڵ�����Ȩ�޵���֯����		
		String[] permOrgs = new String[]{};
		try {
			permOrgs = PMProxy.lookup(IFunctionPermissionPubService.class)
					.getUserPermissionPkOrgs(userid, funcode, groupid);
		} catch (BusinessException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils.getErrorMessage(e));
			return resultProcessor.getMobileResultVO();
		};

		if(ArrayUtil.isEmpty(permOrgs)){
			resultProcessor.setErrorMSGAndResultCode("���û�δ���乤���ƶ�Ȩ�ޣ�");
			return resultProcessor.getMobileResultVO();
		}else{
			
			resultProcessor.setResult(ArrayUtil.changeToList(permOrgs));
	
		}
		
		return resultProcessor.getMobileResultVO();
	}
	
}
