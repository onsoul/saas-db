package com.hitler.common.util;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 数据类型转换
 * @author onsoul by JT 2015-6-24 下午4:32:23
 * */
@SuppressWarnings("unchecked")  
public class TransformUtils {  

    public static boolean toBoolean(Object obj) {  

        return toBoolean(obj, false);  
    }  

    public static boolean toBoolean(Object obj, boolean defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  
        try {  
            return Boolean.parseBoolean(toString(obj));  
        } catch (Exception e) {  
        }  
        return defaultValue ;  
    }  

    public static byte toByte(Object obj) {  
        return toByte(obj, (byte) 0);  
    }  

    public static byte toByte(Object obj, byte defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  

        if (obj instanceof Number) {  
            Number number = (Number) obj;  
            return number.byteValue();  
        }  
        String value = toString(obj) ;  
        try {  
            return Byte.parseByte( value ) ;  
        } catch (Exception e) {  
        }  
        return defaultValue ;  
    }  

    public static char toCharacter(Object obj) {  
        return toCharacter(obj, (char) ' ');  
    }  

    public static char toCharacter(Object obj, char defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  
        String str = obj.toString().trim();  
        if (str.length() == 0) {  
            return defaultValue;  
        }  
        return (char) str.toCharArray()[0];  
    }  

    public static double toDouble(Object obj) {  
        return toDouble(obj, 0d);  
    }  

    public static double toDouble(Object obj, double defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  

        if (obj instanceof Number) {  
            Number number = (Number) obj;  
            return number.doubleValue() ;  
        }  
        String value = toString(obj) ;  
        try {  
            return Double.parseDouble(value) ;  
        } catch (Exception e) {  
        }  
        return defaultValue ;  
    }  

    public static float toFloat(Object obj) {  
        return toFloat(obj, 0);  
    }  

    public static float toFloat(Object obj, float defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  

        if (obj instanceof Number) {  
            Number number = (Number) obj;  
            return number.floatValue();  
        }  
        String value = toString(obj) ;  
        try {  
            return Float.parseFloat(value) ;  
        } catch (Exception e) {  
        }  
        return defaultValue ;  
    }  

    public static int toInt(Object obj) {  
        return toInt(obj, 0);  
    }  

    public static int toInt(Object obj, int defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  

        if (obj instanceof Number) {  
            Number number = (Number) obj;  
            return number.intValue();  
        }  
        String value = toString(obj) ;  
        try {  
            return Integer.parseInt(value) ;  
        } catch (Exception e) {  
        }  
        return defaultValue ;  
    }  

    public static long toLong(Object obj) {  
        return toLong(obj, 0L);  
    }  

    public static long toLong(Object obj, long defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  

        if (obj instanceof Number) {  
            Number number = (Number) obj;  
            return number.longValue();  
        }  
        String value = toString(obj) ;  
        try {  
            return Long.parseLong(value) ;  
        } catch (Exception e) {  
        }  
        return defaultValue ;  
    }  

    public static short toShort(Object obj) {  
        return toShort(obj, (byte) 0);  
    }  
      
      
    public static short toShort(Object obj, short defaultValue) {  
        if (obj == null) {  
            return defaultValue;  
        }  

        if (obj instanceof Number) {  
            Number number = (Number) obj;  
            return number.shortValue();  
        }  
        String value = toString(obj) ;  
        try {  
            return Short.parseShort(value) ;  
        } catch (Exception e) {  
            return defaultValue;  
        }  
    }  

    public static String toString(Object value) {  
        if (value == null) {  
            return "";  
        }  
        return value.toString().trim();  
    }  
      
    public static BigDecimal toBigDecimal(Object value){  
        return toBigDecimal(value , new BigDecimal(0)) ;  
    }  
      
    public static BigDecimal toBigDecimal(Object value, BigDecimal defaultValue) {  
        if(value == null){  
            return defaultValue ;  
        }  
        if(value instanceof BigDecimal){  
            BigDecimal decimal = (BigDecimal) value ;  
            return decimal;   
        }  
        return new BigDecimal( toDouble( value ) );  
    }  

    public static String dateToString(Object value, String pattern){  
        java.util.Date date = (java.util.Date) value;    
        Calendar calendar = Calendar.getInstance();    
        calendar.setTime( date ) ;   
        SimpleDateFormat sdf = new SimpleDateFormat( pattern );    
        return sdf.format( date ) ;   
    }  
      
    public static Object transform(Object obj,Class<?> clazz){  
        if(clazz == null){  
            return obj ;    
        }  
          
        if(clazz.isEnum()){  
            Field[]fields=clazz.getFields() ;  
            int tempInt = toInt(obj) ;  
            if(fields.length > tempInt){  
                try {  
                    return Enum.valueOf((Class)clazz , fields[tempInt].getName());  
                } catch (Exception e) {  
                }  
            }  
        }  
          
        if(obj.getClass().equals(clazz)){  
            return obj ;   
        }  
        if(int.class.equals(clazz) || Integer.class.equals(clazz)){  
            return toInt(obj) ;   
        }else if( String.class.equals(clazz) ){  
            return toString( obj ) ;   
        }else if(float.class.equals(clazz) || Float.class.equals(clazz)){  
            return toFloat(obj) ;   
        }else if(double.class.equals(clazz) || Double.class.equals(clazz)){  
            return toDouble(obj) ;   
        }else if(byte.class.equals(clazz) || Byte.class.equals(clazz)){  
            return toByte(obj) ;   
        }else if(char.class.equals(clazz) || Character.class.equals(clazz)){  
            return toCharacter(obj) ;   
        }else if(short.class.equals(clazz) || Short.class.equals(clazz)){  
            return toShort(obj) ;   
        }else if(long.class.equals(clazz) || Long.class.equals(clazz)){  
            return toLong(obj) ;   
        }else if(boolean.class.equals(clazz) || Boolean.class.equals(clazz)){  
            return toBoolean(obj) ;   
        }else if(BigDecimal.class.equals(clazz)){  
            return toBigDecimal( obj ) ;   
        }else if(java.util.Date.class.equals(clazz) ||   
                java.sql.Date.class.equals(clazz)   
                || java.sql.Timestamp.class.equals(clazz)){  
            try {  
                java.util.Date date = DateUtils.parseDate(obj.toString()   
                        , "yyyyMMdd" , "yyyy-MM-dd" ,   
                        "yyyy年MM月dd日" ,   
                         "yyyyMMddHHmmss" ,   
                         "yyyy-MM-dd HH:mm:ss" ,   
                         "yyyy年MM月dd日HH时mm分ss秒" ) ;    
                if(java.sql.Date.class.equals(clazz)){  
                    return new java.sql.Date(date.getTime()) ;    
                }else if(java.sql.Timestamp.class.equals(clazz)){  
                    return new java.sql.Timestamp(date.getTime()) ;  
                }  
                return date ;   
            } catch (Exception e) {  
                return null;  
            }  
        }  
        return obj ;   
    }
}  