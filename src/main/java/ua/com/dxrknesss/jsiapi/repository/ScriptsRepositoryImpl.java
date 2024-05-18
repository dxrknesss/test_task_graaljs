package ua.com.dxrknesss.jsiapi.repository;

import org.springframework.stereotype.Repository;
import ua.com.dxrknesss.jsiapi.model.Script;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ScriptsRepositoryImpl implements ScriptsRepository {
    private static final Map<Long, Script> scripts = new ConcurrentHashMap<>();
    private static final AtomicLong idCounter = new AtomicLong(0L);

    @Override
    public Set<Script> findAllScripts() {
        return Set.copyOf(scripts.values());
    }

    @Override
    public Script findScriptById(Long id) throws NoSuchElementException {
        return returnScriptOrThrow(id);
    }

    private Script returnScriptOrThrow(Long id) throws NoSuchElementException {
        if (id != null) {
            var script = scripts.get(id);
            if (script != null) {
                return script;
            }
        }
        throw new NoSuchElementException("Script wasn't found by specified id!");
    }

    @Override
    public void addScript(Script script) {
        Long id = idCounter.getAndIncrement();
        script.setId(id);
        scripts.put(id, script);
    }

    @Override
    public void removeScript(Long id) {
        if (id != null) {
            scripts.remove(id);
        }
    }

    @Override
    public void removeAllScripts() {
        for (var key : scripts.keySet()) {
            scripts.remove(key);
        }

        idCounter.set(0);
    }
}
