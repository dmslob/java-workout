package com.dmslob.livelock;

public class Criminal {

    private boolean hostageReleased = false;

    public void releaseHostage(Police police) {
        while (!police.isMoneySent()) {
            System.out.println("Criminal: waiting police to give ransom");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Criminal: released hostage");
        this.hostageReleased = true;
    }

    boolean isHostageReleased() {
        return this.hostageReleased;
    }
}
