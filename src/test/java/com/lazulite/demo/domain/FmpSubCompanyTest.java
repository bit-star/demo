package com.lazulite.demo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.demo.web.rest.TestUtil;

public class FmpSubCompanyTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FmpSubCompany.class);
        FmpSubCompany fmpSubCompany1 = new FmpSubCompany();
        fmpSubCompany1.setId(1L);
        FmpSubCompany fmpSubCompany2 = new FmpSubCompany();
        fmpSubCompany2.setId(fmpSubCompany1.getId());
        assertThat(fmpSubCompany1).isEqualTo(fmpSubCompany2);
        fmpSubCompany2.setId(2L);
        assertThat(fmpSubCompany1).isNotEqualTo(fmpSubCompany2);
        fmpSubCompany1.setId(null);
        assertThat(fmpSubCompany1).isNotEqualTo(fmpSubCompany2);
    }
}
