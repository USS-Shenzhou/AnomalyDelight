package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnomalyDelight.MODID);

    public static final DeferredItem<Item> MAFISH = ITEMS.register("mafish",()->
            new Item(new Item.Properties().stacksTo(1).fireResistant().food(ModFoods.Mafish)));

    public static final DeferredItem<Item> HOT_DRAGON_EGG = ITEMS.register("hot_dragon_egg",()->
            new Item(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> COOKED_DRAGON_EGG = ITEMS.register("cooked_dragon_egg",()->
            new Item(new Item.Properties().stacksTo(16).fireResistant().food(ModFoods.COOKED_DRAGON_EGG)));

    public static DeferredItem<Item> registerItem(String name, Supplier<Item> itemSupplier){
        return ITEMS.register(name,itemSupplier);
    }
}
