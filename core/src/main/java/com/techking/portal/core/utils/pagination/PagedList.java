package com.techking.portal.core.utils.pagination;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PagedList<T> {

    private int total;
    private List<T> data;
    private int pageNum;
    private int pageSize;
}
