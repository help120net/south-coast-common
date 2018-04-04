package cn.south.toast.common.mybatis.base;


import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author huangbh
 *
 * @param <T>
 * @param <E>
 */
public interface IBaseService<T, E> {

    /**
     * @描述：�?�过example组装条件，查询记录数
     * @param example
     *            MyBatis条件组装�?
     * @return 记录�?
     * @返回值：int
     * @异常说明�?
     */
    public int countByExample(E example);

    /**
     * @描述：�?�过example组装条件，删除记�?
     * @param example
     *            MyBatis条件组装�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    public int deleteByExample(E example) ;

    /**
     * @描述：�?�过主键删除记录
     * @param id
     *            主键�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    public int deleteByPrimaryKey(Serializable id) ;

    /**
     * @描述：插入记�?
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    public int insert(T record) ;

    /**
     * @描述：插入记�?(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    public int insertSelective(T record) ;

    /**
     * @描述：�?�过example组装条件，查询实体列�?
     * @param example
     *            MyBatis条件组装�?
     * @return 实体List
     * @返回值：List<T>
     * @异常说明�?
     */
    public List<T> selectByExample(E example);

    /**
     * @描述：�?�过主键查询单个实体
     * @param id
     *            主键�?
     * @return 实体对象
     * @返回值：T
     * @异常说明�?
     */
    public T selectByPrimaryKey(Serializable id);

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
    public int updateByExampleSelective(T record, E example) ;

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
    public int updateByExample(T record, E example) ;

    /**
     * @描述：�?�过主键修改记录(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    public int updateByPrimaryKeySelective(T record) ;

    /**
     * @描述：�?�过主键修改记录
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    public int updateByPrimaryKey(T record) ;

    /**
     * @描述：�?�过example组装条件，查询一条实体记�?
     * @param example
     *            MyBatis条件组装�?
     * @return 单个实体
     * @exception DaoException
     *                如果查出多条记录则抛出这个异�?
     * @返回值：T
     * @异常说明�?
     */
    public T selectOneByExample(E example);

}