package ua.com.dxrknesss.jsiapi.service;

import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.dxrknesss.jsiapi.model.Script;
import org.graalvm.polyglot.Context;
import ua.com.dxrknesss.jsiapi.repository.ScriptsRepository;

@Service
public class ScriptExecutionService {
    private ScriptsRepository scriptsRepository;

    @Autowired
    public ScriptExecutionService(ScriptsRepository scriptsRepository) {
        this.scriptsRepository = scriptsRepository;
    }

    @Async
    public void addScriptToExecutionQueue(Script script) {
        try (Context context = Context.newBuilder("js")
                .out(System.out).err(System.err)
                .option("engine.SpawnIsolate", "true")
                .build()) {
            Value code = context.eval("js", script.getBody());
            if (code.canExecute()) {
                script.setPhase(Script.ExecutionPhase.RUNNING); // TODO: add change methods to repository
                scriptsRepository.addScript(script);
                code.executeVoid();
                script.setPhase(Script.ExecutionPhase.COMPLETED);
            } else {
                script.setPhase(Script.ExecutionPhase.ERROR_EXITED);
            }
        }
    }
}
