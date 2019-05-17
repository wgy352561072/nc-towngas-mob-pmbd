package nc.vo.pmbd.bd.pipelinepointclass;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2019-5-17
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class PipelinepointclassVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_pipelinepointclass;
/**
*集团
*/
public java.lang.String pk_group;
/**
*组织
*/
public java.lang.String pk_org;
/**
*分类编码
*/
public java.lang.String code;
/**
*分类名称
*/
public java.lang.String name;
/**
*父分类
*/
public java.lang.String pk_parent;
/**
*启用状态
*/
public java.lang.Integer enablestate;
/**
*创建人
*/
public java.lang.String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public java.lang.String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*备注
*/
public java.lang.String memo;
/**
*内码
*/
public java.lang.String innercode;
/**
*vdef1
*/
public java.lang.String vdef1;
/**
*vdef2
*/
public java.lang.String vdef2;
/**
*vdef3
*/
public java.lang.String vdef3;
/**
*vdef4
*/
public java.lang.String vdef4;
/**
*vdef5
*/
public java.lang.String vdef5;
/**
*vdef6
*/
public java.lang.String vdef6;
/**
*vdef7
*/
public java.lang.String vdef7;
/**
*vdef8
*/
public java.lang.String vdef8;
/**
*vdef9
*/
public java.lang.String vdef9;
/**
*vdef10
*/
public java.lang.String vdef10;
/**
*时间戳
*/
public UFDateTime ts;
    

/**
*时间戳
*/
public static final String TS="ts";;

public static final String PK_PIPELINEPOINTCLASS = "pk_pipelinepointclass";
public static final String PK_GROUP = "pk_group";
public static final String PK_ORG = "pk_org";
public static final String CODE = "code";
public static final String NAME = "name";
public static final String PK_PARENT = "pk_parent";
public static final String MEMO = "memo";
public static final String INNERCODE = "innercode";
public static final String ENABLESTATE = "enablestate";
public static final String CREATOR = "creator";
public static final String CREATIONTIME = "creationtime";
public static final String MODIFIER = "modifier";
public static final String MODIFIEDTIME = "modifiedtime";
public static final String DEF1 = "def1";
public static final String DEF2 = "def2";
public static final String DEF3 = "def3";
public static final String DEF4 = "def4";
public static final String DEF5 = "def5";
public static final String DEF6 = "def6";
public static final String DEF7 = "def7";
public static final String DEF8 = "def8";
public static final String DEF9 = "def9";
public static final String DEF10 = "def10";
    
