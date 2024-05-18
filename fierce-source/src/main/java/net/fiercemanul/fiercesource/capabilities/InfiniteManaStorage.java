package net.fiercemanul.fiercesource.capabilities;

public class InfiniteManaStorage implements IManaStorage{

    public static final InfiniteManaStorage INSTANCE = new InfiniteManaStorage();

    private InfiniteManaStorage() {}

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
