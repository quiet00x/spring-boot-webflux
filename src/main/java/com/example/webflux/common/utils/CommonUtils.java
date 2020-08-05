package com.example.webflux.common.utils;

import com.example.webflux.common.constant.ResponseConstant;
import com.example.webflux.common.exception.LocalException;
import com.example.webflux.domain.TraceInfoBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/13 - 23:29
 * @author: Mr_Bangb
 * @description 公共工具类
 */
@Slf4j
public class CommonUtils {

    private static final String PATTERN = "yyyyMMddHHmmssSSS";

    /**
     * 线程本地变量，用于存放全局变量
     */
    private static final ThreadLocal<TraceInfoBean> TRACE_INFO_BEAN_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取本地线程变量中保存的 公共参数
     * @return TraceInfoBean
     */
    public static TraceInfoBean getTraceInfo(){
        return TRACE_INFO_BEAN_THREAD_LOCAL.get();
    }

    /**
     * 设置本地线程变量中保存的 TraceInfoBean
     * @param traceInfo 全局变量
     */
    public static void setTraceInfo(TraceInfoBean traceInfo) {
        // 生成traceNo
        String traceNo = generalTraceNo(generalRandom());
        traceInfo = Optional.ofNullable(traceInfo).orElse(new TraceInfoBean());
        if(StringUtils.isBlank(traceInfo.getTraceNo())) {
            traceInfo.setTraceNo(traceNo);
        }
        if(StringUtils.isBlank(traceInfo.getServerIp())) {
            try {
                traceInfo.setServerIp(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                log.info("unKnowHost");
            }
        }
        TRACE_INFO_BEAN_THREAD_LOCAL.set(traceInfo);
    }

    /**
     * 清空本地线程变量，如果用完不清理会导致内存泄露
     */
    public static void cleanResource() {
        TRACE_INFO_BEAN_THREAD_LOCAL.remove();
    }

    /**
     * 生成 traceNo
     *
     * @param random 随机数
     * @return traceNo
     */
    public static String generalTraceNo(Integer random) {
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern(PATTERN));
        return localDateTime.concat(random.toString());
    }

    /**
     * 随机生成 6位整数
     *
     * @return random
     */
    public static Integer generalRandom() {
        return (int)(Math.random() * 1000000 + 1);
    }

    /**
     * 字符串拆分，不对input进行必输校验，具体非空校验逻辑具体考虑
     * @param input 输入项
     * @param regex 正则
     * @return @NotNull list
     */
    public static List<String> splitQueryParam(String input, String regex) {
        if (StringUtils.isBlank(regex)) {
            throw new LocalException(ResponseConstant.ILLEGAL_NULL_PARAM_EXCEPTION_CODE
                    ,"regex 不能为空！");
        }

        String[] inputs = input.split(regex);
        if (ArrayUtils.getLength(inputs) ==0) {
            return Collections.emptyList();
        }

        List<String> inputList = Arrays.asList(inputs);
        inputList.forEach(StringUtils::trim);

        return inputList;
    }
}