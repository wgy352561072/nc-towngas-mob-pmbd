package nc.itf.pmbd.pressuregrade.mob;

import java.util.Map;

/**
 * <p>
 * <b>ѹ���ȼ��ƶ��˷���ӿ�</b>
 * <p>
 * @time 2019-04-17
 * @author wugy
 *
 */
public interface IPressuregradeMobService {

	/**
	 * ��ȡ��Ȩ�޵���֯�µ�ѹ���ȼ�
	 * @param param
	 * @return
	 */
	public Map<String, Object> queryPressuregradeByOrgs(String groupid,
			String[] permOrgs);
}
