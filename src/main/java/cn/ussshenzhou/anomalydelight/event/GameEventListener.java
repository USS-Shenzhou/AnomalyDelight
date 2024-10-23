package cn.ussshenzhou.anomalydelight.event;

import cn.ussshenzhou.anomalydelight.util.RandomTriggerManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.*;

@EventBusSubscriber
public class GameEventListener {
    @SubscribeEvent
    public static void AdvancementEarnEvent(AdvancementEvent.AdvancementEarnEvent event) {
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"AdvancementEvent.AdvancementEarnEvent");
    }
    @SubscribeEvent
    public static void AdvancementProgressEvent(AdvancementEvent.AdvancementProgressEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"AdvancementEvent.AdvancementProgressEvent");
    }
    @SubscribeEvent
    public static void AnvilRepairEvent(AnvilRepairEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"AnvilRepairEvent");
    }
    @SubscribeEvent
    public static void ArrowLooseEvent(ArrowLooseEvent event){
        Player player = event.getEntity();
        Level level = event.getLevel();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"ArrowLooseEvent");
    }
    @SubscribeEvent
    public static void ArrowNockEvent(ArrowNockEvent event){
        Player player = event.getEntity();
        Level level = event.getLevel();
        InteractionHand hand = event.getHand();
        RandomTriggerManager.performRandomAction(player,level,hand,null,null,"ArrowNockEvent");
    }
    @SubscribeEvent
    public static void AttackEntityEvent(AttackEntityEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        Entity target = event.getTarget();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,target,null,"AttackEntityEvent");
    }
    @SubscribeEvent
    public static void BonemealEvent(BonemealEvent event){
        Player player = event.getPlayer();
        Level level = event.getLevel();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"BonemealEvent");
    }
    @SubscribeEvent
    public static void CanContinueSleepingEvent(CanContinueSleepingEvent event){
        if(event.getEntity() instanceof Player player){
            Level level = event.getEntity().level();
            RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"CanContinueSleepingEvent");
        }
    }
    @SubscribeEvent
    public static void CanPlayerSleepEvent(CanPlayerSleepEvent event){
        Player player = event.getEntity();
        Level level = event.getLevel();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"CanPlayerSleepEvent");
    }
    @SubscribeEvent
    public static void ClientInformationUpdatedEvent(ClientInformationUpdatedEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"ClientInformationUpdatedEvent");
    }
    @SubscribeEvent
    public static void CriticalHitEvent(CriticalHitEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        Entity target = event.getTarget();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,target,null,"CriticalHitEvent");
    }
    @SubscribeEvent
    public static void ItemEntityPickupEventPost(ItemEntityPickupEvent.Post event){
        Player player = event.getPlayer();
        Level level = event.getPlayer().level();
        ItemEntity itemEntity = event.getItemEntity();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,itemEntity,null,"ItemEntityPickupEvent");
    }
    @SubscribeEvent
    public static void ItemFishedEvent(ItemFishedEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        FishingHook fishingHook = event.getHookEntity();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,fishingHook,null,"ItemFishedEvent");
    }
    @SubscribeEvent
    public static void ItemTooltipEvent(ItemTooltipEvent event){
        Player player = event.getEntity();
        // 如果 player 为 null，则不执行操作，直接返回
        if (player == null) {
            return;
        }
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"ItemTooltipEvent");
    }
    @SubscribeEvent
    public static void PermissionsChangedEvent(PermissionsChangedEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PermissionsChangedEvent");
    }
    @SubscribeEvent
    public static void PlayerContainerEventOpen(PlayerContainerEvent.Open event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerContainerEvent.Open");
    }
    @SubscribeEvent
    public static void PlayerContainerEventClose(PlayerContainerEvent.Close event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerContainerEvent.Close");
    }
    @SubscribeEvent
    public static void PlayerDestroyItemEvent(PlayerDestroyItemEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        InteractionHand hand = event.getHand();
        RandomTriggerManager.performRandomAction(player,level,hand,null,null,"PlayerDestroyItemEvent");
    }
    @SubscribeEvent
    public static void PlayerEnchantItemEvent(PlayerEnchantItemEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEnchantItemEvent");
    }
    @SubscribeEvent
    public static void PlayerEventBreakSpeed(PlayerEvent.BreakSpeed event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.BreakSpeed");
    }
    @SubscribeEvent
    public static void PlayerEventClone(PlayerEvent.Clone event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.Clone");
    }
    @SubscribeEvent
    public static void PlayerEventHarvestCheck(AnvilRepairEvent.HarvestCheck event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"AnvilRepairEvent.HarvestCheck");
    }
    @SubscribeEvent
    public static void PlayerEventItemCraftedEvent(PlayerEvent.ItemCraftedEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.ItemCraftedEvent");
    }
    @SubscribeEvent
    public static void PlayerEventItemSmeltedEvent(PlayerEvent.ItemSmeltedEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvPlayerEvent.ItemSmeltedEvent");
    }
    @SubscribeEvent
    public static void PlayerEventNameFormat(PlayerEvent.NameFormat event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.NameFormat");
    }
    @SubscribeEvent
    public static void PlayerChangedDimensionEvent(PlayerEvent.PlayerChangedDimensionEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.PlayerChangedDimensionEvent");
    }
    @SubscribeEvent
    public static void PlayerEventPlayerChangeGameModeEvent(PlayerEvent.PlayerChangeGameModeEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.PlayerChangeGameModeEvent");
    }
    @SubscribeEvent
    public static void PlayerEventPlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.PlayerLoggedInEvent");
    }
    @SubscribeEvent
    public static void PlayerEventPlayerRespawnEvent(PlayerEvent.PlayerRespawnEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.PlayerRespawnEvent");
    }
    @SubscribeEvent
    public static void PlayerEventStartTracking(PlayerEvent.StartTracking event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        Entity target = event.getTarget();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,target,null,"PlayerEvent.StartTracking");
    }
    @SubscribeEvent
    public static void PlayerEventStopTracking(PlayerEvent.StopTracking event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        Entity target = event.getTarget();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,target,null,"PlayerEvent.StopTracking");
    }
    @SubscribeEvent
    public static void PlayerEventTabListNameFormat(PlayerEvent.TabListNameFormat event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerEvent.TabListNameFormat");
    }
    @SubscribeEvent
    public static void PlayerFlyableFallEvent(PlayerFlyableFallEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerFlyableFallEvent");
    }
    @SubscribeEvent
    public static void PlayerInteractEventEntityInteract(PlayerInteractEvent.EntityInteract event){
        InteractionHand hand = event.getHand();
        Level level = event.getLevel();
        Player player = event.getEntity();
        Entity target = event.getTarget();
        RandomTriggerManager.performRandomAction(player,level,hand,target,null,"PlayerInteractEvent.EntityInteract");
    }
    public static void PlayerInteractEventEntityInteractSpecific(PlayerInteractEvent.EntityInteractSpecific event){
        InteractionHand hand = event.getHand();
        Level level = event.getLevel();
        Player player = event.getEntity();
        Entity target = event.getTarget();
        RandomTriggerManager.performRandomAction(player,level,hand,target,null,"PlayerInteractEvent.EntityInteractSpecific");
    }
    @SubscribeEvent
    public static void PlayerInteractEventLeftClickBlock(PlayerInteractEvent.LeftClickBlock event){
        InteractionHand hand = event.getHand();
        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockPos blockPos = event.getPos();
        // 获取点击的方向
        Direction face = event.getFace();
        // 构建 BlockHitResult 直接使用事件中的 blockPos 和 face
        BlockHitResult blockHitResult = new BlockHitResult(player.getEyePosition(), face, blockPos, false);
        RandomTriggerManager.performRandomAction(player,level,hand,null,blockHitResult,"PlayerInteractEvent.LeftClickBlock");
    }
    @SubscribeEvent
    public static void PlayerInteractEventRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        InteractionHand hand = event.getHand();
        Level level = event.getLevel();
        Player player = event.getEntity();
        BlockPos blockPos = event.getPos();
        // 获取点击的方向
        Direction face = event.getFace();
        // 构建 BlockHitResult 直接使用事件中的 blockPos 和 face
        BlockHitResult blockHitResult = new BlockHitResult(player.getEyePosition(), face, blockPos, false);
        RandomTriggerManager.performRandomAction(player,level,hand,null,blockHitResult,"PlayerInteractEvent.LeftClickBlock");
    }
    @SubscribeEvent
    public static void PlayerInteractEventLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        InteractionHand hand = event.getHand();
        Level level = event.getLevel();
        Player player = event.getEntity();
        // 设置射线检测的最大距离，假设是10格
        double maxReachDistance = 10.0;
        // 使用射线检测来获取玩家视线方向的方块
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, maxReachDistance);
        // 调用随机触发逻辑
        RandomTriggerManager.performRandomAction(player, level, hand, null, blockHitResult, "PlayerInteractEvent.LeftClickEmpty");
    }
    @SubscribeEvent
    public static void PlayerInteractEventRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        InteractionHand hand = event.getHand();
        Level level = event.getLevel();
        Player player = event.getEntity();
        // 设置射线检测的最大距离，假设是10格
        double maxReachDistance = 10.0;
        // 使用射线检测来获取玩家视线方向的方块
        BlockHitResult blockHitResult = getPlayerPOVHitResult(level, player, maxReachDistance);
        // 调用随机触发逻辑
        RandomTriggerManager.performRandomAction(player, level, hand, null, blockHitResult, "PlayerInteractEvent.LeftClickEmpty");
    }
    public static BlockHitResult getPlayerPOVHitResult(Level level, Player player, double maxReachDistance) {
        // 获取玩家的眼睛位置
        Vec3 eyePosition = player.getEyePosition();
        // 获取玩家的视线方向
        Vec3 lookVector = player.getViewVector(1.0F);
        // 计算射线终点
        Vec3 endVector = eyePosition.add(lookVector.scale(maxReachDistance));
        // 执行射线检测 (Ray Tracing)
        BlockHitResult result = level.clip(new ClipContext(
                eyePosition, endVector, ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        // 如果射线检测结果为空气方块或者未命中任何方块
        if (result.getType() == HitResult.Type.MISS) {
            // 获取玩家当前位置的方块
            BlockPos playerBlockPos = player.blockPosition();
            // 确定命中的方向，根据玩家与方块的关系，这里假设向上
            Direction hitDirection = Direction.UP;
            Vec3 hitVec = new Vec3(playerBlockPos.getX() + 0.5, playerBlockPos.getY(), playerBlockPos.getZ() + 0.5);
            // 构建新的 BlockHitResult 使用玩家脚下的方块位置
            result = new BlockHitResult(hitVec, hitDirection, playerBlockPos, false);
        }
        return result;
    }

    @SubscribeEvent
    public static void PlayerInteractEventRightClickItem(PlayerInteractEvent.RightClickItem event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerInteractEvent.RightClickItem");
    }
    @SubscribeEvent
    public static void PlayerRespawnPositionEvent(PlayerRespawnPositionEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerRespawnPositionEvent");
    }
    @SubscribeEvent
    public static void PlayerSetSpawnEvent(PlayerSetSpawnEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerSetSpawnEvent");
    }
    @SubscribeEvent
    public static void PlayerSpawnPhantomsEvent(PlayerSpawnPhantomsEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerSpawnPhantomsEvent");
    }
    @SubscribeEvent
    public static void PlayerWakeUpEvent(PlayerWakeUpEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerWakeUpEvent");
    }
    @SubscribeEvent
    public static void PlayerXpEventLevelChange(PlayerXpEvent.LevelChange event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerXpEvent.LevelChange");
    }
    @SubscribeEvent
    public static void PlayerXpEventPickupXp(PlayerXpEvent.PickupXp event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerXpEvent.PickupXp");
    }
    @SubscribeEvent
    public static void PlayerXpEventXpChange(PlayerXpEvent.XpChange event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,null,null,"PlayerXpEvent.XpChange");
    }
    @SubscribeEvent
    public static void TradeWithVillagerEvent(TradeWithVillagerEvent event){
        Player player = event.getEntity();
        Level level = event.getEntity().level();
        AbstractVillager abstractVillager = event.getAbstractVillager();
        RandomTriggerManager.performRandomAction(player,level,InteractionHand.MAIN_HAND,abstractVillager,null,"TradeWithVillagerEvent");
    }
    @SubscribeEvent
    public static void UseItemOnBlockEvent(UseItemOnBlockEvent event){
        Player player = event.getPlayer();
        Level level = event.getLevel();
        InteractionHand hand = event.getHand();
        BlockPos blockPos = event.getPos();
        // 获取点击的方向
        Direction face = event.getFace();
        // 构建 BlockHitResult 直接使用事件中的 blockPos 和 face
        BlockHitResult blockHitResult = new BlockHitResult(player.getEyePosition(), face, blockPos, false);
        RandomTriggerManager.performRandomAction(player,level,hand,null,blockHitResult,"UseItemOnBlockEvent");
    }
//    @SubscribeEvent
//    public static void PlayerXpEvent(PlayerXpEvent event){
//        Player player = event.getEntity();
//        Level level = event.getEntity().level();
//        RandomTriggerManager.performRandomAction(player,level,null,null,null,"PlayerInteractEvent.EntityInteract");
//    }
//    @SubscribeEvent
//    public static void PlayerXpEvent(PlayerXpEvent event){
//        Player player = event.getEntity();
//        Level level = event.getEntity().level();
//        RandomTriggerManager.performRandomAction(player,level,null,null,null,"PlayerInteractEvent.EntityInteract");
//    }

}
