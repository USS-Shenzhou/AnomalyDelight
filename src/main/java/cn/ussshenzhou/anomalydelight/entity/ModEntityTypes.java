package cn.ussshenzhou.anomalydelight.entity;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, AnomalyDelight.MODID);

    public static final Supplier<EntityType<ThaumaturgyMelonSliceEntity>> THAUMATURGY_MELON_SLICE = ENTITY_TYPE.register("thaumaturgy_melon_slice",
            () -> EntityType.Builder.of((EntityType<ThaumaturgyMelonSliceEntity> type, Level level) -> new ThaumaturgyMelonSliceEntity(level), MobCategory.MISC)
                    .sized(0.4f, 0.4f)
                    .clientTrackingRange(10)
                    .build("thaumaturgy_melon_slice")
    );
}
