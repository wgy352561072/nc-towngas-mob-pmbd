package nc.vo.pmbd.bd.pressuregrade.mob;

import nc.vo.pm.mobile.base.MobileSuperVO;

/**
 * 压力等级mobvo
 * @author wugy
 *
 */
public class PressuregradeMobVO extends MobileSuperVO {
	
	public static final String PK_PRESSUREGRADE = "pk_pressuregrade";
	public static final String CODE = "code";
	public static final String NAME = "name";
	public static final String PK_ORG = "pk_org";
	public static final String PK_GROUP = "pk_group";
	
	
	public PressuregradeMobVO() {
		// 初始化实体存储数据，供查询时调用
		setAttributeValue(PressuregradeMobVO.PK_PRESSUREGRADE, null);
		setAttributeValue(PressuregradeMobVO.CODE, null);
		setAttributeValue(PressuregradeMobVO.NAME, null);
		setAttributeValue(PressuregradeMobVO.PK_ORG, null);
	}
	

	@Override
	public String getPKFieldName() {
		
		return PressuregradeMobVO.PK_PRESSUREGRADE;
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
