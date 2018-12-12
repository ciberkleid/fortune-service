package io.pivotal.fortune;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {

    // This query is not efficient, but ok for such s small DB
    @Query(value="SELECT * FROM fortune ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Fortune findRandomFortune();

}