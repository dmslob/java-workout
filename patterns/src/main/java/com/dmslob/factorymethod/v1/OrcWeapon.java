package com.dmslob.factorymethod.v1;

public class OrcWeapon implements Weapon {

    private WeaponType weaponType;

    public OrcWeapon(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public String toString() {
        return "Orcish " + weaponType;
    }
}
