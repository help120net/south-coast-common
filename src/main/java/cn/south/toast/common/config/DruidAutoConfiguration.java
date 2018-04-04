package cn.south.toast.common.config;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
/**
 * 
 * @author huangbh
 *  date of 2018年4月4日
 */
@Configuration
@EnableConfigurationProperties({DruidProperties.class})
public class DruidAutoConfiguration {
	private static Logger logger = LoggerFactory.getLogger(DruidAutoConfiguration.class);
	
	@Autowired
	private DruidProperties druidProperties;
	
	//从库配置信息
	@Autowired
	private SlaveDruidProperties slaveDruidProperties;
	
	@Bean
	public WallFilter wallFilter(){
	    WallFilter wallFilter=new WallFilter();
	    wallFilter.setConfig(wallConfig());
	    return  wallFilter;
	}
	@Bean
	public WallConfig wallConfig(){
	    WallConfig config =new WallConfig();
	    config.setMultiStatementAllow(true);//允许一次执行多条语句
	    config.setNoneBaseStatementAllow(true);//允许非基本语句的其他语句
	    return config;
	}
	
	@Bean
	public DataSource dataSource(){
		DruidDataSource datasource = new DruidDataSource();  
        datasource.setUrl(druidProperties.getUrl());  
        datasource.setUsername(druidProperties.getUsername());  
        datasource.setPassword(druidProperties.getPassword());  
        datasource.setDriverClassName(druidProperties.getDriverClassName());  
          
        datasource.setInitialSize(druidProperties.getInitialSize());  
        datasource.setMinIdle(druidProperties.getMinIdle());  
        datasource.setMaxActive(druidProperties.getMaxActive());  
        datasource.setMaxWait(druidProperties.getMaxWait());  
        datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());  
        datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());  
        datasource.setValidationQuery(druidProperties.getValidationQuery());  
        datasource.setTestWhileIdle(druidProperties.getTestWhileIdle());  
        datasource.setTestOnBorrow(druidProperties.getTestOnBorrow());  
        datasource.setTestOnReturn(druidProperties.getTestOnReturn());  
        datasource.setPoolPreparedStatements(druidProperties.getPoolPreparedStatements());  
        datasource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());  
        try {  
            datasource.setFilters(druidProperties.getFilters());
            
            List<Filter> filterList=new ArrayList<Filter>();
            filterList.add(wallFilter());
            datasource.setProxyFilters(filterList);
            
        } catch (SQLException e) {  
            logger.error("druid configuration initialization filter", e);  
        }  
        datasource.setConnectionProperties(druidProperties.getConnectionProperties());
        logger.info("datasource:", datasource);
        return datasource;  
	}
	
	/**
	 * 从库配置
	 * @return
	 * @throws SQLException
	 */
	@Bean("readDataSources")
	public List<DataSource> readDataSources() throws SQLException {
		List<DataSource> list = new ArrayList<DataSource>();
		
		if(slaveDruidProperties != null 
				&& slaveDruidProperties.getUrl() != null 
				&& slaveDruidProperties.getUrl().length > 0){
			DruidDataSource datasource = new DruidDataSource();
			datasource.setDriverClassName(slaveDruidProperties.getDriverClassName());  
	        datasource.setInitialSize(slaveDruidProperties.getInitialSize());  
	        datasource.setMinIdle(slaveDruidProperties.getMinIdle());  
	        datasource.setMaxActive(slaveDruidProperties.getMaxActive());  
	        datasource.setMaxWait(slaveDruidProperties.getMaxWait());  
	        datasource.setTimeBetweenEvictionRunsMillis(slaveDruidProperties.getTimeBetweenEvictionRunsMillis());  
	        datasource.setMinEvictableIdleTimeMillis(slaveDruidProperties.getMinEvictableIdleTimeMillis());  
	        datasource.setValidationQuery(slaveDruidProperties.getValidationQuery());  
	        datasource.setTestWhileIdle(slaveDruidProperties.getTestWhileIdle());  
	        datasource.setTestOnBorrow(slaveDruidProperties.getTestOnBorrow());  
	        datasource.setTestOnReturn(slaveDruidProperties.getTestOnReturn());  
	        datasource.setPoolPreparedStatements(slaveDruidProperties.getPoolPreparedStatements());  
	        datasource.setMaxPoolPreparedStatementPerConnectionSize(slaveDruidProperties.getMaxPoolPreparedStatementPerConnectionSize());
	        datasource.setConnectionProperties(slaveDruidProperties.getConnectionProperties());
	        datasource.setFilters(slaveDruidProperties.getFilters());
            List<Filter> filterList=new ArrayList<Filter>();
            filterList.add(wallFilter());
            datasource.setProxyFilters(filterList);
            
            datasource.setUrl(slaveDruidProperties.getUrl()[0]);  
            datasource.setUsername(slaveDruidProperties.getUsername()[0]);  
            datasource.setPassword(slaveDruidProperties.getPassword()[0]);  
            list.add(datasource);
            
            for(int i=1,size=slaveDruidProperties.getUrl().length;i<size;i++){
            	DruidDataSource dataSourceClone = datasource.cloneDruidDataSource();
            	dataSourceClone.setUrl(slaveDruidProperties.getUrl()[i]);  
            	dataSourceClone.setUsername(slaveDruidProperties.getUsername()[i]);  
            	dataSourceClone.setPassword(slaveDruidProperties.getPassword()[i]);
            	list.add(dataSourceClone);
            }
            
		}
		
		return list;
	}
	
	/**
     * 注册ServletRegistrationBean
     * @return
     */
    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        /** 初始化参数配置，initParams**/
        //白名单
        //bean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        //bean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        bean.addInitParameter("loginUsername", "admin");
        bean.addInitParameter("loginPassword", "qh123456");
        //是否能够重置数据.
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    /**
     * 注册FilterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico, /druid/*");
        return bean;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource){
    	
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    	
    	return jdbcTemplate;
    }
    
}
