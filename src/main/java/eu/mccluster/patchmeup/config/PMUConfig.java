package eu.mccluster.patchmeup.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class PMUConfig extends Config {

    @Skip
    File _configFile;

    public PMUConfig(File file) { _configFile = file; }

    @Order(1)
    @Comment("Chance that a ability patch changes the pokemons ability. Value between 0 (patch will never work) and 100 (patch will always work)")
    public int patchChance = 25;

    @Order(2)
    @Comment("Message the player gets when the patch fails")
    public String failMessage = "§7The patch is rejected by your pokemon and breaks in the process!";

    @Order(3)
    @Comment("Make patched Pokemon unbreedable")
    public boolean makeUnbreedable = false;

    @Order(4)
    @Comment("Make patched Pokemon untradeable")
    public boolean makeUntradeable = false;


    @Override
    public File getFile() {
        return _configFile;
    }
}
