package nc.itf.pmbd.pipelinepointclass.mob;

import java.util.Map;

/**
 * <p>
 * <b>���ߵ�����ƶ��˷���ӿ�</b>
 * <p>
 * @time 2019-04-16
 * @author wugy
 *
 */
public interface IPipelinepointClassMobService {
	
	/**
	 * ��ȡ��Ȩ�޵���֯�µĹ��ߵ����
	 * @param param
	 * @return
	 */
	public Map<String, Object> queryPipelinepointClassByOrgs(String groupid,
			String[] permOrgs);
	
}
