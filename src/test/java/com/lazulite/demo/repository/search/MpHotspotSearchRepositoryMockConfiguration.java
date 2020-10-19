package com.lazulite.demo.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link MpHotspotSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class MpHotspotSearchRepositoryMockConfiguration {

    @MockBean
    private MpHotspotSearchRepository mockMpHotspotSearchRepository;

}
