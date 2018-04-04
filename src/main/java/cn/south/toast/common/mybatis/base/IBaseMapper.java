package cn.south.toast.common.mybatis.base;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * MyBatis Mapper基类
 * @author huangbh
 *
 */
public interface IBaseMapper<T, E> {

    /**
     * 记录参数名，在mapper.xml文件中会引用�?
     */
    String RECORD_PARAM_NAME = "record";

    /**
     * example参数名，在mapper.xml文件中会用到
     */
    String EXAMPLE_PARAM_NAME = "example";

    /**
     * 列名list，在mapper.xml文件中会用到
     */
    String COLUMN_LIST_PARAM_NAME = "columnList";

    /**
     * @描述：�?�过example组装条件，查询记录数
     * @param example
     *            MyBatis条件组装�?
     * @return 记录�?
     * @返回值：int
     * @异常说明�?
     */
    int countByExample(E example);

    /**
     * @描述：�?�过example组装条件，删除记�?
     * @param example
     *            MyBatis条件组装�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int deleteByExample(E example);

    /**
     * @描述：�?�过主键删除记录
     * @param id
     *            主键�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int deleteByPrimaryKey(Serializable id);

    /**
     * @描述：插入记�?
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int insert(T record);

    /**
     * @描述：插入记�?(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int insertSelective(T record);

    /**
     * @描述：�?�过example组装条件，查询实体列�?
     * @param example
     *            MyBatis条件组装�?
     * @return 实体List
     * @返回值：List<T>
     * @异常说明�?
     */
    List<T> selectByExample(E example);

    /**
     * @描述：�?�过主键查询单个实体
     * @param id
     *            主键�?
     * @return 实体对象
     * @返回值：T
     * @异常说明�?
     */
    T selectByPrimaryKey(Serializable id);

    /**
     * @描述：�?�过example组装条件，修改记�?(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @param example
     *            MyBatis条件组装�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int updateByExampleSelective(@Param(RECORD_PARAM_NAME) T record,
                                 @Param(EXAMPLE_PARAM_NAME) E example);

    /**
     * @描述：�?�过example组装条件，修改记�?
     * @param record
     *            实体记录
     * @param example
     *            MyBatis条件组装�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int updateByExample(@Param(RECORD_PARAM_NAME) T record, @Param(EXAMPLE_PARAM_NAME) E example);

    /**
     * @描述：�?�过主键修改记录(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int updateByPrimaryKeySelective(T record);

    /**
     * @描述：�?�过主键修改记录
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    int updateByPrimaryKey(T record);
}