package nc.vo.pm.mobile.base;



/**
 * ��Ŀ�ƶ����ó�����
 * 
 * @version pmapp1.0
 * @author guofya
 * @time 2015-6-15 ����4:14:57
 */
public class MobileCommonKeyConst {
	/************************* �������� *************************/
	public final static String PK = "pk";
	public final static String NULLLOWER = "null";
	public final static String NULLCAPITAL = "NULL";
	
	/************************* �ƶ��˽ӿڷ��ض������key *************************/
	// ��¼�û�����
	public final static String USERNAME = "username";
	// �û�
	public final static String USERID = "userid";
	// ��ʾ�û�����
	public final static String USER_NAME = "user_name";
	// �û����
	public final static String USER_IDENTITY = "user_identity";
	// ʩ������λ
	public final static String PK_SUPPLIER = "pk_supplier";	
	// ����
	public final static String GROUPID = "groupid";
	// ��֯����
	public static final String PK_ORGLIST = "pk_orglist";
	// Ȩ����֯
	public final static String PERMORGS = "permorgs";
	// Ȩ����֯���ݼ���
	public final static String PERMORGLISTMAP = "permorglistmap";
	// ����Ȩ��
	public final static String AUTHINFO = "authInfo";
	// ͳ�Ʊ�����Ȩ��
	public final static String REPORTAUTHINFO = "reportauthinfo";
	// ͳ�Ƽ��ű�����Ȩ��
	public final static String GROUPREPORTAUTHINFO = "groupreportauthinfo";
	
	// ��Ȩ�޹��ܽڵ�����
	public final static String AUTHINFONUM = "authinfonum";
	// ��Ȩ�ޱ����ܽڵ�����
	public final static String REPORTAUTHINFONUM = "reportauthinfonum";
	// ��Ȩ�޼��ű����ܽڵ�����
	public final static String GROUPREPORTAUTHINFONUM = "groupreportauthinfonum";
	// ��ע��Ŀ
	public final static String FOCUSPROJECTLIST = "focusprojectlist";
	// ��ѯ����  ��ֹ����
	public final static String STARTDATE = "startdate";
	public final static String ENDDATE = "enddate";
	
	// ҵ�����
	// �Ƿ��¼�켣
	public final static String RECORDTRACKFLAG = "recordtrackflag";
	// �켣���¼ʱ���������ӣ�
	public final static String RECORDINTERVAL = "recordinterval";
	
	/************************* �쳣���� *************************/
	/** 
	* @Fields ERROR_CODE : �������
	*/ 
	public final static String ERROR_CODE = "errorcode";
	/** 
	* @Fields ERROR_MSG : ������Ϣ
	*/ 
	public final static String ERROR_MSG = "errormsg";
	/** 
	* @Fields ERROR_LEVEL : ����ȼ�
	*/ 
	public final static String ERROR_LEVEL = "error_level";
	/** 
	* @Fields ERROR_LEVEL : ����ȼ���
	*/ 
	public final static int ERROR_LEVEL_LOW = 0;
	/** 
	* @Fields ERROR_LEVEL : ����ȼ�һ��
	*/ 
	public final static int ERROR_LEVEL_GENERAL = 10;
	/** 
	* @Fields ERROR_LEVEL : ����ȼ�����
	*/ 
	public final static int ERROR_LEVEL_HIGH = 99;
	/** 
	* @Fields RESULT : �����
	*/ 
	public final static String RESULT = "result";
	/** 
	* @Fields PARAM : ����ͳһ����
	*/
	public final static String PARAM = "param";
	/** 
	* @Fields RESULT_CODE : 0Ϊ�ɹ���1Ϊʧ��
	*/ 
	public final static String RESULT_CODE = "result_code";
	/** 
	* @Fields PM_ERROR_CODE : ��Ŀ���������볣��
	*/ 
	public final static String PM_ERROR_CODE = "ZP48";
	/** 
	* @Fields SUCCESS : �����ɹ�
	*/ 
	public final static int SUCCESS = 0;
	/** 
	* @Fields FAILURE : ����ʧ��
	*/ 
	public final static int FAILURE = 1;
	
