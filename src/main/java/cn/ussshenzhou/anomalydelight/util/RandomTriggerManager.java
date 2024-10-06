package cn.ussshenzhou.anomalydelight.util;

import cn.ussshenzhou.anomalydelight.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @author Mafuyu33
 */
public class RandomTriggerManager {

    // 定义一个函数式接口，表示要执行的随机动作
    @FunctionalInterface
    public interface RandomAction {
        void execute(Player player, Level world, @Nullable InteractionHand hand, @Nullable Entity target, @Nullable BlockHitResult blockHitResult);
    }

    /**
     * @param weight 权重，表示概率
     */ // 定义一个类，用于存储动作和对应的权重
        public record RandomActionEntry(RandomAction action, int weight) {
    }

    // 创建一个列表，存储所有的随机动作及其权重
    private static final List<RandomActionEntry> ACTIONS = new ArrayList<>();

    static {
        // 添加所有的动作到列表中，初始权重可以根据需要设置

        // 使用物品的动作
        ACTIONS.add(new RandomActionEntry(
                RandomTriggerManager::useRandomItem, 100
        ));

        // 应用随机药水效果
        ACTIONS.add(new RandomActionEntry(
                (player, world, hand, target, blockHitResult) -> {
                    applyRandomPotionEffect(player, world, target);
                }, 10
        ));

        // 随机召唤实体
        ACTIONS.add(new RandomActionEntry(
                (player, world, hand, target, blockHitResult) -> {
                    summonRandomEntity(player, world);
                }, 10
        ));

        // 播放随机音乐
        ACTIONS.add(new RandomActionEntry(
                (player, world, hand, target, blockHitResult) -> {
                    playRandomSoundForPlayer(player, world);
                }, 50
        ));

        // 生成随机粒子
        ACTIONS.add(new RandomActionEntry(
                (player, world, hand, target, blockHitResult) -> {
                    spawnRandomParticle(player, world, target, blockHitResult);
                }, 50
        ));

        // 应用随机效果
        ACTIONS.add(new RandomActionEntry(
                (player, world, hand, target, blockHitResult) -> {
                    applyRandomEffect(player, world, target);
                }, 5
        ));

        // 执行随机的玩家动作
        ACTIONS.add(new RandomActionEntry(
                (player, world, hand, target, blockHitResult) -> {
                    performRandomPlayerAction(player, world);
                }, 1
        ));
    }


    // 使用 HashMap 记录每个玩家对不同事件的触发次数和冷却时间
    private static final Map<UUID, Map<String, Integer>> PLAYER_EVENT_COUNTERS = new HashMap<>();
    private static final Map<UUID, Map<String, Long>> PLAYER_EVENT_TIMERS = new HashMap<>();

    // 冷却时间 (以毫秒为单位)
    // 例如 2 秒冷却时间
    private static final long COOLDOWN_TIME = 1000;


