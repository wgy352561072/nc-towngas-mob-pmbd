package nc.impl.pmbd.pipelinepointclass.mob;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.dao.DAOException;
import nc.impl.am.db.DBAccessUtil;
import nc.itf.pmbd.pipelinepointclass.mob.IPipelinepointClassMobService;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.pm.mobile.base.MobileCommonKeyConst;
import nc.vo.pm.mobile.base.MobileResultProcessor;
import nc.vo.pm.mobile.base.PMMobVOResultSetProcessor;
import nc.vo.pm.util.ArrayUtil;
import nc.vo.pm.util.StringUtil;
import nc.vo.pmbd.bd.pipelinepointclass.mob.PipelinepointclassMobVO;
import nc.vo.pmbd.mob.util.PMMobilePubUtils;
import nc.vo.pub.BusinessException;
import nc.vo.util.VisibleUtil;

/**
 * 管线点分类移动端服务类
 * 
 * @author wugy
 * @time 2019-04-16
 * 
 */
public class PipelinepointClassMobServiceImpl implements
		IPipelinepointClassMobService {

	@Override
	public Map<String, Object> queryPipelinepointClassByOrgs(String groupid,
			String[] permOrgs) {

		MobileResultProcessor resultProcessor = new MobileResultProcessor();
		DBAccessUtil baseDAO = new DBAccessUtil();

		if (ArrayUtil.isEmpty(permOrgs)) {
			resultProcessor.setErrorMSGAndResultCode("当前用户所属组织不能为空！");
			return resultProcessor.getMobileResultVO();
		}

		NODE_TYPE nodeType = PMMobilePubUtils.getNodeTypeByPk_groupAndPk_org(
				groupid, permOrgs[0]);
		String typeMDId = MobileCommonKeyConst.PIPELINEPOINTCLASS;
		String visibleSql = null;

		StringBuffer querySql = new StringBuffer();
		querySql.append(" select pk_pipelinepointclass,code,name,innercode,pk_parent,pk_org from pmbd_pipelinepointclass where (");

		// 管控模式可见条件的拼接
		for (int i = 0; i < permOrgs.length; i++) {
			try {
				visibleSql = VisibleUtil.getVisibleCondition(permOrgs[i],
						groupid, nodeType, typeMDId);
			} catch (BusinessException e) {
				resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils
						.getErrorMessage(e));

				return resultProcessor.getMobileResultVO();
			}
			// 可见性规则拼接
			if (visibleSql != null) {
				querySql.append(visibleSql);
			}
			if (i != permOrgs.length - 1) {
				querySql.append(" or ");
			}
		}

		querySql.append(") and enablestate = 2 and dr = 0 ");
		querySql.append(" order by innercode ");

		List<PipelinepointclassMobVO> pipelinepointclassMobVOs = null;
		try {
			pipelinepointclassMobVOs = baseDAO.executeQuery(
					querySql.toString(),
					new PMMobVOResultSetProcessor<PipelinepointclassMobVO>(
							PipelinepointclassMobVO.class));
		} catch (DAOException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils
					.getErrorMessage(e));
			resultProcessor.getMobileResultVO();
		}
		// 得到父类的pk
		ArrayList<String> pkParentList = getPKParentList(pipelinepointclassMobVOs);

		List<Map> queryResult = new ArrayList<Map>();
		if ((pipelinepointclassMobVOs != null) && (pipelinepointclassMobVOs.size() > 0)) {
			for (PipelinepointclassMobVO vo : pipelinepointclassMobVOs) {
				if (!pkParentList.contains(vo.getAttributeValue(PipelinepointclassMobVO.PK_PIPELINEPOINTCLASS))) {
					vo.setAttributeValue(PipelinepointclassMobVO.ISLEAF, "Y");
				}
				queryResult.add(vo.getResultMap());
			}
		}

		resultProcessor.setResult(queryResult);
		return resultProcessor.getMobileResultVO();
	}

	/**
	 * 得到有子节点的VO的PK
	 * 
	 * @param pipelinepointclassMobVOs
	 * @return
	 */
	private ArrayList<String> getPKParentList(
			List<PipelinepointclassMobVO> pipelinepointclassMobVOs) {
		ArrayList<String> pkParentList = new ArrayList<String>();
		for (PipelinepointclassMobVO mobVO : pipelinepointclassMobVOs) {
			if ((mobVO.getAttributeValue(PipelinepointclassMobVO.PK_PARENT) != null) || (StringUtil.isNotEmpty((String) mobVO.getAttributeValue(PipelinepointclassMobVO.PK_PARENT)))) {
				pkParentList.add((String) mobVO.getAttributeValue(PipelinepointclassMobVO.PK_PARENT));
			}
		}
		return pkParentList;
	}

}
