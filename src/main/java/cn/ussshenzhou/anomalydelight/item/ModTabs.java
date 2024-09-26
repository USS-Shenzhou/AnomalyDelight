package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static cn.ussshenzhou.anomalydelight.AnomalyDelight.MODID;
import static cn.ussshenzhou.anomalydelight.item.ModItems.MAFISH;
import static cn.ussshenzhou.anomalydelight.item.ModItems.TSCP;

/**
 * @author mafuyu33
 */
@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> AD_CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Supplier<CreativeModeTab> ANOMALY_DELIGHT =
            AD_CREATIVE_TABS.register("anomaly_delight",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.ad"))
                            .icon(() -> MAFISH.get().getDefaultInstance())
                            .displayItems((itemDisplayParameters, output) -> output.accept(MAFISH.get())).build());

    @SubscribeEvent
    public static void onTabCreated(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab() == ANOMALY_DELIGHT.get()) {
            event.acceptAll(Stream.of(
                    TSCP
            ).map(s -> new ItemStack(s.get())).toList());
        }
    }
}
