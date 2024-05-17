package ua.com.dxrknesss.jsiapi.repository;

import org.springframework.stereotype.Repository;
import ua.com.dxrknesss.jsiapi.model.Script;

import java.util.NoSuchElementException;

public interface ScriptsRepository {
    Script findOneById(Long id) throws NoSuchElementException;
    void addScript(Script script);
    void removeScript(Long id);
}
