package cn.ussshenzhou.anomalydelight.entity;

import net.minecraft.world.entity.Mob;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

import static net.minecraft.world.entity.ai.attributes.Attributes.*;

/**
 * @author USS_Shenzhou
 */
@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityAttributeRegistry {

    @SubscribeEvent
    public static void registerEntityRenders(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.THAUMATURGY_MELON_SLICE.get(), Mob.createMobAttributes()
                .add(MAX_HEALTH, 1.0F)
                .add(MOVEMENT_SPEED, 0.3F)
                .add(STEP_HEIGHT, 0.4f)
                .build()
        );
    }
}
