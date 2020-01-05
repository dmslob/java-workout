package com.dmslob.servicelocator;

/**
 * Created by Dmytro_Slobodenyuk on 8/9/2018.
 */
public class EmailService implements MessageService {

    private final String serviceName;
    private final int id;
    //ProxyFactory pf = new ProxyFactory();

    public EmailService(String serviceName) {
        this.serviceName = serviceName;

        this.id = (int) Math.floor(Math.random() * 1000) + 1;
    }

    @Override
    public String getName() {
        return serviceName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void execute() {

        //System.out.println("Service {} is now executing with id {}", getName(), getId());
    }
}
