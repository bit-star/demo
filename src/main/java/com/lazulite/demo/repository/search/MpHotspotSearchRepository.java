package com.lazulite.demo.repository.search;

import com.lazulite.demo.domain.MpHotspot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link MpHotspot} entity.
 */
public interface MpHotspotSearchRepository extends ElasticsearchRepository<MpHotspot, Long> {
}
