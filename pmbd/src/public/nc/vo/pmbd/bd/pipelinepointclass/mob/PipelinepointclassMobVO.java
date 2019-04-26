package nc.vo.pmbd.bd.pipelinepointclass.mob;

import nc.vo.pm.mobile.base.MobileSuperVO;

/**
 * ���ߵ����mobvo
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
	
	// �Ƿ�Ϊĩ���ڵ�
	public static final String ISLEAF = "isleaf";
	
	public PipelinepointclassMobVO() {
		// ��ʼ��ʵ��洢���ݣ�����ѯʱ����
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
		// TODO �Զ����ɵķ������

	}

	@Override
	protected void constructMobRefRelationListAggVO() {
		// TODO �Զ����ɵķ������

	}

}
