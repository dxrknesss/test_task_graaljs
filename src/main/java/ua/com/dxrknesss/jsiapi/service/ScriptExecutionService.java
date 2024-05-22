package ua.com.dxrknesss.jsiapi.service;

import org.graalvm.polyglot.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.dxrknesss.jsiapi.model.Script;
import org.graalvm.polyglot.Context;

@Service
public class ScriptExecutionService {
    @Async
    public void addScriptToExecutionQueue(Script script) {
        try (Context context = Context.newBuilder("js").out(System.out).err(System.err).option("engine.SpawnIsolate", "true").build()) {
            Value code = context.eval("js", script.getBody());
            if (code.canExecute()) {
                code.executeVoid();
            }
        }
    }
}