/**
* 属性 pk_pipelinepointclass的Getter方法.属性名：主键
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getPk_pipelinepointclass() {
return this.pk_pipelinepointclass;
} 

/**
* 属性pk_pipelinepointclass的Setter方法.属性名：主键
* 创建日期:2019-5-17
* @param newPk_pipelinepointclass java.lang.String
*/
public void setPk_pipelinepointclass ( java.lang.String pk_pipelinepointclass) {
this.pk_pipelinepointclass=pk_pipelinepointclass;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2019-5-17
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2019-5-17
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2019-5-17
* @return nc.vo.org.OrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2019-5-17
* @param newPk_org nc.vo.org.OrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 code的Getter方法.属性名：分类编码
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getCode() {
return this.code;
} 

/**
* 属性code的Setter方法.属性名：分类编码
* 创建日期:2019-5-17
* @param newCode java.lang.String
*/
public void setCode ( java.lang.String code) {
this.code=code;
} 
 
/**
* 属性 name的Getter方法.属性名：分类名称
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getName() {
return this.name;
} 

/**
* 属性name的Setter方法.属性名：分类名称
* 创建日期:2019-5-17
* @param newName java.lang.String
*/
public void setName ( java.lang.String name) {
this.name=name;
} 
 
/**
* 属性 pk_parent的Getter方法.属性名：父分类
*  创建日期:2019-5-17
* @return nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO
*/
public java.lang.String getPk_parent() {
return this.pk_parent;
} 

/**
* 属性pk_parent的Setter方法.属性名：父分类
* 创建日期:2019-5-17
* @param newPk_parent nc.vo.pmbd.bd.pipelinepointclass.PipelinepointclassVO
*/
public void setPk_parent ( java.lang.String pk_parent) {
this.pk_parent=pk_parent;
} 
 
/**
* 属性 enablestate的Getter方法.属性名：启用状态
*  创建日期:2019-5-17
* @return nc.vo.pm.constant.Enablestate
*/
public java.lang.Integer getEnablestate() {
return this.enablestate;
} 

/**
* 属性enablestate的Setter方法.属性名：启用状态
* 创建日期:2019-5-17
* @param newEnablestate nc.vo.pm.constant.Enablestate
*/
public void setEnablestate ( java.lang.Integer enablestate) {
this.enablestate=enablestate;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2019-5-17
* @return nc.vo.sm.UserVO
*/
public java.lang.String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2019-5-17
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( java.lang.String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2019-5-17
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2019-5-17
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2019-5-17
* @return nc.vo.sm.UserVO
*/
public java.lang.String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2019-5-17
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( java.lang.String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2019-5-17
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2019-5-17
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 memo的Getter方法.属性名：备注
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getMemo() {
return this.memo;
} 

/**
* 属性memo的Setter方法.属性名：备注
* 创建日期:2019-5-17
* @param newMemo java.lang.String
*/
public void setMemo ( java.lang.String memo) {
this.memo=memo;
} 
 
/**
* 属性 innercode的Getter方法.属性名：内码
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getInnercode() {
return this.innercode;
} 

/**
* 属性innercode的Setter方法.属性名：内码
* 创建日期:2019-5-17
* @param newInnercode java.lang.String
*/
public void setInnercode ( java.lang.String innercode) {
this.innercode=innercode;
} 
 
/**
* 属性 vdef1的Getter方法.属性名：vdef1
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef1() {
return this.vdef1;
} 

/**
* 属性vdef1的Setter方法.属性名：vdef1
* 创建日期:2019-5-17
* @param newVdef1 java.lang.String
*/
public void setVdef1 ( java.lang.String vdef1) {
this.vdef1=vdef1;
} 
 
/**
* 属性 vdef2的Getter方法.属性名：vdef2
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef2() {
return this.vdef2;
} 

/**
* 属性vdef2的Setter方法.属性名：vdef2
* 创建日期:2019-5-17
* @param newVdef2 java.lang.String
*/
public void setVdef2 ( java.lang.String vdef2) {
this.vdef2=vdef2;
} 
 
/**
* 属性 vdef3的Getter方法.属性名：vdef3
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef3() {
return this.vdef3;
} 

/**
* 属性vdef3的Setter方法.属性名：vdef3
* 创建日期:2019-5-17
* @param newVdef3 java.lang.String
*/
public void setVdef3 ( java.lang.String vdef3) {
this.vdef3=vdef3;
} 
 
/**
* 属性 vdef4的Getter方法.属性名：vdef4
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef4() {
return this.vdef4;
} 

/**
* 属性vdef4的Setter方法.属性名：vdef4
* 创建日期:2019-5-17
* @param newVdef4 java.lang.String
*/
public void setVdef4 ( java.lang.String vdef4) {
this.vdef4=vdef4;
} 
 
/**
* 属性 vdef5的Getter方法.属性名：vdef5
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef5() {
return this.vdef5;
} 

/**
* 属性vdef5的Setter方法.属性名：vdef5
* 创建日期:2019-5-17
* @param newVdef5 java.lang.String
*/
public void setVdef5 ( java.lang.String vdef5) {
this.vdef5=vdef5;
} 
 
/**
* 属性 vdef6的Getter方法.属性名：vdef6
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef6() {
return this.vdef6;
} 

/**
* 属性vdef6的Setter方法.属性名：vdef6
* 创建日期:2019-5-17
* @param newVdef6 java.lang.String
*/
public void setVdef6 ( java.lang.String vdef6) {
this.vdef6=vdef6;
} 
 
/**
* 属性 vdef7的Getter方法.属性名：vdef7
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef7() {
return this.vdef7;
} 

/**
* 属性vdef7的Setter方法.属性名：vdef7
* 创建日期:2019-5-17
* @param newVdef7 java.lang.String
*/
public void setVdef7 ( java.lang.String vdef7) {
this.vdef7=vdef7;
} 
 
/**
* 属性 vdef8的Getter方法.属性名：vdef8
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef8() {
return this.vdef8;
} 

/**
* 属性vdef8的Setter方法.属性名：vdef8
* 创建日期:2019-5-17
* @param newVdef8 java.lang.String
*/
public void setVdef8 ( java.lang.String vdef8) {
this.vdef8=vdef8;
} 
 
/**
* 属性 vdef9的Getter方法.属性名：vdef9
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef9() {
return this.vdef9;
} 

/**
* 属性vdef9的Setter方法.属性名：vdef9
* 创建日期:2019-5-17
* @param newVdef9 java.lang.String
*/
public void setVdef9 ( java.lang.String vdef9) {
this.vdef9=vdef9;
} 
 
/**
* 属性 vdef10的Getter方法.属性名：vdef10
*  创建日期:2019-5-17
* @return java.lang.String
*/
public java.lang.String getVdef10() {
return this.vdef10;
} 

/**
* 属性vdef10的Setter方法.属性名：vdef10
* 创建日期:2019-5-17
* @param newVdef10 java.lang.String
*/
public void setVdef10 ( java.lang.String vdef10) {
this.vdef10=vdef10;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2019-5-17
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2019-5-17
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pmbd.pipelinepointclass");
    }
   }
    