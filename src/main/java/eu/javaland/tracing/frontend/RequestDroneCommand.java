package eu.javaland.tracing.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Makes a remote call to the backend, requesting the appropriate drone
 */
@Component
public class RequestDroneCommand {
    private static final Logger logger = LoggerFactory.getLogger(RequestDroneCommand.class);

    @Autowired
    private RestTemplate restTemplate;

    public void execute() {
        logger.debug("Executing");
        String response = restTemplate.getForEntity("/backend/request", String.class).getBody();
        logger.debug("Executed. Response: {}", response);
    }
}
