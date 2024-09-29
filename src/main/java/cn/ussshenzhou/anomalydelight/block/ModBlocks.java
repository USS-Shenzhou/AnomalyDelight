package cn.ussshenzhou.anomalydelight.block;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AnomalyDelight.MODID);

    public static final Supplier<Block> TSCP = BLOCKS.register("tscp", () -> new ThaumaturgyStandardCookingPotBlock(
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(1, 12)
                    .sound(SoundType.LANTERN)
                    .lightLevel(b -> 7)
    ));
}
