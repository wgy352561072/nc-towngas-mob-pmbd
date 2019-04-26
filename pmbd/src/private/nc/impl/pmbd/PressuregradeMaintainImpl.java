package nc.impl.pmbd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.bd.service.ValueObjWithErrLog;
import nc.bs.dao.BaseDAO;
import nc.bs.pm.utils.ReferUnChangeUtils;
import nc.bs.pmpub.basedoc.PMMultiManageTypeBaseService;
import nc.itf.pmbd.IPressuregradeMaintain;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.pm.util.ArrayUtil;
import nc.vo.pmbd.bd.pressuregrade.PressuregradeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.uif2.LoginContext;
import nc.vo.util.VisibleUtil;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>项目状态后台操作实现类
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhangzhxa
 * @time 2011-12-23 上午09:31:34
 */
public class PressuregradeMaintainImpl extends
		PMMultiManageTypeBaseService<PressuregradeVO> implements IPressuregradeMaintain {
	private BaseDAO baseDAO = null;

	public PressuregradeMaintainImpl() {
		super(PressuregradeVO.MDID, PressuregradeVO.class, null);
	}

	@Override
	public BatchOperateVO batchSave(BatchOperateVO vo) throws BusinessException {
		// TODO zhangzhxa 补充规则
		// 校验
		List<String> changeList = new ArrayList<String>();

		changeList.add("code");
		changeList.add("name");
		changeList.add("memo");

		ReferUnChangeUtils.validateSuperVO(vo,
				"nc.vo.pmbd.bd.pressuregrade.PressuregradeVO", changeList,
				PressuregradeVO.CODE);

		PressuregradeVO[] deledCurrtypeVOs = ArrayClassConvertUtil.convert(vo.getDelObjs(), PressuregradeVO.class);
		if (ArrayUtil.isNotEmpty(deledCurrtypeVOs)) {
			PMProxy.lookup(IPressuregradeMaintain.class).delete(deledCurrtypeVOs);
		}

		PressuregradeVO[] updatedCurrtypeVOs = ArrayClassConvertUtil.convert(vo.getUpdObjs(), PressuregradeVO.class);
		if (ArrayUtil.isNotEmpty(updatedCurrtypeVOs)) {
			updatedCurrtypeVOs=PMProxy.lookup(IPressuregradeMaintain.class).update(updatedCurrtypeVOs);
		}

		PressuregradeVO[] addedCurrObjects = ArrayClassConvertUtil.convert(vo.getAddObjs(), PressuregradeVO.class);
		if (ArrayUtil.isNotEmpty(addedCurrObjects)) {
			addedCurrObjects=PMProxy.lookup(IPressuregradeMaintain.class).insert(addedCurrObjects);
		}

		BatchOperateVO returnVO = new BatchOperateVO();
		returnVO.setAddObjs(addedCurrObjects);
		returnVO.setUpdObjs(updatedCurrtypeVOs);
		returnVO.setDelObjs(deledCurrtypeVOs);

		return returnVO;
	}

	@Override
	public PressuregradeVO[] disEnable(PressuregradeVO[] vos)
			throws BusinessException {
		 ValueObjWithErrLog returnValue = super.disableVO(vos);
		    return (PressuregradeVO[]) returnValue.getVos();
	}

	@Override
	public PressuregradeVO[] enable(PressuregradeVO[] vo)
			throws BusinessException {
		
		 ValueObjWithErrLog returnValue = this.enableVO(vo); 
		 return (PressuregradeVO[]) returnValue.getVos();
		 
		
	}

	public BaseDAO getBaseDAO() {
		if (this.baseDAO == null) {
			this.baseDAO = new BaseDAO();
		}

		return this.baseDAO;
	}

	@Override
	public SuperVO[] queryVOsByWhereSql(LoginContext context, String whereSql)
			throws BusinessException {
		String whereConStr = null;

		whereConStr = VisibleUtil.getVisibleCondition(context,
				PressuregradeVO.class);

		if (whereSql != null) {
			whereConStr += whereSql;
		}

		whereConStr += " order by " + PressuregradeVO.CODE;

		Collection<PressuregradeVO> result = this.getBaseDAO()
				.retrieveByClause(PressuregradeVO.class, whereConStr);

		return result == null || result.size() == 0 ? null : result
				.toArray(new PressuregradeVO[0]);
	}
	
	/* (non-Javadoc)
	 * @see nc.itf.pmbd.acceptcheck.prv.IAcceptCheck#insert(nc.vo.pmbd.acceptcheck.AcceptCheckVO[])
	 */
	@Override
	public PressuregradeVO[] insert(PressuregradeVO[] vos) throws BusinessException {
		return super.insertVO(vos);
	}

	/* (non-Javadoc)
	 * @see nc.itf.pmbd.acceptcheck.prv.IAcceptCheck#update(nc.vo.pmbd.acceptcheck.PressuregradeVO[])
	 */
	@Override
	public PressuregradeVO[] update(PressuregradeVO[] vos) throws BusinessException {
		// TODO Auto-generated method stub
		return super.updateVO(vos);
	}

	/* (non-Javadoc)
	 * @see nc.itf.pmbd.acceptcheck.prv.IAcceptCheck#delete(nc.vo.pmbd.acceptcheck.PressuregradeVO[])
	 */
	@Override
	public void delete(PressuregradeVO[] vos) throws BusinessException {
		// TODO Auto-generated method stub
		 super.deleteVO(vos);
	}
}
