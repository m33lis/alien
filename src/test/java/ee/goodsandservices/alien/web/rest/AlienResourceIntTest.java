package ee.goodsandservices.alien.web.rest;

import ee.goodsandservices.alien.AlienApp;

import ee.goodsandservices.alien.domain.Alien;
import ee.goodsandservices.alien.repository.AlienRepository;
import ee.goodsandservices.alien.service.AlienService;
import ee.goodsandservices.alien.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AlienResource REST controller.
 *
 * @see AlienResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlienApp.class)
public class AlienResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DNA = 1;
    private static final Integer UPDATED_DNA = 2;

    private static final String DEFAULT_AVATAR_URL = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR_URL = "BBBBBBBBBB";

    @Autowired
    private AlienRepository alienRepository;

    @Autowired
    private AlienService alienService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlienMockMvc;

    private Alien alien;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AlienResource alienResource = new AlienResource(alienService);
        this.restAlienMockMvc = MockMvcBuilders.standaloneSetup(alienResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Alien createEntity(EntityManager em) {
        Alien alien = new Alien()
            .name(DEFAULT_NAME)
            .dna(DEFAULT_DNA)
            .avatarUrl(DEFAULT_AVATAR_URL);
        return alien;
    }

    @Before
    public void initTest() {
        alien = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlien() throws Exception {
        int databaseSizeBeforeCreate = alienRepository.findAll().size();

        // Create the Alien
        restAlienMockMvc.perform(post("/api/aliens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alien)))
            .andExpect(status().isCreated());

        // Validate the Alien in the database
        List<Alien> alienList = alienRepository.findAll();
        assertThat(alienList).hasSize(databaseSizeBeforeCreate + 1);
        Alien testAlien = alienList.get(alienList.size() - 1);
        assertThat(testAlien.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAlien.getDna()).isEqualTo(DEFAULT_DNA);
        assertThat(testAlien.getAvatarUrl()).isEqualTo(DEFAULT_AVATAR_URL);
    }

    @Test
    @Transactional
    public void createAlienWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alienRepository.findAll().size();

        // Create the Alien with an existing ID
        alien.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlienMockMvc.perform(post("/api/aliens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alien)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Alien> alienList = alienRepository.findAll();
        assertThat(alienList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAliens() throws Exception {
        // Initialize the database
        alienRepository.saveAndFlush(alien);

        // Get all the alienList
        restAlienMockMvc.perform(get("/api/aliens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alien.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].dna").value(hasItem(DEFAULT_DNA)))
            .andExpect(jsonPath("$.[*].avatarUrl").value(hasItem(DEFAULT_AVATAR_URL.toString())));
    }

    @Test
    @Transactional
    public void getAlien() throws Exception {
        // Initialize the database
        alienRepository.saveAndFlush(alien);

        // Get the alien
        restAlienMockMvc.perform(get("/api/aliens/{id}", alien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alien.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.dna").value(DEFAULT_DNA))
            .andExpect(jsonPath("$.avatarUrl").value(DEFAULT_AVATAR_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlien() throws Exception {
        // Get the alien
        restAlienMockMvc.perform(get("/api/aliens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlien() throws Exception {
        // Initialize the database
        alienService.save(alien);

        int databaseSizeBeforeUpdate = alienRepository.findAll().size();

        // Update the alien
        Alien updatedAlien = alienRepository.findOne(alien.getId());
        updatedAlien
            .name(UPDATED_NAME)
            .dna(UPDATED_DNA)
            .avatarUrl(UPDATED_AVATAR_URL);

        restAlienMockMvc.perform(put("/api/aliens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlien)))
            .andExpect(status().isOk());

        // Validate the Alien in the database
        List<Alien> alienList = alienRepository.findAll();
        assertThat(alienList).hasSize(databaseSizeBeforeUpdate);
        Alien testAlien = alienList.get(alienList.size() - 1);
        assertThat(testAlien.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAlien.getDna()).isEqualTo(UPDATED_DNA);
        assertThat(testAlien.getAvatarUrl()).isEqualTo(UPDATED_AVATAR_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingAlien() throws Exception {
        int databaseSizeBeforeUpdate = alienRepository.findAll().size();

        // Create the Alien

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlienMockMvc.perform(put("/api/aliens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alien)))
            .andExpect(status().isCreated());

        // Validate the Alien in the database
        List<Alien> alienList = alienRepository.findAll();
        assertThat(alienList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlien() throws Exception {
        // Initialize the database
        alienService.save(alien);

        int databaseSizeBeforeDelete = alienRepository.findAll().size();

        // Get the alien
        restAlienMockMvc.perform(delete("/api/aliens/{id}", alien.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Alien> alienList = alienRepository.findAll();
        assertThat(alienList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alien.class);
        Alien alien1 = new Alien();
        alien1.setId(1L);
        Alien alien2 = new Alien();
        alien2.setId(alien1.getId());
        assertThat(alien1).isEqualTo(alien2);
        alien2.setId(2L);
        assertThat(alien1).isNotEqualTo(alien2);
        alien1.setId(null);
        assertThat(alien1).isNotEqualTo(alien2);
    }
}
