package cn.ussshenzhou.anomalydelight;

import cn.ussshenzhou.anomalydelight.block.ModBlocks;
import cn.ussshenzhou.anomalydelight.block.container.ModMenuTypes;
import cn.ussshenzhou.anomalydelight.block.entity.ModBlockEntityTypes;
import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import cn.ussshenzhou.anomalydelight.item.ModItems;
import cn.ussshenzhou.anomalydelight.item.ModTabs;

import cn.ussshenzhou.anomalydelight.recipe.ModRecipeSerializers;
import cn.ussshenzhou.anomalydelight.recipe.ModRecipeTypes;
import cn.ussshenzhou.anomalydelight.potion.ModPotions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

/**
 * @author USS_Shenzhou
 */
@Mod(AnomalyDelight.MODID)
public class AnomalyDelight {
    public static final String MODID = "anomaly_delight";

    public AnomalyDelight(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.ITEMS.register(modEventBus);
        ModTabs.AD_CREATIVE_TABS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModRecipeTypes.RECIPE_TYPES.register(modEventBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
        ModEffects.EFFECTS.register(modEventBus);
        ModPotions.POTIONS.register(modEventBus);
    }
}
