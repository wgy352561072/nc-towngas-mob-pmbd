package nc.ui.pmbd.bd.pipelinepointclass.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import nc.bs.framework.common.NCLocator;
import nc.bs.pmpub.util.RefrenceUtil;
import nc.bs.uif2.IActionCode;
import nc.bs.uif2.validation.IValidationService;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uif.pub.IUifService;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.ui.pmpub.basedoc.treecard.view.TreeCardBillForm;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.editor.IEditor;
import nc.ui.uif2.model.HierachicalDataAppModel;
import nc.ui.uif2.model.IAppModelService;
import nc.uif.pub.exception.UifException;
import nc.vo.bd.pub.NODE_TYPE;
import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pmpub.common.PMCommonKeyConst;
import nc.vo.pqm.pipelinepointdatas.PipelinepointdatasVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * @author wugy
 */
public class PipelinepointClassDeleteAction extends
		nc.ui.pmpub.basedoc.treecard.action.DeleteAction {

	private static final long serialVersionUID = 1L;

	private IAppModelService service;
	private IEditor editor;

	// 注册删除前校验
	private IValidationService validationService = null;
	
	public PipelinepointClassDeleteAction() {
		super();
		ActionInitializer.initializeAction(this, IActionCode.DELETE);
	}	
	
	// 设置 beforeStartDoAction直接返回true， 把校验提示信息卸载doAction中
	@Override
	public boolean beforeStartDoAction(ActionEvent actionEvent)
			throws Exception {
		return true;
	}	
	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		PipelinepointclassVO obj = (PipelinepointclassVO) ((HierachicalDataAppModel) getModel())
				.getSelectedData();
		if (obj == null) {
			return;
		}

		String pk_org = (String) obj.getAttributeValue(PMCommonKeyConst.PK_ORG);
		NODE_TYPE nodetype = super.getModel().getContext().getNodeType();
		if (NODE_TYPE.ORG_NODE.equals(nodetype)) {
			String refPk = ((TreeCardBillForm) getEditor()).getOrgPanel()
					.getRefPane().getRefPK();
			if (!pk_org.equals(refPk)) {
				throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
						.getNCLangRes().getStrByID("pmpub_0", "04801000-0272")/*
																			 * @res
																			 * "非本组织下的数据，不能删除"
																			 */);
			}
		}		

		//校验
		if (validationService != null) {
			getValidationService().validate(obj);
		}
		
		PipelinepointclassVO[] vos = new PipelinepointclassVO[1];
		vos[0] = obj;
		
/*		boolean isHasChild = RefrenceUtil.checkIsReference(
				"pmbd_pipelinepointclass", vos,
				new String[] { "pmbd_pipelinepointclass" });*/
		
		boolean isHasChild = checkIsHasChild(
				"pmbd_pipelinepointclass", vos,"pk_parent",
				"pmbd_pipelinepointclass");
		
		if(isHasChild){
			String message = "您确定要删除所选数据及其子孙节点吗？";
			if (UIDialog.ID_NO == MessageDialog.showYesNoDlg(getModel()
					.getContext().getEntranceUI(), null, message)) {
				return;
			} else {
				((HierachicalDataAppModel) getModel()).delete();
			}
		}else{
			String message = "您确定要删除所选数据吗？";
			if (UIDialog.ID_NO == MessageDialog.showYesNoDlg(getModel()
					.getContext().getEntranceUI(), null, message)) {
				return;
			} else {
				((HierachicalDataAppModel) getModel()).delete();
			}
		}
		
		showStatusBarMessage(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
				.getStrByID("common", "UCH006")/* @res "删除成功" */);
	}


	/**
	 * @param currenttable 当前表
	 * @param vos 
	 * @param checkfield 检查字段
	 * @param checktable 检查表
	 * @return
	 */
	private boolean checkIsHasChild(String currenttable, PipelinepointclassVO[] vos,
			String checkfield, String checktable) {
		boolean isHasChild = false;
		if(vos == null || vos.length ==0){
			return isHasChild;
		}
		IUAPQueryBS qry = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String[] values = new String[vos.length];
		for(int i = 0; i < vos.length; i++){
			values[i] = vos[i].getPk_pipelinepointclass();
		}
		
		SqlBuilder sqlbuder = new SqlBuilder();
		sqlbuder.append("select * from pmbd_pipelinepointclass where ");
		sqlbuder.append("pk_parent",values);
		sqlbuder.append("and nvl(dr,0) = 0");
		try {
				ArrayList<PipelinepointclassVO> slist = (ArrayList<PipelinepointclassVO>) qry.executeQuery(sqlbuder.toString(), new BeanListProcessor(PipelinepointclassVO.class));
				if(slist != null && slist.size()> 0){
					isHasChild = true;
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		return isHasChild;
	}

	public IAppModelService getService() {
		return service;
	}

	public void setService(IAppModelService service) {
		this.service = service;
	}

	public IEditor getEditor() {
		return editor;
	}

	public void setEditor(IEditor editor) {
		this.editor = editor;
	}

	public IValidationService getValidationService() {
		return validationService;
	}

	public void setValidationService(IValidationService validationService) {
		this.validationService = validationService;
	}

	/** 状态栏显示信息 */
	private void showStatusBarMessage(String msg) {
		ShowStatusBarMsgUtil.showStatusBarMsg(msg, model.getContext());
	}
	
}