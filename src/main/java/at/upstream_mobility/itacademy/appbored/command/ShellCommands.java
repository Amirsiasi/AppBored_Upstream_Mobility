package at.upstream_mobility.itacademy.appbored.command;


import at.upstream_mobility.itacademy.appbored.model.ActivityResponse;
import at.upstream_mobility.itacademy.appbored.service.ActivityService;
import at.upstream_mobility.itacademy.appbored.shell.provider.ActivityTypeValueProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import reactor.core.publisher.Mono;

@ShellComponent
public class ShellCommands {

    @Autowired
    private final ActivityService activityService;

    public ShellCommands(ActivityService activityService) {
        this.activityService = activityService;
    }

    @ShellMethod(value = "Get a random activity. Optionally, specify a type to filter by.", key = "get")
    public String getActivity(@ShellOption(defaultValue = ShellOption.NULL,valueProvider = ActivityTypeValueProvider.class) String type) {
        Mono<ActivityResponse> responseMono;

        if (type == null || type.isEmpty()) {
            responseMono = activityService.getRandomActivity();
        } else {
            responseMono = activityService.getRandomActivityByType(type);
        }

        ActivityResponse response = responseMono
                .doOnError(error -> System.err.println("Error fetching activity: " + error.getMessage()))
                .block();

        return response != null ? response.activity() : "No activity found.";
    }


    }

