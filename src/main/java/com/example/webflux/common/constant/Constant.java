package com.example.webflux.common.constant;

public interface Constant {
	
	/**
	 * 请求成功响应码值
	 */
	public static final String BANGBB_SYSTEM_CODE="10111004";
	/**
	 * 请求成功响应码值
	 */
	public static final String RSP_NORMAL_CODE="N";
	/**
	 * 请求成功响应消息
	 */
	public static final String RSP_NORMAL_MSG="交易成功！";
	/**
	 * 请求失败响应码值
	 */
	public static final String RSP_ERROR_CODE="E";
	/**
	 * 请求失败响应消息
	 */
	public static final String RSP_ERROR_MSG="交易失败！";
	/**
	 * 日期相关码值 天
	 */
	public static final String  DATE_OF_DAY = "day";
	/**
	 * 日期相关码值 周
	 */
	public static final String  DATE_OF_WEEK = "week";
	/**
	 * 日期相关码值 月
	 */
	public static final String  DATE_OF_MONTH = "month";
	/**
	 * 日期相关码值 年
	 */
	public static final String  DATE_OF_YEAR = "year";
	
	/**
	 * 文件后缀 .zip
	 */
	public static final String FILE_SUFFIX_ZIP = ".zip";
	
	/**
	 * 文件后缀 .doc
	 */
	public static final String FILE_SUFFIX_DOC = ".doc";
	
	/**
	 * 文件后缀 .docx
	 */
	public static final String FILE_SUFFIX_DOCX = ".docx";
	
	/**
	 * 文件后缀 .txt
	 */
	public static final String FILE_SUFFIX_TXT = ".txt";
	
	/**
	 * 文件后缀 .xls
	 */
	public static final String FILE_SUFFIX_XLS = ".xls";
	
	/**
	 * 文件后缀 .xlsx
	 */
	public static final String FILE_SUFFIX_XLSX = ".xlsx";
	
	/**
	 * 文件后缀 .pdf
	 */
	public static final String FILE_SUFFIX_PDF = ".pdf";
	
	interface CreditProof {
		
		/**
		 * 资信证明种类
		 */
		public static final String CREDIT_TYPE_PARAM = "creditType";
		
		/**
		 * 资信证明打印类型
		 */
		public static final String CREDIT_PRINT_TYPE_PARAM = "printType";
		
		/**
		 * 资信证明凭证号码
		 */
		public static final String REGISTER_NO_LIST_PARAM = "registerNoList";
		
		/**
		 * 资信证明编号
		 */
		public static final String CREDIT_NO_PARAM = "creditNo";
		
		/**
		 * 凭证类型：
		 * 00 交易明细
		 */
		public static final String CREDIT_TYPE_TRADE_DETAIL_CODE = "00";
		
		/**
		 * 凭证类型：
		 * 01 存款证明 
		 */
		public static final String CREDIT_TYPE_DEPOSIT_CODE = "01";
		
		/**
		 * 凭证类型：
		 * 02 存款发生额证明 
		 */
		public static final String CREDIT_TYPE_DEPOSIT_TRADE_CODE = "02";
		
		/**
		 * 凭证类型：
		 * 03 理财产品持有证明
		 */
		public static final String CREDIT_TYPE_FINANCIAL_CODE = "03";
		
		/**
		 * 凭证类型：
		 * 04 理财产品交易记录证明
		 */
		public static final String CREDIT_TYPE_FINANCIAL_TRADE_CODE = "04";
		
		/**
		 * 凭证类型：
		 * 05 黄金持有证明
		 */
		public static final String CREDIT_TYPE_GOLD_CODE = "05";
		
		/**
		 * 凭证类型：
		 * 06 基金持有证明
		 */
		public static final String CREDIT_TYPE_FUND_CODE = "06";
		
		/**
		 * 凭证类型：
		 * 07 贷款证明 
		 */
		public static final String CREDIT_TYPE_LOAN_CODE = "07";
		
	
		
	}
	
	
	
	
	
}
