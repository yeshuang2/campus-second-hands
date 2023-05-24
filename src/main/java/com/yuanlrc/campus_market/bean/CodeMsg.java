package com.yuanlrc.campus_market.bean;
/**
 * 错误码统一处理类，所有的错误码统一定义在这里
 * @author Administrator
 *
 */
public class CodeMsg {

	private int code;//错误码
	
	private String msg;//错误信息
	
	/**
	 * 构造函数私有化即单例模式
	 * @param code
	 * @param msg
	 */
	private CodeMsg(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//通用错误码定义
	//处理成功消息码
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	//非法数据错误码
	public static CodeMsg DATA_ERROR = new CodeMsg(-1, "非法数据！");
	public static CodeMsg CPACHA_EMPTY = new CodeMsg(-2, "验证码不能为空！");
	public static CodeMsg VALIDATE_ENTITY_ERROR = new CodeMsg(-3, "");
	public static CodeMsg SESSION_EXPIRED = new CodeMsg(-4, "会话已失效，请刷新页面重试！");
	public static CodeMsg CPACHA_ERROR = new CodeMsg(-5, "验证码错误！");
	public static CodeMsg USER_SESSION_EXPIRED = new CodeMsg(-6, "还未登录或会话失效，请重新登录！");
	public static CodeMsg UPLOAD_PHOTO_SUFFIX_ERROR = new CodeMsg(-7, "图片格式不正确！");
	public static CodeMsg UPLOAD_PHOTO_ERROR = new CodeMsg(-8, "图片上传错误！");
	
	
	//后台管理类错误码
	//用户管理类错误
	public static CodeMsg ADMIN_USERNAME_EMPTY = new CodeMsg(-2000, "用户名不能为空！");
	public static CodeMsg ADMIN_PASSWORD_EMPTY = new CodeMsg(-2001, "密码不能为空！");
	public static CodeMsg ADMIN_NO_RIGHT = new CodeMsg(-2002, "您所属的角色没有该权限！");
	
	//登录类错误码
	public static CodeMsg ADMIN_USERNAME_NO_EXIST = new CodeMsg(-3000, "该用户名不存在！");
	public static CodeMsg ADMIN_PASSWORD_ERROR = new CodeMsg(-3001, "密码错误！");
	public static CodeMsg ADMIN_USER_UNABLE = new CodeMsg(-3002, "该用户已被冻结，请联系管理员！");
	public static CodeMsg ADMIN_USER_ROLE_UNABLE = new CodeMsg(-3003, "该用户所属角色状态不可用，请联系管理员！");
	public static CodeMsg ADMIN_USER_ROLE_AUTHORITES_EMPTY = new CodeMsg(-3004, "该用户所属角色无可用权限，请联系管理员！");
	
	//后台菜单管理类错误码
	public static CodeMsg ADMIN_MENU_ADD_ERROR = new CodeMsg(-4000, "菜单添加失败，请联系管理员！");
	public static CodeMsg ADMIN_MENU_EDIT_ERROR = new CodeMsg(-4001, "菜单编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_MENU_ID_EMPTY = new CodeMsg(-4002, "菜单ID不能为空！");
	public static CodeMsg ADMIN_MENU_ID_ERROR = new CodeMsg(-4003, "菜单ID错误！");
	public static CodeMsg ADMIN_MENU_DELETE_ERROR = new CodeMsg(-4004, "改菜单下有子菜单，不允许删除！");
	//后台角色管理类错误码
	public static CodeMsg ADMIN_ROLE_ADD_ERROR = new CodeMsg(-5000, "角色添加失败，请联系管理员！");
	public static CodeMsg ADMIN_ROLE_NO_EXIST = new CodeMsg(-5001, "该角色不存在！");
	public static CodeMsg ADMIN_ROLE_EDIT_ERROR = new CodeMsg(-5002, "角色编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_ROLE_DELETE_ERROR = new CodeMsg(-5003, "该角色下存在用户信息，不可删除！");
	//后台用户管理类错误码
	public static CodeMsg ADMIN_USER_ROLE_EMPTY = new CodeMsg(-6000, "请选择用户所属角色！");
	public static CodeMsg ADMIN_USERNAME_EXIST = new CodeMsg(-6001, "该用户名已存在，请换一个试试！");
	public static CodeMsg ADMIN_USE_ADD_ERROR = new CodeMsg(-6002, "用户添加失败，请联系管理员！");
	public static CodeMsg ADMIN_USE_NO_EXIST = new CodeMsg(-6003, "用户不存在！");
	public static CodeMsg ADMIN_USE_EDIT_ERROR = new CodeMsg(-6004, "用户编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_USE_DELETE_ERROR = new CodeMsg(-6005, "该用户存在关联数据，不允许删除！");
	
	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_USER_UPDATE_PWD_ERROR = new CodeMsg(-7000, "旧密码错误！");
	public static CodeMsg ADMIN_USER_UPDATE_PWD_EMPTY = new CodeMsg(-7001, "新密码不能为空！");
	
	//后台用户修改密码类错误码
	public static CodeMsg ADMIN_DATABASE_BACKUP_NO_EXIST = new CodeMsg(-8000, "备份记录不存在！");
	
	//后台物品及分类管理类错误码
	public static CodeMsg ADMIN_GOODSCATEGORY_ADD_ERROR = new CodeMsg(-9000, "分类添加失败，请联系管理员！");
	public static CodeMsg ADMIN_GOODSCATEGORY_EDIT_ERROR = new CodeMsg(-9001, "分类编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_GOODSCATEGORY_DELETE_ERROR = new CodeMsg(-9002, "该分类下存在子分类或物品信息，不允许删除，请先删除子分类或物品信息后再操作！");
	public static CodeMsg ADMIN_GOODS_NO_EXIST = new CodeMsg(-9003, "物品不存在！");
	public static CodeMsg ADMIN_GOODS_STATUS_NO_CHANGE = new CodeMsg(-9004, "物品状态没有改变！");
	public static CodeMsg ADMIN_GOODS_STATUS_ERROR = new CodeMsg(-9005, "物品状态错误！");
	public static CodeMsg ADMIN_GOODS_EDIT_ERROR = new CodeMsg(-9006, "物品状态编辑失败，请联系管理员！");
	public static CodeMsg ADMIN_GOODS_STATUS_UNABLE = new CodeMsg(-9007, "物品状态不可编辑（已出售的物品不可上架）！");
	public static CodeMsg ADMIN_GOODS_DELETE_ERROR = new CodeMsg(-9008, "该物品存在评论信息或举报信息，不允许删除，请先删除评论信息或举报信息后再操作！");
	
	//后台学生管理
	public static CodeMsg ADMIN_STUDENT_NO_EXIST = new CodeMsg(-9100, "学生不存在！");
	public static CodeMsg ADMIN_STUDENT_STATUS_NO_CHANGE = new CodeMsg(-9101, "学生状态未发生变化！");
	public static CodeMsg ADMIN_STUDENT_STATUS_ERROR = new CodeMsg(-9102, "学生状态错误！");
	public static CodeMsg ADMIN_STUDENT_EDIT_ERROR = new CodeMsg(-9103, "学生编辑错误，请联系管理员！");
	public static CodeMsg ADMIN_STUDENT_DELETE_ERROR = new CodeMsg(-9104, "该学生下存在关联数据（物品、求购物品、评论、举报信息等），请先删除关联数据再操作！");
	
	//后台友情链接管理
	public static CodeMsg ADMIN_NEWS_ADD_ERROR = new CodeMsg(-9200, "友情链接添加失败，请联系管理员！");
	public static CodeMsg ADMIN_NEWS_EDIT_ERROR = new CodeMsg(-9201, "友情链接编辑失败，请联系管理员！");
	//后台友情链接管理
	public static CodeMsg ADMIN_FRIENDLINK_ADD_ERROR = new CodeMsg(-9300, "友情链接添加失败，请联系管理员！");
	public static CodeMsg ADMIN_FRIENDLINK_EDIT_ERROR = new CodeMsg(-9301, "友情链接编辑失败，请联系管理员！");
	
	//网站设置
	public static CodeMsg ADMIN_SITESETTING_EDIT_ERROR = new CodeMsg(-9400, "网站设置失败，请联系管理员！");
	
	//前端用户登录注册类错误码
	public static CodeMsg HOME_STUDENT_REGISTER_SN_EXIST = new CodeMsg(-10000, "该学号已存在，请勿重复注册！");
	public static CodeMsg HOME_STUDENT_REGISTER_ERROR = new CodeMsg(-10001, "注册失败，请联系管理员！");
	public static CodeMsg HOME_STUDENT_SN_NO_EXIST = new CodeMsg(-10002, "该学号不存在！");
	public static CodeMsg HOME_STUDENT_PASSWORD_ERROR = new CodeMsg(-10003, "密码错误！");
	public static CodeMsg HOME_STUDENT_UNABLE = new CodeMsg(-10003, "该用户已被冻结，请联系管理员！");
	//前端用户中心错误码
	public static CodeMsg HOME_STUDENT_EDITINFO_ERROR = new CodeMsg(-20000, "基本信息修改失败，请联系管理员！");
	public static CodeMsg HOME_STUDENT_EDITPWD_OLD_ERROR = new CodeMsg(-20001, "原密码错误！");
	//前端用户发布物品错误码
	public static CodeMsg HOME_STUDENT_PUBLISH_ERROR = new CodeMsg(-30000, "物品发布失败，请联系管理员！");
	public static CodeMsg HOME_STUDENT_GOODS_EDIT_ERROR = new CodeMsg(-30001, "物品发布失败，请联系管理员！");
	public static CodeMsg HOME_STUDENT_GOODS_NO_EXIST = new CodeMsg(-30002, "物品不存在！");
	public static CodeMsg HOME_STUDENT_PUBLISH_CATEGORY_EMPTY = new CodeMsg(-30003, "请选择物品分类！");
	//前端用户举报物品
	public static CodeMsg HOME_STUDENT_REPORT_GOODS_ERROR = new CodeMsg(-40000, "举报失败，请联系管理员！");
	public static CodeMsg HOME_STUDENT_REPORTED_GOODS = new CodeMsg(-40001, "您已经举报过该物品，请勿重复举报！");
	public static CodeMsg HOME_STUDENT_REPORTED_NO_EXIST = new CodeMsg(-40002, "举报信息不存在！");
	//前端用户评论
	public static CodeMsg HOME_STUDENT_COMMENT_ADD_ERROR = new CodeMsg(-50000, "评论失败，请联系管理员！");
}
