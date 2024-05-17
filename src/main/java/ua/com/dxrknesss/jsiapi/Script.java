package ua.com.dxrknesss.jsiapi;

import java.io.OutputStream;
import java.time.LocalDateTime;

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
}
