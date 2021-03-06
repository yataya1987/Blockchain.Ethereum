package com.projects.blockchain.ethereum.poc.node_connector.restful;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.projects.blockchain.ethereum.mongodb.MongoDBImplementation.CollectionType;
import com.projects.blockchain.ethereum.mongodb.MongoDBInterface;
import com.projects.blockchain.ethereum.poc.node_connector.util.ServletContextAttribute;
import com.projects.blockchain.ethereum.utility.microservices.EtherTransferEvent;
import com.projects.blockchain.ethereum.utility.microservices.SmartContractEvent;

/**
 * Standard JAX-RS implementation, where the MongoDB connection comes from the servlet context. It's been replaced by a Spring Boot implementation. 
 * */
@Path("/events")
public final class EventsRESTfulImpl {

	@Context 
	private ServletContext sc;
	
	@GET
	@Path("/getSmartContractEvents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SmartContractEvent> getSmartContractEvents() {
		return ((MongoDBInterface)sc.getAttribute(ServletContextAttribute.MongoDBConnection.toString())).getSmartContractEvents();
	}
	
	@GET
	@Path("/getEtherTransferEvents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EtherTransferEvent> getEtherTransferEvents() {
		return ((MongoDBInterface)sc.getAttribute(ServletContextAttribute.MongoDBConnection.toString())).getEtherTransferEvents();
	}
	
	@DELETE
	@Path("/deleteCollection/{collectionType}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public long deleteCollection(@PathParam("collectionType") String collectionType) {
		return ((MongoDBInterface)sc.getAttribute(ServletContextAttribute.MongoDBConnection.toString())).deleteCollection(CollectionType.valueOf(collectionType));
	}
}
