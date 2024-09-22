package cn.ussshenzhou.anomalydelight;

import cn.ussshenzhou.anomalydelight.item.ModItems;
import cn.ussshenzhou.anomalydelight.item.ModTabs;

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
    }
}
