package cn.ussshenzhou.anomalydelight.networking.packet;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.networking.packet.C2S.PickUpHotDragonEGGC2SPacket;
import cn.ussshenzhou.anomalydelight.networking.packet.S2C.HotDragonEggRandomMoveS2CPacket;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = AnomalyDelight.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModMessage {
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(AnomalyDelight.MODID);

        registrar.playBidirectional(
                HotDragonEggRandomMoveS2CPacket.TYPE,
                HotDragonEggRandomMoveS2CPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        HotDragonEggRandomMoveS2CPacket::handle,
                        null
                )
        );
        registrar.playBidirectional(
                PickUpHotDragonEGGC2SPacket.TYPE,
                PickUpHotDragonEGGC2SPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        null,
                        PickUpHotDragonEGGC2SPacket::receive
                )
        );

    }
}

