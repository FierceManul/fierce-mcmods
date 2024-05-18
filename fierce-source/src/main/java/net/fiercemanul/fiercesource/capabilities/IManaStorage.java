package net.fiercemanul.fiercesource.capabilities;

/**
 * 魔能能力,与电力相同的行为.
 */
public interface IManaStorage {

    /**
     * 注入魔能.返回接受的量.
     * @param maxReceive 注入上限.
     * @param simulate 是否模拟.
     * @return 成功接受的魔能量.
     */
    long receiveMana(long maxReceive, boolean simulate);

    /**
     * 抽取魔能,返回成功抽取的量.
     * @param maxExtract 抽取上限.
     * @param simulate 是否模拟.
     * @return 成功抽取的魔能量.
     */
    long extractEnergy(long maxExtract, boolean simulate);

    /**
     * 返回储存的魔能量.
     */
    long getEnergyStored();

    /**
     * 返回可用储存的魔能量上限.
     */
    long getMaxEnergyStored();

    /**
     * 返回是否可用注入.
     */
    boolean canReceive();

    /**
     * 返回是否可用抽出.
     */
    boolean canExtract();
}
