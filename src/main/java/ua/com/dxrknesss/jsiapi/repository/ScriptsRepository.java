package ua.com.dxrknesss.jsiapi.repository;

import ua.com.dxrknesss.jsiapi.model.Script;

public interface ScriptsRepository {
    Script findOneById(Long id);
    void addScript(Script script);
    void removeScript(Long id);
}
