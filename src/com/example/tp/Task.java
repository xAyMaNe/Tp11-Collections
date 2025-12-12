package com.example.tp;

public class Task {
    private static int counter = 0;
    private final int id;
    private String description;
    private int priority;      // 1 = haute … 5 = basse
    private Status status;

    public Task(String description, int priority) {
        this.id = ++counter;
        this.description = description;
        this.priority = priority;
        this.status = Status.PENDING;
    }

    // Getters et setters  
    public int getId() { return id; }
    public String getDescription() { return description; }
    public int getPriority() { return priority; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("[%d] (prio=%d) %s — %s",
                             id, priority, status, description);
    }
}