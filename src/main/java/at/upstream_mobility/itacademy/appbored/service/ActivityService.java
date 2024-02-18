package at.upstream_mobility.itacademy.appbored.service;

import at.upstream_mobility.itacademy.appbored.model.ActivityResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class ActivityService {

    private final WebClient webClient;

    public ActivityService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.boredapi.com/api/activity").build();
    }

    public ActivityService() {
        this.webClient = WebClient.builder().baseUrl("https://www.boredapi.com/api/activity").build();

    }

    // Abrufen einer zufälligen Aktivität ohne Filter
    public Mono<ActivityResponse> getRandomActivity() {
        return this.webClient.get()
                .retrieve()
                .bodyToMono(ActivityResponse.class)
                .onErrorReturn(new ActivityResponse("Failed to fetch activity", "", "", "", "", "", ""));
    }

    // Abrufen einer zufälligen Aktivität mit optionalem Typenfilter
    public Mono<ActivityResponse> getRandomActivityByType(String type) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("type", type)   //add activity?type="type"
                        .build())
                .retrieve()
                .bodyToMono(ActivityResponse.class)
                .flatMap(response -> {
                    if (response.activity() == null || response.activity().isEmpty()) {
                        return Mono.just(new ActivityResponse("No activity found for the specified type", type, "", "", "", "", ""));
                    }
                    return Mono.just(response);
                })
                     // Spezifische Behandlung basierend auf dem Statuscode, Behandlung von Timeout-Fehlern, allgemeine Fehlerbehandlung
                .onErrorResume(error -> Mono.just(new ActivityResponse("Failed to fetch activity due to an error", "", "", "", "", "", "")));

    }

}