package esrome.scienceMod.gui;

import esrome.scienceMod.blocks.containers.ContainerFabricator;
import esrome.scienceMod.tileentity.TileEntityFabricator;
import esrome.scienceMod.util.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiFabricator extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/fabricator.png");
	private final InventoryPlayer player;
	private final TileEntityFabricator tileentity;
	
	public GuiFabricator(InventoryPlayer player, TileEntityFabricator tileentity) 
	{
		super(new ContainerFabricator(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2) - 10, 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString(String.valueOf((this.tileentity.getEnergyStored())), 120, this.ySize- 96 + 2, 16768873);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 67, this.guiTop + 35, 176, 0, l, 17);
		
		int k = this.getEnergyStoredScaled(73);
		this.drawTexturedModalRect(this.guiLeft + 148, this.guiTop + 6, 176, 17, 16, 73 - k);
		
	}
	
	private int getEnergyStoredScaled(int pixels){
		int i = this.tileentity.getEnergyStored();
		int j = this.tileentity.getMaxEnergyStored();
		if(i==j) {
			return i;
		}
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
	
	private int getCookProgressScaled(int pixels){
		int i = this.tileentity.currentBurnTime;
		int j = this.tileentity.burnTime;
		if(i==25) {
			return pixels;
		}
		if(i==0) {
			return 0;
		}
		return i != 0 ? i * pixels / j : 0;
	}
	
}