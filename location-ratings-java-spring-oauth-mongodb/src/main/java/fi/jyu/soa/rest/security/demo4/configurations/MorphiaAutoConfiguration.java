package fi.jyu.soa.rest.security.demo4.configurations;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
public class MorphiaAutoConfiguration {

    @Autowired
    private MongoClientURI mongoClientURI;

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoProperties mongoProperties;

    @Bean
    public Datastore datastore() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("fi.jyu.soa.rest.security.demo4.models");

        Datastore datastore = morphia.createDatastore(mongoClient, mongoProperties.getDatabase());
        datastore.ensureIndexes();

        return datastore;
    }

    @Bean
    public MongoClientURI mongoClientURI() {
        MongoClientURI uri = new MongoClientURI("mongodb://demo4:Rt43wokraMiyInHu@pop-shard-00-00-nnj8i.gcp.mongodb.net:27017,pop-shard-00-01-nnj8i.gcp.mongodb.net:27017,pop-shard-00-02-nnj8i.gcp.mongodb.net:27017/test?ssl=true&replicaSet=pop-shard-0&authSource=admin&retryWrites=true");
        return uri;
    }

    @Bean
    public MongoClient mongoClient() {
        MongoClient client = new MongoClient(mongoClientURI);
        return client;
    }
}


