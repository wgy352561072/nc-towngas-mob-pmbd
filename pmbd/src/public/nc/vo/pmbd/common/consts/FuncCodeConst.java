package nc.vo.pmbd.common.consts;

/**
 * 功能号
 * 
 * @author wangaa
 * @date 2011-9-21
 */
public interface FuncCodeConst {
	
	/** 项目里程碑--全局 */
    String MILESTONE_GLOBAL = "4801040015";
		
    /** 项目里程碑--集团 */
	String MILESTONE_GROUP = "4801040016";
		
	 /** 项目里程碑--组织 */
     String MILESTONE_ORG = "4801040017";
     //任务状态-组织
     String WBSSTATE_ORG = "4801040007";
     //任务状态-集团
     String WBSSTATE_GROUP = "4801040007";
     //任务状态-全局
     String WBSSTATE_GLOBAL = "4801040007";
	
	//项目状态-集团
	String PROJECTSTATE_GROUP = "4801005035";
	
	//项目状态-全局
	String PROJECTSTATE_GLOBAL = "4801005036";

	/** 检查项--集团 */
	public static final String ACCEPTCHECK_GROUP = "4801025005";

	/** 检查项--组织 */
	public static final String ACCEPTCHECK_ORG = "4801025004";

	/** 预算 */
	public final static String BUDGET = "4810012015";

	/** 预算追加 */
	public final static String BUDGETADD = "4810012025";
	public final static String BUDGETEXE = "4810012035";
	public final static String PROJECTBUILD = "4810005005";
	/** 检查标准--集团 */
	public static final String CHECKREQUEST_GROUP = "4801005055";

	/** 检查标准--组织 */
	public static final String CHECKREQUEST_ORG = "4801005056";

	/** 项目分包合同 (协议付款) */
	public final static String CONTR_PAYMENT = "4820105005";
	/** 合同类型--组织**/
	public static final String CONTRACTTYPE_GROUP = "4820001005";
	/** 合同类型--集团**/
	public static final String CONTRACTTYPE_ORG = "4820001006";

	/** 项目分包合同 (进度款) */
	public final static String CONTR_SCHE = "4820005005";
	
	/** 指标计划*/
	public final static String INDEXPLAN = "4810010008";

	/** 项目分包合同变更单 */
	public final static String CONTRALTER = "4820005010";

	/** 合同结算单 */
	public final static String CONTRBAL = "4820005035";

	/** 合同预付款 */
	public final static String CONTRPREPAY = "4820005025";

	/** 合同进度款 */
	public final static String CONTRSCHE = "4820005030";
	
	/** 费用结算单 */
	public final static String FEEBALANCE = "4820020005";

	/** 资料清单--集团 */
	public static final String DATALIST_GROUP = "4801025015";

	/** 资料清单--组织 */
	public static final String DATALIST_ORG = "4801025014";

	/** 设备卡片 */
	public final static String equip = "4510016005";

	/** 销售合同 */
	public final static String MARKECT_CONTRACT = "4820003005";

	/** 物资需求调价单 */
	public final static String MATERIAL_ALTER_PRICE = "4810010020";

	/** 物资服务及需求单 */
	public final static String MATERIAL_PLAN = "4810010010";

	/** 物资需求追加单 */
	public final static String MATERIAL_PLAN_ADD = "4810010015";

	/** 项目物资备料表 */
	public final static String MATERIAL_STOCK = "4810015030";

	/** 产出物类型-全局 */
	public final static String OUTCOMETYPE_GLOBE = "4801005027";

	/** 产出物类型-集团 */
	public final static String OUTCOMETYPE_GROUP = "4801005026";

	/** 产出物类型-组织 */
	public final static String OUTCOMETYPE_ORG = "4801005025";

	/** 付款计划 */
	public final static String PAYPLAN = "4820105015";

	/** 产出物调整单 */
	public final static String PROADAPTCARD = "4810020015";

	/** 费用记录卡 */
	public static final String PROCOSTCARD = "4801005056";

	/** 产出物维护 */
	public final static String PROJECTPRODUCT = "4810020005";

	/** 项目类型-全局 */
	public final static String PROJECTTYPE_GLOBE = "4801005010";

	/** 项目类型-集团 */
	public final static String PROJECTTYPE_GROUP = "4801005011";

	/** 产出物交付单 */
	public final static String PROTRANCARD = "4810020010";

	/** 决算标志 */
	public static final String FINALFlAG = "4810027005";

	/** 预算控制-全局 */
	String BUDGETCTRL_GLOBE = "4801005019";

	/** 预算控制-集团 */
	String BUDGETCTRL_GROUP = "4801005020";
	
