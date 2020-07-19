package com.puyang.utils;

import org.apache.commons.dbutils.ColumnHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeColumnHandler implements ColumnHandler {
    @Override
    public boolean match(Class<?> propType) {
        return LocalDateTime.class.equals(propType);
    }

    @Override
    public Object apply(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getTimestamp(columnIndex).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime();
    }
}