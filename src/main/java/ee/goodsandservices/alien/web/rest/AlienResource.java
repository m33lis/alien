package ee.goodsandservices.alien.web.rest;

import com.codahale.metrics.annotation.Timed;
import ee.goodsandservices.alien.domain.Alien;
import ee.goodsandservices.alien.service.AlienService;
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
 * REST controller for managing Alien.
 */
@RestController
@RequestMapping("/api")
public class AlienResource {

    private final Logger log = LoggerFactory.getLogger(AlienResource.class);

    private static final String ENTITY_NAME = "alien";
        
    private final AlienService alienService;

    public AlienResource(AlienService alienService) {
        this.alienService = alienService;
    }

    /**
     * POST  /aliens : Create a new alien.
     *
     * @param alien the alien to create
     * @return the ResponseEntity with status 201 (Created) and with body the new alien, or with status 400 (Bad Request) if the alien has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aliens")
    @Timed
    public ResponseEntity<Alien> createAlien(@Valid @RequestBody Alien alien) throws URISyntaxException {
        log.debug("REST request to save Alien : {}", alien);
        if (alien.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new alien cannot already have an ID")).body(null);
        }
        Alien result = alienService.save(alien);
        return ResponseEntity.created(new URI("/api/aliens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aliens : Updates an existing alien.
     *
     * @param alien the alien to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated alien,
     * or with status 400 (Bad Request) if the alien is not valid,
     * or with status 500 (Internal Server Error) if the alien couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aliens")
    @Timed
    public ResponseEntity<Alien> updateAlien(@Valid @RequestBody Alien alien) throws URISyntaxException {
        log.debug("REST request to update Alien : {}", alien);
        if (alien.getId() == null) {
            return createAlien(alien);
        }
        Alien result = alienService.save(alien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, alien.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aliens : get all the aliens.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aliens in body
     */
    @GetMapping("/aliens")
    @Timed
    public List<Alien> getAllAliens() {
        log.debug("REST request to get all Aliens");
        return alienService.findAll();
    }

    /**
     * GET  /aliens/:id : get the "id" alien.
     *
     * @param id the id of the alien to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the alien, or with status 404 (Not Found)
     */
    @GetMapping("/aliens/{id}")
    @Timed
    public ResponseEntity<Alien> getAlien(@PathVariable Long id) {
        log.debug("REST request to get Alien : {}", id);
        Alien alien = alienService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(alien));
    }

    /**
     * DELETE  /aliens/:id : delete the "id" alien.
     *
     * @param id the id of the alien to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aliens/{id}")
    @Timed
    public ResponseEntity<Void> deleteAlien(@PathVariable Long id) {
        log.debug("REST request to delete Alien : {}", id);
        alienService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
