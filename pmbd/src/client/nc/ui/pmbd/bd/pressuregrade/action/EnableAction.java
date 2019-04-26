package nc.ui.pmbd.bd.pressuregrade.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pmbd.IPressuregradeMaintain;
import nc.ui.pm.basedoc.action.PMBatchEnableLineAction;
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
 * <li>项目状态前台启动按钮
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhangzhxa
 * @time 2011-12-23 上午09:33:53
 */
public class EnableAction extends PMBatchEnableLineAction {


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
			throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("acceptbase_0", "04801001-0002")/*
																				 * @
																				 * res
																				 * "没有选择需要启用的数据，请重新选择！"
																				 */);
		}

		if (UIDialog.ID_YES == CommonConfirmDialogUtils
				.showConfirmEnableDialog(this.getModel().getContext()
						.getEntranceUI())) {
			SuperVO[] returnVOs = this.doEnable(vos4Opr);
			BatchOperateVO batchOperateVO = new BatchOperateVO();
			batchOperateVO.setUpdObjs(returnVOs);
			this.getModel().directSave(batchOperateVO);
			this.getModel().getContext().getEntranceUI().updateUI();
			this.getModel().setSelectedOperaRows(new int[]{rows[0]});
		} else {
			ShowStatusBarMsgUtil.showStatusBarMsg(
					nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
							"acceptbase_0", "04801001-0001")/* @res "已取消" */,
					this.getModel().getContext());
		}
		
		 ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
			        .getNCLangRes().getStrByID("acceptbase_0", "04801001-0021")/*
			                                                                     * @res
			                                                                     * "启用成功"
			                                                                     */, this
			        .getModel().getContext());
	}

	@Override
	public Object doEnable(Object obj) throws Exception {
		// TODO ligangm 补充规则
		return null;
	}

	public SuperVO[] doEnable(SuperVO[] obj) throws Exception {
		if (obj != null) {
			PressuregradeVO[] vos = new PressuregradeVO[obj.length];
			for (int i = 0; i < obj.length; i++) {
				vos[i] = (PressuregradeVO) obj[i];
			}
			vos = this.getService().enable(vos);
			return vos;
		}
		return null;
	}

	/*
	 * public boolean isCurrentDataEnable() { Object[] objs =
	 * this.getModel().getSelectedOperaDatas(); if (ArrayUtil.isEmpty(objs)) {
	 * return false; } for (Object object : objs) {
	 * 
	 * CheckDataListVO vo = (CheckDataListVO) object; if
	 * (vo.getAttributeValue(IBaseServiceConst.ENABLESTATE_FIELD) != null &&
	 * (Enablestate.disable == (Integer) vo
	 * .getAttributeValue(IBaseServiceConst.ENABLESTATE_FIELD))) { return false;
	 * } } return true; }
	 */
	private boolean getEnableValue(SuperVO vo) {
		Integer enableState = (Integer) vo
				.getAttributeValue(PressuregradeVO.ENABLESTATE);
		return Enablestate.disable == enableState;
	}

	private IPressuregradeMaintain getService() {
		if (this.service == null) {
			this.service = NCLocator.getInstance().lookup(IPressuregradeMaintain.class);
		}
		return this.service;
	}
}