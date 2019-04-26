package nc.ui.pmbd.bd.pressuregrade.action;

import nc.vo.pmbd.bd.pressuregrade.PressuregradeVO;


/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>前台新增按钮
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhangzhxa
 * @time 2011-12-23 上午09:42:43
 */
public class AddAction extends nc.ui.pubapp.uif2app.actions.batch.BatchAddLineAction {

	@Override
	protected void setDefaultData(Object obj) {
		if (obj != null) {
			PressuregradeVO vo = (PressuregradeVO) obj;
			vo.setAttributeValue("pk_org", this.getModel().getContext().getPk_org());
			vo.setAttributeValue("pk_group", this.getModel().getContext().getPk_group());
			super.setDefaultData(obj);
		}

	}
}
