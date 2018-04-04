package cn.south.toast.common.mybatis.TypeHandle;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

import cn.south.toast.common.utils.JsonUtils;

/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
public class JsonArrayTypeHandler extends BaseTypeHandler<List> {
    private Class<Object> clazz;
    public JsonArrayTypeHandler(Class<Object> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.clazz = clazz;
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List list, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtils.toJson(list));
    }

    @Override
    public List getNullableResult(ResultSet rs, String s) throws SQLException {
        try {
            String result = rs.getString(s);
            if(result==null)  return null;
            JavaType javaType = JsonUtils.mapper.getTypeFactory().constructParametricType(List.class, clazz);
            List t = JsonUtils.mapper.readValue(result,javaType);
            return t;
        } catch (JsonParseException e){
            return null;
        }catch (JsonMappingException e){
            return null;
        }catch (IOException e) {
            return null;
        }
    }

    @Override
    public List getNullableResult(ResultSet rs, int i) throws SQLException {
        try {
            String result = rs.getString(i);
            if(result==null)  return null;
            JavaType javaType = JsonUtils.mapper.getTypeFactory().constructParametricType(List.class, clazz);
            List t = JsonUtils.mapper.readValue(result,javaType);
            return t;
        } catch (JsonParseException e){
            return null;
        }catch (JsonMappingException e){
            return null;
        }catch (IOException e) {
            return null;
        }
    }

    @Override
    public List getNullableResult(CallableStatement cs, int i) throws SQLException {
        try {
            String result = cs.getString(i);
            if(result==null)  return null;
            JavaType javaType = JsonUtils.mapper.getTypeFactory().constructParametricType(List.class, clazz);
            List t = JsonUtils.mapper.readValue(result,javaType);
            return t;
        } catch (JsonParseException e){
            return null;
        }catch (JsonMappingException e){
            return null;
        }catch (IOException e) {
            return null;
        }
    }

//    private Class<T> clazz;
//    public JsonArrayTypeHandler(Class<T> clazz) {
//        if (clazz == null) throw new IllegalArgumentException("Type argument cannot be null");
//        this.clazz = clazz;
//    }

}
