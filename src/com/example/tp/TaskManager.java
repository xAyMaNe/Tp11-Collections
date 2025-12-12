package com.example.tp;

import java.util.*;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    /** Ajoute une tâche à la liste */
    public void addTask(String description, int priority) {
        tasks.add(new Task(description, priority));
    }

    /** Supprime la tâche d’ID donné, retourne true si trouvée */
    public boolean removeTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }

    /** Met à jour le statut d’une tâche, retourne true si trouvée */
    public boolean updateStatus(int id, Status newStatus) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }

    /** Renvoie une nouvelle liste de toutes les tâches */
    public List<Task> listTasks() {
        return new ArrayList<>(tasks);
    }

    /** Filtre les tâches par statut */
    public List<Task> filterByStatus(Status status) {
        return tasks.stream()
                     .filter(t -> t.getStatus() == status)
                     .collect(Collectors.toList());
    }

    /** Filtre les tâches par priorité */
    public List<Task> filterByPriority(int priority) {
        return tasks.stream()
                     .filter(t -> t.getPriority() == priority)
                     .collect(Collectors.toList());
    }

    /** Trie en place par priorité croissante */
    public void sortByPriority() {
        tasks.sort(Comparator.comparingInt(Task::getPriority));
    }
}