    // 主方法，根据权重随机执行一个动作
    public static void performRandomAction(Player player, Level world, InteractionHand hand, @Nullable Entity target, @Nullable BlockHitResult blockHitResult, String eventKey) {
        if (!world.isClientSide) {
            if (player.hasEffect(ModEffects.RANDOM_VARIABLE_SOUP)) {

                UUID playerUUID = player.getUUID();

                // 获取玩家的事件计数器和计时器，如果不存在则初始化
                Map<String, Integer> eventCounters = PLAYER_EVENT_COUNTERS.computeIfAbsent(playerUUID, k -> new HashMap<>());
                Map<String, Long> eventTimers = PLAYER_EVENT_TIMERS.computeIfAbsent(playerUUID, k -> new HashMap<>());

                // 获取该事件的当前触发次数
                int currentCount = eventCounters.getOrDefault(eventKey, 0);

                // 计算当前的触发概率，从 50% 开始递增
                int triggerChance = Math.min(100, currentCount * 10 + 50);

                // 检查冷却时间是否结束
                long currentTime = System.currentTimeMillis();
                long lastTriggerTime = eventTimers.getOrDefault(eventKey, 0L);

                if (currentTime - lastTriggerTime < COOLDOWN_TIME) {
                    // 冷却时间尚未结束，不执行随机动作，但累积概率
                    eventCounters.put(eventKey, currentCount + 1);
                    return;
                }

                // 生成一个随机数来决定是否触发该事件
                Random random = new Random();
                if (random.nextInt(100) < triggerChance) {
                    // 如果通过了概率检测，则执行随机动作
                    Random random1 = new Random();
                    int totalWeight = ACTIONS.stream().mapToInt(RandomActionEntry::weight).sum();
                    int randomValue = random1.nextInt(totalWeight);

                    int currentSum = 0;
                    for (RandomActionEntry entry : ACTIONS) {
                        currentSum += entry.weight();
                        if (randomValue < currentSum) {
                            entry.action().execute(player, world, hand, target, blockHitResult);
                            break;
                        }
                    }

                    // 重置计数器，因为已触发事件
                    eventCounters.put(eventKey, 0);

                    // 更新该事件的最后触发时间，开始冷却
                    eventTimers.put(eventKey, currentTime);
                } else {
                    // 未触发事件，递增该事件的触发计数器
                    eventCounters.put(eventKey, currentCount + 1);
                }
            }
        }
    }

    // 方法信息类，包含方法名称和参数类型
    static class MethodInfo {
        String methodName;
        Class<?>[] parameterTypes;

        MethodInfo(String methodName, Class<?>... parameterTypes) {
            this.methodName = methodName;
            this.parameterTypes = parameterTypes;
        }
    }

    // 随机物品
    public static void useRandomItem(Player player, Level world, @Nullable InteractionHand hand, @Nullable Entity target, @Nullable BlockHitResult blockHitResult) {
        if (!world.isClientSide) {
            // 获取所有注册的物品
            Item[] allItems = world.registryAccess().registryOrThrow(Registries.ITEM).stream().toArray(Item[]::new);

            Random random = new Random();
            Item randomItem = null;
            do {
                int randomIndex = random.nextInt(allItems.length);
                randomItem = allItems[randomIndex];
            } while (randomItem instanceof EggItem
                    || randomItem == Items.ENDER_DRAGON_SPAWN_EGG
                    || randomItem == Items.WITHER_SPAWN_EGG);//排除一些不好的东西

//            randomItem=Items.RED_BED;//测试

            // 定义所有要检测的方法及其参数类型
            List<MethodInfo> methods = new ArrayList<>();
            methods.add(new MethodInfo("use", Level.class, Player.class, InteractionHand.class));
            methods.add(new MethodInfo("useOn", UseOnContext.class));
            methods.add(new MethodInfo("finishUsingItem", ItemStack.class, Level.class, LivingEntity.class));
            methods.add(new MethodInfo("interactLivingEntity", ItemStack.class, Player.class, LivingEntity.class, InteractionHand.class));
            methods.add(new MethodInfo("onCraftedBy", ItemStack.class, Level.class, Player.class));
            methods.add(new MethodInfo("releaseUsing", ItemStack.class, Level.class, LivingEntity.class, int.class));

            // 打乱方法顺序
            Collections.shuffle(methods);

            // 遍历方法列表并执行
            for (MethodInfo methodInfo : methods) {
                if (hasMethodInHierarchy(randomItem.getClass(), methodInfo.methodName, methodInfo.parameterTypes)) {
                    switch (methodInfo.methodName) {
                        case "use":
//                            System.out.println(randomItem.getClass().getSimpleName() + " 调用了 use 方法");
                            randomItem.use(world, player, hand);
                            break;
                        case "useOn":
//                            System.out.println(randomItem.getClass().getSimpleName() + " 调用了 useOn 方法");
                            if (blockHitResult != null) {
                                randomItem.useOn(new UseOnContext(world, player, hand, new ItemStack(randomItem), blockHitResult));
                            }
                            break;
                        case "finishUsingItem":
//                            System.out.println(randomItem.getClass().getSimpleName() + " 调用了 finishUsingItem 方法");
                            randomItem.finishUsingItem(new ItemStack(randomItem), world, player);
                            break;
                        case "interactLivingEntity":
//                            System.out.println(randomItem.getClass().getSimpleName() + " 调用了 interactLivingEntity 方法");
                            if (target instanceof LivingEntity) {
                                randomItem.interactLivingEntity(new ItemStack(randomItem), player, (LivingEntity) target, hand);
                            }
                            break;
                        case "onCraftedBy":
//                            System.out.println(randomItem.getClass().getSimpleName() + " 调用了 onCraftedBy 方法");
                            randomItem.onCraftedBy(new ItemStack(randomItem), world, player);
                            break;
                        case "releaseUsing":
//                            System.out.println(randomItem.getClass().getSimpleName() + " 调用了 releaseUsing 方法");
                            randomItem.releaseUsing(new ItemStack(randomItem), world, player, 0);
                            break;
                    }
                    // 只执行第一个匹配的方法
                    break;
                }
            }

            ItemStack itemStack =new ItemStack(randomItem);
            if(!itemStack.isEmpty()) {
                // 检查是否是食物
                if (itemStack.getFoodProperties(player) != null) {
//                    System.out.println(randomItem.getClass().getSimpleName() + " 是食物，调用食用逻辑");
                    // 玩家开始使用该物品（食物）
                    player.startUsingItem(hand);
                    // 完成食用过程
                    randomItem.finishUsingItem(itemStack, world, player);
                }
            }
        }
    }

