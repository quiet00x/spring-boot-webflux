package com.example.webflux.mapper;

import java.io.Serializable;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/4 - 0:56
 * @author: Mr_Bangb
 */
public class PageReq implements Serializable {

    /**
     * 每页显示大小
     */
    private long  size;

    /**
     * 当前页码
     */
    private  long current;

    /**
     * 最大页数
     */
    private  long maxCurrent;

    /**
     * 数据总条数
     */
    private  long total;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getMaxCurrent() {
        return maxCurrent;
    }

    public void setMaxCurrent(long maxCurrent) {
        this.maxCurrent = maxCurrent;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        if(size != 0){
            if(total % size != 0){
                maxCurrent = total / size + 1;
            }else {
                maxCurrent = total / size;
            }
        }
    }

    public PageReq() {

    }

    public PageReq(long size, long current, long total) {
        this.size = size;
        this.current = current;
        this.total = total;
        setTotal(total);
    }
}
