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

    public GestatingResponse match(Integer alienDna, Integer hostDna) {

        Integer firstHalfOfAlienDna = Character.getNumericValue(alienDna.toString().charAt(0));
        Integer secondHalfOfAlienDna = Character.getNumericValue(alienDna.toString().charAt(1));

        if (firstHalfOfAlienDna.equals(secondHalfOfAlienDna)) {
          if (hostDna.equals(firstHalfOfAlienDna)) {
              return GestatingResponse.GREAT_SUCCESS;
          } else {
              // fixme!
              return GestatingResponse.BIG_SUCCESS;
          }
        }

        return GestatingResponse.FAIL;
    }

}