	/** 预算控制-组织 */
	String BUDGETCTRL_ORG = "4801005021";

	/** 项目-全局 */
	String PROJECT_GLOBE = "4810005010";

	/** 项目-集团 */
	String PROJECT_GROUP = "4810005011";

	/** 项目-组织 */
	String PROJECT_ORG = "4810005012";
	
	/** 服务价目表 */
	public static final String   SERVPRICE_GROUP= "4801005045";
	/** 服务价目表 */
	public static final String SERVPRICE_ORG = "4801005046";
	/** 计量指标-集团 */
	public static final String MEASUREINDEX_GROUP= "4801035005";
	/** 计量指标-组织 */
	public static final String MEASUREINDEX_ORG= "4801035006";
	
	/** 指标模板-集团 */
	public static final String INDEXTEMPLET_GROUP= "4801035010";
	/** 指标模板-组织 */
	public static final String INDEXTEMPLET_ORG= "4801035011";

	
	/** 指标填报单 */
	public static final String COMPWORKFILL = "4810015025";

	/**
	 * #################################质量管理
	 * begin#################################################
	 */
	/** 问题原因-组织 */
	public static final String PROBLEMREASON_ORG = "4850004011";
	/** 问题原因-集团 */
	public static final String PROBLEMREASON_GROUP = "4850004010";
	/** 质检类型-组织 */
	public static final String QCTYPE_ORG = "4850004006";
	/** 质检类型-集团 */
	public static final String QCTYPE_GROUP = "4850004005";
	/** 问题分类-组织 */
	public static final String PROBLEMTYPE_ORG = "4850004046";
	/** 问题分类-集团 */
	public static final String PROBLEMTYPE_GROUP = "4850004045";
	/** 变更级别-组织 */
	public static final String ALTERLEVEL_ORG = "4801030011";
	/** 变更级别-集团 */
	public static final String ALTERLEVEL_GROUP = "4801030010";

	/** 评分结论-全局 */
	public static final String SCOREDEFINITION_GLOBAL = "4850004026";
	/** 评分结论-集团 */
	public static final String SCOREDEFINITION_GROUP = "4850004025";
	/** 权重方案-集团 */
	public static final String EVALPLAN_GROUP = "4850004040";	
	/** 权重方案-组织 */
	public static final String EVALPLAN_ORG = "4850004041";
	/** 评核标准-集团 */
	public static final String SCORESTANDARD_GROUP = "4850004035";
	/** 评分规则-全局 */
	public static final String SCORESECTION_GLOBAL = "4850004031";
	/** 评分规则-集团 */
	public static final String SCORESECTION_GROUP = "4850004030";
	/** 评核标准-组织 */
	public static final String SCORESTANDARD_ORG = "4850004036";

	/** 复工单 */
	public static final String REPEATWORK = "4810019010";

	/** 停工单 */
	public static final String STOPWORK = "4810019005";

	/** 变更单 */
	public static final String ALTER = "4810018005";

	/** 变更单查询 */
	public static final String ALTERQUERY = "4810150050";
	
	/** 评核项集团 */
	public static final String EVALUATEITEM_GROUP= "4850004020";
	
	/** 评核项组织 */
	public static final String EVALUATEITEM_ORG= "4850004021";
	
	/** 评核项分类集团 */
	public static final String ASSESSCLASS_GROUP= "4850004015";
	
	/** 评核项分类组织 */
	public static final String ASSESSCLASS_ORG= "4850004016";
	
	/** 变更类别集团*/
	public static final String ALTERCATEGORY_GROUP= "4801030005";
	
	/** 变更类别组织 */
	public static final String ALTERCATEGORY_ORG= "4801030006";
	
	/** 检查标准分类全局 */ 
	public static final String CHECKSTANDARDCLASSIFY_GLOBAL= "4801035015";
	
	/** 检查标准分类集团 */
	public static final String CHECKSTANDARDCLASSIFY_GROUP= "4801035016";
	
	/** 检查标准分类组织 */
	public static final String CHECKSTANDARDCLASSIFY_ORG= "4801035017";


	public static final String SCORESTANDCLASS_GLOBLE= "4850004032";
	public static final String SCORESTANDCLASS_GORUP= "4850004033";
	public static final String CORESTANDCLASS_ORG= "4850004034";
	
	public static final String PROBLEM= "4850012005";
	/**
	 * #################################质量管理
	 * end#################################################
	 */
	public static final String MILESTON_ORG = "4801040017";

