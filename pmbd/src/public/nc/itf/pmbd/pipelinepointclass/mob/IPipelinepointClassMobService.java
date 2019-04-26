package nc.itf.pmbd.pipelinepointclass.mob;

import java.util.Map;

/**
 * <p>
 * <b>管线点分类移动端服务接口</b>
 * <p>
 * @time 2019-04-16
 * @author wugy
 *
 */
public interface IPipelinepointClassMobService {
	
	/**
	 * 获取有权限的组织下的管线点分类
	 * @param param
	 * @return
	 */
	public Map<String, Object> queryPipelinepointClassByOrgs(String groupid,
			String[] permOrgs);
	
}
