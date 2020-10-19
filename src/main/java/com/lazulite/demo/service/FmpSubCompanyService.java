package com.lazulite.demo.service;

import com.lazulite.demo.domain.FmpSubCompany;
import com.lazulite.demo.repository.FmpSubCompanyRepository;
import com.lazulite.demo.repository.search.FmpSubCompanySearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link FmpSubCompany}.
 */
@Service
@Transactional
public class FmpSubCompanyService {

    private final Logger log = LoggerFactory.getLogger(FmpSubCompanyService.class);

    private final FmpSubCompanyRepository fmpSubCompanyRepository;

    private final FmpSubCompanySearchRepository fmpSubCompanySearchRepository;

    public FmpSubCompanyService(FmpSubCompanyRepository fmpSubCompanyRepository, FmpSubCompanySearchRepository fmpSubCompanySearchRepository) {
        this.fmpSubCompanyRepository = fmpSubCompanyRepository;
        this.fmpSubCompanySearchRepository = fmpSubCompanySearchRepository;
    }

    /**
     * Save a fmpSubCompany.
     *
     * @param fmpSubCompany the entity to save.
     * @return the persisted entity.
     */
    public FmpSubCompany save(FmpSubCompany fmpSubCompany) {
        log.debug("Request to save FmpSubCompany : {}", fmpSubCompany);
        FmpSubCompany result = fmpSubCompanyRepository.save(fmpSubCompany);
        fmpSubCompanySearchRepository.save(result);
        return result;
    }

    /**
     * Get all the fmpSubCompanies.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FmpSubCompany> findAll() {
        log.debug("Request to get all FmpSubCompanies");
        return fmpSubCompanyRepository.findAll();
    }


    /**
     * Get one fmpSubCompany by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FmpSubCompany> findOne(Long id) {
        log.debug("Request to get FmpSubCompany : {}", id);
        return fmpSubCompanyRepository.findById(id);
    }

    /**
     * Delete the fmpSubCompany by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FmpSubCompany : {}", id);
        fmpSubCompanyRepository.deleteById(id);
        fmpSubCompanySearchRepository.deleteById(id);
    }

    /**
     * Search for the fmpSubCompany corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FmpSubCompany> search(String query) {
        log.debug("Request to search FmpSubCompanies for query {}", query);
        return StreamSupport
            .stream(fmpSubCompanySearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
