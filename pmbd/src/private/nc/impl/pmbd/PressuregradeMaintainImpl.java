package nc.impl.pmbd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.bd.service.ValueObjWithErrLog;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pm.utils.ReferUnChangeUtils;
import nc.bs.pmpub.basedoc.PMMultiManageTypeBaseService;
import nc.itf.pmbd.IPressuregradeMaintain;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.pm.util.ArrayUtil;
import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pmbd.bd.pressuregrade.PressuregradeVO;
import nc.vo.pqm.pipelinepointdatas.PipelinepointdatasVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
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
		//是否被引用
		 this.checkIsReference(vos);
		 super.deleteVO(vos);
	}

	private void checkIsReference(PressuregradeVO[] vos) {
		boolean isRef = false;
		
/*		isRef = RefrenceUtil.checkIsReference(
				"pmbd_pipelinepointclass", vos,
				new String[] { "pqm_pipelinepointdatas" });*/
		
		 isRef = checkPLPCIsReference(
				"pmbd_pressuregrade", vos,"pk_pressuregrade",
				"pqm_pipelinepointdatas");
		
		//被引用
		if (isRef) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0046")/*@res "下列数据由于被引用，不能删除，编码："*/+vos[0].getAttributeValue("code"));
		}
	}

	/**
	 * @param currenttable 当前表
	 * @param vos 
	 * @param checkfield 检查字段
	 * @param checktable 检查表
	 * @return
	 */
	private boolean checkPLPCIsReference(String currenttable, PressuregradeVO[] vos,
			String checkfield, String checktable) {
		boolean isReference = false;
		if(vos == null || vos.length ==0){
			return isReference;
		}
		IUAPQueryBS qry = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		String[] values = new String[vos.length];
		for(int i = 0; i < vos.length; i++){
			values[i] = vos[i].getPk_pressuregrade();
		}
		
		SqlBuilder sqlbuder = new SqlBuilder();
		sqlbuder.append("select * from "+checktable+" where ");
		sqlbuder.append(checkfield,values);
		sqlbuder.append("and nvl(dr,0) = 0");
		try {
				ArrayList<PipelinepointdatasVO> slist = (ArrayList<PipelinepointdatasVO>) qry.executeQuery(sqlbuder.toString(), new BeanListProcessor(PipelinepointdatasVO.class));
				if(slist != null && slist.size()> 0){
					isReference = true;
				}
			} catch (BusinessException e) {
				e.printStackTrace();
			}

		return isReference;
	}
}
