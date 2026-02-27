package net.fiercemanul.fiercesource.world.level.app;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;

public interface MenuApp {


    default void menuTick() {}

    void handleData(Object data);

    default boolean menuStillValid(Player player) {
        return true;
    }

    /**
     * 清理，释放内存。
     */
    void setRemove();

    boolean isRemoved();

    /**
     * @return 用于常规处理的实槽组
     */
    default Slot[] buildHardSlots() {
        return new Slot[0];
    }

    /**
     * <p>获取假（动态）槽，总是在真槽之后，服务端和客户端可能有两种结果。
     * <p>服务端调用时，通常为每功能64个槽。
     * <p>客户端调用时，槽数量不确定。
     * @return 假槽组
     */
    default Slot[] getFakeSlots() {
        return new Slot[0];
    }

}
