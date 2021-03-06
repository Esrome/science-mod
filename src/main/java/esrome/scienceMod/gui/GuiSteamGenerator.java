package esrome.scienceMod.gui;

import esrome.scienceMod.blocks.containers.ContainerSteamGenerator;
import esrome.scienceMod.tileentity.TileEntitySteamGenerator;
import esrome.scienceMod.util.Reference;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiSteamGenerator extends GuiContainer {

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID + ":textures/gui/steam_generator.png");
	private final InventoryPlayer player;
	private final TileEntitySteamGenerator tileentity;
	
	public GuiSteamGenerator(InventoryPlayer player, TileEntitySteamGenerator tileentity) 
	{
		super(new ContainerSteamGenerator(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize / 2 - this.fontRenderer.getStringWidth(tileName) / 2 - 10) -5, 6, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getUnformattedText(), 7, this.ySize - 96 + 2, 4210752);
		this.fontRenderer.drawString(String.valueOf((this.tileentity.getEnergyStored())), 77, this.ySize- 96 + 2, 16768873);
		this.fontRenderer.drawString(String.valueOf(this.tileentity.steam), 77, this.ySize- 96 - 8, 15198183);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY){
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
		int l = this.getCookProgressScaled(24);
		this.drawTexturedModalRect(this.guiLeft + 42, this.guiTop + 17, 176, 14, l, 16);
		
		int h = this.getHeatRemaining(13);
		this.drawTexturedModalRect(this.guiLeft + 46, this.guiTop + 50 - h, 176, 13-h, 14, h);
		
		int k = this.getEnergyStoredScaled(75);
		this.drawTexturedModalRect(this.guiLeft + 146, this.guiTop + 7, 176, 31, 16, 76 - k);
		
		int j = this.getSteamStoredScaled(75);
		this.drawTexturedModalRect(this.guiLeft + 122, this.guiTop + 7, 176, 31, 16, 76 - j);
	}
	
	private int getEnergyStoredScaled(int pixels){
		int i = this.tileentity.getEnergyStored();
		int j = this.tileentity.getMaxEnergyStored();
		if(i==j) {
			return i;
		}
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
	
	private int getHeatRemaining(int pixels) {
		int i = this.tileentity.fuelTime;
		int j = this.tileentity.maxFuelTime;
		return i != 0 ? i * pixels / j : 0;
	}
	
	private int getSteamStoredScaled(int pixels){
		int i = this.tileentity.steam;
		int j = this.tileentity.maxSteam;
		return i != 0 && j != 0 ? i * pixels / j : 0; 
	}
	
	private int getCookProgressScaled(int pixels){
		int i = this.tileentity.cookTime;
		int j = this.tileentity.maxCookTime;
		return i != 0 ? i * pixels / j: 0;
	}
	
}