	public static final String MILSTONE_GROUP = "4801040016";
	
	
	/** WBS模板组织 */
	public static final String WBSTEMPLET_ORG = "4801040027";
	/** WBS模板集团*/
	public static final String WBSTEMPLET_GROUP = "4801040026";
	/** WBS模板全局*/
	public static final String WBSTEMPLET_GLOBAL = "4801040025";
	
	/** 文档分类组织 */
	public static final String DOCUMENTTYPE_ORG = "4870005007";
	/** 文档分类集团*/
	public static final String DOCUMENTTYPE_GROUP = "4870005006";
	/** 文档分类全局*/
	public static final String DOCUMENTTYPE_GLOBAL = "4870005005";
	
	/** 单据归档设置组织 */
	public static final String BillDocSetting_ORG = "4870005011";
	/** 单据归档设置集团*/
	public static final String BillDocSetting_GROUP = "4870005010";
	
	/** 文档中心*/
	public static final String PROJECTDOC = "4870010010";
	
	/**材料结算**/
	public static final String MATERIALACC = "4820015005";
		
	/**扣款单**/
	public static final String DEDUCT = "4820025005";
	
	/** 收票登记 */
	public static final String RECEIPT_REGISTER = "4820030005";
	
	/** 核销处理*/
	public static final String RECEIPT_VERIFY = "4820030010";
	
	/**暂估处理*/
	public static final String ESTIMATE = "4820030015";
	
	/**暂估记录*/
	public static final String ESTIMATE_RECORD = "4820030016";
	
	/**无票暂估单*/
	public static final String SURBILL_ESTIMATE = "4820030025";
	
	/**核销结算单*/
	public static final String VERIFY_BALANCE = "4820030020";
	
	/** 工序-集团 */
	public static final String PROCESS_GROUP= "4801040029";
 	/** 工序-组织 */
	public static final String PROCESS_ORG= "4801040030";
 	/** 技术角色-集团 */
	public static final String TECHNICALROLE_GROUP= "480105504";
	/** 技术角色-组织 */
	public static final String TECHNICALROLE_ORG= "480105505";
	/** 证书-组织 */
	public static final String CERTIFICATE_ORG="480105508";
	/** 证书-集团 */
	public static final String CERTIFICATE_GROUP="480105509";
	/** 工作联系*/
	public static final String WORKCONTACT = "4810006001";
	/** 关键进度填报*/
	public static final String KEYPROGRESS = "4810015010";
	/** 指标进度填报*/
	public static final String INDEXPROGRESS = "4810015025";
	/** 图纸提交*/
	public static final String DRAWING = "4810021005";
	/** 现场签证*/
	public static final String SITEVISA = "4810018010";
	/** 质量巡查 */
	public static final String QUALITYPATROL = "4850010010";
	/** 旁站记录 */
	public static final String SIDEQUALITY = "4850010011";
	/** 平行检查 */
	public static final String PARAQUALITY = "4850010012";
	/** 施工记录 */
	public static final String CONSTQUALITY = "4850010013";
	/** 工序报验 */
	public static final String PSCHECK = "4850010014";
	/** 安全巡查 */
	public static final String SECURITYPATROL = "4850010015";
	/** 综合验收 */
	public static final String FINNALACCEPT = "4810025005";
	/** 预验收 */
	public static final String PREACCEPT = "4810025007";
	/** 隐蔽工程验收 */
	public static final String HIDDEDPROJECT = "4810025006";
	/** 焊机数据*/
	public static final String WELDINGDATA = "4850013010";
	/** 焊机校验规则*/
	public static final String WELDINGRULE = "4850013005";
	/** 工序拍照*/
	public static final String PROCESSIMAGE = "4850014005";
	/** 项目日志*/
	public static final String PROJECTLOG = "4850018003";
	/** 签证类别-组织 */
	public static final String VISACATEGORY_ORG="4801030016";
	/** 签证类别-集团 */
	public static final String VISACATEGORY_GROUP="4801030015";
	
	
	
	/************************* 移动辅助功能号  *****************/
	/************** PMBD  ******/
	/** 工程移动*/
	public static final String PMMOBILE = "A0E036";
	/** 签到*/
	public static final String SIGNMOB = "A0E040";

	/************** PIM  ******/
	/** 项目团队*/
	public static final String TEAM = "A0E050";
	/** 任务进度*/
	public static final String KEYPROGRESSMOB = "A0E053";
	/** 指标进度*/
	public static final String INDEXPROGRESSMOB = "A0E054";
	/** 工作联系*/
	public static final String WORKCONTACTMOB = "A0E056";
	/** 变更记录*/
	public static final String CELOGMOB = "A0E058";
	/** 签证记录*/
	public static final String SITEVISAMOB = "A0E059";
	/** 项目预验收*/
	public static final String PECHECKMOB = "A0E060";
	/** 隐蔽工程验收*/
	public static final String HIDDEDPROJECTMOB= "A0E061";
	/** 综合验收*/
	public static final String ALCHECKMOB = "A0E062";
	/** 出库申请单*/
	public static final String OTAPPLY = "A0E067";
	
