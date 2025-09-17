package jacobg5.japi.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

public interface JBlockEntityType {
    void JAPI$addBlocks(Block... blocks);

    /**
     * Makes blocks valid for the specified BlockEntity
     *
     * @param blockEntityType block entity to modify
     * @param blocks blocks that are valid to contain the block entity
     */
    static void addValidBlocks(BlockEntityType<?> blockEntityType, Block... blocks) {
        ((JBlockEntityType)blockEntityType).JAPI$addBlocks(blocks);
    }
}
