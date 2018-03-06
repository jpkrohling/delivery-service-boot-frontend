package eu.javaland.tracing.frontend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This endpoint serves as a REST entry point to this application. Accepts a simple request and will execute the
 * corresponding command.
 */
@RestController
public class RequestDroneEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(RequestDroneEndpoint.class);

    @Autowired
    private RequestDroneCommand command;

    @RequestMapping("/")
    public String getDrone() {
        logger.debug("Requesting drone");
        command.execute();
        logger.debug("Requested drone");
        return "Drone has been requested";
    }
}
