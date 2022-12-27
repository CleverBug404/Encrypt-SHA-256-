package com.springboot.app;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class SpringbootFirstAppApplication {

	// The main method is the entry point of the application. It is used to run the application.
public static void main(String[] args) {
	SpringApplication.run(SpringbootFirstAppApplication.class, args);
	}

	// This method creates a ServletWebServerFactory bean that can be used to create a web server,
	// in this case, an instance of Tomcat.
	@Bean
	public ServletWebServerFactory servletContainer() {
		// TomcatServletWebServerFactory is a concrete implementation of ServletWebServerFactory
		// that creates an instance of Tomcat as the web server.
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
		// The postProcessContext method is called after the Tomcat Context has been created,
		// but before it is used. It allows you to customize the Context.
		@Override
		protected void postProcessContext(Context context) {
		    // Create a new SecurityConstraint and set the user constraint to "CONFIDENTIAL"
		    SecurityConstraint securityConstraint = new SecurityConstraint();
		    securityConstraint.setUserConstraint("CONFIDENTIAL");
		    // Create a new SecurityCollection and add a pattern that matches all URLs
		    SecurityCollection collection = new SecurityCollection();
		    collection.addPattern("/*");
		    // Add the SecurityCollection to the SecurityConstraint
		    securityConstraint.addCollection(collection);
		    // Add the SecurityConstraint to the Context
		    context.addConstraint(securityConstraint);
		}
	    };
	    // Add a connector to redirect HTTP traffic to HTTPS
	    tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
	    return tomcat;
	}

	// This method creates a Connector that can be used to redirect HTTP traffic to HTTPS.
	private Connector httpToHttpsRedirectConnector() {
	  // Create a new Connector using the default HTTP protocol
	  Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
	  // Set the connector scheme to "http"
	  connector.setScheme("http");
	  // Set the connector port to 8082
	  connector.setPort(8082);
	  // Set secure to false
	  connector.setSecure(false);
	  // Set the redirect port to 8443
	  connector.setRedirectPort(8443);
	  return connector;
	}


}
