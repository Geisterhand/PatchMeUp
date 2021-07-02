package eu.mccluster.patchmeup;

import eu.mccluster.dependency.deploader.api.DependencyLoader;
import eu.mccluster.dependency.deploader.api.DependencyLoaderApi;
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
        initDependencies();
        PatchMeUp.load();
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        PatchMeUp.enable(event);
        logger.info("Started PatchMeUp!");
    }

    private void initDependencies() {
        final DependencyLoaderApi depLoader = DependencyLoader.getInstance(this);
        depLoader.loadDependency("eu.mccluster.dependency:configmanager-dependency:1.1");

    }
}