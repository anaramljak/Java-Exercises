package com.lab6.lab6.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lab6.lab6.Models.Measurements;


@Repository
public interface MeasurementRepository extends JpaRepository<Measurements, String>{

	@Query("SELECT SUM(m.result) FROM Measurements m WHERE m.year = :year")
	public Double getAllYearMeasurements(@Param("year") String year);
	
	@Query("SELECT SUM(m.result) FROM Measurements m WHERE m.year = :year AND m.month = :month")
	public Double getMeasurementsByMonths(@Param("year") String year, @Param("month") String month);
	
	@Query("SELECT SUM(m.result) FROM Measurements m  WHERE m.year = :year GROUP BY month")
	public List<String> getYearMeasurementsByMonths(@Param("year") String year);
	


}
