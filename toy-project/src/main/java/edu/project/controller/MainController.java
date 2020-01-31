package edu.project.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Log4j
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String rootFunction() {
        logger.info("--------------SUCCESS-----------");
        return "SUCCESS";
    }
}
