package net.fiercemanul.fiercedecoration.capabilities;

import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;

public class InfinityWaterHandler implements IFluidHandler {


    private static final FluidStack water = new FluidStack(Fluids.WATER, Integer.MAX_VALUE);
    public static final InfinityWaterHandler INSTANCE = new InfinityWaterHandler();

    private InfinityWaterHandler() {}

    @Override
    public int getTanks() {
        return 1;
    }

    @Override
    public FluidStack getFluidInTank(int tank) {
        return water;
    }

    @Override
    public int getTankCapacity(int tank) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isFluidValid(int tank, FluidStack stack) {
        return stack.getFluid().equals(Fluids.WATER);
    }

    @Override
    public int fill(FluidStack resource, FluidAction action) {
        if (resource.getFluid().equals(Fluids.WATER)) return resource.getAmount();
        return 0;
    }

    @Override
    public FluidStack drain(FluidStack resource, FluidAction action) {
        if (resource.getFluid().equals(Fluids.WATER)) return new FluidStack(Fluids.WATER, resource.getAmount());
        return FluidStack.EMPTY;
    }

    @Override
    public FluidStack drain(int maxDrain, FluidAction action) {
        return new FluidStack(Fluids.WATER, maxDrain);
    }
}
