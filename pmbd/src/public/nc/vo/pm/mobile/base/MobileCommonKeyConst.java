package nc.vo.pm.mobile.base;



/**
 * 项目移动常用常量类
 * 
 * @version pmapp1.0
 * @author guofya
 * @time 2015-6-15 下午4:14:57
 */
public class MobileCommonKeyConst {
	/************************* 基本常量 *************************/
	public final static String PK = "pk";
	public final static String NULLLOWER = "null";
	public final static String NULLCAPITAL = "NULL";
	
	/************************* 移动端接口返回对象相关key *************************/
	// 登录用户名称
	public final static String USERNAME = "username";
	// 用户
	public final static String USERID = "userid";
	// 显示用户名称
	public final static String USER_NAME = "user_name";
	// 用户身份
	public final static String USER_IDENTITY = "user_identity";
	// 施工方单位
	public final static String PK_SUPPLIER = "pk_supplier";	
	// 集团
	public final static String GROUPID = "groupid";
	// 组织集合
	public static final String PK_ORGLIST = "pk_orglist";
	// 权限组织
	public final static String PERMORGS = "permorgs";
	// 权限组织数据集合
	public final static String PERMORGLISTMAP = "permorglistmap";
	// 功能权限
	public final static String AUTHINFO = "authInfo";
	// 统计报表功能权限
	public final static String REPORTAUTHINFO = "reportauthinfo";
	// 统计集团报表功能权限
	public final static String GROUPREPORTAUTHINFO = "groupreportauthinfo";
	
	// 有权限功能节点数量
	public final static String AUTHINFONUM = "authinfonum";
	// 有权限报表功能节点数量
	public final static String REPORTAUTHINFONUM = "reportauthinfonum";
	// 有权限集团报表功能节点数量
	public final static String GROUPREPORTAUTHINFONUM = "groupreportauthinfonum";
	// 关注项目
	public final static String FOCUSPROJECTLIST = "focusprojectlist";
	// 查询条件  起止日期
	public final static String STARTDATE = "startdate";
	public final static String ENDDATE = "enddate";
	
	// 业务参数
	// 是否记录轨迹
	public final static String RECORDTRACKFLAG = "recordtrackflag";
	// 轨迹点记录时间间隔（分钟）
	public final static String RECORDINTERVAL = "recordinterval";
	
	/************************* 异常处理 *************************/
	/** 
	* @Fields ERROR_CODE : 错误编码
	*/ 
	public final static String ERROR_CODE = "errorcode";
	/** 
	* @Fields ERROR_MSG : 错误信息
	*/ 
	public final static String ERROR_MSG = "errormsg";
	/** 
	* @Fields ERROR_LEVEL : 错误等级
	*/ 
	public final static String ERROR_LEVEL = "error_level";
	/** 
	* @Fields ERROR_LEVEL : 错误等级低
	*/ 
	public final static int ERROR_LEVEL_LOW = 0;
	/** 
	* @Fields ERROR_LEVEL : 错误等级一般
	*/ 
	public final static int ERROR_LEVEL_GENERAL = 10;
	/** 
	* @Fields ERROR_LEVEL : 错误等级严重
	*/ 
	public final static int ERROR_LEVEL_HIGH = 99;
	/** 
	* @Fields RESULT : 结果集
	*/ 
	public final static String RESULT = "result";
	/** 
	* @Fields PARAM : 交互统一参数
	*/
	public final static String PARAM = "param";
	/** 
	* @Fields RESULT_CODE : 0为成功，1为失败
	*/ 
	public final static String RESULT_CODE = "result_code";
	/** 
	* @Fields PM_ERROR_CODE : 项目领域错误编码常量
	*/ 
	public final static String PM_ERROR_CODE = "ZP48";
	/** 
	* @Fields SUCCESS : 操作成功
	*/ 
	public final static int SUCCESS = 0;
	/** 
	* @Fields FAILURE : 操作失败
	*/ 
	public final static int FAILURE = 1;
	
