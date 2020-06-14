/*
 * Copyright (c) 2019 HRZN LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.hrznstudio.galacticraft.block.entity;

import com.hrznstudio.galacticraft.Galacticraft;
import com.hrznstudio.galacticraft.api.block.entity.ConfigurableElectricMachineBlockEntity;
import com.hrznstudio.galacticraft.energy.GalacticraftEnergy;
import com.hrznstudio.galacticraft.entity.GalacticraftBlockEntities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Tickable;

import java.util.function.Predicate;

/**
 * @author <a href="https://github.com/StellarHorizons">StellarHorizons</a>
 */
public class EnergyStorageModuleBlockEntity extends ConfigurableElectricMachineBlockEntity implements Tickable {
    public static final int CHARGE_BATTERY_SLOT = 0;
    public static final int DRAIN_BATTERY_SLOT = 1;

    public EnergyStorageModuleBlockEntity() {
        super(GalacticraftBlockEntities.ENERGY_STORAGE_MODULE_TYPE);
    }

    @Override
    protected boolean canExtractEnergy() {
        return true;
    }

    @Override
    protected boolean canInsertEnergy() {
        return true;
    }

    @Override
    public int getMaxEnergy() {
        return Galacticraft.configManager.get().energyStorageModuleStorageSize();
    }

    @Override
    protected int getInventorySize() {
        return 2;
    }

    @Override
    public Predicate<ItemStack> getFilterForSlot(int slot) {
        return GalacticraftEnergy.ENERGY_HOLDER_ITEM_FILTER;
    }

    @Override
    protected int getBatteryTransferRate() {
        return 5;
    }

    @Override
    public void tick() {
        if (world.isClient || disabled()) {
            return;
        }
        attemptChargeFromStack(DRAIN_BATTERY_SLOT);
        attemptDrainPowerToStack(CHARGE_BATTERY_SLOT);
        trySpreadEnergy();
    }

    @Override
    public int getEnergyUsagePerTick() {
        return 0;
    }
}
