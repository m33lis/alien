package ee.goodsandservices.alien.service;

import ee.goodsandservices.alien.domain.Host;
import ee.goodsandservices.alien.repository.HostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Implementation for managing Host.
 */
@Service
@Transactional
public class HostService {

    private final Logger log = LoggerFactory.getLogger(HostService.class);
    
    private final HostRepository hostRepository;

    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    /**
     * Save a host.
     *
     * @param host the entity to save
     * @return the persisted entity
     */
    public Host save(Host host) {
        log.debug("Request to save Host : {}", host);
        Host result = hostRepository.save(host);
        return result;
    }

    /**
     *  Get all the hosts.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Host> findAll() {
        log.debug("Request to get all Hosts");
        List<Host> result = hostRepository.findAll();

        return result;
    }

    /**
     *  Get one host by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Host findOne(Long id) {
        log.debug("Request to get Host : {}", id);
        Host host = hostRepository.findOne(id);
        return host;
    }

    /**
     *  Delete the  host by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Host : {}", id);
        hostRepository.delete(id);
    }
}