	/************************* 文件存储、展示名称相关 *************************/
	/** 
	 * @Fields FILEDATA : mobVO关联文件key
	 */
	public final static String FILEDATA = "filedata";
	/** 
	 * @Fields FILENAME : mobVO关联文件key
	 */
	public final static String FILENAME = "filename";
	/** 
	 * @Fields FILELENGTH : mobVO关联文件key
	 */
	public final static String FILELENGTH = "filelength";
	/** 
	 * @Fields FILEMAP : mobVO关联文件key
	 */
	public final static String FILEMAP = "filemap";
	/** 
	 * @Fields FILELISTMAP : mobVO关联文件key
	 */
	public final static String FILELISTMAP = "filelistmap";
	
	/************************* 图片管理存储、展示名称相关 *************************/
	/** 
	* @Fields IMAGES : 图片
	*/ 
	public final static String IMAGES = "images";
	/** 
	* @Fields NONE : 控件隐藏
	*/ 
	public final static String NONE = "none";
	/** 
	* @Fields DISPLAY : 控件显示
	*/ 
	public final static String BLOCK = "block";
	/** 
	* @Fields VISIBLE : 无图片
	*/ 
	public final static String VISIBLE = "visible";
	/** 
	* @Fields HIDDEN : 有图片
	*/ 
	public final static String HIDDEN = "hidden";
	/** 
	* @Fields HASPICTURE : 列表回复是否含图片，用于移动端布局
	*/
	public static final String HASPICTURE= "hasPicture";
	/** 
	 * @Fields PICTUREMAP : mobVO关联图片key
	 */ 
	public final static String PICTUREMAP = "picturemap";
	/** 
	 * @Fields PICTURELISTMAP : mobVO关联图片key
	 */ 
	public final static String PICTURELISTMAP = "picturelistmap";
	/** 
	 * @Fields PICTURE_NUM : 图片张数
	 */ 
	public final static String PICTURE_NUM = "picture_num";
	/** 
	 * @Fields SRC : 图片相关控件属性
	 */ 
	public final static String SRC = "src";
	/** 
	 * @Fields PIC_PSN : 人员身份图片key
	 */ 
	public final static String PIC_PSN = "pic_psn";
	/** 
	 * @Fields NOUSER : 无账户
	 */ 
	public final static String NOUSER = "(无账户)";
	/** 
	 * @Fields DELETEDPICS : 删除图片pk_doc集合
	 */ 
	public final static String DELETEDPICS = "deletedPics";
	
	// 对错（问题状态 & 是否通过）
	/** 
	 * @Fields PIC_RIGHT : 对
	 */ 
	public final static String PIC_RIGHT = "pm_right.png";
	/** 
	 * @Fields PIC_WRONG : 错
	 */ 
	public final static String PIC_WRONG = "pm_wrong.png";
	// 团队成员角色
	/** 
	 * @Fields PIC_PSNDOC : 甲方人员 
	 */ 
	public final static String PIC_PSNDOC = "pm_psndoc.png";
	/** 
	 * @Fields PIC_SUPPLIERPSN : 供应商人员
	 */ 
	public final static String PIC_SUPPLIERPSN = "pm_supplierpsn.png";
	/** 
	 * @Fields PIC_CUSTOMERPSN : 客户人员
	 */ 
	public final static String PIC_CUSTOMERPSN = "pm_customerpsn.png";
	// 质量巡查
	/** 
	 * @Fields PIC_NOT_SELECT : 未选中
	 */ 
	public final static String PIC_NOT_SELECT = "pm_not_select.png";
	// 需拍照标记(灰色)
	public final static String PIC_NEEDPIC = "tupian_1.png";
	// 需拍照标记(点亮)
	public final static String PIC_NEEDPIC_TRUE = "tupian_2.png";
	// 记录参数标记(灰色)
	public final static String PIC_CHECKVALUE = "canshu_1.png";
	// 记录参数标记(点亮)
	public final static String PIC_CHECKVALUE_TRUE = "canshu_2.png";
	// 不适用
	public final static String PIC_NOT_APPLICABLE = "unapplicate_unsel.png";
	public final static String PIC_NOT_APPLICABLE_TRUE = "unapplicate_sel.png";
	// 合格
	public final static String PIC_QUALIFIED = "qual_unsel.png";
	public final static String PIC_QUALIFIED_TRUE = "qual_sel.png";
	// 不合格
	public final static String PIC_UNQUALIFIED = "unqual_unsel.png";
	public final static String PIC_UNQUALIFIED_TRUE = "unqual_sel.png";
	// 检查记录
	public final static String PIC_CHECK_LOG = "notcheckrec.png";
	public final static String PIC_CHECK_LOG_TRUE = "hascheckrec.png";
	// 不合规焊口号
	public final static String PIC_WELD_CHECK = "hasunqualifiedweld.png";
	// 重点项目标记
	public final static String PIC_IMPORT_PROJECT = "importantproject.png";
	
	
	
