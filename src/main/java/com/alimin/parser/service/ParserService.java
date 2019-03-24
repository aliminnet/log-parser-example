package com.alimin.parser.service;

import com.alimin.parser.entity.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * ParserService is the entry point of Parser Project
 *
 * java -jar parser.jar --accesslog=c://access2.log --startDate=2017-01-01.00:00:00 --duration=daily --threshold=100
 *
 * @author Ali Minnet
 * @version 2.0
 */
@Component
public class ParserService {

    private static final Logger logger = LoggerFactory.getLogger(ParserService.class);
    final long HOUR = 3600;
    final long DAY = 86400000;

    @Autowired
    Parameter parameter;

    @Autowired
    LogLoaderService logLoaderService;

    @Autowired
    QueryService queryService;

    @PostConstruct
    public void init() {
        if (null != parameter.getPath()) {
            System.out.println("Loading logs into Database...");
            logLoaderService.loadToDatabase(parameter.getPath());
            System.out.println("Log Loading Finished...");
        }

        queryService.executeQuery();
    }

}
