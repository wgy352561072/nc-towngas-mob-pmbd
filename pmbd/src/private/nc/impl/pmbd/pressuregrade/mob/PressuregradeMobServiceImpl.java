package nc.impl.pmbd.pressuregrade.mob;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.dao.DAOException;
import nc.impl.am.db.DBAccessUtil;
import nc.itf.pmbd.pressuregrade.mob.IPressuregradeMobService;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.pm.mobile.base.MobileCommonKeyConst;
import nc.vo.pm.mobile.base.MobileResultProcessor;
import nc.vo.pm.mobile.base.PMMobVOResultSetProcessor;
import nc.vo.pm.util.ArrayUtil;
import nc.vo.pmbd.bd.pressuregrade.mob.PressuregradeMobVO;
import nc.vo.pmbd.mob.util.PMMobilePubUtils;
import nc.vo.pub.BusinessException;
import nc.vo.util.VisibleUtil;

public class PressuregradeMobServiceImpl implements IPressuregradeMobService {


	@Override
	public Map<String, Object> queryPressuregradeByOrgs(String groupid,
			String[] permOrgs) {

		MobileResultProcessor resultProcessor = new MobileResultProcessor();
		DBAccessUtil baseDAO = new DBAccessUtil();

		if (ArrayUtil.isEmpty(permOrgs)) {
			resultProcessor.setErrorMSGAndResultCode("当前用户所属组织不能为空！");
			return resultProcessor.getMobileResultVO();
		}

		NODE_TYPE nodeType = PMMobilePubUtils.getNodeTypeByPk_groupAndPk_org(
				groupid, permOrgs[0]);
		String typeMDId = MobileCommonKeyConst.PRESSUREGRADE;
		String visibleSql = null;

		StringBuffer querySql = new StringBuffer();
		querySql.append(" select pk_pressuregrade,code,name,pk_org from pmbd_pressuregrade where (");

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
		querySql.append(" order by code ");

		List<PressuregradeMobVO> pressuregradeMobVOs = null;
		try {
			pressuregradeMobVOs = baseDAO.executeQuery(
					querySql.toString(),
					new PMMobVOResultSetProcessor<PressuregradeMobVO>(
							PressuregradeMobVO.class));
		} catch (DAOException e) {
			resultProcessor.setErrorMSGAndResultCode(PMMobilePubUtils
					.getErrorMessage(e));
			resultProcessor.getMobileResultVO();
		}


		List<Map> queryResult = new ArrayList<Map>();
		if ((pressuregradeMobVOs != null)
				&& (pressuregradeMobVOs.size() > 0)) {
			for (PressuregradeMobVO vo : pressuregradeMobVOs) {
				queryResult.add(vo.getResultMap());
			}
		}

		resultProcessor.setResult(queryResult);
		return resultProcessor.getMobileResultVO();
	}

}
