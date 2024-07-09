package net.fiercemanul.fiercedecoration.world.entity;

import com.google.common.collect.ImmutableMap;
import net.fiercemanul.fiercedecoration.FierceDecoration;
import net.minecraft.BlockUtil;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class Seat extends VehicleEntity {


    public static final Vec3 PASSENGER_ATTACHMENT_POINT = new Vec3(0.0, 0.0, 0.0);
    public static final ImmutableMap<Direction, Vec3i[]> DISMOUNT_LOCATIONS = ImmutableMap
            .<Direction, Vec3i[]>builder()
            .put(Direction.NORTH, getOffsets(Direction.NORTH))
            .put(Direction.SOUTH, getOffsets(Direction.SOUTH))
            .put(Direction.WEST, getOffsets(Direction.WEST))
            .put(Direction.EAST, getOffsets(Direction.EAST))
            .build();

    public static Vec3i[] getOffsets(Direction direction) {
        return new Vec3i[]{
                Vec3i.ZERO,
                direction.getNormal(),
                direction.getCounterClockWise().getNormal(),
                direction.getClockWise().getNormal(),
                direction.getOpposite().getNormal(),
                direction.getNormal().offset(direction.getCounterClockWise().getNormal()),
                direction.getNormal().offset(direction.getClockWise().getNormal()),
                direction.getOpposite().getNormal().offset(direction.getCounterClockWise().getNormal()),
                direction.getOpposite().getNormal().offset(direction.getClockWise().getNormal()),
        };
    }

    public Seat(EntityType<? extends Seat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public Seat(Level pLevel, double pX, double pY, double pZ) {
        super(FierceDecoration.SEAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public float maxUpStep() {
        return 0.25F;
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.EVENTS;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity pEntity, EntityDimensions pDimensions, float pScale) {
        return PASSENGER_ATTACHMENT_POINT;
    }

    @Override
    protected boolean canAddPassenger(Entity pPassenger) {
        return pPassenger instanceof Player;
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        if (pPlayer.isSecondaryUseActive()) return InteractionResult.PASS;
        else if (!this.level().isClientSide) return pPlayer.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
        else return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        return entity instanceof LivingEntity livingentity ? livingentity : super.getControllingPassenger();
    }

    @Override
    public Vec3 getRelativePortalPosition(Direction.Axis pAxis, BlockUtil.FoundRectangle pPortal) {
        return LivingEntity.resetForwardDirectionOfRelativePortalPosition(super.getRelativePortalPosition(pAxis, pPortal));
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity pPassenger) {
        BlockPos pos = this.blockPosition();
        Vec3i[] vec3is = DISMOUNT_LOCATIONS.get(pPassenger.getDirection());
        if (vec3is != null) for (Vec3i vec3i : vec3is) {
            Vec3 vec3 = DismountHelper.findSafeDismountLocation(pPassenger.getType(), pPassenger.level(), pos.offset(vec3i), true);
            if (vec3 != null) {
                remove(RemovalReason.DISCARDED);
                return vec3;
            }
        }
        remove(RemovalReason.DISCARDED);
        return super.getDismountLocationForPassenger(pPassenger);
    }

    @Override
    protected AABB makeBoundingBox() {
        if (getFirstPassenger() != null) {
            EntityDimensions dimensions = getFirstPassenger().getType().getDimensions();
            AABB aabb = dimensions.makeBoundingBox(position());
            return aabb.setMaxY(aabb.maxY - dimensions.attachments().get(EntityAttachment.VEHICLE, 0, this.getYRot()).y);
        }
        return super.makeBoundingBox();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isControlledByLocalInstance()) {

            double d1 = this.isNoGravity() ? 0.0 : -0.04F;
            float friction = 0.3F;

            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x * (double)friction, vec3.y + d1, vec3.z * (double)friction);

            if (this.level().isClientSide && getFirstPassenger() instanceof LocalPlayer player) {
                float x = 0.0F;
                float z = 0.0F;
                if (player.input.left) x -= 0.02F;
                if (player.input.right) x += 0.02F;
                if (player.input.up) z -= 0.02F;
                if (player.input.down) z += 0.02F;
                this.setDeltaMovement(this.getDeltaMovement().add(
                        Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)) * z + Mth.cos(player.getYRot() * (float) (Math.PI / 180.0)) * -x,
                        0.0,
                        Mth.cos(player.getYRot() * (float) (Math.PI / 180.0)) * -z + Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)) * -x
                ));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        }
        else this.setDeltaMovement(Vec3.ZERO);
    }

    @Override
    protected Item getDropItem() {
        return Items.AIR;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {}

    @Override
    public boolean shouldRender(double pX, double pY, double pZ) {
        return false;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        return false;
    }
}
