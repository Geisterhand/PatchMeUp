package eu.mccluster.patchmeup.listener;

import com.pixelmonmod.pixelmon.config.PixelmonItems;
import eu.mccluster.patchmeup.PatchMeUp;
import eu.mccluster.patchmeup.config.PMUConfig;
import eu.mccluster.patchmeup.utils.Utils;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AbilityPatchUseEvent {

    final PMUConfig _config;
    final PatchMeUp _mod;

    public AbilityPatchUseEvent(PatchMeUp patchMeUp, PMUConfig config) {
        this._mod = patchMeUp;
        _config = config;
    }

    @SubscribeEvent
    public void onPatchUse(PlayerInteractEvent.EntityInteract event) {
       Item eventItem = event.getItemStack().getItem();
       if(!(eventItem == PixelmonItems.abilityPatch)) {
           return;
       }

        if(event.getHand() == EnumHand.OFF_HAND) {
            return;
        }

       int chance = (int)(Math.random() * 100);
       if(chance > _config.patchChance) {
           event.setCanceled(true);
           event.getEntityPlayer().sendMessage(Utils.toText(Utils.regex(_config.failMessage)));
           event.getEntityPlayer().inventory.clearMatchingItems(eventItem, event.getItemStack().getMetadata(), 1, event.getItemStack().getTagCompound());
       }
    }
}
