package com.george.server.calendar.repository;

import com.george.server.calendar.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    @Transactional
    @Modifying
    @Query("update Calendar c set c.name = ?1 where c.id = ?2")
    int updateNameById(String name, Long id);

    List<Calendar> findByUsername(String username);

    Optional<Calendar> findByIdAndUsername(Long id, String username);


}