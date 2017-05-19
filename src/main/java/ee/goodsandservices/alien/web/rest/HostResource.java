package ee.goodsandservices.alien.web.rest;

import com.codahale.metrics.annotation.Timed;
import ee.goodsandservices.alien.domain.Host;
import ee.goodsandservices.alien.service.HostService;
import ee.goodsandservices.alien.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Host.
 */
@RestController
@RequestMapping("/api")
public class HostResource {

    private final Logger log = LoggerFactory.getLogger(HostResource.class);

    private static final String ENTITY_NAME = "host";
        
    private final HostService hostService;

    public HostResource(HostService hostService) {
        this.hostService = hostService;
    }

    /**
     * POST  /hosts : Create a new host.
     *
     * @param host the host to create
     * @return the ResponseEntity with status 201 (Created) and with body the new host, or with status 400 (Bad Request) if the host has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hosts")
    @Timed
    public ResponseEntity<Host> createHost(@Valid @RequestBody Host host) throws URISyntaxException {
        log.debug("REST request to save Host : {}", host);
        if (host.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new host cannot already have an ID")).body(null);
        }
        Host result = hostService.save(host);
        return ResponseEntity.created(new URI("/api/hosts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hosts : Updates an existing host.
     *
     * @param host the host to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated host,
     * or with status 400 (Bad Request) if the host is not valid,
     * or with status 500 (Internal Server Error) if the host couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hosts")
    @Timed
    public ResponseEntity<Host> updateHost(@Valid @RequestBody Host host) throws URISyntaxException {
        log.debug("REST request to update Host : {}", host);
        if (host.getId() == null) {
            return createHost(host);
        }
        Host result = hostService.save(host);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, host.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hosts : get all the hosts.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of hosts in body
     */
    @GetMapping("/hosts")
    @Timed
    public List<Host> getAllHosts() {
        log.debug("REST request to get all Hosts");
        return hostService.findAll();
    }

    /**
     * GET  /hosts/:id : get the "id" host.
     *
     * @param id the id of the host to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the host, or with status 404 (Not Found)
     */
    @GetMapping("/hosts/{id}")
    @Timed
    public ResponseEntity<Host> getHost(@PathVariable Long id) {
        log.debug("REST request to get Host : {}", id);
        Host host = hostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(host));
    }

    /**
     * DELETE  /hosts/:id : delete the "id" host.
     *
     * @param id the id of the host to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hosts/{id}")
    @Timed
    public ResponseEntity<Void> deleteHost(@PathVariable Long id) {
        log.debug("REST request to delete Host : {}", id);
        hostService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
