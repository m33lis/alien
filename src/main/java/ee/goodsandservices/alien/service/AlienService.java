package ee.goodsandservices.alien.service;

import ee.goodsandservices.alien.domain.Alien;
import ee.goodsandservices.alien.repository.AlienRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Alien.
 */
@Service
@Transactional
public class AlienService {

    private final Logger log = LoggerFactory.getLogger(AlienService.class);
    
    private final AlienRepository alienRepository;

    public AlienService(AlienRepository alienRepository) {
        this.alienRepository = alienRepository;
    }

    /**
     * Save a alien.
     *
     * @param alien the entity to save
     * @return the persisted entity
     */
    public Alien save(Alien alien) {
        log.debug("Request to save Alien : {}", alien);
        Alien result = alienRepository.save(alien);
        return result;
    }

    /**
     *  Get all the aliens.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Alien> findAll() {
        log.debug("Request to get all Aliens");
        List<Alien> result = alienRepository.findAll();

        return result;
    }

    /**
     *  Get one alien by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Alien findOne(Long id) {
        log.debug("Request to get Alien : {}", id);
        Alien alien = alienRepository.findOne(id);
        return alien;
    }

    /**
     *  Delete the  alien by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Alien : {}", id);
        alienRepository.delete(id);
    }
}
