package ua.com.dxrknesss.jsiapi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.com.dxrknesss.jsiapi.JsiapiApplication;
import ua.com.dxrknesss.jsiapi.model.Script;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JsiapiApplication.class)
public class ScriptRepositoryTest {
    private Set<Script> scripts;
    private Script initScript1, initScript2, newScript;
    @Autowired
    private ScriptsRepository scriptsRepository;

    @BeforeEach
    public void setup() {
        scripts = new HashSet<>();
        initScript1 = new Script("console.log('hello world!');");
        initScript2 = new Script("""
                let a = 2;
                const b = a + '1' == 3 ? '3' : '21';
                """);
        newScript = new Script("var a = [1,2,3,4,5,6,'apple','banana']");

        scriptsRepository.addScript(initScript1);
        scriptsRepository.addScript(initScript2);
        scripts.add(initScript1);
        scripts.add(initScript2);
    }

    @AfterEach
    public void tearDown() {
        scripts = new HashSet<>();
        scriptsRepository.removeAllScripts();
    }

    @Test
    public void findAllScripts_shouldReturnAllScripts() {
        assertEquals(scripts, scriptsRepository.findAllScripts());
    }

    @Test
    public void findScriptById_shouldReturnScriptWithSameId() {
        assertEquals(initScript1, scriptsRepository.findScriptById(0L));
        assertEquals(initScript2, scriptsRepository.findScriptById(1L));
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 999L})
    @NullSource
    public void givenScriptByIdNotExists_throwNoSuchElementException(Long nonExistentId) {
        assertThrows(NoSuchElementException.class, () -> scriptsRepository.findScriptById(nonExistentId));
    }

    @Test
    public void addOne_shouldAddOneScript() {
        scriptsRepository.addScript(newScript);

        assertEquals(newScript, scriptsRepository.findScriptById(2L));
    }

    @Test
    public void removeScript_shouldRemoveOneScript() {
        scriptsRepository.removeScript(0L);

        assertThrows(NoSuchElementException.class, () -> scriptsRepository.findScriptById(0L));
    }

    @Test
    public void removeAllScript_shouldRemoveAllScripts() {
        scriptsRepository.removeAllScripts();

        assertEquals(Set.of(), scriptsRepository.findAllScripts());
    }
}
