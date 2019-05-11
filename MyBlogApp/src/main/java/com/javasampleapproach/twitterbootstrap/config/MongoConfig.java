package com.javasampleapproach.twitterbootstrap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.javasampleapproach.twitterbootstrap.dao.RepositoryPackage;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
public class MongoConfig extends AbstractMongoConfiguration {

	@Value("${mongo.db.name}")
	private String mongoDBName;

	@Value("${mongo.host}")
	private String mongoHost;

	@Override
	protected String getDatabaseName() {
		return mongoDBName;
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.javasampleapproach.twitterbootstrap.model";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(mongoHost);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory());
	}

}

