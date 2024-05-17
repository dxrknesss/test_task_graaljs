package ua.com.dxrknesss.jsiapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.dxrknesss.jsiapi.model.Script;

@RestController("/v1/scripts")
public class ScriptController {
    @GetMapping("/{id}")
    public ResponseEntity<Script> findOne(@PathVariable(name = "id") Long id) {
        return
    }
}
