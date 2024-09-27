package cn.ussshenzhou.anomalydelight.block.container;

import cn.ussshenzhou.anomalydelight.block.ModBlocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyStandardCookingPotMenu extends CookingPotMenu {

    @SuppressWarnings("AlibabaConstantFieldShouldBeUpperCase")
    private static final VarHandle canInteractWithCallable;

    static {
        try {
            var lookup = MethodHandles.privateLookupIn(CookingPotMenu.class, MethodHandles.lookup());
            canInteractWithCallable = lookup.findVarHandle(CookingPotMenu.class, "canInteractWithCallable", ContainerLevelAccess.class);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public ContainerLevelAccess canInteractWithCallable() {
        return (ContainerLevelAccess) canInteractWithCallable.get(this);
    }

    public ThaumaturgyStandardCookingPotMenu(int windowId, Inventory playerInventory, FriendlyByteBuf data) {
        super(windowId, playerInventory, data);
    }

    public ThaumaturgyStandardCookingPotMenu(int windowId, Inventory playerInventory, CookingPotBlockEntity blockEntity, ContainerData cookingPotDataIn) {
        super(windowId, playerInventory, blockEntity, cookingPotDataIn);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(this.canInteractWithCallable(), playerIn, ModBlocks.TSCP.get());
    }

    @Override
    public MenuType<?> getType() {
        return ModMenuTypes.THAUMATURGY_STANDARD_COOKING_POT.get();
    }
}
