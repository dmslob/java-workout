package com.dmslob.templatemethod;

import com.dmslob.templatemethod.canonical.User;

public class ModernTemplateMethodClient {

    public static void main(String[] args) {
        LoggingUserStorage storage = new LoggingUserStorage();
        User mikalai = new ModernUserRestorer(storage::store).restore("Mikalai:32");
        System.out.println("Mikalai is restored: " + mikalai);

        // for unit tests
        mikalai = new ModernUserRestorer(user -> 5L).restore("Mikalai:32");
        System.out.println("Mikalai is restored: " + mikalai);
    }
}
