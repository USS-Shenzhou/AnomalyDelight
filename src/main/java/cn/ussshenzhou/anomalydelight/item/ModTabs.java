package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static cn.ussshenzhou.anomalydelight.AnomalyDelight.MODID;
import static cn.ussshenzhou.anomalydelight.item.ModItems.*;

/**
 * @author mafuyu33
 */
public class ModTabs {
    public static final DeferredRegister<CreativeModeTab> AD_CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Supplier<CreativeModeTab> ANOMALY_DELIGHT =
            AD_CREATIVE_TABS.register("anomaly_delight",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.ad"))
                            .icon(() -> TSCP.get().getDefaultInstance())
                            .displayItems((itemDisplayParameters, output) -> output.acceptAll(Stream.of(
                                    TSCP,
                                    MAFISH,
                                    HASOKFISH,
                                    COOKED_HASOKFISH,
                                    HASOKFISH_SANDWICH,
                                    SPINNING_SUSHI,
                                    GRAND_LIBRARY_ESSENCE_COFFEE,
                                    THAUMATURGIC_WATERMELON_JUICE,
                                    SPRING_BREATH_QINGTUAN,
                                    AGED_SAKURA_SWEET_SAKE,
                                    STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH,
                                    LONG_AWAITED_CENTURY_SOUP,
                                    RADISH_CANE_SEA_BREEZE_SWEET_SOUP,
                                    HOMESTYLE_CANNED_CHEESE_BAKED_RICE,
                                    GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE,
                                    SHANGHAI_SUNRISE_COCKTAIL,
                                    ROMANCE_AND_ENCOUNTER,
                                    YOURSELF,
                                    ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD,
                                    FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE,
                                    WUTHERING_DEPTH,
                                    MULTIDIMENSIONAL_SHULKER_BISQUE,
                                    DEEP_OCEAN_BURGER
                            ).map(s -> new ItemStack(s.get())).toList())).build());
}
