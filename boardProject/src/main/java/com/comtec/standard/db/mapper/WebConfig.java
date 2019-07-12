package com.comtec.standard.db.mapper;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
 
// Configuration -> 해당 클래스는 Database에 대한 접속정보를 나타내고 일반 Bean이 아닌 Configuration으로 설정되어 있어야 한다. (실제로는 그 하위에 Bean을 두개 더 생성한다.)
@Configuration
// MapperScan -> 최신 Spring boot에서는 dao를 dao라고 명시하지 않고 mapper라는 이름으로 별도 정의하고 있다. 따라서 어떤 패키지에서
// 					mapper들을 scan할 것인가를 최상위에 annotationo으로 정의하여 주고 있다. 다중 Database에 접근해야 하는 경우, 이 클래스를 두개를 만들어 두고
// 					scan할 대상 mapper 들을 패키지 구조상에서 분리해 두는 것도 하나의 방법이라고 할 수 있다.
@MapperScan(basePackages="com.comtec.standard.db.mapper")
//EnableTransactionManagement -> TransactionManager를 적용할 것인지에 대해 설정하는 annotation
@EnableTransactionManagement
public class WebConfig {
 
	//@Bean SqlSessionFactory -> DataSource를 parameter로 받아, sqlSessionFactory를 생성하는 Bean이다. 아래 있는 SqlSessionTemplate Bean 생성시
	//							인자로 넘겨주기 위해서 사용된다. 즉 여기서 만들어진 기본정보, 설정값 등을 이용해서 SqlSessionTemplate를 만들게 되는 것이다.
	
	//.setMapperLocations() -> 실제 Query 문이 존재하는 xml 파일들의 위치를 지정해 준다. 여기서는 mybatis/mapper 폴더 하위에 있는 모든 xml을 명시하였다.
	//							이 경로는 실제로는 src/main/resource 하위에 존재한다. 이 폴더 구조자체가 생성되어 있지 않거나, 그 폴더에 mapper.xml이 하나도 없으면
	//							, 혹은 그 xml의 문법에 오류가 있으면 초기 구동 시점에 에러가 발생한다. 즉, 초기에 mapper들의 위치정보만을 짖어하는 것이 아니라 해당 mapper.xml
	//							들에 오류가 있는지 문법 체크까지 모두 한 뒤에 Factory가 생성되게 되는 것이므로, 이 부분을 다룰 때에는 중간중간 실행하여 오류가 발생하지는 않는지 꼭 확인하면서 지나가야 한다.
	
	//@Bean SqlSessionTemplate -> 실제 DB접속에 이용되는 SqlSessionTemplate를 생성하여 반환하는 Bean이다. 실제 코드상에서 이용된다고 보면 된다.
	
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        return sessionFactory.getObject();
    }
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
      final SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
      return sqlSessionTemplate;
    }
}

