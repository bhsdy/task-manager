package com.cn.taskManager.dto;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Evan
 * @date 2018/03/06
 */
public class QueryPagingDTO {

    @ApiModelProperty(value="当前页码")
    protected Integer curPage = 0;

    @ApiModelProperty(value="每页数量")
    protected Integer pageSize = 10;

    @ApiModelProperty(value="排序字段")
    protected String orderBy = "id";

    @ApiModelProperty(value="升序 ASC / 降序 DESC")
    protected String xsc;

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getXsc() {
        return xsc;
    }

    public void setXsc(String xsc) {
        this.xsc = xsc;
    }

    public Pageable getPage() {
        Sort sort;

        if ("asc".equalsIgnoreCase(xsc)) {
            sort = new Sort(Sort.Direction.ASC, orderBy);
        } else {
            sort = new Sort(Sort.Direction.DESC, orderBy);
        }

        return new PageRequest(curPage, pageSize, sort);
    }
}
