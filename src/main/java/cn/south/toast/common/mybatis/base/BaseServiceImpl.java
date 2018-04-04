package cn.south.toast.common.mybatis.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author huangbh
 *
 * @param <T>
 * @param <E>
 */
public abstract class BaseServiceImpl<T, E> implements IBaseService<T, E> {

    /**
     * 日志对象
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @描述：获取与实体对应的mapper
     * @return
     * @返回值：IBaseMapper<T,E>
     * @异常说明�?
     */
    public abstract IBaseMapper<T, E> getBaseMapper();

    /**
     * @描述：�?�过example组装条件，查询记录数
     * @param example
     *            MyBatis条件组装�?
     * @return 记录�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    public int countByExample(E example) {
        return getBaseMapper().countByExample(example);
    }

    /**
     * @描述：�?�过example组装条件，删除记�?
     * @param example
     *            MyBatis条件组装�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByExample(E example){
        int count = 0;
        try {
            count = getBaseMapper().deleteByExample(example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * @描述：�?�过主键删除记录
     * @param id
     *            主键�?
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Serializable id){
        int count = 0;
        try {
            count = getBaseMapper().deleteByPrimaryKey(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * @描述：插入记�?
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(T record){
        int count = 0;
        try {
            count = getBaseMapper().insert(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * @描述：插入记�?(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(T record){
        int count = 0;
        try {
            count = getBaseMapper().insertSelective(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * @描述：�?�过example组装条件，查询实体列�?
     * @param example
     *            MyBatis条件组装�?
     * @return 实体List
     * @返回值：List<T>
     * @异常说明�?
     */
    @Override
    public List<T> selectByExample(E example) {
        return getBaseMapper().selectByExample(example);
    }

    /**
     * @描述：�?�过主键查询单个实体
     * @param id
     *            主键�?
     * @return 实体对象
     * @返回值：T
     * @异常说明�?
     */
    @Override
    public T selectByPrimaryKey(Serializable id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByExampleSelective(T record, E example)
           {
        int count = 0;
        try {
            count = getBaseMapper().updateByExampleSelective(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

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
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByExample(T record, E example){
        int count = 0;
        try {
            count = getBaseMapper().updateByExample(record, example);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * @描述：�?�过主键修改记录(遇到属�?�为null时，则跳过该属�??)
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(T record){
        int count = 0;
        try {
            count = getBaseMapper().updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * @描述：�?�过实体修改记录
     * @param record
     *            实体记录
     * @return 受影响行�?
     * @返回值：int
     * @异常说明�?
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(T record){
        int count = 0;
        try {
            count = getBaseMapper().updateByPrimaryKey(record);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return count;
    }

    @Override
    public T selectOneByExample(E example) throws DaoException {
        List<T> list = getBaseMapper().selectByExample(example);
        if (list == null || list.size() == 0) {
            return null;
        } else if (list.size() > 1) {
            throw new DaoException("返回的记录多�?1�?");
        }
        return list.get(0);
    }

}