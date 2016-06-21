package com.mkyong.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.mkyong.core.Env;
import com.mkyong.core.MyContextHolder;
import com.mkyong.customer.dao.CustomerDAO;
import com.mkyong.customer.model.Customer;

@Component
@Path ("/env")
public class EnvEndpoint {
	
	@GET
	@Path ("/dev")
	@Produces({MediaType.APPLICATION_JSON})
	public Response changeEnvDev() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"SpringBeans.xml");
		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		       
        MyContextHolder.setCustomerType(Env.DEV);
        Customer customer = customerDAO.findByCustomerId(2);
		return Response.ok().entity(customer).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path ("/prod")
	@Produces({MediaType.APPLICATION_JSON})
	public Response changeEnvProd() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"SpringBeans.xml");
		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		
		MyContextHolder.setCustomerType(Env.PROD);
        Customer customer = customerDAO.findByCustomerId(2);

		return Response.ok().entity(customer).type(MediaType.APPLICATION_JSON).build();
	}
}
