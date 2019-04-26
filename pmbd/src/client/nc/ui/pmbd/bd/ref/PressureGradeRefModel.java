package nc.ui.pmbd.bd.ref;

import nc.bs.logging.Logger;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.bd.ref.IRefDocEdit;
import nc.ui.bd.ref.IRefMaintenanceHandler;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.pmbd.bd.pressuregrade.PressuregradeVO;
import nc.vo.pmbd.common.consts.FuncCodeConst;
import nc.vo.pub.BusinessException;
import nc.vo.util.VisibleUtil;

/**
 *
 * <p>
 * <b>本类主要完成以下功能：</b>
 *
 * <ul>
 * <li>压力等级的参照
 * </ul>
 *
 * <p>
 * <p>
 *
 * @version 6.5
 * @since 6.5
 * @author wugy
 * @time 2019-04-22 
 */
public class PressureGradeRefModel extends AbstractRefModel {
	private String beanID = null;

	public PressureGradeRefModel(String refNodeName) {
		this.setRefNodeName(refNodeName);

		this.setRefMaintenanceHandler(new IRefMaintenanceHandler() {

			@Override
			public String[] getFucCodes() {
				return new String[] { FuncCodeConst.DATALIST_GROUP, FuncCodeConst.DATALIST_ORG };// 功能号
			}

			@Override
			public IRefDocEdit getRefDocEdit() {
				// TODO wangaa 补充规则
				return null;
			}
		});
	}

	@Override
	public int getDefaultFieldCount() {
		return 2;
	}

	@Override
	public String getRefTitle() {
		return "压力等级";
	}

	@Override
	public void setRefNodeName(String refNodeName) {
		// 业务表的名称
		this.setTableName("pmbd_pressuregrade");
		// 参照名称
		this.m_strRefNodeName = refNodeName;

		// 参照列名称
		this.setFieldCode(new String[] { "pmbd_pressuregrade.code", "pmbd_pressuregrade.name",
				"pmbd_pressuregrade.pk_pressuregrade" });

		// 参照列中文名
		this.setFieldName(new String[] {"压力等级编码","压力等级名称"});

		// 设置主键字段
		this.setPkFieldCode("pmbd_pressuregrade.pk_pressuregrade");

		// 给字段名称赋值
		this.resetFieldName();
		// 是否大小写敏感
		this.setCaseSensive(Boolean.TRUE);

		setResourceID("4801025015");

		this.setFilterRefNodeName(new String[] {"项目组织"});
		// 添加“启用”条件
		this.setAddEnableStateWherePart(true);

		this.addWherePart(" and dr = 0");

	}

	// 管控模式控制
	@Override
	protected String getEnvWherePart() {
		String wherePart = null;
		try {
			wherePart = VisibleUtil.getRefVisibleCondition(getPk_group(), getPk_org(), getBeanID());
		} catch (BusinessException e) {
			Logger.error(e.getMessage());
			wherePart = " 1=2 ";
			return wherePart;
		}
		return wherePart;
	}

	@Override
	public void filterValueChanged(ValueChangedEvent changedValue) {
		String[] pks = (String[]) changedValue.getNewValue();
		if (pks != null && pks.length > 0) {
			String pk_org = pks[0];
			this.setPk_org(pk_org);
		}
	}

	private String getBeanID() throws MetaDataException {
		if (beanID == null) {
			IBean bean = MDBaseQueryFacade.getInstance().getBeanByFullClassName(PressuregradeVO.class.getName());
			beanID = bean.getID();
		}
		return beanID;
	}
}