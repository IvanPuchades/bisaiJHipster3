package com.fujica.bisai.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.fujica.bisai.domain.Torneo;

import com.fujica.bisai.repository.TorneoRepository;
import com.fujica.bisai.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Torneo.
 */
@RestController
@RequestMapping("/api")
public class TorneoResource {

    private final Logger log = LoggerFactory.getLogger(TorneoResource.class);
        
    @Inject
    private TorneoRepository torneoRepository;

    /**
     * POST  /torneos : Create a new torneo.
     *
     * @param torneo the torneo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new torneo, or with status 400 (Bad Request) if the torneo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/torneos")
    @Timed
    public ResponseEntity<Torneo> createTorneo(@Valid @RequestBody Torneo torneo) throws URISyntaxException {
        log.debug("REST request to save Torneo : {}", torneo);
        if (torneo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("torneo", "idexists", "A new torneo cannot already have an ID")).body(null);
        }
        Torneo result = torneoRepository.save(torneo);
        return ResponseEntity.created(new URI("/api/torneos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("torneo", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /torneos : Updates an existing torneo.
     *
     * @param torneo the torneo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated torneo,
     * or with status 400 (Bad Request) if the torneo is not valid,
     * or with status 500 (Internal Server Error) if the torneo couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/torneos")
    @Timed
    public ResponseEntity<Torneo> updateTorneo(@Valid @RequestBody Torneo torneo) throws URISyntaxException {
        log.debug("REST request to update Torneo : {}", torneo);
        if (torneo.getId() == null) {
            return createTorneo(torneo);
        }
        Torneo result = torneoRepository.save(torneo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("torneo", torneo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /torneos : get all the torneos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of torneos in body
     */
    @GetMapping("/torneos")
    @Timed
    public List<Torneo> getAllTorneos() {
        log.debug("REST request to get all Torneos");
        List<Torneo> torneos = torneoRepository.findAll();
        return torneos;
    }

    /**
     * GET  /torneos/:id : get the "id" torneo.
     *
     * @param id the id of the torneo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the torneo, or with status 404 (Not Found)
     */
    @GetMapping("/torneos/{id}")
    @Timed
    public ResponseEntity<Torneo> getTorneo(@PathVariable Long id) {
        log.debug("REST request to get Torneo : {}", id);
        Torneo torneo = torneoRepository.findOne(id);
        return Optional.ofNullable(torneo)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /torneos/:id : delete the "id" torneo.
     *
     * @param id the id of the torneo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/torneos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTorneo(@PathVariable Long id) {
        log.debug("REST request to delete Torneo : {}", id);
        torneoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("torneo", id.toString())).build();
    }

}
