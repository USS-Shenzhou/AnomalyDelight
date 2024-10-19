package cn.ussshenzhou.anomalydelight.entity.render;

import cn.ussshenzhou.anomalydelight.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

/**
 * @author USS_Shenzhou
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderRegistry {

    @SubscribeEvent
    public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.THAUMATURGY_MELON_SLICE.get(), ThaumaturgyMelonSliceRenderer::new);
    }
}
