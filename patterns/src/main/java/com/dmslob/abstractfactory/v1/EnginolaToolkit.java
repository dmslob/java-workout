package com.dmslob.abstractfactory.v1;

public class EnginolaToolkit extends AbstractFactory {

    @Override
    public CPU createCPU() {
        return new EnginolaCPU();
    }

    @Override
    public MMU createMMU() {
        return new EnginolaMMU();
    }
}

enum Architecture {
    ENGINOLA, EMBER
}

// class CPU
abstract class CPU {
}

// class EmberCPU
class EmberCPU extends CPU {
}

// class EnginolaCPU
class EnginolaCPU extends CPU {
}

// class MMU
abstract class MMU {
}

// class EmberMMU
class EmberMMU extends MMU {
}

// class EnginolaMMU
class EnginolaMMU extends MMU {
}