    //随机药水效果
    public static void applyRandomPotionEffect(Player player, Level world, @Nullable Entity target) {
        if (!world.isClientSide) {
            // 获取三种药水类型
            Item[] potionTypes = {Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION};

            // 随机选择一种药水类型
            Random random = new Random();
            int potionTypeIndex = random.nextInt(potionTypes.length);
            Item randomPotionItem = potionTypes[potionTypeIndex];

            // 获取所有注册的药水效果
            Holder<Potion>[] allPotions = BuiltInRegistries.POTION.holders().toArray(Holder[]::new);
            int potionIndex = random.nextInt(allPotions.length);
            Holder<Potion> randomPotionHolder = allPotions[potionIndex];

            // 随机创建一个药水 ItemStack, 将随机的药水效果附加到 ItemStack 上
            ItemStack potionStack = PotionContents.createItemStack(randomPotionItem,randomPotionHolder);

//            System.out.println("随机到了药水类型: " + randomPotionItem.getDescriptionId() + "，效果: "+ randomPotionHolder.value());

            if (randomPotionItem == Items.POTION) {
                // 1/2 概率饮用，1/2 概率给 target 添加效果
                if (random.nextBoolean()) {
                    player.startUsingItem(InteractionHand.MAIN_HAND);
                    randomPotionItem.finishUsingItem(potionStack, world, player);
//                    System.out.println("玩家饮用了药水");
                } else if (target instanceof LivingEntity livingTarget) {
                    if (!randomPotionHolder.value().getEffects().isEmpty()) {
                        livingTarget.addEffect(randomPotionHolder.value().getEffects().get(0));
//                        System.out.println("给目标添加了药水效果");
                    } else {
//                        System.out.println("没有效果可以应用");
                    }
                }
            } else if (randomPotionItem == Items.SPLASH_POTION) {
                // 1/2 概率在玩家脚下生成，1/2 概率在目标生成
                ThrownPotion splashPotionEntity = new ThrownPotion(world, player);
                splashPotionEntity.setItem(potionStack);

                if (random.nextBoolean()) {
                    splashPotionEntity.setPos(player.getX(), player.getY(), player.getZ());
                    world.addFreshEntity(splashPotionEntity);
//                    System.out.println("在玩家脚下生成喷溅药水");
                } else if (target != null) {
                    splashPotionEntity.setPos(target.getX(), target.getY(), target.getZ());
                    world.addFreshEntity(splashPotionEntity);
//                    System.out.println("在目标生成喷溅药水");
                }
            } else if (randomPotionItem == Items.LINGERING_POTION) {
                // 1/2 概率在玩家脚下生成滞留药水，1/2 概率在目标生成
                ThrownPotion lingeringPotionEntity = new ThrownPotion(world, player);
                lingeringPotionEntity.setItem(potionStack);

                if (random.nextBoolean()) {
                    lingeringPotionEntity.setPos(player.getX(), player.getY(), player.getZ());
                    world.addFreshEntity(lingeringPotionEntity);
//                    System.out.println("在玩家脚下生成滞留药水");
                } else if (target != null) {
                    lingeringPotionEntity.setPos(target.getX(), target.getY(), target.getZ());
                    world.addFreshEntity(lingeringPotionEntity);
//                    System.out.println("在目标生成滞留药水");
                }
            }
        }
    }

