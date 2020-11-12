package tfar.proxies;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Supplier;

public class EntityProxyBlock extends ProxyBlock {

    public EntityProxyBlock(Properties properties, Supplier<TileEntityType<?>> supplier) {
        super(properties, supplier);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        EntityProxyBlockEntity blockProxyBlockEntity = (EntityProxyBlockEntity) worldIn.getTileEntity(pos);
        if (!worldIn.isRemote && stack.hasTag()) {
            UUID uuid = stack.getTag().getUniqueId("proxy");
            blockProxyBlockEntity.setProxy(uuid);
        }
    }

}
