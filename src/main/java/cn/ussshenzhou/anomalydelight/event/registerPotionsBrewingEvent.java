package cn.ussshenzhou.anomalydelight.event;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.item.ModItems;
import cn.ussshenzhou.anomalydelight.potion.ModPotions;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

@EventBusSubscriber(modid = AnomalyDelight.MODID)
public class registerPotionsBrewingEvent {

    @SubscribeEvent
    public static void onRegisterPotionsBrewing(RegisterBrewingRecipesEvent event){
        PotionBrewing.Builder builder = event.getBuilder();

        // 旋转药水
        builder.addMix(Potions.AWKWARD,
                ModItems.SPINNING_SUSHI.get(), ModPotions.SPIN_POTION);
    }
}
