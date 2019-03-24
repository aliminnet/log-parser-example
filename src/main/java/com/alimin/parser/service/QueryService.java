package com.alimin.parser.service;

import com.alimin.parser.entity.BlockedData;
import com.alimin.parser.entity.Parameter;
import com.alimin.parser.repository.BlockedDataRepository;
import com.alimin.parser.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * QueryService will find any IPs that made more than threshold starting from startDate in given period
 *
 * @author Ali Minnet
 * @version 2.0
 */

@Component
public class QueryService {

    private static final Logger logger = LoggerFactory.getLogger(QueryService.class);

    final long HOUR = 3600;
    final long DAY = 86400000;

    @Autowired
    Parameter parameter;

    @Autowired
    LogRepository logRepository;

    @Autowired
    BlockedDataRepository blockedDataRepository;

    public void executeQuery() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-M.hh:mm:ss");

        java.util.Date utilDate = null;
        try {
            utilDate = sdf.parse(parameter.getStartDate());
        } catch (ParseException e) {
            logger.error("Error on parsing date at log file ", e);
        }

        Date startDate = new java.sql.Date(utilDate.getTime());
        Date endDate;
        if (parameter.getDuration().equals("hourly")) {
            endDate = new java.sql.Date(utilDate.getTime() + HOUR * 1000);
        } else if (parameter.getDuration().equals("daily")) {
            endDate = new java.sql.Date(utilDate.getTime() + DAY * 1000);
        } else {
            endDate = new java.sql.Date(System.currentTimeMillis()); //Default is currrent time
        }

        List<Object[]> statistics = logRepository.findRequestStatistics(startDate, endDate);

        System.out.println("########################################");
        System.out.println("Blocked IPs");
        statistics.stream().forEach(statistic -> {
            int reqCount = ((BigInteger) statistic[1]).intValue();
            if (Integer.parseInt(parameter.getThreshold()) < reqCount) {
                String reason = "More than " + parameter.getThreshold() + " requests after " + parameter.getStartDate() + " in " + parameter.getDuration() + " basis";
                String ip = statistic[0].toString();

                BlockedData blockedData = new BlockedData(ip, reqCount, reason);
                System.out.println(blockedData);
                blockedDataRepository.save(blockedData);
            }
        });
        System.out.println("########################################");
    }
}

