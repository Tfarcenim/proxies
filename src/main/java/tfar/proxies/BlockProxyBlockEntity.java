package tfar.proxies;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockProxyBlockEntity extends TileEntity {

    protected BlockPos proxy;

    public BlockProxyBlockEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public static BlockProxyBlockEntity itemProxy() {
        return new BlockProxyBlockEntity(ModBlockEntites.ITEM_PROXY);
    }

    public void setProxy(BlockPos proxy) {
        this.proxy = proxy;
    }

    public BlockPos getProxy() {
        return proxy;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (proxy != null) {
            TileEntity blockEntity = world.getTileEntity(proxy);
            if (blockEntity != null) {
                return blockEntity.getCapability(cap,side);
            }
        }
        return super.getCapability(cap, side);
    }
}
