package cn.ussshenzhou.anomalydelight.block.entity;

import cn.ussshenzhou.anomalydelight.block.container.ThaumaturgyStandardCookingPotMenu;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyStandardCookingPotBlockEntity extends CookingPotBlockEntity {

    @SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
    private static final VarHandle inputHandler;
    @SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
    private static final VarHandle outputHandler;

    static {
        try {
            var lookup = MethodHandles.privateLookupIn(CookingPotBlockEntity.class, MethodHandles.lookup());
            inputHandler = lookup.findVarHandle(CookingPotBlockEntity.class, "inputHandler", IItemHandler.class);
            outputHandler = lookup.findVarHandle(CookingPotBlockEntity.class, "outputHandler", IItemHandler.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public IItemHandler getInputHandler() {
        return (IItemHandler) inputHandler.get(this);
    }

    public IItemHandler getOutputHandler() {
        return (IItemHandler) outputHandler.get(this);
    }

    public ThaumaturgyStandardCookingPotBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntityTypes.TSCP.get();
    }

    @Override
    protected boolean canCook(CookingPotRecipe recipe) {
        return super.canCook(recipe);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory player, Player entity) {
        return new ThaumaturgyStandardCookingPotMenu(id, player, this, this.cookingPotData);
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, CookingPotBlockEntity cookingPot) {
        if (cookingPot.isHeated(level, pos)) {
            RandomSource random = level.random;
            double x;
            double y;
            double z;
            if (random.nextFloat() < 0.2F) {
                x = (double)pos.getX() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
                y = (double)pos.getY() + 0.95;
                z = (double)pos.getZ() + 0.5 + (random.nextDouble() * 0.6 - 0.3);
                level.addParticle(ParticleTypes.BUBBLE_POP, x, y, z, 0.0, 0.0, 0.0);
            }

            if (random.nextFloat() < 0.05F) {
                x = (double)pos.getX() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                y = (double)pos.getY() + 0.75;
                z = (double)pos.getZ() + 0.5 + (random.nextDouble() * 0.4 - 0.2);
                double motionY = random.nextBoolean() ? 0.015 : 0.005;
                level.addParticle(ModParticleTypes.STEAM.get(), x, y, z, 0.0, motionY, 0.0);
            }
        }

    }
}
