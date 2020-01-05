package com.dmslob.factorymethod.v1;

/**
 * In Factory Method we have an interface (Blacksmith) with a method for
 * creating objects (manufactureWeapon). The concrete subclasses (OrcBlacksmith,
 * ElfBlacksmith) then override the method to produce objects of their liking.
 */

// 1.  java.util.Calendar, ResourceBundle and NumberFormat getInstance()
// methods uses Factory pattern.
// 2.  valueOf() method in wrapper classes like Boolean, Integer etc.
public class App {

    public static void main(String[] args) {

        Blacksmith blacksmith = new OrcBlacksmith();
        Weapon weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
        System.out.println(weapon);

        weapon = blacksmith.manufactureWeapon(WeaponType.AXE);
        System.out.println(weapon);

        blacksmith = new ElfBlacksmith();
        weapon = blacksmith.manufactureWeapon(WeaponType.SHORT_SWORD);
        System.out.println(weapon);

        weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR);
        System.out.println(weapon);
    }
}
