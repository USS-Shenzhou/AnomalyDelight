package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

/**
 * @author mafuyu33
 */
public class ModFoodProperties {
    public static final FoodProperties MAFISH = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.JUMP), 1)
            .build();
}
