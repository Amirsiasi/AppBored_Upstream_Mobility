package at.upstream_mobility.itacademy.appbored.shell;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {"spring.shell.interactive.enabled=false"})
public class ShellIntegrationTest {

    @Autowired
    private Shell shell;

    @Test
    public void testGetRandomActivityCommand() {
        Object result = shell.evaluate(() -> "get");
        assertThat(result).isNotNull();
    }
}
