package vttp2022.Workshop11;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
public class Workshop11Application {

	private static final Logger logger = LoggerFactory.getLogger(Workshop11Application.class);

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter(){
		CommonsRequestLoggingFilter requestLoggingFilter = new CommonsRequestLoggingFilter();
		requestLoggingFilter.setIncludeClientInfo(true);
		requestLoggingFilter.setIncludeHeaders(true);
		requestLoggingFilter.setIncludeQueryString(true);
		requestLoggingFilter.setIncludePayload(true);
		return requestLoggingFilter;
	}

	public static void main(String[] args) {
		logger.info("Starting Web App");
		SpringApplication app = new SpringApplication(Workshop11Application.class);
		String port = "3000";
		ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if (cliOpts.containsOption("port")){
			List<String> portlist = cliOpts.getOptionValues("port");
			logger.info("portlist > "+portlist.toString());
			if(portlist.size()>0){
				port = portlist.get(0);
				logger.info("port > "+ port);
			}
		}else{
			String envPort = System.getenv("PORT");
			if(null!=envPort){
				port =envPort;
				logger.info("port > "+ port);
			}
		}
		logger.info("port > "+ port);
		app.setDefaultProperties(Collections.singletonMap("server.port", port));
		app.run(args);
	}

}
