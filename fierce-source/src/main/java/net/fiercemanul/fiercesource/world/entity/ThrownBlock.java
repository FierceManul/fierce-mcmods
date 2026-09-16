package net.fiercemanul.fiercesource.world.entity;

import net.fiercemanul.fiercesource.FierceSource;
import net.fiercemanul.fiercesource.config.CommonConfig;
import net.fiercemanul.fiercesource.data.FSDamageTypes;
import net.fiercemanul.fiercesource.data.FSEntities;
import net.fiercemanul.fiercesource.data.tags.FSBlockTags;
import net.fiercemanul.fiercesource.world.level.block.ThrowableBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.entity.IEntityWithComplexSpawn;
import net.neoforged.neoforge.event.EventHooks;

import javax.annotation.Nullable;

public class ThrownBlock extends Projectile implements IEntityWithComplexSpawn {


    /**
     * 第一击伤害通常弱低于基础伤害
     * <p>造成的伤害(大概) = 基础伤害 * 方块硬度 * 速度<p/>
     */
    public static final float DEFAULT_DAMAGE = 8.0F;
    public static final double DEFAULT_GRAVITY = 0.04;
    private static final EntityDataAccessor<Float> DESTROY_PROGRESS = SynchedEntityData.defineId(ThrownBlock.class, EntityDataSerializers.FLOAT);
    //用于被玩家攻击
    protected static final int DESTROY_PROGRESS_MULTIPLY = 5;
    public static final double MIN_QUALITY = 0.05;
    public static final double MIN_SPEED = 0.001;
    public static final double DEFAULT_SPEED_LIMIT = 0.25;

    protected BlockState blockState = Blocks.DIRT.defaultBlockState();
    @Nullable
    protected CompoundTag blockEntityData;
    @Nullable
    protected ItemContainerContents blockConter;
    protected float destroySpeed;
    protected float explosionResistance;
    protected boolean flammable;
    protected float baseDamage;
    protected double blockQuality;
    protected double gravity;
    protected float friction;
    protected FacingAnimation facingAnimation = FacingAnimation.NONE;

    protected double speedLimit = DEFAULT_SPEED_LIMIT;
    protected float destroyProgress;
    protected float damageMultiply = 1F;
    //public double xArc, zArc, xArcO, zArcO;

    //public Quaternionf rollRotation = new Quaternionf();
    //public Quaternionf rollRotationPrev = new Quaternionf();

    public double rollAngle;
    public double rollAnglePrev;


    public ThrownBlock(EntityType<? extends ThrownBlock> entityType, Level level) {
        super(entityType, level);
        blocksBuilding = true;
    }

    public ThrownBlock(EntityType<? extends ThrownBlock> entityType, Level level, BlockState state) {
        this(entityType, level);
        setBlockState(state);
    }

    public ThrownBlock(EntityType<? extends ThrownBlock> entityType, double x, double y, double z, Level level, BlockState state) {
        this(entityType, level);
        setPos(x, y, z);
        setBlockState(state);
    }

    public ThrownBlock(EntityType<? extends ThrownBlock> entityType, Position pos, Level level, BlockState state) {
        this(entityType, pos.x(), pos.y(), pos.z(), level, state);
    }

    public ThrownBlock(EntityType<? extends ThrownBlock> entityType, Entity shooter, Level level, double yOffset, BlockState state) {
        this(entityType, level);
        setPos(shooter.getX(), shooter.getEyeY() + yOffset, shooter.getZ());
        setOwner(shooter);
        setBlockState(state);
    }

    /**
     * @param stack 需要复制过的
     */
    public static ThrownBlock rotationThrowFromItem(ItemStack stack, Level level, Entity shooter, float rx, float ry) {
        return rotationThrowFromItem(stack, level, shooter, rx, ry, 0, -0.1, 1.25, 1, 1);
    }

