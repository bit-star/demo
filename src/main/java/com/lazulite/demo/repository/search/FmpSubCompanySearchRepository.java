package com.lazulite.demo.repository.search;

import com.lazulite.demo.domain.FmpSubCompany;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * Spring Data Elasticsearch repository for the {@link FmpSubCompany} entity.
 */
public interface FmpSubCompanySearchRepository extends ElasticsearchRepository<FmpSubCompany, Long> {
}
