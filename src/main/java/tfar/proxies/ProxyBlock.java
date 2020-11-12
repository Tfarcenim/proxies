package tfar.proxies;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ProxyBlock extends Block {
    protected final Supplier<TileEntityType<?>> supplier;

    public ProxyBlock(Properties properties, Supplier<TileEntityType<?>> supplier) {
        super(properties);
        this.supplier = supplier;
    }

    @Override
    public final boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public final TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return supplier.get().create();
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof BlockProxyBlockEntity) {
            BlockProxyBlockEntity blockProxyBlockEntity = (BlockProxyBlockEntity) worldIn.getTileEntity(pos);
            if (!worldIn.isRemote && stack.hasTag()) {
                int[] xyz = stack.getTag().getIntArray("proxy");
                blockProxyBlockEntity.setProxy(new BlockPos(xyz[0], xyz[1], xyz[2]));
            }
        }
    }
}