	/************************* 移动审批 *************************/
	public final static String TASKID = "taskid";
	public final static String STATUSKEY = "statuskey";
	public final static String STATUSCODE = "statuscode";
	public final static String ACTIONCODE = "actioncode";
	// 是否指派
	public final static String ISASSIGN = "isassign";
	public final static String ACTIONVOLIST = "actionvolist";
	// 审批历史
	public final static String APPROVEHISTORYLINELIST = "approvehistorylinelist";
	// 启用查询审批流标识 
	public final static String ACGFLAG = "acgflag";
	// 查询审批流指派人列表
	public final static String PSNSTRUCTLIST = "psnstructlist";
	// 审批流匹配成功标识
	public final static String FLOW_TRUE = "true";
	// 审批流匹配失败标识
	public final static String FLOW_FALSE = "false";
	// 代办任务列表
	public final static String TASKSTRUCTLIST = "taskstructlist";
	
	
	
	/************************* SQL中单个人员名称、单位、部门查询用 *************************/
	/** 
	 * @Fields PSNDOCNAME : 甲方人员名称
	 */ 
	public final static String PSNDOCNAME = "psndocname";
	/** 
	 * @Fields PSNUNITNAME : 甲方人员单位
	 */ 
	public final static String PSNUNITNAME = "psnunitName";
	/** 
	 * @Fields DEPTNAME : 部门
	 */ 
	public final static String DEPTNAME = "deptName";
	/** 
	 * @Fields SUPSNNAME : 乙方人员名称
	 */ 
	public final static String SUPSNNAME = "supsnname";
	/** 
	 * @Fields SUPUNITNAME : 乙方人员单位
	 */ 
	public final static String SUPUNITNAME = "supunitname";
	/** 
	 * @Fields PSNNAME : 人员名称
	 */ 
	public final static String PSNNAME = "psnname";
	/** 
	 * @Fields UNITNAME : 人员单位
	 */ 
	public final static String UNITNAME = "unitName";
	
	/************************* 元数据ID *************************/
	/**
	 * 供应商 ID nc.vo.bd.supplier.SupplierVO
	 */
	public static final String SUPPLIER="720dcc7c-ff19-48f4-b9c5-b90906682f45";
	/**
	 * 客户 Customer nc.vo.bd.cust.CustomerVO
	 */
	public static final String CUSTOMER="e4f48eaf-5567-4383-a370-a59cb3e8a451";
	/** 
	* @Fields PROCESS : 工序
	*/ 
	public static final String PROCESS="c6f7fe62-d8b1-4f1c-8862-b82eb5ffdaf0";
	/** 
	* @Fields problemreason : 问题原因
	*/ 
	public static final String PROBLEMREASON = "09c78a36-e17d-48b3-8d3c-1190347c507a";
	/** 
	* @Fields checkrequest : 检查标准
	*/ 
	public static final String CHECKREQUEST = "581ec515-b1fe-43cf-940a-faac65bd3e81";
	/**
	 * @Fields visacategory :签证类别
	 */
	public static final String VISACATEGORY = "62a5c28e-dbe5-455c-be91-66b2e9642392";
	/**
	 * @Fields contacttype :联系类型
	 */
	public static final String CONTACTTYPE = "bf54c52f-228e-441c-81f1-53b7aa473fe1";
	/**
	 * @Fields altercategory :变更类别
	 */
	public static final String ALTERCATEGORY = "0361ae7c-c8da-4493-8bcb-dcdb3f6042af";
	//add by wugy on 2019-04-18 case:新增元数据 start
	/**
	 * @Fields pipelinepointclass :管线点分类
	 */
	public static final String PIPELINEPOINTCLASS = "58ae182e-3c9d-47d8-ba99-727c06bc1005";
	/**
	 * @Fields pressuregrade :压力等级
	 */
	public static final String PRESSUREGRADE = "5d9a7c0d-cda1-404c-bd6c-e9cb31189c6f";
	//add by wugy on 2019-04-18 case:新增元数据 end
	
	
	/************************* 单据公共常量 *************************/
	/** 集团 */
	public static final String PK_GROUP= "pk_group";
	/** 组织 */
	public static final String PK_ORG= "pk_org";
	/** 组织版本 */
	public static final String PK_ORG_V= "pk_org_v";
	/** 单据号 */
	public static final String BILL_CODE= "bill_code";
	/** 单据状态 */
	public static final String BILL_STATUS= "bill_status";
	/** 单据类型 */
	public static final String BILL_TYPE= "bill_type";
	/** 交易类型编码 */
	public static final String TRANSI_TYPE= "transi_type";
	/** 交易类型 */
	public static final String PK_TRANSITYPE= "pk_transitype";
	/** 交易类型名称 */
	public static final String BILLTYPENAME= "billtypename";
	/** 单据类型集合 */
	public static final String BILLTYPE_MAP= "billtype_map";
	/** 业务类型 */
	public static final String BUSI_TYPE= "busi_type";
	/** 创建人 */
	public static final String CREATOR= "creator";
	/** 创建时间 */
	public static final String CREATIONTIME= "creationtime";
	/** 制单人 */
	public static final String BILLMAKER= "billmaker";
	/** 制单时间 */
	public static final String BILLMAKETIME= "billmaketime";
	/** 项目 */
	public static final String PK_PROJECT= "pk_project";
	/** 责任组织 */
	public static final String PK_DUTY_ORG= "pk_duty_org";
	/** 责任组织版本 */
	public static final String PK_DUTY_ORG_V= "pk_duty_org_v";
	/** 位置 */
	public static final String POSITION= "position";
	/** 地理信息 */
	public static final String LOCATION= "location";
	/** 经度 */
	public static final String LONGITUDE = "longitude";
	/** 纬度 */
	public static final String LATITUDE = "latitude";
	/** 备注 */
	public static final String MEMO= "memo";
	/** dr */
	public static final String DR= "dr";
	
