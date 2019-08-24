package com.hrznstudio.galacticraft.api.entity;

import com.hrznstudio.galacticraft.api.wire.WireNetwork;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;

import java.util.UUID;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class WireBlockEntity extends BlockEntity {

    public boolean tickedOnce;

    /**
     * The ID of the WireNetwork that this BlockEntity belongs to.
     *
     * @see WireNetwork
     */
    public UUID networkId;

    public WireBlockEntity(BlockEntityType<?> blockEntityType_1) {
        super(blockEntityType_1);
    }

    public void onPlaced() {
        networkId = new WireNetwork(this).getId();
        tickedOnce = true;
        WireNetwork.blockPlaced();
    }
}