package net.fiercemanul.fiercesource.world.level.capabilities;

import java.util.TreeMap;

/**
 * like IItemHandler
 * @param <T> like item
 * @param <S> like itemStack
 */
public interface IStackLikeContainerHandle<T, S> {


    /**
     * @return ContainerMaxSlots
     */
    int getMaxSlots();

    /**
     * @return Slots that not empty
     */
    int getSize();

    /**
     * Returns the Stack in a given slot.
     * The result's stack size may be greater than the stack's max size.
     * If the result is empty, then the slot is empty.
     *
     * <p>
     * <strong>IMPORTANT:</strong> This Stack <em>MUST NOT</em> be modified. This method is not for
     * altering an inventory's contents. Any implementers who are able to detect
     * modification through this method should throw an exception.
     * </p>
     * <p>
     * <strong><em>SERIOUSLY: DO NOT MODIFY THE RETURNED STACK</em></strong>
     * </p>
     *
     * @param slot Slot to query
     * @return Stack in given slot. Empty Stack if the slot is empty.
     **/
    S getStackInSlot(int slot);

    /**
     * Retrieves the maximum stack size allowed to exist in the given slot.
     *
     * @param slot Slot to query.
     * @return The maximum stack size allowed in the slot.
     */
    long getSlotMaxAmount(int slot);

    /**
     * Returns the lists of stacks.
     * The result's stack size may be greater than the stack's max size.
     *
     * <p>
     * <strong>IMPORTANT:</strong> This Stack <em>MUST NOT</em> be modified. This method is not for
     * altering an inventory's contents. Any implementers who are able to detect
     * modification through this method should throw an exception.
     * </p>
     * <p>
     * <strong><em>SERIOUSLY: DO NOT MODIFY THE RETURNED STACK</em></strong>
     * </p>
     *
     * @return Lists of stacks in given slot. Empty Stack if the slot is empty.
     **/
    S[] getAllStacks();


    /**
     * @return All same stacks total.
     */
    long getStackAmount(S stack);

    /**
     * @return All same type total.
     */
    long getTypeAmount(T item);

    default S insertStack(S stack, boolean simulate) {
        return insertStack(stack, simulate, false);
    }

    S insertStack(S stack, boolean simulate, boolean ignoreRate);

    S insertStack(int slot, S stack, boolean simulate);

    default long insertType(T type, long amount, boolean simulate) {
        return insertType(type, amount, simulate, false);
    }

    long insertType(T type, long amount, boolean simulate, boolean ignoreRate);


    /**
     * <strong>IMPORTANT:</strong> This Stack(MAP) <em>MUST NOT</em> be modified. This method is not for
     * altering an inventory's contents. Any implementers who are able to detect
     * modification through this method should throw an exception.
     *
     * <p>
     * <strong><em>SERIOUSLY: DO NOT MODIFY THE RETURNED STACK(MAP)</em></strong>
     * </p>
     *
     * @return Lists of can insert Slots.
     */
    TreeMap<Integer, S> eachValidInsertSlots();

    default S extractStack(S stack, boolean simulate) {
        return extractStack(stack, simulate, false);
    }

    S extractStack(S stack, boolean simulate, boolean ignoreRate);

    default S extractType(T type, long amount, boolean simulate) {
        return extractType(type, amount, simulate, false);
    }

    S extractType(T type, long amount, boolean simulate, boolean ignoreRate);

    S extractType(int slot, int amount, boolean simulate);


    /**
     * <strong>IMPORTANT:</strong> This Stack(MAP) <em>MUST NOT</em> be modified. This method is not for
     * altering an inventory's contents. Any implementers who are able to detect
     * modification through this method should throw an exception.
     *
     * <p>
     * <strong><em>SERIOUSLY: DO NOT MODIFY THE RETURNED STACK(MAP)</em></strong>
     * </p>
     *
     * @return Lists of can extract Slots.
     */
    TreeMap<Integer, S> eachValidExtractStacks();

    default boolean isStackValid(S stack) {
        return true;
    }

    default boolean isStackValid(int slot, S stack) {
        return true;
    }

    default boolean isTypeValid(T type) {
        return true;
    }

    default boolean isTypeValid(int slot, T type) {
        return true;
    }

    boolean isStandardContainer();
}
