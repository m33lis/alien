package ee.goodsandservices.alien.web.rest;

import com.codahale.metrics.annotation.Timed;
import ee.goodsandservices.alien.domain.GestatingResponse;
import ee.goodsandservices.alien.service.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by m3l on 19.05.17.
 */

@RestController
@RequestMapping("/api")
public class MatchResource {

    private final Logger log = LoggerFactory.getLogger(MatchResource.class);

    private final MatchService matchService;

    public MatchResource(MatchService matchService) {
        this.matchService = matchService;
    }

    /**
    * GET /match/:alienDna/:hostDna : match alienDna with hostDna
    *
    * @param alienDna dna of alien
    * @param hostDna dna of host
    * @return the GestatingResponse
    */
    @GetMapping("/match/{alienDna}/{hostDna}")
    @Timed
    public GestatingResponse matchAlienAndHost(@PathVariable Integer alienDna, @PathVariable Integer hostDna) {
        log.debug("REST request to match alienDna : {} with hostDna : {}", alienDna, hostDna);
        return matchService.match(alienDna, hostDna);
    }
}
