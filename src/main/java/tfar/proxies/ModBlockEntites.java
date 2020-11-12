package tfar.proxies;

import net.minecraft.tileentity.TileEntityType;

public class ModBlockEntites {

    public static final TileEntityType<?> ITEM_PROXY = TileEntityType.Builder.create(BlockProxyBlockEntity::itemProxy,ModBlocks.ITEM_PROXY).build(null);
    public static final TileEntityType<?> ENTITY_PROXY = TileEntityType.Builder.create(EntityProxyBlockEntity::itemProxy,ModBlocks.ENTITY_PROXY).build(null);


}
