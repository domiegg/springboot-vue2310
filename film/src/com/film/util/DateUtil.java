package com.film.util;
 
import java.text.SimpleDateFormat;
import java.util.Date;

 
/**
 * 日期工具类
 */
public class DateUtil {  
	  
    private int type;  
  
    public static final String getCurrentDateStr() {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");  
        return sdf.format(DateUtil.now());  
    }  
  
    public static final String getCurrentDateStrByFormatType(int type) {  
        if (type == 1) {  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
            return sdf.format(DateUtil.now());  
        } else {  
            return DateUtil.getCurrentDateStr();  
        }  
    }  
  
    public static final Date now() {  
        return new Date();  
    }  
  
    public int getType() {  
        return type;  
    }  
  
    public void setType(int type) {  
        this.type = type;  
    }  
  
}  