    // 在世界中随机召唤实体的方法
    public static void summonRandomEntity(Player player, Level world) {
        if (!world.isClientSide) {
            // 获取所有注册的实体类型
            EntityType<?>[] allEntityTypes = world.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).stream().toArray(EntityType[]::new);

            // 随机选择一个实体类型
            Random random = new Random();
            EntityType<?> randomEntityType;
            int tries = 10;

            // 防止生成 EnderDragon 或 WitherBoss
            do {
                int randomIndex = random.nextInt(allEntityTypes.length);
                randomEntityType = allEntityTypes[randomIndex];
                tries--;
            } while ((randomEntityType == EntityType.ENDER_DRAGON || randomEntityType == EntityType.WITHER) && tries > 0);

            // 如果没有找到合适的实体类型，则返回
            if (tries == 0) {
                System.out.println("无法找到合适的实体类型");
                return;
            }

            // 创建实体实例
            Entity randomEntity = randomEntityType.create(world);
            if (randomEntity == null) {
                System.out.println("无法创建实体: " + randomEntityType.toString());
                return;
            }

            // 设置唯一的 UUID，防止 UUID 冲突
            randomEntity.setUUID(UUID.randomUUID());

            Vec3 playerPosition = player.position();
            // 尝试10次找到合适的位置
            tries = 10;
            boolean isPositionValid = false;

            while (tries > 0) {
                // 生成随机位置，位置在玩家附近
                double randomX = playerPosition.x + random.nextDouble() * 5 - 2.5;
                double randomZ = playerPosition.z + random.nextDouble() * 5 - 2.5;
                // 使用世界高度来获取生成位置的地面高度，确保实体不会生成在空中或地下
                double randomY = world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int)randomX, (int)randomZ);

                // 确认生成位置是否可站立且头部没有阻挡物
                BlockPos groundPos = new BlockPos((int) randomX, (int)randomY - 1, (int)randomZ);
                BlockPos entityPos = new BlockPos((int)randomX, (int)randomY, (int)randomZ);
                BlockPos headPos = new BlockPos((int)randomX, (int)randomY + (int)randomEntity.getBbHeight(), (int)randomZ);

                // 检查地面是否为实心块
                boolean isGroundSolid = world.getBlockState(groundPos).isSolidRender(world, groundPos);
                // 检查实体站立的位置是否为空气
                boolean isEntitySpaceEmpty = world.getBlockState(entityPos).isAir();
                // 检查头部位置是否为空气，避免窒息
                boolean isHeadSpaceEmpty = world.getBlockState(headPos).isAir();

                if (isGroundSolid && isEntitySpaceEmpty && isHeadSpaceEmpty) {
                    randomEntity.setPos(randomX, randomY, randomZ);
                    isPositionValid = true;
                    break;
                }

