package nc.ui.pmbd.bd.pressuregrade.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pmbd.IPressuregradeMaintain;
import nc.ui.pm.basedoc.action.PMBatchDisableLineAction;
import nc.ui.pub.beans.UIDialog;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.components.CommonConfirmDialogUtils;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pm.constant.Enablestate;
import nc.vo.pmbd.bd.pressuregrade.PressuregradeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;

/**
 *
 * <p>
 * <b>本类主要完成以下功能：</b>
 *
 * <ul>
 * <li>前台操作的停用按钮
 * </ul>
 *
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhangzhxa
 * @time 2011-12-23 上午09:40:28
 */
public class DisEnableAction extends PMBatchDisableLineAction {

	private IPressuregradeMaintain service = null;

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object[] vos = this.getModel().getSelectedOperaDatas();
		
		Integer[] rows = this.getModel().getSelectedOperaRows();
		
		List<SuperVO> voOprList = new ArrayList<SuperVO>();
		List<SuperVO> voUnOprList = new ArrayList<SuperVO>();
		for (Object obj : vos) {
			SuperVO superVO = (SuperVO) obj;
			if (this.getEnableValue(superVO)) {
				voOprList.add(((SuperVO) obj));
			} else {
				voUnOprList.add(((SuperVO) obj));
			}
		}

		SuperVO[] vos4Opr = voOprList.toArray(new SuperVO[voOprList.size()]);
		if (vos4Opr == null || vos4Opr.length == 0) {
			throw new BusinessException("没有选择需要停用的数据，请重新选择！");
		}

		if (UIDialog.ID_YES == CommonConfirmDialogUtils.showConfirmDisableDialog(this.getModel().getContext()
				.getEntranceUI())) {
			SuperVO[] returnVOs = this.doDisable(vos4Opr);
			BatchOperateVO batchOperateVO = new BatchOperateVO();
			batchOperateVO.setUpdObjs(returnVOs);
			this.getModel().directSave(batchOperateVO);
			this.getModel().getContext().getEntranceUI().updateUI();
			this.getModel().setSelectedOperaRows(new int[]{rows[0]});
		} else {
			ShowStatusBarMsgUtil.showStatusBarMsg("已取消", this.getModel().getContext());
		}
		
		ShowStatusBarMsgUtil.showStatusBarMsg(
				nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
						"acceptbase_0", "04801001-0020")/*
														 * @res "停用成功"
														 */, this.getModel()
						.getContext());
	}

	@Override
	public Object doDisable(Object obj) throws Exception {

		return null;
	}

	public SuperVO[] doDisable(SuperVO[] obj) throws Exception {
		if (obj != null) {
			PressuregradeVO[] vos = new PressuregradeVO[obj.length];
			for (int i = 0; i < obj.length; i++) {
				vos[i] = (PressuregradeVO) obj[i];
			}
			vos = this.getService().disEnable(vos);
			return vos;
		}
		return null;
	}

	private boolean getEnableValue(SuperVO vo) {
		Integer enableState = (Integer) vo.getAttributeValue(PressuregradeVO.ENABLESTATE);
		return Enablestate.enable == enableState;
	}

	private IPressuregradeMaintain getService() {
		if (this.service == null) {
			this.service = NCLocator.getInstance().lookup(IPressuregradeMaintain.class);
		}
		return this.service;
	}
}