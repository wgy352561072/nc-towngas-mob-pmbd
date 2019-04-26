package nc.vo.pmbd.bd.pipelinepointclass.util;

import nc.itf.uap.bd.refcheck.IReferenceCheck;
import nc.vo.pm.proxy.PMProxy;
import nc.vo.pub.BusinessException;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
/**
 * 检查项分类引用校验
 * @author wangaa
 *
 */
public class PipelinepointClassRefrenceUtil {
	public static boolean checkIsReference(SuperVO vo){
		if(null==vo)
			return false;
		String key = vo.getPrimaryKey();
		boolean isRef = false;
		try {
			isRef = getCheckService().isReferencedOnlyInclude("pmbd_pipelinepointclass", key, new String[]{"pqm_pipelinepointdatas"});
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
		return isRef;
	}
	
	private static IReferenceCheck getCheckService() {
	    return PMProxy.lookup(IReferenceCheck.class);
	  }
}
