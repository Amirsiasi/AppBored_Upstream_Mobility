package at.upstream_mobility.itacademy.appbored.service;

import at.upstream_mobility.itacademy.appbored.model.ActivityResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ActivityServiceTest {

    private final ActivityService activityService = new ActivityService();


    @BeforeEach
    void setUp() {


    }
    @Test
    public void testGetRandomActivity(){
        //when
        Mono<ActivityResponse> responseMono = activityService.getRandomActivity();

        //then
//        BoredResponse response = responseMono.block();
//        assertNotNull(response);
//        assertNotNull(response.activity());
//        assertFalse(response.activity().isEmpty());

        StepVerifier.create(responseMono)
                .assertNext(res -> {
                    assertNotNull(res);
                    assertNotNull(res.activity());
                    assertFalse(res.activity().isEmpty());
                })
                .expectComplete()
                .verify();


    }

    @Test
    public void testGetRandomActivityByType(){
        //Given
        String type = "education";
        //when
        Mono<ActivityResponse> responseMono = activityService.getRandomActivityByType(type);

        //then
        StepVerifier.create(responseMono)
                .assertNext(res -> {
                    assertNotNull(res);
                    assertNotNull(res.activity());
                    assertFalse(res.activity().isEmpty());

                })
                .thenConsumeWhile(res -> {
                    assertThat(res).isEqualTo(type);
                    return true;
                })
                .expectComplete()
                .verify();

    }

    @Test
    public void testGetRandomActivityByTypeWithNoActivityFound() {
        // Mocken Sie die Abhängigkeit oder Konfiguration, um sicherzustellen, dass eine Situation simuliert wird,
        // in der keine Aktivität gefunden wird. Dies könnte beispielsweise durch das Mocken des WebClient-Aufrufs geschehen,
        // um ein Response-Objekt zurückzugeben, dessen `activity` Feld `null` oder leer ist.

        String type = "sport";
        Mono<ActivityResponse> responseMono = activityService.getRandomActivityByType(type);

        // Verwenden Sie StepVerifier, um die Reaktion zu überprüfen
        StepVerifier.create(responseMono)
                .assertNext(response -> {
                    assertNotNull(response);
                    assertEquals("No activity found for the specified type", response.activity());
                })
                .expectComplete()
                .verify();
    }
    @Test
    void testActivityServiceConstructor() {
        // Mock des WebClient.Builder
        WebClient.Builder builderMock = mock(WebClient.Builder.class);
        WebClient webClientMock = mock(WebClient.class);

        // Konfigurieren des Mocks, um beim Aufruf von baseUrl() und build() sich selbst bzw. einen WebClient Mock zurückzugeben
        when(builderMock.baseUrl(anyString())).thenReturn(builderMock);
        when(builderMock.build()).thenReturn(webClientMock);

        // Erstellen einer Instanz von ActivityService mit dem gemockten Builder
        ActivityService activityService = new ActivityService(builderMock);

        // Überprüfen, ob baseUrl mit dem erwarteten Wert aufgerufen wurde
        verify(builderMock).baseUrl("https://www.boredapi.com/api/activity");

        // Hier könnten Sie weitere Verifikationen hinzufügen, um sicherzustellen,
        // dass der WebClient wie erwartet verwendet wird.
    }






}