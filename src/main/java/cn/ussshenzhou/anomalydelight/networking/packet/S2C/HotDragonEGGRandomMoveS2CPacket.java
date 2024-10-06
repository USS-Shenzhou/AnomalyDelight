package cn.ussshenzhou.anomalydelight.networking.packet.S2C;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.handling.IPayloadContext;

/**
 * @author Mafuyu33
 */
public class HotDragonEGGRandomMoveS2CPacket implements CustomPacketPayload {
    public static Type<HotDragonEGGRandomMoveS2CPacket> TYPE =
            new Type<HotDragonEGGRandomMoveS2CPacket>(ResourceLocation.fromNamespaceAndPath(AnomalyDelight.MODID,"hot_dragon_egg_random_move"));

    // stream codec
    public static final StreamCodec<FriendlyByteBuf, HotDragonEGGRandomMoveS2CPacket> STREAM_CODEC =
            CustomPacketPayload.codec(HotDragonEGGRandomMoveS2CPacket::write, HotDragonEGGRandomMoveS2CPacket::new);
    public Vec3 finalVelocity;
    public int id;

    public HotDragonEGGRandomMoveS2CPacket(int id, Vec3 finalVelocity){
        this.id=id;
        this.finalVelocity=finalVelocity;
    }

    public HotDragonEGGRandomMoveS2CPacket(FriendlyByteBuf buf){
        this.id=buf.readInt();
        this.finalVelocity=buf.readVec3();
    }

    public void write(FriendlyByteBuf pBuffer) {
        pBuffer.writeInt(this.id);
        pBuffer.writeVec3(this.finalVelocity);
    }
    public static void handle(HotDragonEGGRandomMoveS2CPacket data, IPayloadContext context){

        context.enqueueWork(()->{
            if(data.id!=-1 && Minecraft.getInstance().level!=null) {
                Entity entity = Minecraft.getInstance().level.getEntity(data.id);
                if(entity!=null) {
                    entity.setDeltaMovement(data.finalVelocity);
                }
            }
        });
    }
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}

