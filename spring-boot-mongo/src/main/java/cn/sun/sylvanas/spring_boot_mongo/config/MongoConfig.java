package cn.sun.sylvanas.spring_boot_mongo.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.omg.CORBA.portable.UnknownException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by sylvanasp on 2016/8/7.
 */
@Configuration
@EnableMongoRepositories
public class MongoConfig {

    @Bean
    public MongoClient client() throws UnknownException {
        MongoClient mongoClient = new MongoClient(new ServerAddress("127.0.0.1", 27017));
        return mongoClient;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        String database = new MongoClientURI("mongodb://localhost/test").getDatabase();
        return new SimpleMongoDbFactory(client(),database);
    }

}
