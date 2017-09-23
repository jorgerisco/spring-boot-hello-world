package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	protected Logger logger = LoggerFactory.getLogger(GreetingController.class.getName());
	
    private final AddressService service;
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
	
	@Autowired
    public GreetingController(AddressService service) {
        this.service = service;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) throws Exception {
		
		String serverAddress = service.getServerAddress();
		logger.info("Message logged at INFO level - GreetingController.greeting(" + name + ")");
		// logger.error("Message logged at ERROR level");
		// logger.warn("Message logged at WARN level");
		// logger.debug("Message logged at DEBUG level");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name),
							serverAddress);
    }
}