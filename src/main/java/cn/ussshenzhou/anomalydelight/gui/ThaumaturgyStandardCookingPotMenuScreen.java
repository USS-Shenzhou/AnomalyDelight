package cn.ussshenzhou.anomalydelight.gui;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import vectorwing.farmersdelight.client.gui.CookingPotRecipeBookComponent;
import vectorwing.farmersdelight.client.gui.CookingPotScreen;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.container.CookingPotMenu;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
public class ThaumaturgyStandardCookingPotMenuScreen extends CookingPotScreen {
    private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, "textures/gui/cooking_pot.png");
    private static final Rectangle HEAT_ICON = new Rectangle(47, 55, 17, 15);
    private static final Rectangle PROGRESS_ARROW = new Rectangle(89, 25, 0, 17);

    public ThaumaturgyStandardCookingPotMenuScreen(CookingPotMenu screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, Component.translatable("block.anomaly_delight.tscp"));
        try {
            var recipeBookComponentField = CookingPotScreen.class.getDeclaredField("recipeBookComponent");
            recipeBookComponentField.setAccessible(true);
            recipeBookComponentField.set(this,new ThaumaturgyStandardCookingPotRecipeBookComponent());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LogUtils.getLogger().error(e.toString());
        }
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics gui, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        if (this.minecraft != null) {
            gui.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
            if (this.menu.isHeated()) {
                gui.blit(BACKGROUND_TEXTURE, this.leftPos + HEAT_ICON.x, this.topPos + HEAT_ICON.y, 176, 0, HEAT_ICON.width, HEAT_ICON.height);
            }

            int l = this.menu.getCookProgressionScaled();
            gui.blit(BACKGROUND_TEXTURE, this.leftPos + PROGRESS_ARROW.x, this.topPos + PROGRESS_ARROW.y, 176, 15, l + 1, PROGRESS_ARROW.height);
        }
    }
}
