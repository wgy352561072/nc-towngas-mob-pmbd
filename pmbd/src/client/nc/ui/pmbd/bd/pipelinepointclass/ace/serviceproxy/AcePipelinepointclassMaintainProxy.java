package nc.ui.pmbd.bd.pipelinepointclass.ace.serviceproxy;

import nc.itf.pmbd.IPipelinepointclassMaintain;
import nc.itf.pmbd.acceptchecktype.prv.IAccheckType;
import nc.ui.pmpub.basedoc.treecard.model.TreeCardAppModelService;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

/**
 * 检查项分类服务类
 * @author wangaa
 *
 */
public class AcePipelinepointclassMaintainProxy extends TreeCardAppModelService{

	protected LoginContext context;

	public static IPipelinepointclassMaintain getPPService() {
		return PMProxy.lookup(IPipelinepointclassMaintain.class);
	}

	/**
     * <p>
     * 新增档案数据。
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *            新增档案数据            
     * @return AssessclassmentVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public Object insert(Object object) throws Exception {
		if (object instanceof PipelinepointclassVO) {
			return getPPService().insertPipelinepointclassVO(getContext(), (PipelinepointclassVO) object);
		}
		return object;
	}
	/**
     * <p>
     * 查询档案数据。
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *            查询档案数据            
     * @return AssessclassmentVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * <p>
     * 修改档案数据。
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *            修改档案数据            
     * @return AssessclassmentVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public Object update(Object object) throws Exception {
		if (object instanceof PipelinepointclassVO) {
			return getPPService().updatePipelinepointclassVO(getContext(), (PipelinepointclassVO) object);
		}
		return null;
	}
    /**
     * <p>
     * 删除档案数据。
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *            删除档案数据            
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public void delete(Object object) throws Exception {
		if (object instanceof PipelinepointclassVO) {
			getPPService().deletePipelinepointclassVO(context, (PipelinepointclassVO)object);
		}
	}
	/**
     * <p>
     * 停用档案数据。
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *            停用档案数据            
     * @return AssessclassmentVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	public Object[] doSeal(LoginContext context, Object obj) throws Exception {
		PipelinepointclassVO vo = (PipelinepointclassVO) obj;

		return getPPService().doSeal(context, vo);
	}
    /**
     * <p>
     * 启用档案数据。
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *            启用档案数据            
     * @return AssessclassmentVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	public Object doUnSeal(LoginContext context, Object obj) throws Exception {
		PipelinepointclassVO vo = (PipelinepointclassVO) obj;

		return getPPService().doUnSeal(context, vo);
	}
	/**
     * <p>
     * 判断子节点档案是否停用
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *           判断子节点档案是否停用         
     * @return boolean
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	public boolean isLowerLevelSeal(LoginContext context, Object obj) throws Exception {
		PipelinepointclassVO vo = (PipelinepointclassVO) obj;

		return getPPService().isLowerLevelSeal(context, vo);
	}
    /**
     * <p>
     * 判断父节点是否启用
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *           判断父节点是否启用  
     * @return boolean
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	public boolean isParentLevelUnSeal(LoginContext context, Object obj) throws Exception {
		PipelinepointclassVO vo = (PipelinepointclassVO) obj;

		return getPPService().isParentLevelUnSeal(context, vo);
	}

	/*
	 * public boolean isHaveChildren(LoginContext context, Object obj) throws
	 * Exception { AssessclassmentVO vo = (AssessclassmentVO) obj;
	 * 
	 * return getACService().isHaveChildren(context, vo); }
	 */
    /**
     * <p>
     * 根据传入的查询条件查询档案
     * </p>
     * 
     * @param context
     *            环境变量
     * @param object
     *           判断父节点是否启用  
     * @return AssessclassmentVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	public PipelinepointclassVO[] queryAllAssessclassment(LoginContext context, String condition) throws Exception {
		return getPPService().queryAllIPipelineclass(context, condition);
	}

	public LoginContext getContext() {
		return context;
	}

	public void setContext(LoginContext context) {
		this.context = context;
	}

}
