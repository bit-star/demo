package com.lazulite.demo.service;

import com.lazulite.demo.domain.MpHotspot;
import com.lazulite.demo.repository.MpHotspotRepository;
import com.lazulite.demo.repository.search.MpHotspotSearchRepository;
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
 * Service Implementation for managing {@link MpHotspot}.
 */
@Service
@Transactional
public class MpHotspotService {

    private final Logger log = LoggerFactory.getLogger(MpHotspotService.class);

    private final MpHotspotRepository mpHotspotRepository;

    private final MpHotspotSearchRepository mpHotspotSearchRepository;

    public MpHotspotService(MpHotspotRepository mpHotspotRepository, MpHotspotSearchRepository mpHotspotSearchRepository) {
        this.mpHotspotRepository = mpHotspotRepository;
        this.mpHotspotSearchRepository = mpHotspotSearchRepository;
    }

    /**
     * Save a mpHotspot.
     *
     * @param mpHotspot the entity to save.
     * @return the persisted entity.
     */
    public MpHotspot save(MpHotspot mpHotspot) {
        log.debug("Request to save MpHotspot : {}", mpHotspot);
        MpHotspot result = mpHotspotRepository.save(mpHotspot);
        mpHotspotSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the mpHotspots.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MpHotspot> findAll() {
        log.debug("Request to get all MpHotspots");
        return mpHotspotRepository.findAll();
    }


    /**
     * Get one mpHotspot by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MpHotspot> findOne(Long id) {
        log.debug("Request to get MpHotspot : {}", id);
        return mpHotspotRepository.findById(id);
    }

    /**
     * Delete the mpHotspot by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MpHotspot : {}", id);
        mpHotspotRepository.deleteById(id);
        mpHotspotSearchRepository.deleteById(id);
    }

    /**
     * Search for the mpHotspot corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MpHotspot> search(String query) {
        log.debug("Request to search MpHotspots for query {}", query);
        return StreamSupport
            .stream(mpHotspotSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
