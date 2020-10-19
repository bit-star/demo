package com.lazulite.demo.repository;

import com.lazulite.demo.domain.MpHotspot;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MpHotspot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MpHotspotRepository extends JpaRepository<MpHotspot, Long> {
}
