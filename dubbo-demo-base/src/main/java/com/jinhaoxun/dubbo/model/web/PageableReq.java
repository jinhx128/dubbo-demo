package com.jinhaoxun.dubbo.model.web;

import com.jinhaoxun.dubbo.model.base.Model;

/**
 * @description: 分页请求模型
 * @program: session1
 * @author: JiangFeng
 * @create: 2019-03-12 15:17
 **/
public class PageableReq extends Model {
    
    private static final long serialVersionUID = -5131901205982539515L;
    /** 当前页号 */
    private short page;
    /** 每页显示记录数 */
    private short size;
    
    public short getPage() {
        return page;
    }
    
    public void setPage(short page) {
        this.page = page;
    }
    
    public short getSize() {
        return size;
    }
    
    public void setSize(short size) {
        this.size = size;
    }
}
