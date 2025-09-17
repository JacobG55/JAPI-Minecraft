package jacobg5.japi.block;

import net.minecraft.item.AutomaticItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class AutomaticBlockPlacementContext extends AutomaticItemPlacementContext {
    private final Direction facing;

    public AutomaticBlockPlacementContext(World world, BlockPos pos, Direction facing, ItemStack stack, Direction side) {
        super(world, pos, facing, stack, side);
        this.facing = facing;
    }

    @Override
    public Direction getPlayerLookDirection() {
        return this.facing.getOpposite();
    }
}
