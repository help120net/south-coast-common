package cn.south.toast.common.mybatis.query;


import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author huangbh
 *
 */
public class SQLFragment {

    private String sql;

    private Object value;

    public SQLFragment(String sql){
        this.sql=sql;
    }

    public SQLFragment(Object value){
        this.value=value;
    }

    public String  getSql(){
        return sql;
    }

    public Object getValue(){
        return value;
    }

    public boolean isSqlFragment(){
        return StringUtils.isNotBlank(sql)&&value==null;
    }

    public boolean isValueFragment(){
        return StringUtils.isBlank(sql)&&value!=null;
    }
}
