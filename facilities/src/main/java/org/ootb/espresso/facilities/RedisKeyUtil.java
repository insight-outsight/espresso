package org.ootb.espresso.facilities;

public class RedisKeyUtil {

    /**
     * redis的key 形式为：
     * 表名:主键名:主键值:列名
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param majorKeyValue 主键值
     * @param column 列名
     * @return
     */
    public static String getKey(String tableName,String majorKey,String majorKeyValue,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(majorKeyValue).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    /**
     * redis的key形式为：
     * 表名:主键名:主键值
     *
     * @param tableName 表名
     * @param majorKey 主键名
     * @param column 列名
     * @return
     */
    public static String getKey(String tableName,String majorKey,String column){
        StringBuffer buffer = new StringBuffer();
        buffer.append(tableName).append(":");
        buffer.append(majorKey).append(":");
        buffer.append(column);
        return buffer.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(RedisKeyUtil.getKey("user", "133355", "name"));
        System.out.println(RedisKeyUtil.getKey("user", "133355", "address","city"));
    }
}