package com.leitianyu.blue.engine.mapping;

import javax.servlet.Filter;

/**
 * @author leitianyu
 * @date 2024/1/13
 */
public class FilterMapping extends AbstractMapping {

    public final String filterName;
    public final Filter filter;

    public FilterMapping(String filterName, String urlPattern, Filter filter) {
        super(urlPattern);
        this.filterName = filterName;
        this.filter = filter;
    }

}
