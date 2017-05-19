package ee.goodsandservices.alien.repository;

import ee.goodsandservices.alien.domain.Host;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Host entity.
 */
@SuppressWarnings("unused")
public interface HostRepository extends JpaRepository<Host,Long> {

}
