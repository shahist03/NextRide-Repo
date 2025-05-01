package com.app.app.repository.evalutionRepo;

import com.app.app.entity.evalution.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {

    /*@Query("select ag from Area a JOIN a.agent ag" +
            "where ag.id=:id")
    List<Agents> searchAgent(
            @Param("id") long id
    );*/

   List<Area> findByPinCode(long pinCode);
}