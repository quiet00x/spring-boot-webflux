package com.example.webflux.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/9/4 - 23:44
 * @author: Mr_Bangb
 */
public class BusValidatorUtils <T> {
    /**
     * 重复参数校验
     *
     * @param inputList 入参列表
     * @param validator 校验类型
     * @param outPutType 输出参数类型
     * @return list
     */
    private static <T> List<String> validateRepeatInput(List<T> inputList, String validator, String outPutType) {
        String errMsg = "";

//
//        // 按照validator进行去重
//        List<String> distinctList = inputList.stream()
//                .map(BatchOptVO::getNo).distinct().collect(Collectors.toList());
//
////        List<String> disList = new ArrayList<>();
////        for (T src: srcList) {
////            if (!disList.contains(src.getNo())) {
////                disList.add(src.getNo());
////            }
////        }
//        if (disList.size() < srcList.size()) {
//            // 表示有重复数据
//            System.out.println("*************************************************************");
//            List<String> outTempList = new ArrayList<>();
//            Iterator<T> outIterator = srcList.iterator();
//            while (outIterator.hasNext()) {
//                T outElement = outIterator.next();
//                String rowNumOut = outElement.getRowNum();
//                String noOut = outElement.getNo();
//                Iterator<T> innerIterator = descList.iterator();
//                List<String> innerTempList = new ArrayList<>();
//                while (innerIterator.hasNext()) {
//                    T innerElement = innerIterator.next();
//                    String rowNumInner = innerElement.getRowNum();
//                    String noInner = innerElement.getNo();
//                    if (noOut.equals(noInner) && !rowNumOut.equals(rowNumInner)
//                            && Integer.parseInt(rowNumOut) < Integer.parseInt(rowNumInner)) {
//                        if (!innerTempList.contains(rowNumOut)) {
//                            innerTempList.add(rowNumOut);
//                        }
//                        innerTempList.add(rowNumInner);
//                        innerIterator.remove();
//                    }
//                }
//                String innerJoin = String.join("&", innerTempList);
//                if (StringUtils.isNotBlank(innerJoin)) {
//                    outTempList.add(innerJoin);
//                }
//            }
//            errMsg = String.join(",", outTempList);
//        }
//        System.out.println(errMsg);
        return null;
    }
}