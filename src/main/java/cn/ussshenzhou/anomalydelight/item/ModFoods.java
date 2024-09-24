package cn.ussshenzhou.anomalydelight.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties Mafish = new FoodProperties.Builder().nutrition(4)
            .saturationModifier(0.3f).alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.JUMP),1).build();

    public static final FoodProperties COOKED_DRAGON_EGG = new FoodProperties.Builder()
            // 恢复的饥饿值
            .nutrition(8)
            // 饱和度
            .saturationModifier(0.3f)
            // 总是可以食用（即使饱腹）
            .alwaysEdible()
            // 力量 III，持续无限时间
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, -1, 2), 1.0F)
            .build();
}
