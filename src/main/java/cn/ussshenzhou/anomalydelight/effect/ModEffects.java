package cn.ussshenzhou.anomalydelight.effect;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEffects {
    public static DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, AnomalyDelight.MODID);

    public static final DeferredHolder<MobEffect,MobEffect> RANDOM_VARIABLE_SOUP =registerDeferredHolder("random_variable_soup",()->new NormalEffect(MobEffectCategory.NEUTRAL,0xFF0000)) ;


    public static DeferredHolder<MobEffect,MobEffect> registerDeferredHolder(String name, Supplier<MobEffect> supplier){
        return EFFECTS.register(name,supplier);
    }

    public static void register(IEventBus eventBus){
        EFFECTS.register(eventBus);
    }
}
