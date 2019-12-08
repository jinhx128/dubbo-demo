package com.jinhaoxun.dubbo.model.web;

import com.jinhaoxun.dubbo.model.base.Model;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @description: 分页查询响应模型
 * @program: session1
 * @author: JiangFeng
 * @create: 2019-03-11 18:36
 **/
public class PageableRes extends Model {
    
    /** 总页数 */
    private int pages;
    /** 总记录数 */
    private int records;
    
    private List<T> list;
    
    public List<T> getList() {
        return list;
    }
    
    public void setList(List<T> list) {
        this.list = list;
    }
    
    public int getRecords() {
        return records;
    }
    
    public void setRecords(int records) {
        this.records = records;
    }
    
    public int getPages() {
        return pages;
    }
    
    public void setPages(int pages) {
        this.pages = pages;
    }
}