    /**
     * @param stack 需要复制过的
     * @param limit 用于限制投射力量，越大，力量越小，必须>0
     */
    public static ThrownBlock rotationThrowFromItem(
            ItemStack stack,
            Level level,
            Entity shooter,
            float rx, float ry,
            float z, double yOffset,
            double limit,
            float inaccuracy,
            float damageMultiply
    ) {
        Item item = stack.getItem();
        BlockState state;
        if (item instanceof BlockItem blockItem) state = blockItem.getBlock().defaultBlockState();
        else throw new IllegalArgumentException("创建ThrownBlock用的ItemStack必须用BlockItem");
        var bISP = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        if (!bISP.isEmpty()) state = bISP.apply(state);

        ThrownBlock thrownBlock = new ThrownBlock(FSEntities.THROWN_BLOCK.get(), shooter, level, yOffset, state);
        thrownBlock.damageMultiply = damageMultiply;

        if (state.hasBlockEntity()) {
            var components = stack.getComponents();
            var data = components.get(DataComponents.BLOCK_ENTITY_DATA);
            if (data != null) thrownBlock.blockEntityData = data.copyTag();
            var contents = components.get(DataComponents.CONTAINER);
            if (contents != null) thrownBlock.blockConter = contents;
        }

        thrownBlock.speedLimit = limit;
        thrownBlock.shootFromRotation(shooter, rx, ry, z, 1F, inaccuracy);
        return thrownBlock;
    }

    public static float getLaunchStrength(ThrownBlock thrownBlock, @Nullable Entity shooter) {
        return getLaunchStrength(thrownBlock, shooter, DEFAULT_SPEED_LIMIT);
    }

