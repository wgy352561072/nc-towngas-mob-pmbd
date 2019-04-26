package nc.itf.pmbd;

import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pub.BusinessException;
import nc.vo.uif2.LoginContext;

public interface IPipelinepointclassMaintain {
	/**
	 * 插入一条检查项分类
	 * 
	 * @param context
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public PipelinepointclassVO insertPipelinepointclassVO(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

	/**
	 * 修改一条检查项分类
	 * 
	 * @param context
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public PipelinepointclassVO updatePipelinepointclassVO(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

	/**
	 * 删除一条检查项分类
	 * 
	 * @param context
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public void deletePipelinepointclassVO(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

	/**
	 * 查询所有的检查项分类
	 * 
	 * @param context
	 * @param c
	 * @return
	 * @throws BusinessException
	 */

	public PipelinepointclassVO[] queryAllIPipelineclass(LoginContext context, String condition) throws BusinessException;

	/**
	 * 判断是否下级都是封存状态
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public boolean isLowerLevelSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

	/**
	 * 判断是否父节点是启用状态
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public boolean isParentLevelUnSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

	/**
	 * 判断是否有下级项目
	 * 
	 * @param context
	 * @param vo
	 * @return
	 * @throws BusinessException
	 * 
	 *             public boolean isHaveChildren(LoginContext context,PipelinepointclassVO vo)
	 *             throws BusinessException;
	 */
	/**
	 * 将其本身和孩子置于封存状态
	 * 
	 * @param context
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public PipelinepointclassVO[] doSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

	/**
	 * 将其本身置于取消封存状态
	 * 
	 * @param context
	 * @param vo
	 * @return
	 * @throws BusinessException
	 */
	public PipelinepointclassVO doUnSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException;

}