package ee.goodsandservices.alien.service;

import ee.goodsandservices.alien.domain.GestatingResponse;
import ee.goodsandservices.alien.repository.HostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by m3l on 19.05.17.
 *
 * Match service
 *
 */

@Service
@Transactional
public class MatchService {

    private final Logger log = LoggerFactory.getLogger(MatchService.class);

    public GestatingResponse match(Integer[] alienDna, Integer hostDna) {
        log.debug("Trying to match hostDna: {} with alienDna: {}", hostDna, alienDna);
        Integer firstHalfOfAlienDna = alienDna[0];
        Integer secondHalfOfAlienDna = alienDna[1];

        GestatingResponse res = new GestatingResponse();

        if (firstHalfOfAlienDna.equals(secondHalfOfAlienDna)) {
          if (hostDna.equals(firstHalfOfAlienDna)) {
              // equilateral
              res.setCode("GREAT_SUCCESS");
              res.setMessage("new viable and sulphurous creature has left the nest");
          } else {
              // isosceles
              res.setCode("BIG_SUCCESS");
              res.setMessage("new viable and strong creature has left the nest");
          }
        } else {
            if (!hostDna.equals(firstHalfOfAlienDna)
                && !hostDna.equals(secondHalfOfAlienDna)) {
                // scalene
                res.setCode("SUCCESS");
                res.setMessage("new alien has left the nest");
            } else {
                res.setCode("FAIL");
                res.setMessage("gestating aborted");
            }
        }

        return res;
    }

}