	/************************* �ļ��洢��չʾ������� *************************/
	/** 
	 * @Fields FILEDATA : mobVO�����ļ�key
	 */
	public final static String FILEDATA = "filedata";
	/** 
	 * @Fields FILENAME : mobVO�����ļ�key
	 */
	public final static String FILENAME = "filename";
	/** 
	 * @Fields FILELENGTH : mobVO�����ļ�key
	 */
	public final static String FILELENGTH = "filelength";
	/** 
	 * @Fields FILEMAP : mobVO�����ļ�key
	 */
	public final static String FILEMAP = "filemap";
	/** 
	 * @Fields FILELISTMAP : mobVO�����ļ�key
	 */
	public final static String FILELISTMAP = "filelistmap";
	
	/************************* ͼƬ����洢��չʾ������� *************************/
	/** 
	* @Fields IMAGES : ͼƬ
	*/ 
	public final static String IMAGES = "images";
	/** 
	* @Fields NONE : �ؼ�����
	*/ 
	public final static String NONE = "none";
	/** 
	* @Fields DISPLAY : �ؼ���ʾ
	*/ 
	public final static String BLOCK = "block";
	/** 
	* @Fields VISIBLE : ��ͼƬ
	*/ 
	public final static String VISIBLE = "visible";
	/** 
	* @Fields HIDDEN : ��ͼƬ
	*/ 
	public final static String HIDDEN = "hidden";
	/** 
	* @Fields HASPICTURE : �б�ظ��Ƿ�ͼƬ�������ƶ��˲���
	*/
	public static final String HASPICTURE= "hasPicture";
	/** 
	 * @Fields PICTUREMAP : mobVO����ͼƬkey
	 */ 
	public final static String PICTUREMAP = "picturemap";
	/** 
	 * @Fields PICTURELISTMAP : mobVO����ͼƬkey
	 */ 
	public final static String PICTURELISTMAP = "picturelistmap";
	/** 
	 * @Fields PICTURE_NUM : ͼƬ����
	 */ 
	public final static String PICTURE_NUM = "picture_num";
	/** 
	 * @Fields SRC : ͼƬ��ؿؼ�����
	 */ 
	public final static String SRC = "src";
	/** 
	 * @Fields PIC_PSN : ��Ա���ͼƬkey
	 */ 
	public final static String PIC_PSN = "pic_psn";
	/** 
	 * @Fields NOUSER : ���˻�
	 */ 
	public final static String NOUSER = "(���˻�)";
	/** 
	 * @Fields DELETEDPICS : ɾ��ͼƬpk_doc����
	 */ 
	public final static String DELETEDPICS = "deletedPics";
	
	// �Դ�����״̬ & �Ƿ�ͨ����
	/** 
	 * @Fields PIC_RIGHT : ��
	 */ 
	public final static String PIC_RIGHT = "pm_right.png";
	/** 
	 * @Fields PIC_WRONG : ��
	 */ 
	public final static String PIC_WRONG = "pm_wrong.png";
	// �Ŷӳ�Ա��ɫ
	/** 
	 * @Fields PIC_PSNDOC : �׷���Ա 
	 */ 
	public final static String PIC_PSNDOC = "pm_psndoc.png";
	/** 
	 * @Fields PIC_SUPPLIERPSN : ��Ӧ����Ա
	 */ 
	public final static String PIC_SUPPLIERPSN = "pm_supplierpsn.png";
	/** 
	 * @Fields PIC_CUSTOMERPSN : �ͻ���Ա
	 */ 
	public final static String PIC_CUSTOMERPSN = "pm_customerpsn.png";
	// ����Ѳ��
	/** 
	 * @Fields PIC_NOT_SELECT : δѡ��
	 */ 
	public final static String PIC_NOT_SELECT = "pm_not_select.png";
	// �����ձ��(��ɫ)
	public final static String PIC_NEEDPIC = "tupian_1.png";
	// �����ձ��(����)
	public final static String PIC_NEEDPIC_TRUE = "tupian_2.png";
	// ��¼�������(��ɫ)
	public final static String PIC_CHECKVALUE = "canshu_1.png";
	// ��¼�������(����)
	public final static String PIC_CHECKVALUE_TRUE = "canshu_2.png";
	// ������
	public final static String PIC_NOT_APPLICABLE = "unapplicate_unsel.png";
	public final static String PIC_NOT_APPLICABLE_TRUE = "unapplicate_sel.png";
	// �ϸ�
	public final static String PIC_QUALIFIED = "qual_unsel.png";
	public final static String PIC_QUALIFIED_TRUE = "qual_sel.png";
	// ���ϸ�
	public final static String PIC_UNQUALIFIED = "unqual_unsel.png";
	public final static String PIC_UNQUALIFIED_TRUE = "unqual_sel.png";
	// ����¼
	public final static String PIC_CHECK_LOG = "notcheckrec.png";
	public final static String PIC_CHECK_LOG_TRUE = "hascheckrec.png";
	// ���Ϲ溸�ں�
	public final static String PIC_WELD_CHECK = "hasunqualifiedweld.png";
	// �ص���Ŀ���
	public final static String PIC_IMPORT_PROJECT = "importantproject.png";
	
	
	
