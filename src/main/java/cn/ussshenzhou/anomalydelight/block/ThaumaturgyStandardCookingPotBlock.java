package cn.ussshenzhou.anomalydelight.block;

import cn.ussshenzhou.anomalydelight.block.entity.ModBlockEntityTypes;
import cn.ussshenzhou.anomalydelight.block.entity.ThaumaturgyStandardCookingPotBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vectorwing.farmersdelight.common.block.CookingPotBlock;


import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
public class ThaumaturgyStandardCookingPotBlock extends CookingPotBlock {
    public ThaumaturgyStandardCookingPotBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.TSCP.get().create(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntity) {
        return level.isClientSide ?
                createTickerHelper(blockEntity, ModBlockEntityTypes.TSCP.get(), ThaumaturgyStandardCookingPotBlockEntity::animationTick)
                : createTickerHelper(blockEntity, ModBlockEntityTypes.TSCP.get(), ThaumaturgyStandardCookingPotBlockEntity::cookingTick);
    }
}
