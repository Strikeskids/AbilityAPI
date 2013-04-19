package sk.map.ultimate.teleport;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.Tile;

import sk.action.BookUtil;
import sk.action.magic.AncientSpell;
import sk.action.magic.FreeSpell;
import sk.action.magic.LunarSpell;
import sk.action.magic.Spell;
import sk.action.magic.StandardSpell;
import sk.item.RequiredGroup;
import sk.requirement.MultiRequirement;
import sk.requirement.Requirement;
import sk.requirement.SkillRequirement;

public class SpellTeleport implements Teleport {

	private final Spell spell;
	private final Tile dest;
	private final Requirement reqs;

	public SpellTeleport(Spell s) {
		this.dest = LOCATIONS.get(s);
		if (this.dest == null)
			throw new IllegalArgumentException("This spell is not valid");
		this.spell = s;
		this.reqs = new MultiRequirement(true, new Requirement() {
			@Override
			public boolean meets() {
				return spell.getSpellbook().isOpen();
			}
		}, new SkillRequirement(Skills.MAGIC, spell.getLevel()));
	}

	@Override
	public Tile getLocation() {
		return dest;
	}

	@Override
	public RequiredGroup getItems() {
		return spell.getRuneGroup();
	}

	@Override
	public boolean use() {
		// TODO implement use
		return false;
		// return ActionBar.useAbility(spell) && new TimedCondition(1000) {
		// @Override
		// public boolean isDone() {
		// return Script.isPlayerAnimated();
		// }
		// }.waitStop() && new TimedCondition(5000) {
		// @Override
		// public boolean isDone() {
		// if (Script.holdExecution())
		// return false;
		// return !Script.isPlayerAnimated() || TeleportUtil.atEnd(SpellTeleport.this)
		// && !Script.isPlayerAnimated(true);
		// }
		// }.waitStop();
	}

	@Override
	public Requirement getRequirements() {
		return reqs;
	}

	@Override
	public boolean canUse() {
		return BookUtil.canUse(spell);
	}

	@Override
	public int getWeight() {
		return 30;
	}

	private static Map<Spell, Tile> LOCATIONS = new HashMap<>();

	static {
		LOCATIONS.put(StandardSpell.MOBILISING_ARMIES_TELEPORT, new Tile(2414, 2845, 0));
		LOCATIONS.put(FreeSpell.VARROCK_TELEPORT, new Tile(3214, 3422, 0));
		LOCATIONS.put(FreeSpell.LUMBRIDGE_TELEPORT, new Tile(3223, 3218, 0));
		LOCATIONS.put(FreeSpell.FALADOR_TELEPORT, new Tile(2966, 3380, 0));
		LOCATIONS.put(StandardSpell.CAMELOT_TELEPORT, new Tile(2758, 3476, 0));
		LOCATIONS.put(StandardSpell.ARDOUGNE_TELEPORT, new Tile(2663, 3301, 0));
		LOCATIONS.put(StandardSpell.WATCHTOWER_TELEPORT, new Tile(2549, 3113, 0));
		LOCATIONS.put(StandardSpell.TROLLHEIM_TELEPORT, new Tile(2890, 3669, 0));
		LOCATIONS.put(StandardSpell.APE_ATOLL_TELEPORT, new Tile(2799, 2794, 0));
		LOCATIONS.put(AncientSpell.PADDEWWA_TELEPORT, new Tile(3095, 3469, 0));
		LOCATIONS.put(AncientSpell.SENNTISTEN_TELEPORT, new Tile(3324, 3333, 0));
		LOCATIONS.put(AncientSpell.KHARYRLL_TELEPORT, new Tile(3495, 3476, 0));
		LOCATIONS.put(AncientSpell.LASSAR_TELEPORT, new Tile(3007, 3471, 0));
		LOCATIONS.put(AncientSpell.DAREEYAK_TELEPORT, new Tile(3162, 3733, 0));
		LOCATIONS.put(AncientSpell.CARRALLANGER_TELEPORT, new Tile(3161, 3651, 0));
		LOCATIONS.put(AncientSpell.ANNAKARL_TELEPORT, new Tile(3289, 3884, 0));
		LOCATIONS.put(AncientSpell.GHORROCK_TELEPORT, new Tile(2985, 3910, 0));
		LOCATIONS.put(LunarSpell.MOONCLAN_TELEPORT, new Tile(2107, 3912, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_MOONCLAN, new Tile(2107, 3912, 0));
		LOCATIONS.put(LunarSpell.OURANIA_TELEPORT, new Tile(2468, 3246, 0));
		LOCATIONS.put(LunarSpell.WATERBIRTH_TELEPORT, new Tile(2549, 3751, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_WATERBIRTH, new Tile(2549, 3752, 0));
		LOCATIONS.put(LunarSpell.BARBARIAN_TELEPORT, new Tile(2543, 3565, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_BARBARIAN, new Tile(2543, 3568, 0));
		LOCATIONS.put(LunarSpell.KHAZARD_TELEPORT, new Tile(2635, 3166, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_KHAZARD, new Tile(2635, 3166, 0));
		LOCATIONS.put(LunarSpell.FISHING_GUILD_TELEPORT, new Tile(2613, 3381, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_FISHING_GUILD, new Tile(2613, 3381, 0));
		LOCATIONS.put(LunarSpell.CATHERBY_TELEPORT, new Tile(2804, 3446, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_CATHERBY, new Tile(2804, 3446, 0));
		LOCATIONS.put(LunarSpell.ICE_PLATEAU_TELEPORT, new Tile(2958, 3931, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_ICE_PLATEAU, new Tile(2958, 3931, 0));
		LOCATIONS.put(LunarSpell.TROLLHEIM_TELEPORT, new Tile(2890, 3669, 0));
		LOCATIONS.put(LunarSpell.TELEGROUP_TROLLHEIM, new Tile(2890, 3669, 0));
	}

	public static void load() {
		for (Spell s : LOCATIONS.keySet()) {
			TeleportUtil.add(new SpellTeleport(s));
		}
	}

	@Override
	public boolean isFreeToPlay() {
		return EnumSet.allOf(FreeSpell.class).contains(spell);
	}
}
