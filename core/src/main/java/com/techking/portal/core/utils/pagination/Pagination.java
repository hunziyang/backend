package com.techking.portal.core.utils.pagination;

import com.techking.portal.core.exception.BaseException;
import com.techking.portal.core.result.ResultCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Min;

@Data
public class Pagination {
    @Min(value = 1, message = "页码最小为1")
    private int pageNum;
    @Min(value = 20, message = "条目最小为20")
    private int pageSize;
    private String orderBy;
    private String orderType;

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

    public void checkOrder() {
        if ((StringUtils.isBlank(orderBy) && StringUtils.isNotBlank(orderType))
                || (StringUtils.isNotBlank(orderBy) && StringUtils.isBlank(orderType))) {
            throw new BaseException(ResultCode.ORDER_ERROR);
        }
    }
}
