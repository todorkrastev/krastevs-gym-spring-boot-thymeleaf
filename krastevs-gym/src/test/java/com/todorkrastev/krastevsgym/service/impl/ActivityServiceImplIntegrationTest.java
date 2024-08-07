package com.todorkrastev.krastevsgym.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import com.todorkrastev.krastevsgym.config.ActivityApiConfig;
import com.todorkrastev.krastevsgym.model.dto.ActivityDTO;
import com.todorkrastev.krastevsgym.model.dto.CreateActivityDTO;
import com.todorkrastev.krastevsgym.service.ActivityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@EnableWireMock(
        @ConfigureWireMock(name = "activity-api-service")
)
class ActivityServiceImplIntegrationTest {

    @InjectWireMock("activity-api-service")
    private WireMockServer wireMockServer;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityApiConfig activityApiConfig;

    @BeforeEach
    public void setUp() {
        activityApiConfig.setBaseUrl(wireMockServer.baseUrl());
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.resetAll();
    }

    @Test
    void testFindAll() throws JsonProcessingException {
        String json = """
              [
                  {
                    "id": 1,
                    "title": "Free Weights",
                    "description": "If you suffer from acute iron deficiency, you've come to the right place. In the Free Weights Area, you can let off steam on the gym80 Olympia Premium barbells or dumbbells up to 150 kg.",
                    "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/weights_otkv2w.jpg"
                  },
                  {
                    "id": 2,
                    "title": "Machines",
                    "description": "Indestructible, incomparable and incredibly effective: With the ultimate equipment from gym80. The King of Machines you train all muscles absolutely precisely thanks to the excellent biomechanics.",
                    "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648981/krastevs-gym/imgs/home/machines_r8v6au.jpg"
                  },
                  {
                    "id": 3,
                    "title": "Cardio Area",
                    "description": "Take your endurance to the next level with top equipment from Precor and Matrix and look forward to a gigantic selection - in Constance even between 10 meter high trees!",
                    "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/cardio_e8p7uu.jpg"
                  }
                ]
            """;

        List<ActivityDTO> activitiesObjectMapper = new ObjectMapper().reader()
                .forType(new TypeReference<List<ActivityDTO>>() {})
                .readValue(json);

        wireMockServer.stubFor(get("/activities/all").willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(new ObjectMapper().writeValueAsString(activitiesObjectMapper))));

        List<ActivityDTO> activities = activityService.findAll();

