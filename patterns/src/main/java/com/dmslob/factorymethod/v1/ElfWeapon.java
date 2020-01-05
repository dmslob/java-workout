package com.dmslob.factorymethod.v1;

public class ElfWeapon implements Weapon {

    private WeaponType weaponType;

    public ElfWeapon(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public String toString() {
        return "Elven " + weaponType;
    }
}
