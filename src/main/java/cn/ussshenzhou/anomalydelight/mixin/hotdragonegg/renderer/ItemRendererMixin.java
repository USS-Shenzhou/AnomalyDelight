package cn.ussshenzhou.anomalydelight.mixin.hotdragonegg.renderer;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    //之后有贴图可替换渲染。

    @Unique
    private final Minecraft mc = Minecraft.getInstance();

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void renderItem(ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay, BakedModel p_model, CallbackInfo ci) {
        // 检查是否是特定物品
        if (BuiltInRegistries.ITEM.getKey(itemStack.getItem()).equals(ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, "hot_dragon_egg"))) {
            // 取消默认渲染
            ci.cancel();

            // 渲染模型
            EnderDragon enderDragon = new EnderDragon(EntityType.ENDER_DRAGON, mc.level);
            poseStack.pushPose();

            poseStack.scale(0.1F, 0.1F, 0.1F);// 调整缩放比例
            mc.getEntityRenderDispatcher().render(enderDragon, 0, 0, 0, 180.0F, 1.0F, poseStack, bufferSource, combinedLight);
            poseStack.popPose();
        }

        // 检查是否是特定物品
        if (BuiltInRegistries.ITEM.getKey(itemStack.getItem()).equals(ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID, "cooked_dragon_egg"))) {
            // 取消默认渲染
            ci.cancel();

            // 渲染模型
            EnderDragon enderDragon = new EnderDragon(EntityType.ENDER_DRAGON, mc.level);
            poseStack.pushPose();

            poseStack.scale(0.1F, 0.1F, 0.1F);// 调整缩放比例
            mc.getEntityRenderDispatcher().render(enderDragon, 0, 0, 0, 0.0F, 1.0F, poseStack, bufferSource, combinedLight);
            poseStack.popPose();
        }
    }
}
