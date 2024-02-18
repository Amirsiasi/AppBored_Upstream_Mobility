package at.upstream_mobility.itacademy.appbored.shell.provider;

import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionProposal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActivityTypeValueProviderTest {


    @Test
    void testSupports() {
        ActivityTypeValueProvider provider = new ActivityTypeValueProvider();
        MethodParameter parameter = mock(MethodParameter.class);

        when(parameter.getParameterName()).thenReturn("type");
        when(parameter.getParameterType()).thenReturn((Class) String.class);

        boolean supports = provider.supports(parameter, null); // Der CompletionContext kann null sein, da er hier nicht verwendet wird.

        assertTrue(supports);
    }

    @Test
    void testComplete() {
        ActivityTypeValueProvider provider = new ActivityTypeValueProvider();

        List<CompletionProposal> proposals = provider.complete(null, null, null); // Kontext und Strings sind in diesem Test nicht relevant.

        List<String> expectedTypes = Arrays.asList("education", "recreational", "social", "diy", "charity", "cooking", "relaxation");
        assertThat(proposals).extracting(CompletionProposal::value).containsExactlyInAnyOrderElementsOf(expectedTypes);
    }

    @Test
    public void testCompleteReturnsExpectedActivityTypes() {
        ActivityTypeValueProvider provider = new ActivityTypeValueProvider();

        // Da der CompletionContext und der MethodParameter hier nicht direkt verwendet werden,
        // ist es in Ordnung, sie als null zu übergeben, solange die Methode dies handhaben kann.

        List<CompletionProposal> proposals = provider.complete(null, null, null);

        assertThat(proposals).isNotNull();
        assertThat(proposals).hasSize(7); // Erwarte 7 Vorschläge basierend auf die Konfiguration
        List<String> proposalValues = proposals.stream().map(CompletionProposal::value).collect(Collectors.toList());
        assertThat(proposalValues).containsExactlyInAnyOrder("education", "recreational", "social", "diy", "charity", "cooking", "relaxation");
    }
}