	/************************* �ƶ����� *************************/
	public final static String TASKID = "taskid";
	public final static String STATUSKEY = "statuskey";
	public final static String STATUSCODE = "statuscode";
	public final static String ACTIONCODE = "actioncode";
	// �Ƿ�ָ��
	public final static String ISASSIGN = "isassign";
	public final static String ACTIONVOLIST = "actionvolist";
	// ������ʷ
	public final static String APPROVEHISTORYLINELIST = "approvehistorylinelist";
	// ���ò�ѯ��������ʶ 
	public final static String ACGFLAG = "acgflag";
	// ��ѯ������ָ�����б�
	public final static String PSNSTRUCTLIST = "psnstructlist";
	// ������ƥ��ɹ���ʶ
	public final static String FLOW_TRUE = "true";
	// ������ƥ��ʧ�ܱ�ʶ
	public final static String FLOW_FALSE = "false";
	// ���������б�
	public final static String TASKSTRUCTLIST = "taskstructlist";
	
	
	
	/************************* SQL�е�����Ա���ơ���λ�����Ų�ѯ�� *************************/
	/** 
	 * @Fields PSNDOCNAME : �׷���Ա����
	 */ 
	public final static String PSNDOCNAME = "psndocname";
	/** 
	 * @Fields PSNUNITNAME : �׷���Ա��λ
	 */ 
	public final static String PSNUNITNAME = "psnunitName";
	/** 
	 * @Fields DEPTNAME : ����
	 */ 
	public final static String DEPTNAME = "deptName";
	/** 
	 * @Fields SUPSNNAME : �ҷ���Ա����
	 */ 
	public final static String SUPSNNAME = "supsnname";
	/** 
	 * @Fields SUPUNITNAME : �ҷ���Ա��λ
	 */ 
	public final static String SUPUNITNAME = "supunitname";
	/** 
	 * @Fields PSNNAME : ��Ա����
	 */ 
	public final static String PSNNAME = "psnname";
	/** 
	 * @Fields UNITNAME : ��Ա��λ
	 */ 
	public final static String UNITNAME = "unitName";
	
	/************************* Ԫ����ID *************************/
	/**
	 * ��Ӧ�� ID nc.vo.bd.supplier.SupplierVO
	 */
	public static final String SUPPLIER="720dcc7c-ff19-48f4-b9c5-b90906682f45";
	/**
	 * �ͻ� Customer nc.vo.bd.cust.CustomerVO
	 */
	public static final String CUSTOMER="e4f48eaf-5567-4383-a370-a59cb3e8a451";
	/** 
	* @Fields PROCESS : ����
	*/ 
	public static final String PROCESS="c6f7fe62-d8b1-4f1c-8862-b82eb5ffdaf0";
	/** 
	* @Fields problemreason : ����ԭ��
	*/ 
	public static final String PROBLEMREASON = "09c78a36-e17d-48b3-8d3c-1190347c507a";
	/** 
	* @Fields checkrequest : ����׼
	*/ 
	public static final String CHECKREQUEST = "581ec515-b1fe-43cf-940a-faac65bd3e81";
	/**
	 * @Fields visacategory :ǩ֤���
	 */
	public static final String VISACATEGORY = "62a5c28e-dbe5-455c-be91-66b2e9642392";
	/**
	 * @Fields contacttype :��ϵ����
	 */
	public static final String CONTACTTYPE = "bf54c52f-228e-441c-81f1-53b7aa473fe1";
	/**
	 * @Fields altercategory :������
	 */
	public static final String ALTERCATEGORY = "0361ae7c-c8da-4493-8bcb-dcdb3f6042af";
	//add by wugy on 2019-04-18 case:����Ԫ���� start
	/**
	 * @Fields pipelinepointclass :���ߵ����
	 */
	public static final String PIPELINEPOINTCLASS = "58ae182e-3c9d-47d8-ba99-727c06bc1005";
	/**
	 * @Fields pressuregrade :ѹ���ȼ�
	 */
	public static final String PRESSUREGRADE = "5d9a7c0d-cda1-404c-bd6c-e9cb31189c6f";
	//add by wugy on 2019-04-18 case:����Ԫ���� end
	
	
	/************************* ���ݹ������� *************************/
	/** ���� */
	public static final String PK_GROUP= "pk_group";
	/** ��֯ */
	public static final String PK_ORG= "pk_org";
	/** ��֯�汾 */
	public static final String PK_ORG_V= "pk_org_v";
	/** ���ݺ� */
	public static final String BILL_CODE= "bill_code";
	/** ����״̬ */
	public static final String BILL_STATUS= "bill_status";
	/** �������� */
	public static final String BILL_TYPE= "bill_type";
	/** �������ͱ��� */
	public static final String TRANSI_TYPE= "transi_type";
	/** �������� */
	public static final String PK_TRANSITYPE= "pk_transitype";
	/** ������������ */
	public static final String BILLTYPENAME= "billtypename";
	/** �������ͼ��� */
	public static final String BILLTYPE_MAP= "billtype_map";
	/** ҵ������ */
	public static final String BUSI_TYPE= "busi_type";
	/** ������ */
	public static final String CREATOR= "creator";
	/** ����ʱ�� */
	public static final String CREATIONTIME= "creationtime";
	/** �Ƶ��� */
	public static final String BILLMAKER= "billmaker";
	/** �Ƶ�ʱ�� */
	public static final String BILLMAKETIME= "billmaketime";
	/** ��Ŀ */
	public static final String PK_PROJECT= "pk_project";
	/** ������֯ */
	public static final String PK_DUTY_ORG= "pk_duty_org";
	/** ������֯�汾 */
	public static final String PK_DUTY_ORG_V= "pk_duty_org_v";
	/** λ�� */
	public static final String POSITION= "position";
	/** ������Ϣ */
	public static final String LOCATION= "location";
	/** ���� */
	public static final String LONGITUDE = "longitude";
	/** γ�� */
	public static final String LATITUDE = "latitude";
	/** ��ע */
	public static final String MEMO= "memo";
	/** dr */
	public static final String DR= "dr";
	
