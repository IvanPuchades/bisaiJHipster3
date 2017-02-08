package com.fujica.bisai.repository;

import com.fujica.bisai.domain.Torneo;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Torneo entity.
 */
@SuppressWarnings("unused")
public interface TorneoRepository extends JpaRepository<Torneo,Long> {

}
