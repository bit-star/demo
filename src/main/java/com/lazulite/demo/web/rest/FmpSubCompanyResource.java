package com.lazulite.demo.web.rest;

import com.lazulite.demo.domain.FmpSubCompany;
import com.lazulite.demo.service.FmpSubCompanyService;
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
 * REST controller for managing {@link com.lazulite.demo.domain.FmpSubCompany}.
 */
@RestController
@RequestMapping("/api")
public class FmpSubCompanyResource {

    private final Logger log = LoggerFactory.getLogger(FmpSubCompanyResource.class);

    private static final String ENTITY_NAME = "fmpSubCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FmpSubCompanyService fmpSubCompanyService;

    public FmpSubCompanyResource(FmpSubCompanyService fmpSubCompanyService) {
        this.fmpSubCompanyService = fmpSubCompanyService;
    }

    /**
     * {@code POST  /fmp-sub-companies} : Create a new fmpSubCompany.
     *
     * @param fmpSubCompany the fmpSubCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fmpSubCompany, or with status {@code 400 (Bad Request)} if the fmpSubCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fmp-sub-companies")
    public ResponseEntity<FmpSubCompany> createFmpSubCompany(@RequestBody FmpSubCompany fmpSubCompany) throws URISyntaxException {
        log.debug("REST request to save FmpSubCompany : {}", fmpSubCompany);
        if (fmpSubCompany.getId() != null) {
            throw new BadRequestAlertException("A new fmpSubCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FmpSubCompany result = fmpSubCompanyService.save(fmpSubCompany);
        return ResponseEntity.created(new URI("/api/fmp-sub-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fmp-sub-companies} : Updates an existing fmpSubCompany.
     *
     * @param fmpSubCompany the fmpSubCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fmpSubCompany,
     * or with status {@code 400 (Bad Request)} if the fmpSubCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fmpSubCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fmp-sub-companies")
    public ResponseEntity<FmpSubCompany> updateFmpSubCompany(@RequestBody FmpSubCompany fmpSubCompany) throws URISyntaxException {
        log.debug("REST request to update FmpSubCompany : {}", fmpSubCompany);
        if (fmpSubCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FmpSubCompany result = fmpSubCompanyService.save(fmpSubCompany);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fmpSubCompany.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fmp-sub-companies} : get all the fmpSubCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fmpSubCompanies in body.
     */
    @GetMapping("/fmp-sub-companies")
    public List<FmpSubCompany> getAllFmpSubCompanies() {
        log.debug("REST request to get all FmpSubCompanies");
        return fmpSubCompanyService.findAll();
    }

    /**
     * {@code GET  /fmp-sub-companies/:id} : get the "id" fmpSubCompany.
     *
     * @param id the id of the fmpSubCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fmpSubCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fmp-sub-companies/{id}")
    public ResponseEntity<FmpSubCompany> getFmpSubCompany(@PathVariable Long id) {
        log.debug("REST request to get FmpSubCompany : {}", id);
        Optional<FmpSubCompany> fmpSubCompany = fmpSubCompanyService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fmpSubCompany);
    }

    /**
     * {@code DELETE  /fmp-sub-companies/:id} : delete the "id" fmpSubCompany.
     *
     * @param id the id of the fmpSubCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fmp-sub-companies/{id}")
    public ResponseEntity<Void> deleteFmpSubCompany(@PathVariable Long id) {
        log.debug("REST request to delete FmpSubCompany : {}", id);
        fmpSubCompanyService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/fmp-sub-companies?query=:query} : search for the fmpSubCompany corresponding
     * to the query.
     *
     * @param query the query of the fmpSubCompany search.
     * @return the result of the search.
     */
    @GetMapping("/_search/fmp-sub-companies")
    public List<FmpSubCompany> searchFmpSubCompanies(@RequestParam String query) {
        log.debug("REST request to search FmpSubCompanies for query {}", query);
        return fmpSubCompanyService.search(query);
    }
}
