package at.upstream_mobility.itacademy.appbored.shell.provider;

import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ActivityTypeValueProvider  implements ValueProvider {
    @Override
    public boolean supports(MethodParameter parameter, CompletionContext completionContext) {
       // Diese Methode prüft, ob dieser ValueProvider für den gegebenen Parameter verwendet werden soll.
        //  denParametername und -typ, um zu bestätigen, dass der ValueProvider anwendbar ist.


        return parameter.getParameterName().equals("type") && String.class.isAssignableFrom(parameter.getParameterType());
    }



    @Override
    public List<CompletionProposal> complete(MethodParameter methodParameter, CompletionContext completionContext, String[] strings) {
        // die Liste der Vorschläge, die für den Parameter angezeigt werden sollen.
        List<String> activityTypes = Arrays.asList("education", "recreational", "social", "diy", "charity", "cooking", "relaxation");

        return activityTypes.stream()
                .map(CompletionProposal::new)
                .collect(Collectors.toList());


    }
}
