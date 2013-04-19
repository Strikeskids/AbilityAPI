package sk.graphics.main;

import static sk.graphics.main.MainPaint.FONT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.PriorityQueue;
import java.util.Queue;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.SkillData;
import org.powerbot.game.api.util.Time;

import sk.Script;
import sk.Universal;
import sk.general.ImageCodes;
import sk.graphics.Anchor;
import sk.graphics.TextUtil;

public class SkillTab extends StaticPaintTab {

	private final int numSkills;
	private int[] skillIds = SKILL_IDS;
	private SkillData data = null;

	public SkillTab() {
		this(8);
	}

	public SkillTab(int numSkills) {
		super(ImageCodes.SKILL.get(), numSkills * (BAR_HEIGHT + SPACING) - SPACING);
		this.numSkills = numSkills;
		if (!Universal.testing && !Script.holdExecution())
			data = new SkillData();
	}

	public SkillTab(int... skillIds) {
		this(skillIds.length);
		this.skillIds = skillIds;
	}

	private static class SkillGain implements Comparable<SkillGain> {

		private int skill, exp;

		public SkillGain(int skill, int exp) {
			this.skill = skill;
			this.exp = exp;
		}

		@Override
		public int compareTo(SkillGain o) {
			if (o == null)
				return -1;
			if (o.skill == this.skill)
				return 0;
			if (o.exp == this.exp)
				return this.skill - o.skill;
			return o.exp - this.exp;
		}

		public int getSkill() {
			return skill;
		}

		public int getGained() {
			return exp;
		}

		@Override
		public boolean equals(Object o) {
			return o != null && o instanceof SkillGain && ((SkillGain) o).skill == this.skill;
		}
	}

	@Override
	public void paint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		if (Universal.testing)
			return;
		if (data == null) {
			if (Script.holdExecution()) {
				return;
			}
			data = new SkillData();
		}
		Queue<SkillGain> order = new PriorityQueue<SkillGain>();

		for (int i : skillIds) {
			order.add(new SkillGain(i, data.experience(i)));
		}

		long time = Script.getElapsed();
		long mult = 60 * 60;
		int loc = -1;
		Point start = new Point(SPACING, SPACING);

		while (!order.isEmpty()) {
			SkillGain cur = order.poll();
			int gained = cur.getGained();
			int skill = cur.getSkill();
			if (gained == 0)
				break;
			if (++loc >= numSkills)
				break;
			int xp = Skills.getExperience(skill);
			int level = Skills.getLevelAt(xp);
			int xpnl = Skills.getExperienceRequired(level + 1);
			int xpcl = Skills.getExperienceRequired(level);
			long tleft = (xpnl - xp) * time / gained;
			
			int maxWidth = MainPaint.MAIN_WIDTH - SPACING * 2;
			int pleft = (Skills.getExperience(skill) - xpcl) * maxWidth / (xpnl - xpcl);

			g.setColor(MainPaint.BACKGROUND);
			g.fillRoundRect(start.x, start.y, maxWidth, BAR_HEIGHT, 2, 2);

			g.setColor(progressBar);
			g.fillRoundRect(start.x, start.y, pleft, BAR_HEIGHT, 2, 2);

			g.setColor(MainPaint.TEXT_COLOR);
			g.setFont(FONT);

			String str = String.format("%s %3d (+%2d): %4dK (%3dK) %s", SKILL_ABBREVS[skill], level,
					data.level(skill), gained / 1000, gained * mult / time, (tleft / mult / 1000 < 10 ? " ("
							+ Time.format(tleft) + ")" : ""));

			TextUtil.drawTextFromPoint(g, new Point(start.x + 4, start.y), str, Anchor.TOP_LEFT);

			start.y += BAR_HEIGHT + SPACING;
		}
	}

	private static final Color progressBar = new Color(200, 200, 200, MainPaint.BACKGROUND.getAlpha());

	private static final int BAR_HEIGHT = 16, SPACING = 2;

	public static final String[] SKILL_NAMES = { "Attack", "Defense", "Strength", "Constitution", "Range",
			"Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting",
			"Smithing", "Mining", "Herblore", "Agility", "Thieving", "Slayer", "Farming", "Runecrafting",
			"Hunter", "Construction", "Summoning", "Dungeoneering" }, SKILL_ABBREVS = { "Attk", "Defc", "Strn",
			"Hitp", "Rang", "Pray", "Mage", "Cook", "Wood", "Flet", "Fish", "Fire", "Crft", "Smth", "Mine",
			"Herb", "Agil", "Thvg", "Slay", "Farm", "Rune", "Hunt", "Cons", "Summ", "Dung" };

	private static final int[] SKILL_IDS = new int[25];
	static {
		for (int i = 0; i < SKILL_IDS.length; i++) {
			SKILL_IDS[i] = i;
		}
	}
}
