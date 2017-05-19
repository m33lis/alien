package ee.goodsandservices.alien.service;

import ee.goodsandservices.alien.AlienApp;
import ee.goodsandservices.alien.domain.GestatingResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by m3l on 19.05.17.
 *
 * Test class for MatchService
 *
 * @see MatchService
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlienApp.class)
public class MatchServiceTest {

    private MatchService matchService;

    private static final Integer[] alienDna = { 112, 110 };
    private static final Integer hostDna = 300;

   /* @Test
    public void matchServiceRuns() {
        GestatingResponse res = matchService.match(alienDna, hostDna);
        System.out.print(res);
    }
    */
}
