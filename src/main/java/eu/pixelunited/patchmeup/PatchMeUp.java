package eu.pixelunited.patchmeup;

import eu.pixelunited.patchmeup.config.PMUConfig;
import eu.pixelunited.patchmeup.listener.AbilityPatchUseEvent;
import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PatchMeUp {

    public static final Path PATH = ConfigManagement.getInstance().getPluginFolder();

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
        _config = ConfigManagement.getInstance().loadConfig(PMUConfig.class, Paths.get(PATH + File.separator + "Config.yml"));
        onReload();
        registerListeners();
    }

    public void onReload() {
        _config = ConfigManagement.getInstance().loadConfig(PMUConfig.class, Paths.get(PATH + File.separator + "Config.yml"));
    }

    private void registerListeners() {
        AbilityPatchUseEvent abilityPatchUseEvent = new AbilityPatchUseEvent(this);
        MinecraftForge.EVENT_BUS.register(abilityPatchUseEvent);
    }

}
