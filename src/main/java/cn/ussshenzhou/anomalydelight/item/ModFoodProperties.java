package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import vectorwing.farmersdelight.common.registry.ModEffects;

import static net.minecraft.world.effect.MobEffects.*;
import static vectorwing.farmersdelight.common.registry.ModEffects.*;

/**
 * @author mafuyu33
 */
public class ModFoodProperties {
    public static final FoodProperties MAFISH = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(JUMP), 1)
            .build();

    public static final FoodProperties SPRING_BREATH_QINGTUAN = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(COMFORT, 300 * 20), 1)
            .effect(() -> new MobEffectInstance(ABSORPTION, 300 * 20), 1)
            .build();

    public static final FoodProperties STARRY_SKY_WHITE_FLAVOR_RAILWAY_GRILLED_FISH = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(NOURISHMENT, 300 * 20), 1)
            .effect(() -> new MobEffectInstance(MOVEMENT_SPEED, 300 * 20), 1)
            .build();

    public static final FoodProperties LONG_AWAITED_CENTURY_SOUP = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(1)
            .effect(() -> new MobEffectInstance(NOURISHMENT, 420 * 20), 1)
            .effect(() -> new MobEffectInstance(COMFORT, 420 * 20), 1)
            .build();

    public static final FoodProperties RADISH_CANE_SEA_BREEZE_SWEET_SOUP = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(NOURISHMENT, 420 * 20), 1)
            .effect(() -> new MobEffectInstance(COMFORT, 420 * 20), 1)
            .build();

    public static final FoodProperties HOMESTYLE_CANNED_CHEESE_BAKED_RICE = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(1)
            .effect(() -> new MobEffectInstance(DAMAGE_RESISTANCE, 180 * 20, 1), 1)
            .effect(() -> new MobEffectInstance(NOURISHMENT, 300 * 20), 1)
            .build();

    public static final FoodProperties GOLDEN_CUPCAKES_WITH_BANDED_AGATE_CHOCOLATE = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(1.2f)
            .effect(() -> new MobEffectInstance(REGENERATION, 120 * 20, 1), 1)
            .effect(() -> new MobEffectInstance(HEALTH_BOOST, 120 * 20, 1), 1)
            .build();

    public static final FoodProperties ROMANCE_AND_ENCOUNTER = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(REGENERATION, 520 * 20), 1)
            .build();

    public static final FoodProperties YOURSELF = new FoodProperties.Builder()
            .nutrition(0)
            .saturationModifier(0)
            .build();

    public static final FoodProperties ROASTED_MILLENNIUM_BUG_WITH_BINARY_TREE_WOOD = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(1f)
            .effect(() -> new MobEffectInstance(CONFUSION, 5 * 20), 1)
            .effect(() -> new MobEffectInstance(DAMAGE_RESISTANCE, 300 * 20, 1), 1)
            .build();

    public static final FoodProperties FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(1.2f)
            .effect(() -> new MobEffectInstance(NOURISHMENT, 180 * 20), 1)
            .build();

    public static final FoodProperties WUTHERING_DEPTH = new FoodProperties.Builder()
            .nutrition(14)
            .saturationModifier(1.2f)
            .effect(() -> new MobEffectInstance(NOURISHMENT, 240 * 20), 1)
            .build();

    public static final FoodProperties MULTIDIMENSIONAL_SHULKER_BISQUE = new FoodProperties.Builder()
            .nutrition(12)
            .saturationModifier(1)
            .effect(() -> new MobEffectInstance(DAMAGE_RESISTANCE, 180 * 20), 1)
            .effect(() -> new MobEffectInstance(COMFORT, 180 * 20), 1)
            .build();

    public static final FoodProperties DEEP_OCEAN_BURGER = new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.8f)
            .effect(() -> new MobEffectInstance(WATER_BREATHING, 300 * 20), 1)
            .build();
}
