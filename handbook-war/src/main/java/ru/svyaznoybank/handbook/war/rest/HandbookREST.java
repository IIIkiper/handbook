package ru.svyaznoybank.handbook.war.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class HandbookREST extends /*Application*/  ResourceConfig {
		
	public HandbookREST() {
		packages("ru.svyaznoybank.handbook.war.rest");
		register(JacksonFeature.class);
	}
	
}