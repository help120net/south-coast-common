package cn.south.toast.common.mybatis.TypeHandle;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import cn.south.toast.common.utils.JsonUtils;

/**
 * 
 * @author huangbh
 *
 * @param <T>
 */
public class JsonTypeHandler<T extends Object> extends BaseTypeHandler<T> {

    private Class<T> clazz;
    public JsonTypeHandler(Class<T> clazz) {
        if (clazz == null) throw new IllegalArgumentException("Type argument cannot be null");
        this.clazz = clazz;
    }
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, JsonUtils.toJson(parameter));
    }

    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            String result = rs.getString(columnName);
            if(result==null)  return null;
            T t = JsonUtils.fromJson(result, clazz);
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
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            String result = rs.getString(columnIndex);
            if(result==null)  return null;
            return JsonUtils.fromJson(result, clazz);
        } catch (JsonParseException e){
            return null;
        }catch (JsonMappingException e){
            return null;
        }catch (IOException e) {
            return null;
        }
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            String result = cs.getString(columnIndex);
            if(result==null)  return null;
            return JsonUtils.fromJson(result, clazz);
        } catch (JsonParseException e){
            return null;
        }catch (JsonMappingException e){
            return null;
        }catch (IOException e) {
            return null;
        }
    }
}
