package cn.ussshenzhou.anomalydelight.block.container;

import cn.ussshenzhou.anomalydelight.AnomalyDelight;
import cn.ussshenzhou.anomalydelight.gui.ThaumaturgyStandardCookingPotMenuScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

/**
 * @author USS_Shenzhou
 */
@EventBusSubscriber(modid = AnomalyDelight.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(BuiltInRegistries.MENU, AnomalyDelight.MODID);

    public static final Supplier<MenuType<ThaumaturgyStandardCookingPotMenu>> THAUMATURGY_STANDARD_COOKING_POT = MENU_TYPES.register("cooking_pot",
            () -> IMenuTypeExtension.create(ThaumaturgyStandardCookingPotMenu::new)
    );

    @SubscribeEvent
    public static void registerMenuScreens(RegisterMenuScreensEvent event) {
        event.register(THAUMATURGY_STANDARD_COOKING_POT.get(), ThaumaturgyStandardCookingPotMenuScreen::new);
    }
}
