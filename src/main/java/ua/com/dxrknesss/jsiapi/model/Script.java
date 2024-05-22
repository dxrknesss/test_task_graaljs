package ua.com.dxrknesss.jsiapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.OutputStream;
import java.time.LocalDateTime;

@Data
public class Script {
    private Long id;
    private String body;
    private ExecutionPhase phase;
    public enum ExecutionPhase {
        QUEUED, RUNNING, COMPLETED, ERROR_EXITED
    }
    @EqualsAndHashCode.Exclude
    private OutputStream stdout;
    @EqualsAndHashCode.Exclude
    private OutputStream stderr;
    private LocalDateTime scheduleTime;
    private LocalDateTime executionStartTime;

    public Script(String scriptBody) {
        this.body = scriptBody;
        this.scheduleTime = LocalDateTime.now();
        this.phase = ExecutionPhase.QUEUED;
    }
}
