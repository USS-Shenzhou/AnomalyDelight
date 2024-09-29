package cn.ussshenzhou.anomalydelight.item;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
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
            .effect(() -> new MobEffectInstance(ModEffects.RANDOM_VARIABLE_SOUP, 6000, 0), 1.0F)
            .build();

    public static final FoodProperties COOKED_DRAGON_EGG = new FoodProperties.Builder()
            /*TODO .food*/
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