                tries--;
            }

            if (isPositionValid) {
                // 将实体添加到世界中
                world.addFreshEntity(randomEntity);
                System.out.println("成功随机召唤了实体: " + randomEntityType.getDescriptionId());
            } else {
                System.out.println("未找到有效的生成位置");
            }
        }
    }


    // 在世界中随机音乐
    public static void playRandomSoundForPlayer(Player player, Level world) {
        if (!world.isClientSide) {
            // 获取所有已注册的声音事件
            Holder<SoundEvent>[] allSounds = BuiltInRegistries.SOUND_EVENT.holders().toArray(Holder[]::new);

            // 随机选择一个声音事件
            Random random = new Random();
            int randomIndex = random.nextInt(allSounds.length);
            Holder<SoundEvent> randomSoundHolder = allSounds[randomIndex];

            // 获取声音事件并播放
            SoundEvent randomSoundEvent = randomSoundHolder.value();
            world.playSound(null, player.getX(), player.getY(), player.getZ(), randomSoundEvent, SoundSource.RECORDS, 1.0F, 1.0F);

            // 如果是唱片音乐，推送消息给玩家
            if (isMusicDisc(randomSoundEvent)) {
                // 获取唱片音乐的描述 ID
                String musicDescription = randomSoundEvent.getLocation().toString();
                // 发送聊天消息给玩家
                if (player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.sendSystemMessage(Component.translatable("正在播放音乐: " + musicDescription),true);
                }
            }
        }
    }

    // 随机生成粒子
    public static void spawnRandomParticle(Player player, Level world, @Nullable Entity target, @Nullable BlockHitResult blockHitResult) {
        // 确保是在服务器端执行
        if (!world.isClientSide && world instanceof ServerLevel serverLevel) {
            // 获取所有已注册的粒子类型
            Holder<ParticleType<?>>[] allParticles = BuiltInRegistries.PARTICLE_TYPE.holders().toArray(Holder[]::new);

            // 随机选择一个粒子类型
            Random random = new Random();
            int randomIndex = random.nextInt(allParticles.length);
            Holder<ParticleType<?>> randomParticleHolder = allParticles[randomIndex];

            // 获取实际的粒子类型
            ParticleType<?> randomParticleType = randomParticleHolder.value();

            // 检查该粒子类型是否实现了 ParticleOptions 接口
            if (randomParticleType instanceof ParticleOptions particleOptions) {
                // 生成粒子的位置
                double x, y, z;

                // 如果有目标实体，就在实体上生成粒子
                if (target != null) {
                    x = target.getX();
                    // 粒子生成在实体的中心高度
                    y = target.getY() + target.getBbHeight() / 2.0;
                    z = target.getZ();
                }
                // 如果有方块结果，就在方块上生成粒子
                else if (blockHitResult != null) {
                    // 获取方块位置
                    BlockPos blockPos = blockHitResult.getBlockPos();
                    // 生成在方块的中心
                    x = blockPos.getX() + 0.5;
                    y = blockPos.getY() + 0.5;
                    z = blockPos.getZ() + 0.5;
                }
                // 否则在玩家自身生成粒子
                else {
                    x = player.getX();
                    y = player.getY() + player.getBbHeight() / 2.0;
                    z = player.getZ();
                }

                // 向客户端发送粒子生成指令
                serverLevel.sendParticles(particleOptions, x, y, z, 20, 0.5, 0.5, 0.5, 0.01);
            } else {
                System.out.println("该粒子类型不支持 ParticleOptions: " + randomParticleType);
            }
        }
    }

    // 随机效果
    public static void applyRandomEffect(Player player, Level world, @Nullable Entity target) {
        if (!world.isClientSide) {
            // 获取所有注册的效果 (获取 Holder<MobEffect> 数组)
            Holder<MobEffect>[] allEffects = BuiltInRegistries.MOB_EFFECT.holders().toArray(Holder[]::new);
            Holder<MobEffect> randomEffectHolder;
            Random random = new Random();

            // 防止生成
            do {
                // 随机选择一个效果
                int randomIndex = random.nextInt(allEffects.length);
                randomEffectHolder = allEffects[randomIndex];
            } while (randomEffectHolder.value() == MobEffects.HARM);

            // 随机选择效果等级（放大器），这里选择0到2
            int amplifier = random.nextInt(3); // 等级 0, 1, 或 2

            // 按放大器减少持续时间：放大器越高，时间越短
            int maxDurationSeconds = 60; // 最大时间为 60 秒
            int minDurationSeconds = 5;  // 最小时间为 5 秒
            int durationRange = maxDurationSeconds - minDurationSeconds;

            // 计算根据放大器减少的持续时间，放大器越高时间越短
            int durationSeconds = maxDurationSeconds - amplifier * (durationRange / 3); // 放大器为2时，时长最短
            int durationTicks = durationSeconds * 20; // 将秒转换为游戏刻（20刻=1秒）

            // 创建 MobEffectInstance，使用 Holder<MobEffect>
            MobEffectInstance effectInstance = new MobEffectInstance(randomEffectHolder, durationTicks, amplifier);

            // 随机选择将效果应用于玩家或目标实体
            if (random.nextBoolean() && target instanceof LivingEntity livingTarget) {
                livingTarget.addEffect(effectInstance);
                // 输出调试信息
                // System.out.println("给目标添加了效果: " + randomEffectHolder.value().getDescriptionId() + "，持续时间: " + durationSeconds + " 秒");
            } else {
                player.addEffect(effectInstance);
                // 输出调试信息
                // System.out.println("给玩家添加了效果: " + randomEffectHolder.value().getDescriptionId() + "，持续时间: " + durationSeconds + " 秒");
            }
        }
    }


    // 随机执行玩家动作
    public static void performRandomPlayerAction(Player player, Level world) {
        if (!world.isClientSide) {
            PoseMixinHandler.storeEntityValue(player.getId(),200);
            PoseMixinHandler.storeFlagMap(player.getId(),new Random().nextInt(2) + 1);
        }
    }

    // 检查是否是唱片音乐，支持自定义唱片
    private static boolean isMusicDisc(SoundEvent soundEvent) {
        // 获取 SoundEvent 的注册名（类似于 minecraft:music_disc_cat）
        String soundEventName = soundEvent.getLocation().toString();

        // 判断名称中是否包含 "music_disc"（支持原版和自定义的唱片）
        return soundEventName.contains("music_disc");
    }

    // 递归检查类的继承层次，查看是否有方法重写
    public static boolean hasMethodInHierarchy(Class<?> subclass, String methodName, Class<?>... parameterTypes) {
        // 检查当前类是否重写了方法
        if (hasMethodOverride(subclass, methodName, parameterTypes)) {
            return true;
        }
        // 如果没有找到，递归检查父类，直到到达 Item 类或没有父类
        Class<?> superclass = subclass.getSuperclass();
        if (superclass != null && !superclass.equals(Item.class)) {
            return hasMethodInHierarchy(superclass, methodName, parameterTypes);
        }
        return false;
    }
    public static boolean hasMethodOverride(Class<?> subclass, String methodName, Class<?>... parameterTypes) {
        try {
            // 获取子类中的方法
            Method subMethod = subclass.getMethod(methodName, parameterTypes);

            // 获取父类中的方法
            Method superMethod = subclass.getSuperclass().getMethod(methodName, parameterTypes);

            // 检查该方法是否由子类声明，而不是从父类继承的
            return !subMethod.equals(superMethod);
        } catch (NoSuchMethodException e) {
            // 如果方法在子类或父类中不存在，说明没有重写
            return false;
        }
    }
}