        assertNotNull(activities);
        assertEquals(3, activities.size());
        assertEquals(1L, activities.getFirst().getId());
        assertEquals("Free Weights", activities.getFirst().getTitle());
        assertEquals("If you suffer from acute iron deficiency, you've come to the right place. In the Free Weights Area, you can let off steam on the gym80 Olympia Premium barbells or dumbbells up to 150 kg.", activities.getFirst().getDescription());
        assertEquals("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/weights_otkv2w.jpg", activities.getFirst().getImageURL());
    }


    @Test
    void testFindAll_ResponseNull() {
        wireMockServer.stubFor(get("/activities/all").willReturn(
                aResponse()
                        .withStatus(204)
        ));

        List<ActivityDTO> activities = activityService.findAll();

        assertNotNull(activities);
        assertEquals(0, activities.size());
    }

    @Test
    void testFindAll_ThrowsException() {
        wireMockServer.stubFor(get("/activities/all").willReturn(
                aResponse()
                        .withStatus(500)
        ));

        List<ActivityDTO> activities = activityService.findAll();

        assertNotNull(activities);
        assertEquals(0, activities.size());
    }

    @Test
    void testGetActivityById() {
        wireMockServer.stubFor(get("/activities/2").willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                  {
                                    "id": 2,
                                    "title": "Machines",
                                    "description": "Indestructible, incomparable and incredibly effective: With the ultimate equipment from gym80. The King of Machines you train all muscles absolutely precisely thanks to the excellent biomechanics.",
                                    "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648981/krastevs-gym/imgs/home/machines_r8v6au.jpg"
                                  }
                                """)
                        .withStatus(200)));

        ActivityDTO activity = activityService.getActivityById(2L);

        assertNotNull(activity);
        assertEquals(2L, activity.getId());
        assertEquals("Machines", activity.getTitle());
        assertEquals("Indestructible, incomparable and incredibly effective: With the ultimate equipment from gym80. The King of Machines you train all muscles absolutely precisely thanks to the excellent biomechanics.", activity.getDescription());
        assertEquals("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648981/krastevs-gym/imgs/home/machines_r8v6au.jpg", activity.getImageURL());
    }

    @Test
    void testCreateActivity() {
        wireMockServer.stubFor(post("/activities/create").willReturn(
                aResponse()
                        .withBody("""
                                    {
                                        "id": 100,
                                        "title": "Yoga",
                                        "description": "Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.",
                                        "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"
                                    }
                                """
                        )
                        .withStatus(201)
        ));


        activityService.createActivity(new CreateActivityDTO()
                .setId(100L)
                .setTitle("Yoga")
                .setDescription("Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.")
                .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"));

        wireMockServer.verify(postRequestedFor(urlEqualTo("/activities/create"))
                .withRequestBody(equalToJson("""
                            {
                                "id": 100,
                                "title": "Yoga",
                                "description": "Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.",
                                "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"
                            }
                        """)));

    }

    @Test
    void testCreateActivity_BadRequest() {
        wireMockServer.stubFor(post("/activities/create").willReturn(
                aResponse()
                        .withStatus(400)
        ));

        assertThrows(RestClientResponseException.class, () -> {
            activityService.createActivity(new CreateActivityDTO()
                    .setId(100L)
                    .setTitle("Yoga")
                    .setDescription("Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.")
                    .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"));
        });
    }

    @Test
    void testCreateActivity_InternalServerError() {
        wireMockServer.stubFor(post("/activities/create").willReturn(
                aResponse()
                        .withStatus(500)
        ));

        assertThrows(Exception.class, () -> {
            activityService.createActivity(new CreateActivityDTO()
                    .setId(100L)
                    .setTitle("Yoga")
                    .setDescription("Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.")
                    .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"));
        });
    }

    @Test
    void testDoesTitleExist() {
        wireMockServer.stubFor(get("/activities/exists/Yoga").willReturn(
                aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")
                        .withStatus(200)
        ));

        boolean exists = activityService.doesTitleExist("Yoga");

        assertTrue(exists);
    }

    @Test
    void testUpdateActivity() {
        wireMockServer.stubFor(post("/activities/create").willReturn(
                aResponse()
                        .withBody("""
                                    {
                                        "id": 100,
                                        "title": "Yoga",
                                        "description": "Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.",
                                        "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"
                                    }
                                """
                        )
                        .withStatus(201)
        ));

        activityService.createActivity(new CreateActivityDTO()
                .setId(100L)
                .setTitle("Yoga")
                .setDescription("Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.")
                .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"));

        wireMockServer.verify(postRequestedFor(urlEqualTo("/activities/create"))
                .withRequestBody(equalToJson("""
                            {
                                "id": 100,
                                "title": "Yoga",
                                "description": "Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.",
                                "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"
                            }
                        """)));

        wireMockServer.stubFor(put("/activities/100").willReturn(
                aResponse()
                        .withStatus(204)
        ));

        activityService.updateActivity(100L, new ActivityDTO()
                .setId(100L)
                .setTitle("Yoga Title Updated")
                .setDescription("Yoga Description Updated")
                .setImageURL("Yoga Image URL Updated"));

        wireMockServer.verify(putRequestedFor(urlEqualTo("/activities/100"))
                .withRequestBody(equalToJson("""
                            {
                                "id": 100,
                                "title": "Yoga Title Updated",
                                "description": "Yoga Description Updated",
                                "imageURL": "Yoga Image URL Updated"
                            }
                        """)));
    }

    @Test
    void testUpdateActivity_BadRequest() {
        wireMockServer.stubFor(put("/activities/100").willReturn(
                aResponse()
                        .withStatus(400)
        ));

        assertThrows(RestClientResponseException.class, () -> {
            activityService.updateActivity(100L, new ActivityDTO()
                    .setId(100L)
                    .setTitle("Yoga Title Updated")
                    .setDescription("Yoga Description Updated")
                    .setImageURL("Yoga Image URL Updated"));
        });
    }

    @Test
    void testUpdateActivity_InternalServerError() {
        wireMockServer.stubFor(put("/activities/100").willReturn(
                aResponse()
                        .withStatus(500)
        ));

        assertThrows(Exception.class, () -> {
            activityService.updateActivity(100L, new ActivityDTO()
                    .setId(100L)
                    .setTitle("Yoga Title Updated")
                    .setDescription("Yoga Description Updated")
                    .setImageURL("Yoga Image URL Updated"));
        });
    }

    @Test
    void testDeleteActivity() {
        wireMockServer.stubFor(post("/activities/create").willReturn(
                aResponse()
                        .withBody("""
                                    {
                                        "id": 1,
                                        "title": "Yoga",
                                        "description": "Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.",
                                        "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"
                                    }
                                """
                        )
                        .withStatus(201)
        ));

        activityService.createActivity(new CreateActivityDTO()
                .setId(1L)
                .setTitle("Yoga")
                .setDescription("Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.")
                .setImageURL("https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"));

        wireMockServer.verify(postRequestedFor(urlEqualTo("/activities/create"))
                .withRequestBody(equalToJson("""
                            {
                                "id": 1,
                                "title": "Yoga",
                                "description": "Yoga is a group of physical, mental, and spiritual practices or disciplines that originated in ancient India. Yoga is one of the six Āstika schools of Indian philosophical traditions.",
                                "imageURL": "https://res.cloudinary.com/dgtuddxqf/image/upload/v1720648980/krastevs-gym/imgs/home/yoga_ayzv8v.jpg"
                            }
                        """)));


        wireMockServer.stubFor(delete("/activities/1").willReturn(
                aResponse()
                        .withStatus(204)
        ));

        activityService.deleteActivity(1L);

        wireMockServer.verify(deleteRequestedFor(urlEqualTo("/activities/1")));
    }
}