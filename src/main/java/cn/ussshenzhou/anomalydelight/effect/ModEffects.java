package cn.ussshenzhou.anomalydelight.effect;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
public class ModEffects {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, AnomalyDelight.MODID);
    //量子态
    public static final Supplier<MobEffect> RANDOM_VARIABLE_SOUP = EFFECTS.register("random_variable_soup", () -> new MobEffect(MobEffectCategory.NEUTRAL, 0xFF0000));
    //旋转
    public static final Supplier<MobEffect> SPIN_EFFECT = EFFECTS.register("spin_effect", () -> new MobEffect(MobEffectCategory.HARMFUL, 0xFF0000));

    public static final Supplier<MobEffect> GRAND_LIBRARY_ESSENCE_COFFEE = EFFECTS.register("grand_library_essence_coffee", () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xcc66ff));
    public static final Supplier<MobEffect> FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE = EFFECTS.register("fried_echo_shards_with_aged_rose_sauce", () -> new MobEffect(MobEffectCategory.NEUTRAL, 0xff2e3b));
    public static final Supplier<MobEffect> WUTHERING_DEPTH = EFFECTS.register("wuthering_depth", () -> new MobEffect(MobEffectCategory.BENEFICIAL, 0xff2e3b));

}
