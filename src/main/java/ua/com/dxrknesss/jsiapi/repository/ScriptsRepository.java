package ua.com.dxrknesss.jsiapi.repository;

import ua.com.dxrknesss.jsiapi.model.Script;

import java.util.NoSuchElementException;
import java.util.Set;

public interface ScriptsRepository {
    Set<Script> findAllScripts();
    Script findScriptById(Long id) throws NoSuchElementException;
    void addScript(Script script);
    void removeScript(Long id);
    void removeAllScripts();
}
