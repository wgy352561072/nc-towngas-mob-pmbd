package nc.ui.pmbd.bd.pressuregrade.model;

import nc.itf.pmbd.IPressuregradeMaintain;
import nc.ui.uif2.model.IBatchAppModelService;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.uif2.LoginContext;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>项目状态前台服务类
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhangzhxa
 * @time 2011-12-23 上午10:21:43
 */
public class PressureGradeModelService implements IBatchAppModelService {

	@Override
	public BatchOperateVO batchSave(BatchOperateVO batchVO) throws Exception {
		// TODO Auto-generated method stub
		return PMProxy.lookup(IPressuregradeMaintain.class).batchSave(batchVO);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] queryByWhereSql(LoginContext context, String whereSql) throws Exception {
		// TODO Auto-generated method stub
		return PMProxy.lookup(IPressuregradeMaintain.class).queryVOsByWhereSql(context,  whereSql);
	}

}
