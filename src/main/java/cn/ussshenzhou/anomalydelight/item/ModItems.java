package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.block.ModBlocks;
import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import cn.ussshenzhou.t88.task.RepeatTask;
import cn.ussshenzhou.t88.task.TaskHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdStructure;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author mafuyu33
 */
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnomalyDelight.MODID);

    public static DeferredItem<Item> registerItem(String name, Supplier<Item> itemSupplier){
        return ITEMS.register(name,itemSupplier);
    }

    public static final Supplier<Item> MAFISH = ITEMS.register("mafish",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(64)
                            .food(ModFoodProperties.MAFISH)
            )
    );

    public static final Supplier<Item> MAFISH_SOUP = ITEMS.register("mafish_soup",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(1)
                            .food(ModFoodProperties.MAFISH_SOUP)
            )
    );

    public static final Supplier<Item> HASOKFISH = ITEMS.register("hasokfish",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(64)
                            .fireResistant()
            )
    );

    public static final Supplier<Item> COOKED_HASOKFISH = ITEMS.register("cooked_hasokfish",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(64)
                            .fireResistant()
            )
    );
    public static final Supplier<Item> HASOKFISH_SANDWICH = ITEMS.register("hasokfish_sandwich",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(16)
                            .fireResistant()
            )
    );

    public static final Supplier<Item> TSCP = ITEMS.register("tscp",
            () -> new ThaumaturgyStandardCookingPotBlockItem(ModBlocks.TSCP.get(), new Item.Properties()
                    .stacksTo(1)
            )
    );
    public static final Supplier<Item> SPINNING_SUSHI = ITEMS.register("spinning_sushi",
            () -> new Item(
                    new Item.Properties()
                            .stacksTo(64)
                            .food(ModFoodProperties.SPINNING_SUSHI)
            )
    );

    @SuppressWarnings("unchecked")
    public static final Supplier<Item> GRAND_LIBRARY_ESSENCE_COFFEE = ITEMS.register("grand_library_essence_coffee",
            () -> new BaseAnomalyDelightDrink(
                    new Item.Properties()
                            .stacksTo(16)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_dream_tea_house")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xcc66ff),
                    entity -> {
                        var level = (ServerLevel) entity.getLevel();
                        if (level == null) {
                            return false;
                        }
                        var structures = level.structureManager().startsForStructure(new ChunkPos(entity.getBlockPos()), structure -> structure instanceof StrongholdStructure);
                        if (structures.isEmpty()) {
                            return false;
                        }
                        for (var structure : structures) {
                            var libraries = structure.getPieces().stream().filter(structurePiece -> structurePiece instanceof StrongholdPieces.Library).toList();
                            for (var library : libraries) {
                                if (library.getBoundingBox().isInside(entity.getBlockPos())) {
                                    return true;
                                }
                            }
                        }
                        return false;
                    },
                    //TODO hiddenEffects
                    eater -> eater.addEffect(new MobEffectInstance((Holder<MobEffect>) ModEffects.GRAND_LIBRARY_ESSENCE_COFFEE, 300 * 20, 0, false, false, false))
            )
    );

    public static final Supplier<Item> THAUMATURGIC_WATERMELON_JUICE = ITEMS.register("thaumaturgic_watermelon_juice",
            () -> new BaseAnomalyDelightDrink(
                    new Item.Properties()
                            .stacksTo(16)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_24h_fast_food_convenience_store")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x66ccff),
                    null,
                    eater -> {
                        eater.heal(4);
                        var toRemove = eater.getActiveEffects().stream().filter(e -> e.getEffect().value().getCategory() == MobEffectCategory.HARMFUL).toList();
                        toRemove.forEach(e -> eater.removeEffect(e.getEffect()));
                    }
            )
    );

    public static final Supplier<Item> SPRING_BREATH_QINGTUAN = ITEMS.register("spring_breath_qingtuan",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.SPRING_BREATH_QINGTUAN),
                    Component.translatable("item.anomaly_delight.spring_breath_qingtuan.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_moms_cooking")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xffcc99)
            )
    );

    public static final Supplier<Item> AGED_SAKURA_SWEET_SAKE = ITEMS.register("aged_sakura_sweet_sake",
            () -> new BaseAnomalyDelightDrink(
                    new Item.Properties()
                            .stacksTo(16)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_moms_cooking")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xffcc99),
                    null,
                    eater -> {
                        if (!eater.level().isClientSide) {
                            var toRemove = eater.getActiveEffects().stream().filter(e -> e.getEffect().value().getCategory() == MobEffectCategory.HARMFUL).toList();
                            toRemove.forEach(e -> eater.removeEffect(e.getEffect()));
                            eater.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60 * 20));
                        }
                    }
            )
    );

    public static final Supplier<Item> STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH = ITEMS.register("starry_sky_white_flavor_railway_grilled_fish",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH),
                    Component.translatable("item.anomaly_delight.starry_sky_white_flavor_railway_grilled_fish.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084),
                    null,
                    eater -> {
                        if (eater.level().isClientSide) {
                            eater.level().playLocalSound(eater, SoundEvents.MINECART_RIDING, SoundSource.PLAYERS, 0.4f, 1);
                        }
                    }
            )
    );

    public static final Supplier<Item> LONG_AWAITED_CENTURY_SOUP = ITEMS.register("long_awaited_century_soup",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.LONG_AWAITED_CENTURY_SOUP),
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
                            .food(ModFoodProperties.RADISH_CANE_SEA_BREEZE_SWEET_SOUP),
                    Component.translatable("item.anomaly_delight.radish_cane_sea_breeze_sweet_soup.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084),
                    entity -> entity.getLevel() != null && entity.getLevel().getBiome(entity.getBlockPos()) == Biomes.BEACH
            )
    );

    public static final Supplier<Item> HOMESTYLE_CANNED_CHEESE_BAKED_RICE = ITEMS.register("homestyle_canned_cheese_baked_rice",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.HOMESTYLE_CANNED_CHEESE_BAKED_RICE),
                    Component.translatable("item.anomaly_delight.homestyle_canned_cheese_baked_rice.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084),
                    null,
                    eater -> {
                        if (!eater.level().isClientSide) {
                            var toRemove = eater.getActiveEffects().stream().filter(e -> e.getEffect().value().getCategory() == MobEffectCategory.HARMFUL).toList();
                            toRemove.forEach(e -> eater.removeEffect(e.getEffect()));
                        }
                    }
            )
    );

    public static final Supplier<Item> GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE = ITEMS.register("golden_cupcakes_with_banded_agate_chocolate",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE),
                    Component.translatable("item.anomaly_delight.golden_cupcakes_with_banded_agate_chocolate.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084)
            )
    );

    public static final Supplier<Item> SHANGHAI_SUNRISE_COCKTAIL = ITEMS.register("shanghai_sunrise_cocktail",
            () -> new BaseAnomalyDelightDrink(
                    new Item.Properties()
                            .stacksTo(16)
                            .craftRemainder(Items.GLASS_BOTTLE),
                    Component.translatable("item.anomaly_delight.shanghai_sunrise_cocktail.desc")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xaaaaaa),
                    Component.translatable("item.ad.restaurant.ambrose_dust_and_dreams_tavern")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xf4b084),
                    null,
                    eater -> eater.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 2))
            )
    );

    public static final Supplier<Item> ROMANCE_AND_ENCOUNTER = ITEMS.register("romance_and_encounter",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.ROMANCE_AND_ENCOUNTER),
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_leaf_house")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x70ad47),
                    null,
                    eater -> {
                        if (eater.level().isClientSide) {
                            return;
                        }
                        var players = eater.level().getNearbyPlayers(TargetingConditions.forNonCombat(), eater, eater.getBoundingBox().inflate(2));
                        var effect = switch (players.size()) {
                            case 0 -> new MobEffectInstance(MobEffects.WEAKNESS, 71 * 20, 0);
                            case 1 -> new MobEffectInstance(MobEffects.REGENERATION, 520 * 20, 0);
                            default -> new MobEffectInstance(MobEffects.CONFUSION, 30 * 20, 0);
                        };
                        eater.addEffect(effect);
                    }
            )
    );

    public static final Supplier<Item> YOURSELF = ITEMS.register("yourself",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.YOURSELF),
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
                            .food(ModFoodProperties.ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD),
                    null,
                    Component.translatable("item.ad.restaurant.ambrose_data_layer_branch")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0x000099)
            )
    );

    @SuppressWarnings("unchecked")
    public static final Supplier<Item> FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE = ITEMS.register("fried_echo_shards_with_aged_rose_sauce",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE),
                    null,
                    Component.translatable("item.ad.restaurant.shenzhou")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b),
                    null,
                    eater -> eater.addEffect(new MobEffectInstance((Holder<MobEffect>) ModEffects.FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE, 120 * 20, 0, false, false, false))
            )
    );

    public static final Supplier<Item> WUTHERING_DEPTH = ITEMS.register("wuthering_depth",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.WUTHERING_DEPTH),
                    null,
                    Component.translatable("item.ad.restaurant.shenzhou")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b),
                    null,
                    eater -> {
                        //noinspection unchecked
                        eater.addEffect(new MobEffectInstance((Holder<MobEffect>) ModEffects.WUTHERING_DEPTH, 120 * 20, 0, false, false, false));
                        if (!eater.level().isClientSide && eater instanceof Player player) {
                            //FIXME
                            eater.level().playSeededSound(player, eater.position().x, eater.position().y, eater.position().z, SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.PLAYERS, 1, 1, 42L);
                        }
                    }
            )
    );

    public static final Supplier<Item> MULTIDIMENSIONAL_SHULKER_BISQUE = ITEMS.register("multidimensional_shulker_bisque",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.MULTIDIMENSIONAL_SHULKER_BISQUE),
                    null,
                    Component.translatable("item.ad.restaurant.shenzhou")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b)
            )
    );

    public static final Supplier<Item> DEEP_OCEAN_BURGER = ITEMS.register("deep_ocean_burger",
            () -> new BaseAnomalyDelightMeal(
                    new Item.Properties()
                            .stacksTo(16)
                            .food(ModFoodProperties.DEEP_OCEAN_BURGER),
                    null,
                    Component.translatable("item.ad.restaurant.shenzhou")
                            .withStyle(ChatFormatting.ITALIC)
                            .withColor(0xff2e3b),
                    null,
                    eater -> {
                        //TODO tp
                        var level = eater.level();
                        if (level.isClientSide) {
                            return;
                        }
                        var uuid = eater.getUUID();
                        TaskHelper.addServerTask(new RepeatTask(() -> {
                            var e = level.getPlayerByUUID(uuid);
                            if (e != null) {
                                e.removeEffect(MobEffects.DIG_SLOWDOWN);
                            }
                        }, 0, 20) {
                            @SuppressWarnings("FieldCanBeLocal")
                            private final int life = 300 * 20;
                            private int age = 0;

                            @Override
                            public void tick() {
                                if (age >= life) {
                                    this.cancel();
                                }
                                super.tick();
                                age++;
                            }
                        });
                    }
            )
    );
}
