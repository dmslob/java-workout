package com.dmslob.tasks;

import java.util.*;

public class TaskSchedulerLPT {

    static class Worker implements Comparable<Worker> {
        int id;
        int load; // total time assigned to this worker

        Worker(int id) {
            this.id = id;
            this.load = 0;
        }

        @Override
        public int compareTo(Worker other) {
            // Compare by load (lower load = higher priority)
            return Integer.compare(this.load, other.load);
        }

        @Override
        public String toString() {
            return "Worker %s (load=%s)".formatted(id, load);
        }
    }

    public record Task(int durationMs) {
    }

    public List<Worker> schedule(List<Task> tasks, int k) {
        List<Task> sorted = tasks.stream()
                .sorted(Comparator.comparingLong(Task::durationMs).reversed())
                .toList();
        PriorityQueue<Worker> heap = new
                PriorityQueue<>(Comparator.comparingLong(w -> w.load));
        for (int i = 0; i < k; i++) heap.add(new Worker(i));
        for (Task t : sorted) {
            Worker w = heap.poll();
            //w.assign(t.durationMs);
            heap.add(w);
        }
        return new ArrayList<>(heap);
    }

    public static Map<Integer, List<Integer>> assignTasks(int[] tasks, int k) {
        // Sort tasks descending by time
        Integer[] sortedTasks = Arrays.stream(tasks).boxed()
                .sorted(Comparator.reverseOrder())
                .toArray(Integer[]::new);
        // Priority queue of workers, ordered by current total load
        PriorityQueue<Worker> pq = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            pq.add(new Worker(i));
        }
        // Result mapping: workerId -> list of assigned tasks
        Map<Integer, List<Integer>> assignment = new HashMap<>();
        for (int i = 0; i < k; i++) {
            assignment.put(i, new ArrayList<>());
        }
        // Assign each task to the least-loaded worker
        for (int t : sortedTasks) {
            Worker w = pq.poll(); // worker with the smallest current load
            assert w != null;
            assignment.get(w.id).add(t);
            w.load += t;
            pq.add(w); // reinsert with updated load
        }
        return assignment;
    }

    public static void main(String[] args) {
        int[] tasks = {7, 2, 5, 10, 8, 3};
        int k = 3;

        var loads = Arrays.stream(tasks)
                .mapToObj(Task::new)
                .toList();
        List<Task> sortedLoads = loads.stream()
                .sorted(Comparator.comparingLong(Task::durationMs).reversed())
                .toList();

        Map<Integer, List<Integer>> result = assignTasks(tasks, k);

        // Print assignment
        for (Map.Entry<Integer, List<Integer>> e : result.entrySet()) {
            System.out.println(STR."Worker \{e.getKey()}: \{e.getValue()}");
        }
        // Compute total completion time (make span)
        int makespan = result.entrySet().stream()
                .mapToInt(e -> e.getValue().stream().mapToInt(Integer::intValue).sum())
                .max().orElse(0);
        System.out.println(STR."Total completion time (makespan): \{makespan}");
    }
}