	/************************* ���ܵ����� *************************/
	/****** ������ϵ*********/
	public static final String WORKCONTACTMOB = "workcontact";
	/****** ��Ŀ����*********/
	public static final String PROJECTISSUEMOB = "projectissue";
	/****** ��Ŀ���*********/
	public static final String QUALITYINSPECTIONMOB = "qualityinspection";
	/****** ��վ���*********/
	public static final String SIDEINSPECTIONMOB = "sideinspection";
	/****** ƽ�м��*********/
	public static final String PARAINSPECTIONMOB = "parainspection";
	/****** ʩ����¼��*********/
	public static final String CONSTRUCTIONMOB = "construction";
	/******��������*********/
	public static final String WELDPICMOB = "weldpic";
	/******��������*********/
	public static final String CORROSIONPICMOB = "corrosionpic";
	/******��������*********/
	public static final String PROCESSPICMOB = "processpic";
	/****** ָ�����*********/
	public static final String INDEXPROGRESSMOB = "indexprogress";
	/****** �ؼ�����*********/
	public static final String KEYPROGRESSMOB = "keyprogress";
	/****** ��Ŀ��̬*********/
	public static final String PROJECTSTATUSMOB = "projectstatus";
	/** ���ι��� */
	public static final String HIDDEDPROJECTMOB= "hiddedproject";
	/** ��Ŀ��־*/
	public static final String PROJECTLOG = "projectlog";
	/** ��������*/
	public static final String WELDINGDATA = "weldingdata";
	
	/** �������� add by lhp 20181225*/
	public static final String HOTMELTDATA = "hotmeltdata";
	
	/** ���̲��� add by wugy on 2019-04-18 */
	public static final String ENGINEERINGSURVEYMOB = "engineeringsurveymob";
	
	/** �����¼*/
	public static final String CHANGELOG = "changelog";
	/** ǩ֤��¼*/
	public static final String VISALOG = "visalog";
	/** �������뵥*/
	public static final String OUTAPPLY = "outapply";
	/** �ۺ�����*/
	public static final String  ALLCHECK = "allcheck";
	/** ��ĿԤ����*/
	public static final String PRECHECK = "precheck";
	/** ������*/
	public static final String PROCESSCHECK = "processcheck";
	/** ���°�ǩ��*/
	public static final String SIGNINOUT = "signinout";
	/** ���Ӽ�¼�ϴ�*/
	public static final String WDUPLOAD = "wdupload";

