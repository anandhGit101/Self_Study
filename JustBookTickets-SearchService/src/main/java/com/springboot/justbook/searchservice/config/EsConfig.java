/**
 * 
 */

package com.springboot.justbook.searchservice.config;

import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

/**
 * @author M1006601
 *
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.springboot.justbook.searchservice.repository")
public class EsConfig {

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String EsClusterNodes;

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String EsClusterName;

	@Bean
	public Client client() throws Exception {

		Settings esSettings = Settings.builder()
				.put("cluster.name", EsClusterName).build();

		String server = EsClusterNodes.split(":")[0];
		Integer port = Integer.parseInt(EsClusterNodes.split(":")[1]);
		// https://www.elastic.co/guide/en/elasticsearch/guide/current/
		// _transport_client_versus_node_client.html
		@SuppressWarnings("resource")
		TransportClient client = new PreBuiltTransportClient(esSettings)
	            .addTransportAddress(new TransportAddress (InetAddress.getByName(server), port));
		
		return client;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
	
	  
	public static class CustomEntityMapper implements EntityMapper {

		private ObjectMapper objectMapper;
		
		public CustomEntityMapper() {
			objectMapper = new ObjectMapper();
			objectMapper.setDefaultPropertyInclusion(Include.NON_NULL);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.findAndRegisterModules();
		}

		@Override
		public String mapToString(Object object) throws IOException {
			return objectMapper.writeValueAsString(object);
		}

		@Override
		public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
			return objectMapper.readValue(source, clazz);
		}
	}
	 
	}
