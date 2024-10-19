package cn.ussshenzhou.anomalydelight.entity.render;

import cn.ussshenzhou.anomalydelight.entity.ThaumaturgyMelonSliceEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author USS_Shenzhou
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ThaumaturgyMelonSliceRenderer extends EntityRenderer<ThaumaturgyMelonSliceEntity> {
    public final ItemStack melonSlice = new ItemStack(Items.GLISTERING_MELON_SLICE);
    private final ItemRenderer itemRenderer;

    public ThaumaturgyMelonSliceRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.2f;
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(ThaumaturgyMelonSliceEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0, 0.125, 0);
        poseStack.mulPose(Axis.YP.rotation(entityYaw));
        BakedModel bakedmodel = this.itemRenderer.getModel(melonSlice, entity.level(), null, entity.getId());
        itemRenderer.render(melonSlice, ItemDisplayContext.GROUND, false, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedmodel);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, 240);
    }

    @Override
    public ResourceLocation getTextureLocation(ThaumaturgyMelonSliceEntity entity) {
        return ResourceLocation.withDefaultNamespace("missing_no");
    }
}
