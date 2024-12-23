package cn.ussshenzhou.anomalydelight.potion;


import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, AnomalyDelight.MODID);

    public static Holder<Potion> SPIN_POTION = registerPotion("spin_potion",480,0, (Holder<MobEffect>)ModEffects.SPIN_EFFECT);

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
    public static DeferredHolder<Potion, Potion> registerPotion(String name, int duration, int amplifier, Holder<MobEffect> statusEffects) {
        return POTIONS.register(name,()-> new Potion(new MobEffectInstance(statusEffects,duration,amplifier)));
    }


}
