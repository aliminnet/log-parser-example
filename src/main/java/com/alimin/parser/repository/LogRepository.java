package com.alimin.parser.repository;

import com.alimin.parser.entity.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.sql.Date;
import java.util.List;


public interface LogRepository extends PagingAndSortingRepository<Log, Long> {

    @Query(value = "SELECT  ip, count(ip) as count FROM log where date between ?1 AND ?2 group by ip", nativeQuery = true)
    List<Object[]> findRequestStatistics(Date start, Date end);

}
