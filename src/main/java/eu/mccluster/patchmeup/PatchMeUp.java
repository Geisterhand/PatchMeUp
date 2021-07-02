package eu.mccluster.patchmeup;

import eu.mccluster.patchmeup.config.PMUConfig;
import eu.mccluster.patchmeup.listener.AbilityPatchUseEvent;
import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;

public class PatchMeUp {

    @Getter
    private static PMUConfig _config;

    @Getter
    private static PatchMeUp _instance;

    public static void load() {
        if(_instance == null) {
            _instance = new PatchMeUp();
        }
    }

    public static void enable(FMLServerStartingEvent event){
        _instance.onEnable(event);
    }

    private void onEnable(FMLServerStartingEvent event) {
        _config = new PMUConfig(new File(PatchMeUpMod.getInstance().getDataFolder(), "Config.conf"));
        onReload();
        registerListeners();
    }

    public void onReload() {
        _config.load();
    }

    private void registerListeners() {
        AbilityPatchUseEvent abilityPatchUseEvent = new AbilityPatchUseEvent(this, _config);
        MinecraftForge.EVENT_BUS.register(abilityPatchUseEvent);
    }

}
