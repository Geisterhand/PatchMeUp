package eu.pixelunited.patchmeup;

import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(
        modid = "patchmeup",
        name = "PatchMeUp",
        version = "@VERSION@",
        acceptableRemoteVersions = "*"
)
public class PatchMeUpMod {

    private Logger logger;

    @Getter
    private static PatchMeUpMod _instance;

    @Getter
    private File _dataFolder;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        _dataFolder = new File(event.getModConfigurationDirectory(), "patchmeup");
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        _instance = this;
        PatchMeUp.load();
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        PatchMeUp.enable(event);
        logger.info("Started PatchMeUp!");
    }

}