	/************** PQM  ******/
	/** 质量安全巡查*/
	public static final String QUALITYPATROLMOB = "A0E080";
	/** 旁站记录*/
	public static final String SIDEQUALITYMOB = "A0E081";
	/** 平行检查*/
	public static final String PARAQUALITYMOB = "A0E082";
	/** 施工记录*/
	public static final String CONSTQUALITYMOB = "A0E083";
	/** 工序报验*/            
	public static final String PSCHECKMOB = "A0E085";
	/** 质量安全问题*/
	public static final String PROBLEMMOB = "A0E087";
	/** 焊机数据上传*/
	public static final String WELDINGDATAMOB = "A0E088";
	/** 电熔焊接拍照*/
	public static final String ELEWELDINGPROCESS = "A0E090";
	/** 防腐补口拍照*/
	public static final String PATCHPROCESS = "A0E091";
	/** 热熔焊接拍照*/
	public static final String HOTWELDINGPROCESS = "A0E092";
	/** 其它工序拍照*/
	public static final String OTHERPROCESS = "A0E093";
	/** 项目日志*/
	public static final String PROJECTLOGMOB = "A0E095";
	/** 焊接记录上传*/
	public static final String WELDUPLOADMOB = "A0E097";
	
	/**************  企业报表 ******/
	/** 项目活跃度排行榜*/
	public static final String PROJECTSCOREMOB = "A0E0A0";
	/** 人员活跃度排行榜*/
	public static final String PERSONSCOREMOB = "A0E0A1";
	/** 单位活跃度查询*/
	public static final String SUPPLIERSCOREMOB = "A0E0A2";
	/** 未关闭问题统计*/
	public static final String UNSOLVEPROBLEMMOB = "A0E0A3";
	/** 问题现象多发分析*/
	public static final String PROREASONANALYZEMOB = "A0E0A4";
	/** 新增问题月度曲线*/
	public static final String PROBLEMMONTHMOB = "A0E0A5";
	/** 重点项目进度查阅*/
	public static final String IMPORTPROJECTMOB = "A0E0A6";
	/** 工商业项目到期/超期预警*/
	public static final String PROJECTWARNINGMOB = "A0E0A7";
	/** 项目物料查询*/
	public static final String MATERIALMOB = "A0E0A8";
	/** 物料库存查询*/
	public static final String MATERIALSTOCKMOB = "A0E0A9";

	/**************  集团报表 ******/
	/** 企业工程服务商活跃度*/
	public static final String PROVIDERACTIVITYMOB = "A0E0B0";
	/** 企业工程管理人员活跃度*/
	public static final String MANAGERIALACTIVITYMOB = "A0E0B1";
	/** 企业施工单位巡查平均得分*/
	public static final String PATROLSCOREMOB = "A0E0B2";
	/** 问题现象多发分析*/
	public static final String PROBLEMATICMOB = "A0E0B3";
	/** 企业项目平均结算周期*/
	public static final String BILLINGCYCLEMOB = "A0E0B4";
	/** 企业工商用户项目平均施工周期*/
	public static final String CONSTRUCTIONPERIODMOB = "A0E0B5";
	/** 企业当期项目预算总额*/
	public static final String TOTALBUDGETMOB = "A0E0B6";
	/**企业当期项目决算总额*/
	public static final String FINALACCOUNTSMOB = "A0E0B7";
	/** 企业投资项目超预算比例*/
	public static final String OVERBUDGETMOB = "A0E0B8";
	/** 企业项目户均成本查询*/
	public static final String HOUSEHOLDCOSTMOB = "A0E0B9";
	
	/*add by lhp begin at 21081225 新加项：热熔数据; 都是功能注册里面的节点编码*/
	public static final String HOTMELTDATA = "4850013011";
	public static final String HOTMELTDATAMOB = "A0E089";
	/*add by lhp end at 21081225*/
	
	//add by wugy on 2019-04-18 case:新加移动端功能：工程测量 下列编码为功能节点编码 start
	public static final String ENGINEERINGSURVEY = "485002201";
	public static final String ENGINEERINGSURVEYMOB = "A0E098";
	//add by wugy on 2019-04-18 case:新加移动端功能：工程测量 下列编码为功能节点编码 end
}
