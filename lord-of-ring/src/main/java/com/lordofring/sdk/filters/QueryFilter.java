package com.lordofring.sdk.filters;

public class QueryFilter {
    private String field;
    private QueryFilterType queryFilterType;
    private String value;

    public QueryFilter(String field, QueryFilterType queryFilterType, String value) {
        this.field = field;
        this.queryFilterType = queryFilterType;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public QueryFilterType getFilterType() {
        return queryFilterType;
    }

    public void setFilterType(QueryFilterType queryFilterType) {
        this.queryFilterType = queryFilterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "field='" + field + '\'' +
                ", filterType=" + queryFilterType +
                ", value='" + value + '\'' +
                '}';
    }
}

