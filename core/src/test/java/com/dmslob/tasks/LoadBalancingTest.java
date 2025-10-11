package com.dmslob.tasks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class LoadBalancingTest {
    static class WorkerLoad {
        int i;
        int totalMs;

        public WorkerLoad(int i) {
            this.i = i;
        }

        public void assign(Task task) {

        }
    }

    record Task(int durationMs) {
    }

    public List<WorkerLoad> schedule(List<Task> tasks, int k) {
        List<Task> sorted = tasks.stream()
                .sorted(Comparator.comparingLong(Task::durationMs).reversed())
                .collect(Collectors.toList());
        PriorityQueue<WorkerLoad> heap = new
                PriorityQueue<>(Comparator.comparingLong(w -> w.totalMs));
        for (int i = 0; i < k; i++) heap.add(new WorkerLoad(i));
        for (Task t : sorted) {
            WorkerLoad w = heap.poll();
            w.assign(t);
            heap.add(w);
        }
        return new ArrayList<>(heap);
    }
}
