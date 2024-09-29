package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author mafuyu33
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnomalyDelight.MODID);

    public static final Supplier<Item> TSCP = ITEMS.register("tscp",
            () -> new ThaumaturgyStandardCookingPotBlockItem(ModBlocks.TSCP.get(), new Item.Properties()
                    .stacksTo(1)
            )
    );

    public static DeferredItem<Item> registerItem(String name, Supplier<Item> itemSupplier){
        return ITEMS.register(name,itemSupplier);
    }

    public static final Supplier<Item> MAFISH = ITEMS.register("mafish",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            .fireResistant()
                            .food(ModFoodProperties.MAFISH)
            )
    );

    public static final DeferredItem<Item> HOT_DRAGON_EGG = ITEMS.register("hot_dragon_egg",()->
            new Item(new Item.Properties().stacksTo(1).fireResistant()));
    public static final DeferredItem<Item> COOKED_DRAGON_EGG = ITEMS.register("cooked_dragon_egg",()->
            new Item(new Item.Properties().stacksTo(16).fireResistant().food(ModFoodProperties.COOKED_DRAGON_EGG)));

    public static final Supplier<Item> GRAND_LIBRARY_ESSENCE_COFFEE = ITEMS.register("grand_library_essence_coffee",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_dream_tea_house")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xcc66ff)
            )
    );

    public static final Supplier<Item> THAUMATURGIC_WATERMELON_JUICE = ITEMS.register("thaumaturgic_watermelon_juice",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_24h_fast_food_convenience_store")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x66ccff)
            )
    );

    public static final Supplier<Item> SPRING_BREATH_QINGTUAN = ITEMS.register("spring_breath_qingtuan",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.spring_breath_qingtuan.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_moms_cooking")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xffcc99)
            )
    );

    public static final Supplier<Item> AGED_SAKURA_SWEET_SAKE = ITEMS.register("aged_sakura_sweet_sake",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_moms_cooking")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xffcc99)
            )
    );

    public static final Supplier<Item> STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH = ITEMS.register("starry_sky_white_flavor_railway_grilled_fish",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.starry_sky_white_flavor_railway_grilled_fish.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> LONG_AWAITED_CENTURY_SOUP = ITEMS.register("long_awaited_century_soup",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.long_awaited_century_soup.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> RADISH_CANE_SEA_BREEZE_SWEET_SOUP = ITEMS.register("radish_cane_sea_breeze_sweet_soup",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.radish_cane_sea_breeze_sweet_soup.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> HOMESTYLE_CANNED_CHEESE_BAKED_RICE = ITEMS.register("homestyle_canned_cheese_baked_rice",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.homestyle_canned_cheese_baked_rice.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE = ITEMS.register("golden_cupcakes_with_banded_agate_chocolate",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.golden_cupcakes_with_banded_agate_chocolate.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> SHANGHAI_SUNRISE_COCKTAIL = ITEMS.register("shanghai_sunrise_cocktail",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    Component.translatable("item.anomaly_delight.shanghai_sunrise_cocktail.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> ROMANCE_AND_ENCOUNTER = ITEMS.register("romance_and_encounter",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_leaf_house")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x70ad47)
            )
    );

    public static final Supplier<Item> YOURSELF = ITEMS.register("yourself",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x000099)
            )
    );

    public static final Supplier<Item> ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD = ITEMS.register("roasted_millennium_bug_with_binary_tree_wood",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x000099)
            )
    );

    public static final Supplier<Item> FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE = ITEMS.register("fried_echo_shards_with_aged_rose_sauce",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_minecraft_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x000099)
            )
    );

    public static final Supplier<Item> WUTHERING_DEPTH = ITEMS.register("wuthering_depth",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_minecraft_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b)
            )
    );

    public static final Supplier<Item> MULTIDIMENSIONAL_SHULKER_BISQUE = ITEMS.register("multidimensional_shulker_bisque",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_minecraft_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b)
            )
    );

    public static final Supplier<Item> DEEP_OCEAN_BURGER = ITEMS.register("deep_ocean_burger",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                    /*TODO .food*/,
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_minecraft_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b)
            )
    );
}
