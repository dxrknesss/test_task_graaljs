package ua.com.dxrknesss.jsiapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.com.dxrknesss.jsiapi.model.Script;
import ua.com.dxrknesss.jsiapi.repository.ScriptsRepository;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/v1/scripts")
public class ScriptController {
    @Autowired
    ScriptsRepository scriptsRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Script> findOne(@PathVariable(name = "id") Long id) {
        Script script;
        try {
            script = scriptsRepository.findScriptById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok(script);
    }

    @PostMapping public ResponseEntity<String> addOne(@RequestBody String scriptBody) {
        Script script = new Script(scriptBody);
        scriptsRepository.addScript(script);

        return ResponseEntity.ok("Script has been scheduled successfully!");
    }
}
