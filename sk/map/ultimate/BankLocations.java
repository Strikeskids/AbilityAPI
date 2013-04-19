package sk.map.ultimate;

import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.Tile;

public enum BankLocations {
	BURTHORPE(new Tile(2890, 3524, 0)),
	TAVERLEY(new Tile(2878, 3417, 0)),
	FALADOR_EAST(new Tile(3012, 3357, 0)),
	FALADOR_WEST(new Tile(2946, 3370, 0)),
	DRAYNOR(new Tile(3093, 3243, 0)),
	AL_KHARID(new Tile(3270, 3168, 0)),
	DUEL_ARENA(new Tile(3384, 3269, 0)),
	VARROCK_EAST(new Tile(3254, 3422, 0)),
	VARROCK_WEST(new Tile(3187, 3438, 0)),
	GRAND_EXCHANGE(new Tile(3172, 3476, 0)),
	EDGEVILLE(new Tile(3093, 3497, 0)),
	CATHERBY(new Tile(2809, 3439, 0)),
	SEERS_VILLAGE(new Tile(2726, 3488, 0)),
	ARDOUGNE_NORTH(new Tile(2617, 3334, 0)),
	ARDOUGNE_SOUTH(new Tile(2651, 3283, 0)),
	LUNAR_ISLE(new Tile(2101, 3917, 0)),
	LLETYA(new Tile(2353, 3161, 0)),
	CASTLE_WARS(new Tile(2442, 3087, 0)),
	MOBILISING_ARMIES(new Tile(2409, 2839, 0)),
	SHILO_VILLAGE(new Tile(2853, 2956, 0)),
	SHANTAY_PASS(new Tile(3303, 3123, 0)),
	NARDAH(new Tile(3435, 2892, 0)),
	BURGH_DE_ROTT(new Tile(3497, 3216, 0)),
	CANIFIS(new Tile(3509, 3480, 0)),
	PORT_PHASMATYS(new Tile(3689, 3469, 0)),
	DAEMONHEIM(new Tile(3449, 3714, 0)),
	YANILLE(new Tile(2611, 3092, 0));

	private final Tile loc;

	private BankLocations(Tile loc) {
		this.loc = loc;
	}

	public Tile getLocation() {
		return loc;
	}

	public static boolean atBank() {
		return Bank.getNearest() != null;
	}
}
