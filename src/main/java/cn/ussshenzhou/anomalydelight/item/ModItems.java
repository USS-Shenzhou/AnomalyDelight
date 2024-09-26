package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.block.ModBlocks;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author mafuyu33
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnomalyDelight.MODID);

    public static final Supplier<Item> MAFISH = ITEMS.register("mafish",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            .fireResistant()
                            .food(ModFoodProperties.MAFISH)
            )
    );

    public static final Supplier<Item> TSCP = ITEMS.register("tscp",
            () -> new ThaumaturgyStandardCookingPotBlockItem(ModBlocks.TSCP.get(), new Item.Properties()
                    .stacksTo(1)
            )
    );
}
