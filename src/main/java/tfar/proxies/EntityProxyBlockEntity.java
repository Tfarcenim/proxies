package tfar.proxies;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class EntityProxyBlockEntity extends TileEntity {

    protected UUID proxy;

    public EntityProxyBlockEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public static EntityProxyBlockEntity itemProxy() {
        return new EntityProxyBlockEntity(ModBlockEntites.ITEM_PROXY);
    }

    public void setProxy(UUID proxy) {
        this.proxy = proxy;
    }

    public UUID getProxy() {
        return proxy;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (proxy != null && cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            Entity entity = ((ServerWorld)world).getEntityByUuid(proxy);
            if (entity != null) {
                return entity.getCapability(cap,side);
            }
        }
        return super.getCapability(cap, side);
    }
}
