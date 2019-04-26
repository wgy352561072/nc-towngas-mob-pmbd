package nc.ui.pmbd.bd.pipelinepointclass.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.itf.pmbd.IPipelinepointclassMaintain;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.DefaultAppModelDataManager;
import nc.ui.uif2.model.IAppModelDataManagerEx;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.logging.Debug;
import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.uif2.LoginContext;

/**
 * 数据模型管理器 主要负责各种方式的模型初始化
 * @author wangaa
 *
 */
public class PipelinepointclassModelDataManager extends DefaultAppModelDataManager implements IAppModelDataManagerEx {

	/**
	 * 是否显示停用
	 */
	private boolean isShowSeal = false;

	private LoginContext context;

	private String sqlWhere = null;

	 /**
     * <p>
     * 根据传入的查询条件查询数据并展示到界面（用于查询模板）
     * </p>
     * @param queryCondition
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public void initModel() {
		PipelinepointclassVO[] ppVos = null;
		try {
			ppVos = getIPipelinepointclass().queryAllIPipelineclass(getModel().getContext(), createCondition(sqlWhere));
		} catch (BusinessException e) {
			Debug.error(e);
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
		getModel().initModel(ppVos);
	}

	 /**
     * <p>
     * 根据传入的查询条件查询数据并展示到界面（打开节点展示数据）
     * </p>
     * * @param queryCondition
     * @throws BusinessException
     *             业务异常
     */
	public void initModelByQuery(String queryCondition) {
		PipelinepointclassVO[] ppVos = null;
		try {
			ppVos = getIPipelinepointclass().queryAllIPipelineclass(getModel().getContext(), queryCondition);
		} catch (BusinessException e) {
			Debug.error(e);
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
		getModel().initModel(ppVos);
		String msg = null;
		if (ppVos.length == 0)
			msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("evaluatemng_0","04850002-0000")/*@res "未查到符合条件的数据，请确认查询条件后重新查询。"*/;
		else
			msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("evaluatemng_0","04850002-0001")/*@res "查询成功，已查到"*/+ ppVos.length + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("evaluatemng_0","04850002-0002")/*@res "条数据"*/;

		ShowStatusBarMsgUtil.showStatusBarMsg(msg, context);
	}

	public void initModelBySqlWhere(String sqlWhere) {
		this.sqlWhere = null;
		PipelinepointclassVO[] ppVos = null;
		try {
			ppVos = getIPipelinepointclass().queryAllIPipelineclass(getModel().getContext(), createCondition(sqlWhere));
			getModel().initModel(ppVos);
		} catch (BusinessException e) {
			Debug.error(e);
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}
	/**
	 * <p>
	 * 打开节点时默认根据启用状态为启用对数据进行过滤
	 * </p>
	 * @param queryCondition
	 * @author xiafy1
	 * @time 2012-6-26 下午04:32:00
	 */
	private String createCondition(String sqlWhere) {
		String condition = null;
		if (!isShowSeal) {
			condition = PipelinepointclassVO.ENABLESTATE + " = " + IPubEnumConst.ENABLESTATE_ENABLE;
		} else {
			condition = " ( " + PipelinepointclassVO.ENABLESTATE + " = " + IPubEnumConst.ENABLESTATE_ENABLE + " or "
					+ PipelinepointclassVO.ENABLESTATE + " = " + IPubEnumConst.ENABLESTATE_DISABLE + " or " + PipelinepointclassVO.ENABLESTATE
					+ " = " + IPubEnumConst.ENABLESTATE_INIT + " ) ";
		}
		return condition;
	}

	public boolean isShowSeal() {
		return isShowSeal;
	}

	public void setShowSeal(boolean isShowSeal) {
		this.isShowSeal = isShowSeal;
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	private IPipelinepointclassMaintain getIPipelinepointclass() {
		return NCLocator.getInstance().lookup(IPipelinepointclassMaintain.class);
	}

	@Override
	public void setShowSealDataFlag(boolean showSealDataFlag) {
		isShowSeal = showSealDataFlag;

	}

	@Override
	public void refresh() {
		this.initModel();
	}
}