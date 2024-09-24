package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static cn.ussshenzhou.anomalydelight.AnomalyDelight.MODID;

/**
 * @author mafuyu33
 */
public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> AD_CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ANOMALY_DELIGHT =
            AD_CREATIVE_TABS.register("anomaly_delight",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.ad"))
                            .icon(() -> ModItems.MAFISH.get().getDefaultInstance())
                            .displayItems((itemDisplayParameters, output) -> output.accept(ModItems.MAFISH.get())).build());
}
