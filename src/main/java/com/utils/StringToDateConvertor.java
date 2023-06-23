package com.utils;
import org.springframework.core.convert.converter.Converter;
import java.text.SimpleDateFormat;
import java.util.Date;
public class StringToDateConvertor implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = sdf.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parse;
    }
}