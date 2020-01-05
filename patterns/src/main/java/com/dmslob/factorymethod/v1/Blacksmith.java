package com.dmslob.factorymethod.v1;

/**
 * The interface containing method for producing objects.
 */
public interface Blacksmith {

    Weapon manufactureWeapon(WeaponType weaponType);
}
