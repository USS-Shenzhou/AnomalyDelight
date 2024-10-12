package cn.ussshenzhou.anomalydelight.util;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.sound.PlaySoundSourceEvent;
import net.neoforged.neoforge.client.event.sound.PlayStreamingSourceEvent;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL11;
import org.lwjgl.openal.EXTEfx;

/**
 * @author USS_Shenzhou
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class AudioReverbListener {
    private static int effectSlot;
    private static boolean initialized = false;

    @SubscribeEvent
    public static void onSoundPlay(PlaySoundSourceEvent event) {
        if (shouldApply()) {
            if (!initialized) {
                if (AL.getCapabilities().ALC_EXT_EFX) {
                    int reverbEffect = EXTEfx.alGenEffects();
                    EXTEfx.alEffecti(reverbEffect, EXTEfx.AL_EFFECT_TYPE, EXTEfx.AL_EFFECT_REVERB);
                    EXTEfx.alEffectf(reverbEffect, EXTEfx.AL_REVERB_DECAY_TIME, 5.0f);
                    EXTEfx.alEffectf(reverbEffect, EXTEfx.AL_REVERB_GAIN, 0.9f);
                    effectSlot = EXTEfx.alGenAuxiliaryEffectSlots();
                    EXTEfx.alAuxiliaryEffectSloti(effectSlot, EXTEfx.AL_EFFECTSLOT_EFFECT, reverbEffect);
                }
                initialized = true;
            }
            apply(event.getChannel().source);
        }
    }

    @SubscribeEvent
    public static void onStreamSoundPlay(PlayStreamingSourceEvent event) {
        if (shouldApply()) {
            apply(event.getChannel().source);
        }
    }

    public static void apply(int source) {
        AL11.alSource3i(source, EXTEfx.AL_AUXILIARY_SEND_FILTER, effectSlot, 0, EXTEfx.AL_FILTER_NULL);
    }

    @SuppressWarnings("unchecked")
    private static boolean shouldApply() {
        return Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect((Holder<MobEffect>) ModEffects.FRIED_ECHO_SHARDS_WITH_AGED_ROSE_SAUCE);
    }
}
