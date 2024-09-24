package cn.ussshenzhou.anomalydelight.datagen;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

/**
 * @author USS_Shenzhou
 */
@EventBusSubscriber(modid = AnomalyDelight.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDataGenListener {

    @SubscribeEvent
    public static void dataGen(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = event.getGenerator().getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new AdvancementProvider(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator())));
    }
}
