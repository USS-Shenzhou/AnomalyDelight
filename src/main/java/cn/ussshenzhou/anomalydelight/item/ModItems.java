package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author mafuyu33
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnomalyDelight.MODID);

    public static final DeferredItem<Item> MAFISH = ITEMS.register("mafish",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            .fireResistant()
                            .food(ModFoodProperties.MAFISH)
            )
    );
}
