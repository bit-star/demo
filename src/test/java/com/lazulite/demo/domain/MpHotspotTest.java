package com.lazulite.demo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.lazulite.demo.web.rest.TestUtil;

public class MpHotspotTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MpHotspot.class);
        MpHotspot mpHotspot1 = new MpHotspot();
        mpHotspot1.setId(1L);
        MpHotspot mpHotspot2 = new MpHotspot();
        mpHotspot2.setId(mpHotspot1.getId());
        assertThat(mpHotspot1).isEqualTo(mpHotspot2);
        mpHotspot2.setId(2L);
        assertThat(mpHotspot1).isNotEqualTo(mpHotspot2);
        mpHotspot1.setId(null);
        assertThat(mpHotspot1).isNotEqualTo(mpHotspot2);
    }
}