	/************************* 功能点名称 *************************/
	/****** 工作联系*********/
	public static final String WORKCONTACTMOB = "workcontact";
	/****** 项目问题*********/
	public static final String PROJECTISSUEMOB = "projectissue";
	/****** 项目检查*********/
	public static final String QUALITYINSPECTIONMOB = "qualityinspection";
	/****** 旁站检查*********/
	public static final String SIDEINSPECTIONMOB = "sideinspection";
	/****** 平行检查*********/
	public static final String PARAINSPECTIONMOB = "parainspection";
	/****** 施工记录单*********/
	public static final String CONSTRUCTIONMOB = "construction";
	/******焊口拍照*********/
	public static final String WELDPICMOB = "weldpic";
	/******防腐拍照*********/
	public static final String CORROSIONPICMOB = "corrosionpic";
	/******工序拍照*********/
	public static final String PROCESSPICMOB = "processpic";
	/****** 指标进度*********/
	public static final String INDEXPROGRESSMOB = "indexprogress";
	/****** 关键进度*********/
	public static final String KEYPROGRESSMOB = "keyprogress";
	/****** 项目动态*********/
	public static final String PROJECTSTATUSMOB = "projectstatus";
	/** 隐蔽工程 */
	public static final String HIDDEDPROJECTMOB= "hiddedproject";
	/** 项目日志*/
	public static final String PROJECTLOG = "projectlog";
	/** 焊机数据*/
	public static final String WELDINGDATA = "weldingdata";
	
	/** 热熔数据 add by lhp 20181225*/
	public static final String HOTMELTDATA = "hotmeltdata";
	
	/** 工程测量 add by wugy on 2019-04-18 */
	public static final String ENGINEERINGSURVEYMOB = "engineeringsurveymob";
	
	/** 变更记录*/
	public static final String CHANGELOG = "changelog";
	/** 签证记录*/
	public static final String VISALOG = "visalog";
	/** 出库申请单*/
	public static final String OUTAPPLY = "outapply";
	/** 综合验收*/
	public static final String  ALLCHECK = "allcheck";
	/** 项目预验收*/
	public static final String PRECHECK = "precheck";
	/** 工序报验*/
	public static final String PROCESSCHECK = "processcheck";
	/** 上下班签到*/
	public static final String SIGNINOUT = "signinout";
	/** 焊接记录上传*/
	public static final String WDUPLOAD = "wdupload";

