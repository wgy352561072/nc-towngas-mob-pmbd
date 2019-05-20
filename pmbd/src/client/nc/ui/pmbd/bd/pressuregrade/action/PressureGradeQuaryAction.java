package nc.ui.pmbd.bd.pressuregrade.action;

import nc.vo.pm.util.StringUtil;



/**
 * 
 * ѹ���ȼ���ѯ��ť
 * 
 */
public class PressureGradeQuaryAction extends nc.ui.uif2.actions.QueryAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void executeQuery(String sqlWhere) {
		if (StringUtil.isNotEmpty(sqlWhere)) {
			sqlWhere = " and " + sqlWhere;
		} 
		super.executeQuery(sqlWhere);
	}



}