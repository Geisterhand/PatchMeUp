package eu.pixelunited.patchmeup.listener;

import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import eu.pixelunited.patchmeup.ConfigManagement;
import eu.pixelunited.patchmeup.PatchMeUp;
import eu.pixelunited.patchmeup.config.PMUConfig;
import eu.pixelunited.patchmeup.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.nio.file.Paths;

public class AbilityPatchUseEvent {

    final PatchMeUp _mod;

    public AbilityPatchUseEvent(PatchMeUp patchMeUp) {
        this._mod = patchMeUp;
    }

    @SubscribeEvent
    public void onPatchUse(PlayerInteractEvent.EntityInteract event) {
       Item eventItem = event.getItemStack().getItem();


       if(!(event.getTarget() instanceof EntityPixelmon)) {
           return;
       }

       if(!(eventItem == PixelmonItems.abilityPatch)) {
           return;
       }

        if(event.getHand() == EnumHand.OFF_HAND) {
            return;
        }

       int chance = (int)(Math.random() * 100 + 1);
        EntityPixelmon pixelmon = (EntityPixelmon) event.getTarget();
        PMUConfig _config = ConfigManagement.getInstance().loadConfig(PMUConfig.class, Paths.get(PatchMeUp.PATH + File.separator + "Config.yml"));
       if(chance > _config.patchChance) {
           event.setCanceled(true);
           event.getEntityPlayer().sendMessage(Utils.toText(Utils.regex(_config.failMessage)));
           event.getEntityPlayer().inventory.clearMatchingItems(eventItem, event.getItemStack().getMetadata(), 1, event.getItemStack().getTagCompound());
           return;

       }

        if(_config.makeUnbreedable) {
            pixelmon.getPokemonData().addSpecFlag("unbreedable");
        }

        if(_config.makeUntradeable) {
            pixelmon.getPokemonData().addSpecFlag("untradeable");
        }
    }
}
