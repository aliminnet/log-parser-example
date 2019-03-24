package com.alimin.parser.service;


import com.alimin.parser.entity.Log;
import com.alimin.parser.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * LogLoaderService is responsible for the load log file, parse and save to the log table
 *
 * @author Ali Minnet
 * @version 2.0
 */

@Component
public class LogLoaderService {

    private static final Logger logger = LoggerFactory.getLogger(LogLoaderService.class);

    @Autowired
    private LogRepository logRepository;

    public void loadToDatabase(String path) {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        try {
            is = new FileInputStream(path);
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = "";

            while ((line = br.readLine()) != null) {
                lineToLog(line);
            }

        } catch (Exception e) {
            logger.error("Error on loading log file", e);
        }
    }

    private void lineToLog(String line) {
        String[] splittedLine = line.split("\\|");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-M hh:mm:ss.SSS"); //2017-01-01 00:00:11.763

        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(splittedLine[0]);
        } catch (ParseException e) {
            logger.error("Error on parsing date at log file ", e);
        }
        Date sqlDate = new java.sql.Date(utilDate.getTime());
        Log log = new Log(sqlDate, splittedLine[1], splittedLine[2], splittedLine[3], splittedLine[4]);
        logRepository.save(log);
    }
}


