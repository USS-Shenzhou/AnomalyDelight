package cn.ussshenzhou.anomalydelight.networking.packet.C2S;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.UUID;

public class PickUpHotDragonEGGC2SPacket implements CustomPacketPayload {

    public UUID playerUUID;
    public int itemEntityID;
    public static final Type<PickUpHotDragonEGGC2SPacket> TYPE = new Type<PickUpHotDragonEGGC2SPacket>(ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID,"pick_up_hot_dragon_egg"));
    public static final StreamCodec<FriendlyByteBuf, PickUpHotDragonEGGC2SPacket> STREAM_CODEC =
            CustomPacketPayload.codec(PickUpHotDragonEGGC2SPacket::write, PickUpHotDragonEGGC2SPacket::new);

    private void write(FriendlyByteBuf buf) {
        buf.writeUUID(playerUUID);
        buf.writeInt(itemEntityID);
    }

    public PickUpHotDragonEGGC2SPacket(FriendlyByteBuf buf) {
        this.playerUUID = buf.readUUID();
        this.itemEntityID = buf.readInt();
    }

    public PickUpHotDragonEGGC2SPacket(UUID playerUUID, int itemEntityID){
        this.playerUUID = playerUUID;
        this.itemEntityID = itemEntityID;
    }

    public static void receive(final PickUpHotDragonEGGC2SPacket data, final IPayloadContext context) {
        Player player = context.player();
        Level level = player.level();
        ItemEntity itemEntity = ((ItemEntity) level.getEntity(data.itemEntityID));
        if(itemEntity != null) {
            // 删除该掉落物，并给玩家添加相应物品
            player.swing(InteractionHand.MAIN_HAND);
            player.addItem(new ItemStack((ItemLike) ModItems.HOT_DRAGON_EGG));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.RECORDS, 1.0F, 1.0F);
            itemEntity.discard();
        }
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
