package tfar.proxies;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;

public class ModBlocks {

    public static final Block ITEM_PROXY = new ProxyBlock(AbstractBlock.Properties.from(Blocks.IRON_BLOCK),() -> ModBlockEntites.ITEM_PROXY);
    public static final Block ENTITY_PROXY = new EntityProxyBlock(AbstractBlock.Properties.from(Blocks.IRON_BLOCK),() -> ModBlockEntites.ENTITY_PROXY);

}
