
package org.bukkit.craftbukkit.entity;

import net.minecraft.server.EntityHuman;
import net.minecraft.server.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.inventory.CraftInventoryPlayer;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.PermissibleBase;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

//import org.bukkit.GameMode;

public class CraftHumanEntity extends CraftLivingEntity implements HumanEntity {
    private CraftInventoryPlayer inventory;
    protected final PermissibleBase perm = new PermissibleBase(this);
    private boolean op;

    public CraftHumanEntity(final CraftServer server, final EntityHuman entity) {
        super(server, entity);
        this.inventory = new CraftInventoryPlayer(entity.inventory);
    }

    public String getName() {
        return getHandle().name;
    }

    @Override
    public EntityHuman getHandle() {
        return (EntityHuman) entity;
    }

    public void setHandle(final EntityHuman entity) {
        super.setHandle((EntityHuman) entity);
        this.entity = entity;
        this.inventory = new CraftInventoryPlayer(entity.inventory);
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public InventoryView getOpenInventory() {
        return getHandle().activeContainer.getBukkitView();
    }

    public InventoryView openInventory(Inventory inventory) {
        return null; // TODO
    }

    public InventoryView openWorkbench(Location location, boolean force) {
        return null; // TODO
    }

    public void openInventory(InventoryView inventory) {
        // TODO
    }

    public void closeInventory() {
        ((EntityPlayer) getHandle()).y();
    }

    public ItemStack getItemInHand() {
        return getInventory().getItemInHand();
    }

    public void setItemInHand(ItemStack item) {
        getInventory().setItemInHand(item);
    }

    public ItemStack getItemOnCursor() {
        return new CraftItemStack(getHandle().inventory.j());
    }

    public void setItemOnCursor(ItemStack item) {
        getHandle().inventory.b(new net.minecraft.server.ItemStack(item.getTypeId(), item.getAmount(), item.getDurability()));
        ((EntityPlayer) getHandle()).z();
    }

    @Override
    public String toString() {
        return "CraftHumanEntity{" + "id=" + getEntityId() + "name=" + getName() + '}';
    }

    public boolean isSleeping() {
        return getHandle().sleeping;
    }

    public int getSleepTicks() {
        return getHandle().sleepTicks;
    }

    public boolean isOp() {
        return op;
    }

    public boolean isPermissionSet(String name) {
        return perm.isPermissionSet(name);
    }

    public boolean isPermissionSet(Permission perm) {
        return this.perm.isPermissionSet(perm);
    }

    public boolean hasPermission(String name) {
        return perm.hasPermission(name);
    }

    public boolean hasPermission(Permission perm) {
        return this.perm.hasPermission(perm);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
        return perm.addAttachment(plugin, name, value);
    }

    public PermissionAttachment addAttachment(Plugin plugin) {
        return perm.addAttachment(plugin);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
        return perm.addAttachment(plugin, name, value, ticks);
    }

    public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
        return perm.addAttachment(plugin, ticks);
    }

    public void removeAttachment(PermissionAttachment attachment) {
        perm.removeAttachment(attachment);
    }

    public void recalculatePermissions() {
        perm.recalculatePermissions();
    }

    public void setOp(boolean value) {
        this.op = value;
        perm.recalculatePermissions();
    }

    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return perm.getEffectivePermissions();
    }

//    public GameMode getGameMode() {
//        return GameMode.SURVIVAL;
//    }
//
//    public void setGameMode(GameMode mode) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
