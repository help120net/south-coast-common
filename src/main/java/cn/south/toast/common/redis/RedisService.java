package cn.south.toast.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 * @param <K>
 * @param <V>
 */
public interface RedisService<K, V> {

	public boolean set(K key, V value);  
    
    public V get(K key, Class<V> cls);  
      
    public boolean expire(K key,long expire);  
      
    public boolean setList(K key ,List<V> list);  
      
    public List<V> getList(K key, int start, int end);  
      
    public long lpush(K key, V value);  
      
    public long rpush(K key, V value);  
      
    public String lpop(K key);

    public boolean remove(K key);

    public void batchRemove(K pattern);
    
    public List<V> multiGet(List<K> keyList, Class<V> clazz);
    
    public Map<K, V> multiGet(Set<K> keySet, Class<V> clazz);
}
