package ua.com.dxrknesss.jsiapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.com.dxrknesss.jsiapi.JsiapiApplication;
import ua.com.dxrknesss.jsiapi.model.Script;
import ua.com.dxrknesss.jsiapi.repository.ScriptsRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JsiapiApplication.class)
public class ScriptExecutionServiceTests {
    @Autowired
    private ScriptExecutionService scriptExecutionService;
    @MockBean
    private ScriptsRepository scriptsRepository;

    private Script s1;

    @BeforeEach
    public void setup() {
        s1 = new Script("""
            let j = 0;
            for (let i = 0; i < 10000; i++)
                j += Math.sqrt((i * 2) % 100);
            """);
    }

    @Test
    public void whenScriptIsAddedToExecutionQueue_thenItWasCreatedInStorage() {
        scriptExecutionService.addScriptToExecutionQueue(s1);

        Mockito.verify(scriptsRepository, Mockito.atLeastOnce()).addScript(s1);

        assertEquals(s1, scriptsRepository.findScriptById(0L));
        assertEquals(Script.ExecutionPhase.RUNNING, s1.getPhase());
    }
}
