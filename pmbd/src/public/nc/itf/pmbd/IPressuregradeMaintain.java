package nc.itf.pmbd;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pmbd.bd.pressuregrade.PressuregradeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.uif2.LoginContext;

/**
 * 
 * <p>
 * <b>本类主要完成以下功能：</b>
 * 
 * <ul>
 * <li>检查项的后台操作
 * </ul>
 * 
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhangzhxa
 * @time 2011-12-23 上午08:58:35
 */
public interface IPressuregradeMaintain {
	/**
	 * 
	 * 方法功能描述：批量保存
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 *             <p>
	 * @since 6.0
	 * @author zhangzhxa
	 * @time 2011-12-23 上午09:22:43
	 */
	BatchOperateVO batchSave(BatchOperateVO vo) throws BusinessException;

	/**
	 * 
	 * 方法功能描述：批量停用
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 *             <p>
	 * @since 6.0
	 * @author zhangzhxa
	 * @time 2011-12-23 上午09:23:18
	 */
	PressuregradeVO[] disEnable(PressuregradeVO[] vo) throws BusinessException;

	/**
	 * 
	 * 方法功能描述：批量启动
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param vo
	 * @return
	 * @throws BusinessException
	 *             <p>
	 * @since 6.0
	 * @author zhangzhxa
	 * @time 2011-12-23 上午09:23:06
	 */
	PressuregradeVO[] enable(PressuregradeVO[] vo) throws BusinessException;

	/**
	 * 
	 * 方法功能描述：初始化界面时，查询数据
	 * <p>
	 * <b>参数说明</b>
	 * 
	 * @param context
	 * @param className
	 * @param whereSql
	 * @return
	 * @throws BusinessException
	 *             <p>
	 * @since 6.0
	 * @author zhangzhxa
	 * @time 2011-12-23 上午10:25:02
	 */
	SuperVO[] queryVOsByWhereSql(LoginContext context,  String whereSql) throws BusinessException;
	
	PressuregradeVO[] insert(PressuregradeVO[] vo) throws BusinessException;

	PressuregradeVO[] update(PressuregradeVO[] vo) throws BusinessException;

	void delete(PressuregradeVO[] vo) throws BusinessException;


}
