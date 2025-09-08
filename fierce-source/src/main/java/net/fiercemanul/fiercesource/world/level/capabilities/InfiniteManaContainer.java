package net.fiercemanul.fiercesource.world.level.capabilities;

public class InfiniteManaContainer implements IManaHandler {

    public static final InfiniteManaContainer INSTANCE = new InfiniteManaContainer();

    private InfiniteManaContainer() {}

    @Override
    public long receiveMana(long maxReceive, boolean simulate){
        return maxReceive;
    }

    @Override
    public long extractEnergy(long maxExtract, boolean simulate) {
        return maxExtract;
    }

    @Override
    public long getEnergyStored() {
        return Long.MAX_VALUE;
    }

    @Override
    public long getMaxEnergyStored() {
        return Long.MAX_VALUE;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

    @Override
    public boolean canExtract() {
        return true;
    }
}
