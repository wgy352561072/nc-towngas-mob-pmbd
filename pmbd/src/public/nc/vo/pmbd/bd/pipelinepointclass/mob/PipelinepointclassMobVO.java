package nc.vo.pmbd.bd.pipelinepointclass.mob;

import nc.vo.pm.mobile.base.MobileSuperVO;

/**
 * 管线点分类mobvo
 * @author wugy
 *
 */
public class PipelinepointclassMobVO extends MobileSuperVO {
	
	public static final String PK_PIPELINEPOINTCLASS = "pk_pipelinepointclass";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String PK_PARENT = "pk_parent";
	public static final String INNERCODE = "innercode";
	public static final String PK_ORG = "pk_org";
	public static final String PK_GROUP = "pk_group";
	
	// 是否为末级节点
	public static final String ISLEAF = "isleaf";
	
	public PipelinepointclassMobVO() {
		// 初始化实体存储数据，供查询时调用
		setAttributeValue(PipelinepointclassMobVO.PK_PIPELINEPOINTCLASS, null);
		setAttributeValue(PipelinepointclassMobVO.CODE, null);
		setAttributeValue(PipelinepointclassMobVO.NAME, null);
		setAttributeValue(PipelinepointclassMobVO.PK_PARENT, null);
		setAttributeValue(PipelinepointclassMobVO.INNERCODE, null);
		setAttributeValue(PipelinepointclassMobVO.PK_ORG, null);
	}
	

	@Override
	public String getPKFieldName() {
		
		return PipelinepointclassMobVO.PK_PIPELINEPOINTCLASS;
	}


	@Override
	protected void constructMobRefRelationCardAggVO() {
		// TODO 自动生成的方法存根

	}

	@Override
	protected void constructMobRefRelationListAggVO() {
		// TODO 自动生成的方法存根

	}

}
