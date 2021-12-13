package eu.pixelunited.patchmeup.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class PMUConfig {

    private PMUConfig() {}


    public int patchChance = 25;

    public String failMessage = "§7The patch is rejected by your pokemon and breaks in the process!";

    public boolean makeUnbreedable = false;

    public boolean makeUntradeable = false;


}
