package com.dmslob.factorymethod.v1;

/**
 * Concrete subclass for creating new objects.
 */
public class OrcBlacksmith implements Blacksmith {

    public Weapon manufactureWeapon(WeaponType weaponType) {
        return new OrcWeapon(weaponType);
    }
}
