package cn.ussshenzhou.anomalydelight.block.entity;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
public class ModBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AnomalyDelight.MODID);

    @SuppressWarnings("DataFlowIssue")
    public static final Supplier<BlockEntityType<ThaumaturgyStandardCookingPotBlockEntity>> TSCP = BLOCK_ENTITY_TYPES.register("tscp",
            () -> BlockEntityType.Builder.of(ThaumaturgyStandardCookingPotBlockEntity::new, new Block[]{ModBlocks.TSCP.get()}).build(null)
    );
}
