package com.lazulite.demo.web.rest;

import com.lazulite.demo.domain.MpHotspot;
import com.lazulite.demo.service.MpHotspotService;
import com.lazulite.demo.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.lazulite.demo.domain.MpHotspot}.
 */
@RestController
@RequestMapping("/api")
public class MpHotspotResource {

    private final Logger log = LoggerFactory.getLogger(MpHotspotResource.class);

    private static final String ENTITY_NAME = "mpHotspot";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MpHotspotService mpHotspotService;

    public MpHotspotResource(MpHotspotService mpHotspotService) {
        this.mpHotspotService = mpHotspotService;
    }

    /**
     * {@code POST  /mp-hotspots} : Create a new mpHotspot.
     *
     * @param mpHotspot the mpHotspot to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mpHotspot, or with status {@code 400 (Bad Request)} if the mpHotspot has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mp-hotspots")
    public ResponseEntity<MpHotspot> createMpHotspot(@RequestBody MpHotspot mpHotspot) throws URISyntaxException {
        log.debug("REST request to save MpHotspot : {}", mpHotspot);
        if (mpHotspot.getId() != null) {
            throw new BadRequestAlertException("A new mpHotspot cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MpHotspot result = mpHotspotService.save(mpHotspot);
        return ResponseEntity.created(new URI("/api/mp-hotspots/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mp-hotspots} : Updates an existing mpHotspot.
     *
     * @param mpHotspot the mpHotspot to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mpHotspot,
     * or with status {@code 400 (Bad Request)} if the mpHotspot is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mpHotspot couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mp-hotspots")
    public ResponseEntity<MpHotspot> updateMpHotspot(@RequestBody MpHotspot mpHotspot) throws URISyntaxException {
        log.debug("REST request to update MpHotspot : {}", mpHotspot);
        if (mpHotspot.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MpHotspot result = mpHotspotService.save(mpHotspot);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mpHotspot.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mp-hotspots} : get all the mpHotspots.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mpHotspots in body.
     */
    @GetMapping("/mp-hotspots")
    public List<MpHotspot> getAllMpHotspots() {
        log.debug("REST request to get all MpHotspots");
        return mpHotspotService.findAll();
    }

    /**
     * {@code GET  /mp-hotspots/:id} : get the "id" mpHotspot.
     *
     * @param id the id of the mpHotspot to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mpHotspot, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mp-hotspots/{id}")
    public ResponseEntity<MpHotspot> getMpHotspot(@PathVariable Long id) {
        log.debug("REST request to get MpHotspot : {}", id);
        Optional<MpHotspot> mpHotspot = mpHotspotService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mpHotspot);
    }

    /**
     * {@code DELETE  /mp-hotspots/:id} : delete the "id" mpHotspot.
     *
     * @param id the id of the mpHotspot to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mp-hotspots/{id}")
    public ResponseEntity<Void> deleteMpHotspot(@PathVariable Long id) {
        log.debug("REST request to delete MpHotspot : {}", id);
        mpHotspotService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/mp-hotspots?query=:query} : search for the mpHotspot corresponding
     * to the query.
     *
     * @param query the query of the mpHotspot search.
     * @return the result of the search.
     */
    @GetMapping("/_search/mp-hotspots")
    public List<MpHotspot> searchMpHotspots(@RequestParam String query) {
        log.debug("REST request to search MpHotspots for query {}", query);
        return mpHotspotService.search(query);
    }
}
