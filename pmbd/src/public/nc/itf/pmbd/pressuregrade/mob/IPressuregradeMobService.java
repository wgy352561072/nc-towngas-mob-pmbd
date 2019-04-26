package nc.itf.pmbd.pressuregrade.mob;

import java.util.Map;

/**
 * <p>
 * <b>压力等级移动端服务接口</b>
 * <p>
 * @time 2019-04-17
 * @author wugy
 *
 */
public interface IPressuregradeMobService {

	/**
	 * 获取有权限的组织下的压力等级
	 * @param param
	 * @return
	 */
	public Map<String, Object> queryPressuregradeByOrgs(String groupid,
			String[] permOrgs);
}
