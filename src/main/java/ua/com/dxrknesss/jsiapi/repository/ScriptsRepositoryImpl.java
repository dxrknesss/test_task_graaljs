package ua.com.dxrknesss.jsiapi.repository;

import ua.com.dxrknesss.jsiapi.model.Script;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ScriptsRepositoryImpl implements ScriptsRepository {
    private static Map<Long, Script> scripts = new ConcurrentHashMap<>();
    private static AtomicLong idCounter = new AtomicLong(0L);

    @Override
    public Script findOneById(Long id) throws NoSuchElementException {
        return returnScriptOrThrow(id);
    }

    private Script returnScriptOrThrow(Long id) throws NoSuchElementException {
        var script = scripts.get(id);
        if (script != null) {
            return script;
        }
        throw new NoSuchElementException("Script wasn't found by specified id!");
    }

    @Override
    public void addScript(Script script) {
        Long id = idCounter.getAndIncrement();
        scripts.put(id, script);
    }

    @Override
    public void removeScript(Long id) throws NoSuchElementException {
        scripts.remove(id);
    }
}
