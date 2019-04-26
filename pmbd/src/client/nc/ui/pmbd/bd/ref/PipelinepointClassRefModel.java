package nc.ui.pmbd.bd.ref;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.logging.Logger;
import nc.md.MDBaseQueryFacade;
import nc.md.model.IBean;
import nc.md.model.MetaDataException;
import nc.ui.bd.ref.AbstractRefTreeModel;
import nc.ui.bd.ref.IRefDocEdit;
import nc.ui.bd.ref.IRefMaintenanceHandler;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.vo.logging.Debug;
import nc.vo.pm.constant.Enablestate;
import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pmbd.common.consts.FuncCodeConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.util.VisibleUtil;

/**
 * 管线点分类参照类
 * @author wugy
 *
 */
public class PipelinepointClassRefModel extends AbstractRefTreeModel {

	private String beanID = null;
	private IBean ibean;
	public PipelinepointClassRefModel(String refNodeName) {
		setRefNodeName(refNodeName);
		// 维护按钮
		setRefMaintenanceHandler(new IRefMaintenanceHandler() {

		@Override
		public String[] getFucCodes() {
			return new String[] {"4801025001","4801025002","4801025003" };// 功能号
		}

		@Override
		public IRefDocEdit getRefDocEdit() {
			return null;
			}
		});


		//项目组织
		setFilterRefNodeName(new String[]{"项目组织"});/*-=notranslate=-*/
		//添加 启用状态 过滤条件开关
		setAddEnableStateWherePart(true);
	}



	@Override
	public void setRefNodeName(String refNodeName) {
		m_strRefNodeName = refNodeName;
		setFieldCode(new String[] { PipelinepointclassVO.CODE, PipelinepointclassVO.NAME, PipelinepointclassVO.PK_PARENT });
		setFieldName(new String[] {"编码","名称"});
		setHiddenFieldCode(new String[] { PipelinepointclassVO.PK_PIPELINEPOINTCLASS });
		setTableName("pmbd_pipelinepointclass");
		setPkFieldCode(PipelinepointclassVO.PK_PIPELINEPOINTCLASS);
		resetFieldName();
		setCaseSensive(true);
		setChildField(PipelinepointclassVO.PK_PIPELINEPOINTCLASS);
		setFatherField(PipelinepointclassVO.PK_PARENT);

		setResourceID("4801025003");

		//项目组织
		setFilterRefNodeName(new String[]{nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0022")/*@res "项目组织"*/});
	}

	@Override
	protected String getEnvWherePart() {
		try {
			return VisibleUtil.getRefVisibleCondition(getPk_group(), getPk_org(), getBeanID());
		} catch (BusinessException e) {
			Logger.error(e.getMessage());
			throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0023")/*@res "获取参照条件出错"*/);
		}
	}

	private String getBeanID() throws MetaDataException {
		if (beanID == null) {
			IBean bean = MDBaseQueryFacade.getInstance().getBeanByFullClassName(PipelinepointclassVO.class.getName());
			beanID = bean.getID();
		}
		return beanID;
	}

	@Override
	public String getRefTitle() {
		return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0039")/*@res "检查项分类"*/;
	}
	@Override
	public String getWherePart() {
		String slqWhere;
		try {
			slqWhere = getSql();
		} catch (BusinessException e) {
			Debug.error(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0041")/*@res "获取检查项分类管控模式下条件出错"*/);
			slqWhere = " 1=2 ";
		}
		return slqWhere;
	}
	public String getSql() throws BusinessException{

		if(ibean == null){
			try {
				ibean = MDBaseQueryFacade.getInstance().getBeanByFullClassName(PipelinepointclassVO.class.getName());
			} catch (MetaDataException e) {
				throw new MetaDataException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0042")/*@res "获取检查项分类管控元数据ID失败"*/);
			}
		}
		String condition = VisibleUtil.getRefVisibleCondition(getPk_group(), getPk_org(), ibean.getID());

		return condition;
	}

	/**
	 * 项目组织切换时重新加载数据
	 * <p>
	 *
	 * @param changedValue
	 * @author weizq
	 * @time 2013-1-9 下午03:58:09
	 */
	@Override
	public void filterValueChanged(ValueChangedEvent changedValue) {
		String[] pks = (String[]) changedValue.getNewValue();
		if (pks != null && pks.length > 0) {
			String pk_org = pks[0];
			this.setPk_org(pk_org);
		}
	}


}