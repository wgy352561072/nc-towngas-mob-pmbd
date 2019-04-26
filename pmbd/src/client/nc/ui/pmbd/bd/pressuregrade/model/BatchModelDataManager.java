package nc.ui.pmbd.bd.pressuregrade.model;

import nc.ui.uif2.model.BatchBillTableModel;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.pm.constant.CommonKeyConst;
import nc.vo.pm.constant.Enablestate;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.uif2.LoginContext;
import nc.vo.util.SqlWhereUtil;

public class BatchModelDataManager implements IAppModelDataManagerEx {

	private BatchBillTableModel model;
	private boolean showSeal = false;
	private String sqlWhere = null;

	public BatchBillTableModel getModel() {
		return this.model;
	}

	@Override
	public void initModel() {
		// TODO Auto-generated method stub
		this.initModelBySqlWhere(this.sqlWhere);
	}

	@Override
	public void initModelBySqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
		Object[] objs = null;
		LoginContext context = this.getModel().getContext();
		try {
			objs = ((PressureGradeModelService) this.getModel().getService()).queryByWhereSql(context,
					this.createCondition(sqlWhere));
		} catch (Exception e) {
			throw new BusinessRuntimeException("", e);
		}
		this.getModel().initModel(objs);

	}

	public boolean isShowSeal() {
		return this.showSeal;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		this.initModelBySqlWhere(this.sqlWhere);
	}

	public void setModel(BatchBillTableModel model) {
		this.model = model;
	}

	public void setShowSeal(boolean showSeal) {
		this.showSeal = showSeal;
	}

	@Override
	public void setShowSealDataFlag(boolean showSealDataFlag) {
		// TODO Auto-generated method stub
		this.showSeal = showSealDataFlag;
	}

	private String createCondition(String sqlWhere) {
		SqlWhereUtil swu = new SqlWhereUtil(sqlWhere);
		if (!this.showSeal) {
			swu.l().and(" and " + CommonKeyConst.ENABLESTATE + " = " + Enablestate.enable).r();
		}
		return swu.getSQLWhere();
	}
}
