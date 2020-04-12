package com.example.webflux.common.constant;
/**
 * 常量接口，可以利用内部类对各个应用进行区分
 * @author Preciousness
 *
 */
public interface WsErrConstant {
	
	/**
	 * 请求参数异常
	 */
	public static final String REQ_PARAMS_ERROR_CODE="STY00040003";
	
	/**
	 * 请求参数异常
	 */
	public static final String REQ_PARAMS_ERROR_MSG="请求参数异常！";
	
	/**
	 * IO流异常
	 */
	public static final String IO_EXCEPTION_ERROR_CODE="STY00040004";
	
	/**
	 * IO流异常
	 */
	public static final String IO_EXCEPTION_ERROR_MSG="IO流异常";
	
	/**
	 * document异常
	 */
	public static final String DOCUMENT_EXCEPTION_ERROR_CODE="STY00040005";
	
	/**
	 * document异常
	 */
	public static final String DOCUMENT_EXCEPTION_ERROR_CODE_MSG="Document异常";
	
	/**
	 * 类型转换异常
	 */
	public static final String CLASS_CASE_EXCEPTION_CODE="STY00040006";
	/**
	 * 类型转换异常
	 */
	public static final String CLASS_CASE_EXCEPTION_MSG="类型转换异常";
	
	/**
	 * 参数异常，不允许为空
	 */
	public static final String ILLEGAL_NULL_PARAM_EXCEPTION_CODE="STY00040007";
	
	/**
	 * 参数异常，不允许为空
	 */
	public static final String ILLEGAL_NULL_PARAM_EXCEPTION_MSG="参数异常，不允许为空";
	
	/**
	 * 获取实例异常
	 */
	public static final String INSTANTIATION_EXCEPTION_CODE="STY00040008";
	
	/**
	 * 获取实例异常
	 */
	public static final String INSTANTIATION_EXCEPTION_MSG="获取实例异常";
	
	/**
	 * 非法参数异常
	 */
	public static final String ILLEGAL_ARGUMENT_EXCEPTION_CODE="STY00040009";
	
	/**
	 * 非法参数异常
	 */
	public static final String ILLEGAL_ARGUMENT_EXCEPTION_MSG="非法参数异常";
	
	/**
	 * 访问权限异常
	 */
	public static final String ILLEGAL_ACCESS_EXCEPTION_CODE="STY00040010";
	
	/**
	 * 访问权限异常
	 */
	public static final String ILLEGAL_ACCESS_EXCEPTION_MSG="访问权限异常";
	
	
	class FileAbout{
		/**
		 * 文件找不到
		 */
		public static final String FILE_NOT_FOUND_ERROR_CODE="STY00040001";
		/**
		 * 文件找不到
		 */
		public static final String FILE_NOT_FOUND_ERROR_MSG="文件不存在异常";
	}
	
	class XMLAbout {
		/**
		 * 文档加载异常
		 */
		public static final String DOCUMENT_EXCEPTION_CODE="STY00040002";
		/**
		 * 文档加载异常
		 */
		public static final String DOCUMENT_EXCEPTION_MSG="xml文档加载异常";
	}
	
}
