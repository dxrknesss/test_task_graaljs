package ua.com.dxrknesss.jsiapi.model;

import lombok.Getter;
import lombok.Setter;

import java.io.OutputStream;
import java.time.LocalDateTime;

@Getter
@Setter
public class Script {
    private Long id;
    private String body;
    private ExecutionPhase phase;
    public enum ExecutionPhase {
        QUEUED, RUNNING, COMPLETED, ERROR_EXITED
    }
    private OutputStream stdout;
    private OutputStream stderr;
    private LocalDateTime scheduleTime;
    private LocalDateTime executionStartTime;

    public Script(String scriptBody) {
        this.body = scriptBody;
        this.scheduleTime = LocalDateTime.now();
    }
}