	/** ��Ŀ��Ծ�����а�*/
	public static final String PROJECTSCORE = "projectscore";
	/** ��Ա��Ծ�����а�*/
	public static final String PERSONSCORE = "personscore";
	/** ��λ��Ծ�Ȳ�ѯ*/
	public static final String SUPPLIERSCORE = "supplierscore";
	/** δ�ر�����ͳ��*/
	public static final String UNSOLVEPROBLEM = "unsolveproblem";
	/** ��������෢����*/
	public static final String PROREASONANALYZE = "proreasonanalyze";
	/** ���������¶�����*/
	public static final String PROBLEMMONTH = "problemmonth";
	/** �ص���Ŀ���Ȳ���*/
	public static final String IMPORTPROJECT = "importproject";
	/** ����ҵ��Ŀ����/����Ԥ��*/
	public static final String PROJECTWARNING = "projectwarning";
	/** ��Ŀ���ϲ�ѯ*/
	public static final String MATERIAL = "material";
	/** ���Ͽ���ѯ*/
	public static final String MATERIALSTOCK = "materialstock";
	
	/** ��ҵ���̷����̻�Ծ��*/
	public static final String PROVIDERACTIVITY = "provideractivity";
	/** ��ҵ���̹�����Ա��Ծ��*/
	public static final String MANAGERIALACTIVITY = "managerialactivity";
	/** ��ҵʩ����λѲ��ƽ���÷�*/
	public static final String PATROLSCORE = "patrolscore";
	/** ��������෢����*/
	public static final String PROBLEMATIC = "problematic";
	/** ��ҵ��Ŀƽ����������*/
	public static final String BILLINGCYCLE = "billingcycle";
	/** ��ҵ�����û���Ŀƽ��ʩ������*/
	public static final String CONSTRUCTIONPERIOD = "constructionperiod";
	/** ��ҵ������ĿԤ���ܶ�*/
	public static final String TOTALBUDGET = "totalbudget";
	/**��ҵ������Ŀ�����ܶ�*/
	public static final String FINALACCOUNTS = "finalaccounts";
	/** ��ҵͶ����Ŀ��Ԥ�����*/
	public static final String OVERBUDGET = "overbudget";
	/** ��ҵ��Ŀ�����ɱ���ѯ*/
	public static final String HOUSEHOLDCOST = "householdcost";
	/************************* mob�����ؼ��� *************************/
	// �б����
	public final static String GROUPLIST = "grouplist";
	// ��ҳ��ʾ  pk����
	public final static String PAGEPKLIST = "pagepklist";
	// �����ؼ���
	public final static String SEARCHKEY = "searchkey";
	
	/**
	 * ���ظ�����ֻ��ͼƬʱ��ʾ����
	 */
	public final static String  NOCONTENT= "[ͼƬ]";
	
	/** 
	* @Fields NOT_APPLICABLE : ������
	*/ 
	public final static String NOT_APPLICABLE = "������";
	
	/** 
	* @Fields POSTIONMAP : ����λ����Ϣ
	*/ 
	public final static String POSITIONMAP = "positionmap";
	
	/** 
	* @Fields POSTIONMAP : ����λ����Ϣ�б�
	*/ 
	public final static String POSITIONMAPLIST = "positionmaplist";
	
	/** 
	* @Fields POSTIONMAP : ����λ��ͼƬ
	*/ 
	public final static String ICON = "icon";
	
	/** 
	* @Fields ROWNO : �к�
	*/ 
	public final static String ROWNO = "rowno";

	/** 
	* @Fields MODIFIED : �޸�״̬
	*/ 
	public final static String MODIFIED = "modified";
	
	/**
	 *@Fields POSITIONNO :����λ���кţ������ݵ���λ��Ϊ�գ�
	 */
	public final static String POSITIONNO = "positionno";
	/*
	 * @Fields FangFu :�������֮һ ���ۺ���
	 */
	public final static String HanJie = "1";
	/*
	 * @Fields FangFu :�������֮һ ��������
	 */
	public final static String FangFu = "2";
	/*
	 * @Fields FangFu :�������֮һ ��������
	 */
	public final static String OTHERPOCESS = "3";
	
	
	
	/************************* �����ӿ� *************************/
	/** 
	* @Fields FUSIONFILESUFFIX : ���ż����ļ���׺
	*/ 
	public final static String FUSIONFILESUFFIX = ".RDF";
	/** 
	* @Fields DRAGONFILESUFFIX : ���������ļ���׺
	*/ 
	public final static String DRAGONFILESUFFIX = ".CSV";
	
	
}