	/** 项目活跃度排行榜*/
	public static final String PROJECTSCORE = "projectscore";
	/** 人员活跃度排行榜*/
	public static final String PERSONSCORE = "personscore";
	/** 单位活跃度查询*/
	public static final String SUPPLIERSCORE = "supplierscore";
	/** 未关闭问题统计*/
	public static final String UNSOLVEPROBLEM = "unsolveproblem";
	/** 问题现象多发分析*/
	public static final String PROREASONANALYZE = "proreasonanalyze";
	/** 新增问题月度曲线*/
	public static final String PROBLEMMONTH = "problemmonth";
	/** 重点项目进度查阅*/
	public static final String IMPORTPROJECT = "importproject";
	/** 工商业项目到期/超期预警*/
	public static final String PROJECTWARNING = "projectwarning";
	/** 项目物料查询*/
	public static final String MATERIAL = "material";
	/** 物料库存查询*/
	public static final String MATERIALSTOCK = "materialstock";
	
	/** 企业工程服务商活跃度*/
	public static final String PROVIDERACTIVITY = "provideractivity";
	/** 企业工程管理人员活跃度*/
	public static final String MANAGERIALACTIVITY = "managerialactivity";
	/** 企业施工单位巡查平均得分*/
	public static final String PATROLSCORE = "patrolscore";
	/** 问题现象多发分析*/
	public static final String PROBLEMATIC = "problematic";
	/** 企业项目平均结算周期*/
	public static final String BILLINGCYCLE = "billingcycle";
	/** 企业工商用户项目平均施工周期*/
	public static final String CONSTRUCTIONPERIOD = "constructionperiod";
	/** 企业当期项目预算总额*/
	public static final String TOTALBUDGET = "totalbudget";
	/**企业当期项目决算总额*/
	public static final String FINALACCOUNTS = "finalaccounts";
	/** 企业投资项目超预算比例*/
	public static final String OVERBUDGET = "overbudget";
	/** 企业项目户均成本查询*/
	public static final String HOUSEHOLDCOST = "householdcost";
	/************************* mob公共关键字 *************************/
	// 列表分组
	public final static String GROUPLIST = "grouplist";
	// 分页显示  pk集合
	public final static String PAGEPKLIST = "pagepklist";
	// 搜索关键字
	public final static String SEARCHKEY = "searchkey";
	
	/**
	 * 当回复内容只有图片时显示常量
	 */
	public final static String  NOCONTENT= "[图片]";
	
	/** 
	* @Fields NOT_APPLICABLE : 不适用
	*/ 
	public final static String NOT_APPLICABLE = "不适用";
	
	/** 
	* @Fields POSTIONMAP : 地理位置信息
	*/ 
	public final static String POSITIONMAP = "positionmap";
	
	/** 
	* @Fields POSTIONMAP : 地理位置信息列表
	*/ 
	public final static String POSITIONMAPLIST = "positionmaplist";
	
	/** 
	* @Fields POSTIONMAP : 地理位置图片
	*/ 
	public final static String ICON = "icon";
	
	/** 
	* @Fields ROWNO : 行号
	*/ 
	public final static String ROWNO = "rowno";

	/** 
	* @Fields MODIFIED : 修改状态
	*/ 
	public final static String MODIFIED = "modified";
	
	/**
	 *@Fields POSITIONNO :地理位置行号（有数据地理位置为空）
	 */
	public final static String POSITIONNO = "positionno";
	/*
	 * @Fields FangFu :工序分类之一 电熔焊接
	 */
	public final static String HanJie = "1";
	/*
	 * @Fields FangFu :工序分类之一 防腐补口
	 */
	public final static String FangFu = "2";
	/*
	 * @Fields FangFu :工序分类之一 其他工序
	 */
	public final static String OTHERPOCESS = "3";
	
	
	
	/************************* 焊机接口 *************************/
	/** 
	* @Fields FUSIONFILESUFFIX : 辉信加密文件后缀
	*/ 
	public final static String FUSIONFILESUFFIX = ".RDF";
	/** 
	* @Fields DRAGONFILESUFFIX : 塑龙加密文件后缀
	*/ 
	public final static String DRAGONFILESUFFIX = ".CSV";
	
	
}
