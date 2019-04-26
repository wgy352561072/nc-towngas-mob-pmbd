package nc.impl.pmbd;

import java.util.Collection;
import java.util.Map;

import nc.bs.bd.baseservice.validator.BDTreeUpdateLoopValidator;
import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.bs.pmpub.basedoc.PMBDRefCheck4MuiltFileds;
import nc.bs.pmpub.basedoc.PMCodeTreeBaseService;
import nc.bs.pmpub.util.RefrenceUtil;
import nc.bs.uif2.validation.Validator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.pmbd.IPipelinepointclassMaintain;
import nc.md.MDBaseQueryFacade;
import nc.md.data.access.NCObject;
import nc.md.model.IBusinessEntity;
import nc.md.model.MetaDataException;
import nc.vo.bd.meta.IBDObject;
import nc.vo.bd.pub.IPubEnumConst;
import nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO;
import nc.vo.pmbd.bd.pipelinepointclass.util.PipelinepointClassRefrenceUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uif2.LoginContext;
import nc.vo.util.BDUniqueRuleValidate;
import nc.vo.util.SuperVOTreeStructrueValidator;
import nc.vo.util.VisibleUtil;

/**
 * 检查项分类后台实现类
 * @author wangaa
 *
 */
public class PipelinepointclassMaintainImpl extends PMCodeTreeBaseService<PipelinepointclassVO> implements IPipelinepointclassMaintain{
	private static String PP_MDID = null;
	static {
		try {
			// 获取元数据ID
			PP_MDID = MDBaseQueryFacade.getInstance().getBeanByFullClassName(PipelinepointclassVO.class.getName()).getID();
		} catch (MetaDataException e) {
			Logger.error(e.getMessage(), e);
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
	}

	public PipelinepointclassMaintainImpl() {
		super(PP_MDID, null);
	}
    /**
     * <p>
     * 插入一条档案数据。
     * </p>
     *
     * @param context
     *            环境变量
     * @param vo
     *            插入档案数据
     * @return PipelinepointclassVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	public PipelinepointclassVO insertPipelinepointclassVO(LoginContext context, PipelinepointclassVO vo) throws BusinessException {
		return insertVO(vo);
	}
    /**
     * <p>
     * 更新一条档案数据。
     * </p>
     *
     * @param context
     *            环境变量
     * @param vo
     *            更新档案数据
     * @return PipelinepointclassVO
     *            返回结果
     * @throws BusinessException
     *            业务异常
     */
	public PipelinepointclassVO updatePipelinepointclassVO(LoginContext context, PipelinepointclassVO vo) throws BusinessException {

		return updateVO(vo);
	}
    /**
     * <p>
     * 删除一条档案数据。
     * </p>
     *
     * @param context
     *            环境变量
     * @param vo
     *            删除档案数据
     * @return
     *
     * @throws BusinessException
     *            业务异常
     */
	public void deletePipelinepointclassVO(LoginContext context, PipelinepointclassVO vo) throws BusinessException {
		//是否被引用
		this.checkIsReference(vo);
		//是否有子节点，子节点是否被引用
		this.isHasChild(vo);
		
		//删除选择点下所有节点
		deleteMultiVOs(vo);
	}

	/**
	 * 是否被引用
	 * 
	 * @param vo
	 */
	private void checkIsReference(PipelinepointclassVO vo){
		boolean isRef = false;

		PipelinepointclassVO[] vos = new PipelinepointclassVO[1];
		vos[0] = vo;
		
		isRef = RefrenceUtil.checkIsReference(
				"pmbd_pipelinepointclass", vos,
				new String[] { "pqm_pipelinepointdatas" });
		
		//被引用
		if (isRef) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("acceptbase_0","04801001-0046")/*@res "下列数据由于被引用，不能删除，编码："*/+vo.getAttributeValue("code"));
		}
	}
	
	/**
	 * 查询时候有子节点,并判断子节点是否被引用
	 */
	public void isHasChild(PipelinepointclassVO vo) {
		VOQuery<PipelinepointclassVO> query = new VOQuery<PipelinepointclassVO>(
				PipelinepointclassVO.class);
		StringBuffer condition = new StringBuffer();

		condition.append(" and ").append(PipelinepointclassVO.INNERCODE)
				.append(" like ").append("'").append(vo.getAttributeValue("innercode"))
				.append("%'").append(" and ")
				.append(PipelinepointclassVO.INNERCODE).append(" <> ").append("'")
				.append(vo.getAttributeValue("innercode")).append("'");
		
		PipelinepointclassVO[] vos = query.query(condition.toString(), null);

		boolean isRef = false;
		// 判断子孙节点是否被引用
		isRef = RefrenceUtil.checkIsReference(
				"pmbd_pipelinepointclass", vos,
				new String[] { "pqm_pipelinepointdatas" });
		if (isRef) {
			ExceptionUtils
					.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
							.getNCLangRes().getStrByID("acceptbase_0",
									"04801001-0040")/* @res "该文档分类子节点被引用，不能删除！" */);
		}
	}	
	
	/**
	 * 检查是否被引用
	 */
	private void checkReference(PipelinepointclassVO vo){
		boolean isRef = PipelinepointClassRefrenceUtil.checkIsReference(vo);
		if(isRef){
			ExceptionUtils.wrappBusinessException("该管线点分类被引用，不能删除！");
		}
	}

    /**
     * <p>
     * 停用档案数据。
     * </p>
     *
     * @param context
     *            环境变量
     * @param vo
     *           停用档案数据
     * @return PipelinepointclassVO
     *           返回结果
     * @throws BusinessException
     *            业务异常
     */
	@Override
	public PipelinepointclassVO[] doSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException {
		return disableMutilVO(vo);
	}



    /**
     * <p>
     * 启用档案数据。
     * </p>
     *
     * @param context
     *            环境变量
     * @param vo
     *           启用档案数据
     * @return PipelinepointclassVO
     *           返回结果
     * @throws BusinessException
     *            业务异常
     */
	@Override
	public PipelinepointclassVO doUnSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException {
		return enableVO(vo);
	}

	@Override
	// 判断下级节点是否都处于停用状态
	public boolean isLowerLevelSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException {
		BaseDAO basedao = new BaseDAO();
		PipelinepointclassVO[] returnCategory;
		String whereSql = PipelinepointclassVO.INNERCODE + " like '" + vo.getAttributeValue("innercode") + "%'" + " and " + PipelinepointclassVO.ENABLESTATE + " = "
				+ IPubEnumConst.ENABLESTATE_ENABLE + " and " + PipelinepointclassVO.PK_GROUP + " = '" + vo.getAttributeValue("pk_group") + "'";

		// 得到返回的没有封存的VO
		Collection<PipelinepointclassVO> acvo = basedao.retrieveByClause(PipelinepointclassVO.class, whereSql);
		returnCategory = acvo.toArray(new PipelinepointclassVO[0]);
		boolean isAllSeal = true;
		if (returnCategory.length <= 1) {
			isAllSeal = false;
		}
		return isAllSeal;
	}

    /**
     * <p>
     * 判断父节点是否启用
     * </p>
     *
     * @param context
     *            环境变量
     * @param vo
     *            当前档案数据
     * @return boolean
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public boolean isParentLevelUnSeal(LoginContext context, PipelinepointclassVO vo) throws BusinessException {
		BaseDAO basedao = new BaseDAO();
		PipelinepointclassVO[] returnCategory;
		String whereSql = PipelinepointclassVO.PK_PIPELINEPOINTCLASS + " = " + "'" + vo.getAttributeValue("pk_parent") + "'";
		Collection<PipelinepointclassVO> acparent_vo = basedao.retrieveByClause(PipelinepointclassVO.class, whereSql);
		returnCategory = acparent_vo.toArray(new PipelinepointclassVO[0]);
		if (returnCategory.length == 0) {
			return true;

		}
		if ((int)returnCategory[0].getAttributeValue("enablestate") == 2)
			return true;
		return false;
	}

    /**
     * <p>
     * 查询
     * </p>
     *
     * @param context
     *            环境变量
     * @param condition
     *            查询条件
     * @return PipelinepointclassVO
     *            返回结果
     * @throws BusinessException
     *             业务异常
     */
	@Override
	public PipelinepointclassVO[] queryAllIPipelineclass(LoginContext context, String condition) throws BusinessException {
		// 排序

		// 管控模式下的可见性
		String visible = VisibleUtil.getVisibleCondition(context, PP_MDID);
		if (condition == null) {
			condition = visible;
		} else
			condition = condition + " and " + visible;

		BaseDAO basedao = new BaseDAO();
		Collection<PipelinepointclassVO> pp = basedao.retrieveByClause(PipelinepointclassVO.class, condition, PipelinepointclassVO.CODE);

		return pp.toArray(new PipelinepointclassVO[0]);
	}

	@Override
	protected Validator[] getUpdateValidator(PipelinepointclassVO oldVO) {
		String codeField = getCodeFieldName(oldVO);
		Validator[] validators = new Validator[] { new BDTreeUpdateLoopValidator(), new BDUniqueRuleValidate(),new PMBDRefCheck4MuiltFileds(new String[]{codeField})};
		return validators;
	}
	@Override
	protected Validator[] getInsertValidator() {
		Validator[] validators = new Validator[] {new BDUniqueRuleValidate(),
				new SuperVOTreeStructrueValidator(PipelinepointclassVO.PK_PARENT) };
		return validators;
	}

	private String getCodeFieldName(SuperVO vo) {
		if (vo == null) {
			return null;
		}
		NCObject ncObj = NCObject.newInstance(vo);

		Map<String, String> flowBizInfoMap = (Map<String, String>) ((IBusinessEntity) ncObj.getRelatedBean()).getBizInterfaceMapInfo(IBDObject.class.getName());
		String codeField = flowBizInfoMap.get(PipelinepointclassVO.CODE);
		return codeField;
	}

	@Override
	protected void writeEnableBusiLog(PipelinepointclassVO vo)
			throws BusinessException {
	}
	@Override
	protected void writeInsertBusiLog(PipelinepointclassVO... vos)
			throws BusinessException {
	}
	@Override
	protected void writeUpdatedBusiLog(PipelinepointclassVO[] oldVOs,
			PipelinepointclassVO[] vos) throws BusinessException {
	}
}