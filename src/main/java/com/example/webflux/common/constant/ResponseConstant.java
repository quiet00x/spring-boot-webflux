package com.example.webflux.common.constant;
/**
 * 常量接口，可以利用内部类对各个应用进行区分
 * @author Preciousness
 *
 */
public interface ResponseConstant {
	/**
	 * 请求成功响应标志
	 */
	String RSP_NORMAL_RESULT="SUCCESS";
	/**
	 * 请求失败响应标志
	 */
	String RSP_ERROR_RESULT="FAILURE";

	/**
	 * 交易成功响应码
	 */
	String RSP_NORMAL_CODE="STY00000200";

	/**
	 * 交易成功响应信息
	 */
	String RSP_NORMAL_MSG="交易成功！";

	/**
	 * 参数异常，不允许为空
	 */
	String ILLEGAL_NULL_PARAM_EXCEPTION_CODE="STY00040003";

	/**
	 * 参数异常，不允许为空
	 */
	String ILLEGAL_NULL_PARAM_EXCEPTION_MSG="参数异常，不允许为空";

	/**
	 * 非法参数异常
	 */
	String ILLEGAL_ARGUMENT_EXCEPTION_CODE="STY00040004";

	/**
	 * 非法参数异常
	 */
	String ILLEGAL_ARGUMENT_EXCEPTION_MSG="非法参数异常";

	/**
	 * IO流异常
	 */
	String IO_EXCEPTION_ERROR_CODE="STY00040005";
	
	/**
	 * IO流异常
	 */
	String IO_EXCEPTION_ERROR_MSG="IO流异常";
	
	/**
	 * document异常
	 */
	String DOCUMENT_EXCEPTION_ERROR_CODE="STY00040006";
	
	/**
	 * document异常
	 */
	String DOCUMENT_EXCEPTION_ERROR_CODE_MSG="Document异常";
	
	/**
	 * 类型转换异常
	 */
	String CLASS_CASE_EXCEPTION_CODE="STY00040007";
	/**
	 * 类型转换异常
	 */
	String CLASS_CASE_EXCEPTION_MSG="类型转换异常";

	/**
	 * 获取实例异常
	 */
	String INSTANTIATION_EXCEPTION_CODE="STY00040008";
	
	/**
	 * 获取实例异常
	 */
	String INSTANTIATION_EXCEPTION_MSG="获取实例异常";
	
	/**
	 * 访问权限异常
	 */
	String ILLEGAL_ACCESS_EXCEPTION_CODE="STY00040009";
	
	/**
	 * 访问权限异常
	 */
	String ILLEGAL_ACCESS_EXCEPTION_MSG="访问权限异常";

	/**
	 * 访问权限异常
	 */
	String UNKNOW_SYSTEM_EXCEPTION_CODE="STY00040010";

	/**
	 * 访问权限异常
	 */
	String UNKNOW_SYSTEM_EXCEPTION_MSG="未知系统异常";

	interface FileAbout{
		/**
		 * 文件找不到
		 */
		String FILE_NOT_FOUND_ERROR_CODE="STY00040001";
		/**
		 * 文件找不到
		 */
		String FILE_NOT_FOUND_ERROR_MSG="文件不存在异常";
	}

	interface XMLAbout {
		/**
		 * 文档加载异常
		 */
		String DOCUMENT_EXCEPTION_CODE="STY00040002";
		/**
		 * 文档加载异常
		 */
		String DOCUMENT_EXCEPTION_MSG="xml文档加载异常";
	}
	
}
