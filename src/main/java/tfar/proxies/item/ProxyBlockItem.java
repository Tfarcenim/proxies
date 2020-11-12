package tfar.proxies.item;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ProxyBlockItem extends BlockItem {
    public ProxyBlockItem(Block block,Properties properties) {
        super(block,properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        if (world.getTileEntity(pos) != null) {
            if (!world.isRemote) {
                context.getItem().getOrCreateTag().putIntArray("proxy",new int[]{pos.getX(),pos.getY(),pos.getZ()});
            }
            return ActionResultType.SUCCESS;
        }
        return super.onItemUse(context);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.hasTag() && stack.getTag().contains("proxy")) {
            int[] xyz = stack.getTag().getIntArray("proxy");
            tooltip.add(new StringTextComponent("X: "+xyz[0]));
            tooltip.add(new StringTextComponent("Y: "+xyz[1]));
            tooltip.add(new StringTextComponent("Z: "+xyz[2]));
        }
    }
}