    public static float getLaunchStrength(ThrownBlock thrownBlock, @Nullable Entity shooter, double limit) {
        double vector = thrownBlock.blockQuality;
        if (shooter instanceof LivingEntity livingEntity) {
            var strengthEff = livingEntity.getEffect(MobEffects.DAMAGE_BOOST);
            int strength = strengthEff != null ? strengthEff.getAmplifier() + 1 : 0;
            var weakEff = livingEntity.getEffect(MobEffects.WEAKNESS);
            int weak = weakEff != null ? weakEff.getAmplifier() + 1 : 0;
            int i = strength - weak;
            if (i > 0) vector /= (i + 1);
            else if (i < 0) vector -= i;
        }
        return (float)(1 / Math.max(vector, limit));
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, getLaunchStrength(this, getOwner(), speedLimit) * velocity, inaccuracy);
    }

    @Override
    public void tick() {
        if (blockState.isAir()) {
            discard();
            return;
        }
        super.tick();

        double friction = onGround() ? this.friction : 0.98;
        Vec3 delta = getDeltaMovement();
        setDeltaMovement(delta.x * friction, delta.y - gravity, delta.z * friction);

        HitResult hitresult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
        if (hitresult.getType() != HitResult.Type.MISS && !EventHooks.onProjectileImpact(this, hitresult))
            hitTargetOrDeflectSelf(hitresult);
        if (isRemoved()) return;

        move(MoverType.SELF, getDeltaMovement());
        if (level() instanceof ServerLevel serverLevel && onGround() && getDeltaMovement().length() < MIN_SPEED) {
            if (toBlock(blockPosition(), Direction.UP)) discard();
            else destroy(ItemStack.EMPTY, serverLevel);
        }
        else {
            updateRotation();
            checkInsideBlocks();
        }
    }

    @Override
    protected void updateRotation() {
        //TODO:检查摸酱是否修复了弹射物旋转方向错误的bug,为了兼容性暂维持
        Vec3 vec3 = getDeltaMovement();
        if (vec3.length() > MIN_SPEED) setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 180.0F / (float)Math.PI));
        if (onGround()) setXRot(0);
        else setXRot((float)(Mth.atan2(vec3.y, vec3.horizontalDistance()) * 180.0F / (float)Math.PI));
        if (level().isClientSide && facingAnimation == FacingAnimation.NONE) {
            /*double p = Math.PI / 2;
            xArcO = xArc; zArcO = zArc;
            xArc = xArcO + vec3.z * p;
            zArc = zArcO + vec3.x * p;
            double pai2 = 2 * Math.PI;
            if (Math.min(xArc, xArcO) > pai2) {
                xArc -= pai2; xArcO -= pai2;
            }
            else if (Math.max(xArc, xArcO) < 0) {
                xArc += pai2; xArcO += pai2;
            }
            if (Math.min(zArc, zArcO) > pai2) {
                zArc -= pai2; zArcO -= pai2;
            }
            else if (Math.max(zArc, zArcO) < 0) {
                zArc += pai2; zArcO += pai2;
            }*/
            /*double speed = vec3.horizontalDistance();
            rollRotationPrev.set(rollRotation);
            if (speed > MIN_SPEED) {
                float axisX = (float)(vec3.z / speed);
                float axisZ = (float)(-vec3.x / speed);
                rollRotation.rotateAxis((float)(speed * (Math.PI / 2)), axisX, 0, axisZ);
                rollRotation.normalize();
            }*/
            double speed = vec3.horizontalDistance();
            rollAnglePrev = rollAngle;
            if (speed > MIN_SPEED) rollAngle += speed * (Math.PI / 2);
            else rollAngle = 0;
        }
    }

    //备用
    public float getGroundFriction() {
        //船同款
        AABB sourceAABB = getBoundingBox();
        AABB testAABB = new AABB(sourceAABB.minX, sourceAABB.minY - 0.03125, sourceAABB.minZ, sourceAABB.maxX, sourceAABB.minY, sourceAABB.maxZ);
        int x1 = Mth.floor(testAABB.minX) -1;
        int x2 = Mth.ceil(testAABB.maxX) +1;
        int y1 = Mth.floor(testAABB.minY) -1;
        int y2 = Mth.ceil(testAABB.maxY) +1;
        int z1 = Mth.floor(testAABB.minZ) -1;
        int z2 = Mth.ceil(testAABB.maxZ) +1;
        VoxelShape voxelshape = Shapes.create(testAABB);
        float friction = 0.0F;
        int blocks = 0;
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
        for (int x = x1; x < x2; x++) {
            for (int z = z1; z < z2; z++) {
                int j2 = (x != x1 && x != x2 - 1 ? 0 : 1) + (z != z1 && z != z2 - 1 ? 0 : 1);
                if (j2 != 2) {
                    for (int y = y1; y < y2; y++) {
                        if (j2 <= 0 || y != y1 && y != y2 - 1) {
                            blockPos.set(x, y, z);
                            BlockState blockstate = level().getBlockState(blockPos);
                            if (Shapes.joinIsNotEmpty(
                                    blockstate.getCollisionShape(level(), blockPos).move(x, y, z),
                                    voxelshape,
                                    BooleanOp.AND
                            )) {
                                friction += blockstate.getFriction(level(), blockPos, this);
                                blocks++;
                            }
                        }
                    }
                }
            }
        }

        return friction / (float)blocks;
    }

    @Override
    protected boolean canHitEntity(Entity target) {
        return super.canHitEntity(target) && !blockState.getCollisionShape(level(), blockPosition(), CollisionContext.of(target)).isEmpty();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity target = result.getEntity();
        LivingEntity livingTarget = target instanceof LivingEntity le ? le : null;
        ThrownBlock targetBlock = target instanceof ThrownBlock tb ? tb : null;
        LivingEntity livingOwner = getOwner() instanceof LivingEntity le ? le : null;

        Vec3 vec = getDeltaMovement();
        //相对速度
        double speed = vec.subtract(target.getDeltaMovement()).length();
        double damage = baseDamage * blockQuality * speed * damageMultiply;
        //要避免触发投掷方块的hurt，因为那里用伤害换破坏,逻辑是不一样的
        boolean hurt = targetBlock == null && target.hurt(
                damageSources().source(FSDamageTypes.THROWN_BLOCK, this, this.getOwner()),
                (float) damage
        );

        //伤害类型为无击退，换自定义击退
        //用碰撞箱模拟目标质量
        double targetQuality = targetBlock == null ? Math.max(target.getBbWidth() * target.getBbHeight(), MIN_QUALITY) : targetBlock.blockQuality;
        double lostSpeed = speed - speed * (blockQuality / (blockQuality + targetQuality));
        double blockSpeed = vec.length();
        double remainingSpeed = blockSpeed - lostSpeed;
        setDeltaMovement(vec.scale(blockSpeed == 0 ? 0 : remainingSpeed / blockSpeed));
        //模拟反弹和推撞的击退
        if (hurt && livingTarget != null) livingTarget.knockback(Math.max(lostSpeed, remainingSpeed), -vec.x, -vec.z);
            //如果是方块互撞，则可以安全手动击退
        else if (targetBlock != null) {
            targetBlock.addDeltaMovement(vec.scale(Math.max(lostSpeed, remainingSpeed)));
            targetBlock.destroyProgress += (float) lostSpeed;
            targetBlock.checkDestroy(ItemStack.EMPTY);
        }

        if (livingOwner != null) livingOwner.setLastHurtMob(target);
        destroyProgress += (float) lostSpeed;
        checkDestroy(ItemStack.EMPTY);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (level().isClientSide) return;
        Direction direction = result.getDirection();
        if (toBlock(result.getBlockPos().relative(direction), direction.getOpposite())) discard();
    }

    protected boolean toBlock(BlockPos pos, Direction facing) {
        BlockState posState = level().getBlockState(pos);
        if (!posState.canBeReplaced()) return false;

        Direction hFacing = facing.getAxis() == Direction.Axis.Y ? getDirection() : facing;
        BlockState state = blockState;
        if (state.getBlock() instanceof ThrowableBlock tb) state = tb.getHitOnBlockState(level(), pos, facing, state);
        else {
            if (state.is(BlockTags.SLABS) && state.hasProperty(BlockStateProperties.SLAB_TYPE)) state = state.setValue(
                    BlockStateProperties.SLAB_TYPE,
                    facing == Direction.UP ? SlabType.TOP : SlabType.BOTTOM
            );
            if (state.hasProperty(BlockStateProperties.ATTACH_FACE)) state = state.setValue(
                    BlockStateProperties.ATTACH_FACE,
                    switch (facing) {
                        case UP -> AttachFace.CEILING;
                        case DOWN -> AttachFace.FLOOR;
                        default -> AttachFace.WALL;
                    }
            );
            if (state.hasProperty(BlockStateProperties.FACING)) state = state.setValue(BlockStateProperties.FACING, facing);
            else if (state.hasProperty(BlockStateProperties.AXIS)) state = state.setValue(BlockStateProperties.AXIS, facing.getAxis());
            else if (state.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) state = state.setValue(
                    BlockStateProperties.HORIZONTAL_FACING,
                    //摸酱做的弹射物方向是错的，需要修正
                    hFacing.getAxis() == Direction.Axis.Z ? hFacing : hFacing.getOpposite()
            );
            else if (state.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) state = state.setValue(BlockStateProperties.HORIZONTAL_AXIS, hFacing.getAxis());
        }
        if (!state.canSurvive(level(), pos)) return false;

        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && level().getFluidState(pos).getType() == Fluids.WATER)
            state = state.setValue(BlockStateProperties.WATERLOGGED, true);
        if (level().setBlock(pos, state, Block.UPDATE_ALL)) {
            Player player = getOwner() instanceof Player p ? p : null;
            if (blockEntityData != null && state.hasBlockEntity()) {
                BlockEntity blockentity = level().getBlockEntity(pos);
                if (blockentity != null) {
                    try {
                        Item item = state.getBlock().asItem();
                        if (item instanceof BlockItem blockItem) {
                            ItemStack stack = blockItem.getDefaultInstance();
                            stack.set(DataComponents.BLOCK_ENTITY_DATA, CustomData.of(blockEntityData));
                            if (blockConter != null) stack.set(DataComponents.CONTAINER, blockConter);
                            BlockItem.updateCustomBlockEntityTag(level(), player, pos, stack);
                            blockentity.applyComponentsFromItemStack(stack);
                            blockentity.setChanged();
                            state.getBlock().setPlacedBy(level(), pos, state, player, stack);
                        }
                    } catch (Exception exception) {
                        FierceSource.LOGGER.error("Failed to load block entity from thrown block", exception);
                    }
                    blockentity.setChanged();
                }
            }
            if (player instanceof ServerPlayer sp) CriteriaTriggers.PLACED_BLOCK.trigger(sp, pos, ItemStack.EMPTY);
            return true;
        }
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        ItemStack weaponItem = source.getWeaponItem() == null ? ItemStack.EMPTY : source.getWeaponItem();
        //当由玩家直接打击时
        //   覆盖owner并击退
        //   如果手上的工具是正确的挖掘工具(空手不算)，则进行破坏
        //其他情况用伤害替代破坏，如果伤害来源是实体则用伤害造成击退
        if (!(source.getDirectEntity() instanceof ThrownBlock)
                && source.getDirectEntity() instanceof Player player
                && player == source.getEntity()
                && source.is(DamageTypeTags.IS_PLAYER_ATTACK)
        ) {
            setOwner(player);
            //不要让客户端模拟，玩家的移动向量和服务端差距明显
            if (!(level() instanceof ServerLevel)) return super.hurt(source, amount);
            //玩家的移动向量是客户端的,用这个
            Vec3 playerVector = player.getKnownMovement();
            //引用攻击冷却防止连击器暴力行为
            float attackScale = player.getAttackStrengthScale(0.0F);
            addDeltaMovement(playerVector.add(player.getLookAngle().normalize().scale(
                    attackScale * getLaunchStrength(this, player)
            )));
            if (weaponItem.isEmpty()) return super.hurt(source, amount);
            //创造模式直接破坏
            if (player.hasInfiniteMaterials()) {
                level().broadcastEntityEvent(this, (byte) 3);
                this.discard();
                return super.hurt(source, amount);
            }
            if (isSaveBlock(blockState)) {
                //TODO: 检查原版改变 blockState.getDestroyProgress();
                boolean canHarvest = EventHooks.doPlayerHarvestCheck(player, blockState, level(), blockPosition());
                if (canHarvest) destroyProgress += player.getDigSpeed(blockState, blockPosition()) / 30
                        * attackScale
                        * DESTROY_PROGRESS_MULTIPLY
                        + (float) getDeltaMovement().subtract(playerVector).length();
            }
        }
        else {
            boolean doDamage = !isInvulnerableTo(source);
            if (doDamage) {
                boolean isExplosion = source.is(DamageTypes.EXPLOSION);
                if (isExplosion) amount -= explosionResistance;
                float damage = amount > 0 ? amount / DEFAULT_DAMAGE : 0;
                destroyProgress += damage;
                if (source.getDirectEntity() instanceof Entity entity && !isExplosion)
                    addDeltaMovement(position().subtract(entity.position()).scale(damage));
            }
        }
        checkDestroy(weaponItem);
        markHurt();
        return true;
    }

    protected void checkDestroy(ItemStack tool) {
        if (!(level() instanceof ServerLevel level)) return;
        if (destroyProgress > destroySpeed) destroy(tool, level);
        else syncDestroyProgress();
    }

    protected void destroy(ItemStack tool, ServerLevel level) {
        level.broadcastEntityEvent(this, (byte) 3);
        boolean isCreative = getOwner() instanceof LivingEntity le && le.hasInfiniteMaterials();
        if (!isCreative && isSaveBlock(blockState)) {
            BlockEntity blockEntity = null;
            if (blockState.getBlock() instanceof EntityBlock entityBlock && blockEntityData != null) {
                blockEntity = entityBlock.newBlockEntity(blockPosition(), blockState);
                if (blockEntity != null) blockEntity.loadWithComponents(blockEntityData, level.registryAccess());
            }

            //手动收集掉落物，不经过neo事件，在实际位置生成掉落物，可能会漏掉部分模组独特附加的掉落物，需手动触发掉经验。
            Block.getDrops(blockState, level, blockPosition(), blockEntity, getOwner(), tool).forEach(this::spawnAtLocation);
            if (level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS) && !level.restoringBlockSnapshots)
                ExperienceOrb.award(
                        level,
                        position(),
                        EnchantmentHelper.processBlockExperience(
                                level, tool,
                                blockState.getExpDrop(level, blockPosition(), blockEntity, getOwner(), tool)
                        )
                );

            //这个做法会在当前的方块位置生成掉落物而不是实际位置，但能正常经过neo的掉落物收集api，兼容性更好？但这个方块是假的，其他模组当真了可能会炸。
            //Block.dropResources(blockState, level, blockPosition(), blockEntity, getOwner(), tool);

            //掉落其他
            if (blockConter != null) blockConter.nonEmptyItems().forEach(this::spawnAtLocation);
        }
        this.discard();
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float multiplier, DamageSource source) {
        return false;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id != 3) super.handleEntityEvent(id);
        if (!level().isClientSide) return;
        //TODO:检查原版更改 ParticleEngine.destroy
        //原版方法用方块坐标，此处需要实体坐标，并且碰撞箱不敏感
        Level level = level();
        for (int l = 0; l < 4; l++) {
            for (int i1 = 0; i1 < 4; i1++) {
                for (int j1 = 0; j1 < 4; j1++) {
                    double d4 = (l + 0.5) / 4;
                    double d5 = (i1 + 0.5) / 4;
                    double d6 = (j1 + 0.5) / 4;
                    level.addParticle(
                            new BlockParticleOption(ParticleTypes.BLOCK, blockState).setPos(blockPosition()),
                            position().x -0.5 + d4,
                            position().y + d5,
                            position().z -0.5 + d6,
                            d4 - 0.5,
                            d5 - 0.5,
                            d6 - 0.5
                    );
                }
            }
        }
    }

    @Override
    protected Component getTypeName() {
        return Component.translatable("entity.fiercecraft.thrown_block", blockState.getBlock().getName());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DESTROY_PROGRESS, 0F);
    }

    protected void syncDestroyProgress() {
        entityData.set(DESTROY_PROGRESS, destroyProgress);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);
        if (level().isClientSide) {
            if (DESTROY_PROGRESS.equals(key)) {
                destroyProgress = entityData.get(DESTROY_PROGRESS);
            }
        }
    }

    @Override
    public void writeSpawnData(RegistryFriendlyByteBuf buffer) {
        buffer.writeNbt(NbtUtils.writeBlockState(blockState));
    }

    @Override
    public void readSpawnData(RegistryFriendlyByteBuf additionalData) {
        setBlockState(NbtUtils.readBlockState(level().holderLookup(Registries.BLOCK), additionalData.readNbt()));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.put("BlockState", NbtUtils.writeBlockState(blockState));
        if (blockEntityData != null) compound.put("BlockEntityData", blockEntityData);
        if (blockConter != null) {
            var codec = DataComponents.CONTAINER.codec();
            if (codec != null) codec.encode(blockConter, level().registryAccess().createSerializationContext(NbtOps.INSTANCE), compound);
        }

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        setBlockState(NbtUtils.readBlockState(level().holderLookup(Registries.BLOCK), compound.getCompound("BlockState")));
        if (blockState.hasBlockEntity() && compound.contains("BlockEntityData")) blockEntityData = compound.getCompound("BlockEntityData").copy();
        var codec = DataComponents.CONTAINER.codec();
        if (codec != null) codec.decode(level().registryAccess().createSerializationContext(NbtOps.INSTANCE), compound).ifSuccess(p -> blockConter = p.getFirst());

    }

    protected void setBlockState(BlockState blockState) {
        if (blockState.getBlock() instanceof ThrowableBlock block) {
            blockState = block.getThrowedBlockState(blockState);
            baseDamage = block.getBaseDamage(blockState);
            gravity = block.getGravity(blockState);
            facingAnimation = block.getFacingAnimation(blockState);
        }
        else {
            if (!blockState.isCollisionShapeFullBlock(level(), blockPosition())) {
                if (blockState.getBlock() instanceof PointedDripstoneBlock) facingAnimation = FacingAnimation.UP;
                else if (blockState.hasProperty(BlockStateProperties.FACING)) {
                    blockState = blockState.setValue(BlockStateProperties.FACING, Direction.NORTH);
                    facingAnimation = FacingAnimation.FRONT;
                }
                else if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_FACING)) {
                    blockState = blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH);
                    facingAnimation = FacingAnimation.FRONT;
                }
                else if (blockState.hasProperty(BlockStateProperties.AXIS)) {
                    blockState = blockState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z);
                    facingAnimation = FacingAnimation.FRONT;
                }
                else if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
                    blockState = blockState.setValue(BlockStateProperties.HORIZONTAL_AXIS, Direction.Axis.Z);
                    facingAnimation = FacingAnimation.FRONT;
                }
                else facingAnimation = FacingAnimation.DOWN;
            }
            baseDamage = DEFAULT_DAMAGE;
            gravity = DEFAULT_GRAVITY;
        }

        this.blockState = blockState;
        destroySpeed = this.blockState.getDestroySpeed(level(), blockPosition());
        blockQuality = Math.max(destroySpeed, MIN_QUALITY);
        if (!this.blockState.isCollisionShapeFullBlock(level(), blockPosition())) blockQuality *= 0.5;
        explosionResistance = this.blockState.getBlock().getExplosionResistance();
        friction = this.blockState.getFriction(level(), blockPosition(), this);
        for (Direction direction : Direction.values()) {
            if (this.blockState.getFlammability(level(), blockPosition(), direction) > 0) {
                flammable = true;
                break;
            }
        }
    }

    /**
     * 越大，速度越小，仅影响发射
     */
    public void setSpeedLimit(double speedLimit) {
        this.speedLimit = Math.max(speedLimit, 0.01);
    }

    @Override
    public boolean fireImmune() {
        return !flammable;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    public float getPickRadius() {
        return 0;
    }

    @Override
    protected double getDefaultGravity() {
        return gravity;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public boolean canUsePortal(boolean allowPassengers) {
        return true;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !isRemoved() && leftOwner;
    }

    //TODO:伤害型tag 方块行为

    public BlockState getBlockState() {
        return blockState;
    }

    @Nullable
    public CompoundTag getBlockEntityData() {
        return blockEntityData;
    }

    public float getDestroyProgress() {
        return destroySpeed == 0 ? destroyProgress > 0 ? 1 : 0 : Math.min(destroyProgress / destroySpeed, 1F);
    }

    public FacingAnimation getFacingAnimation() {
        return facingAnimation;
    }

    /**
     * 检查是否能安全执行一些方块代码比如掉落物hook，有些模组的方块的行为需要真实的方块，在此实体上执行可能会导致崩溃。
     * <p>不过绝大多数情况下都是安全的</p>
     */
    protected static boolean isSaveBlock(BlockState blockState) {
        return !(CommonConfig.thrownBlockSaveMode || blockState.is(FSBlockTags.THROWN_BAN));
    }

    public static boolean isValid(ItemStack stack) {
        return isValid(stack, EmptyBlockGetter.INSTANCE, BlockPos.ZERO);
    }
    
    public static boolean isValid(ItemStack stack, BlockGetter level, BlockPos pos) {
        return !stack.has(DataComponents.BLOCK_ENTITY_DATA)
                && !stack.has(DataComponents.CONTAINER)
                && stack.getItem() instanceof BlockItem item
                && isValid(item.getBlock().defaultBlockState(), level, pos);
    }

    public static boolean isValid(BlockState state, BlockGetter level, BlockPos pos) {
        return !state.isAir() && isSaveBlock(state) && !state.is(FSBlockTags.CANT_THROW)
                && state.getDestroySpeed(level, pos) >= 0 && !state.hasBlockEntity();
    }

    public enum FacingAnimation {
        NONE,
        FRONT,
        UP,
        DOWN
    }

}
