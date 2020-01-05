package com.dmslob.condition;

import java.util.concurrent.*;

public class HandoffMain {

    public static void main(String[] args) throws InterruptedException {
        Handoff<Object> handoff;
        handoff = new IntrinsicHandoff<>();
//        handoff = new ExplicitHandoff<>();
//        handoff = new AqsHandoff<>();

        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<Void> cs = new ExecutorCompletionService<Void>(executorService);
        int numTakers = 3;
        for (int i = 0; i < numTakers; i++) {
            cs.submit(new HandoffTaker(handoff), null);
        }

        int numGivers = numTakers;
        for (int i = 0; i < numGivers; i++) {
            cs.submit(new HandoffGiver(handoff), null);
        }

        for (int i = 0; i < numGivers + numTakers; i++) {
            Future<Void> future = cs.take();
            try {
                future.get();
            } catch (ExecutionException e) {
                System.out.println("Task failed with " + e.getCause());
            }
        }

        executorService.shutdown();
    }

}
