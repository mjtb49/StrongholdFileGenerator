package kaptainwutax.featureutils.structure.generator.piece.stronghold;
import kaptainwutax.featureutils.structure.Stronghold;
import kaptainwutax.seedutils.lcg.rand.JRand;
import kaptainwutax.seedutils.util.Direction;

import java.util.*;

public class Start extends SpiralStaircase {

	public PieceWeight pieceWeight;
	public PortalRoom portalRoom;

	public Start(JRand rand, int x, int z) {
		super(0, rand, x, z);
	}
	public List<Stronghold.Piece> pieces = new ArrayList<>();
	public Map<Stronghold.Piece, List<Stronghold.Piece>> tree = new HashMap<>();
	public void printContents() {
		List<Stronghold.Piece> pieces = new ArrayList<>(this.pieces);
		Collections.reverse(pieces);
		Map<Stronghold.Piece, Integer> index = new HashMap<>();
		for (int i = 0; i < pieces.size(); i++) {
			Stronghold.Piece p = pieces.get(i);
			index.put(p, i);
			String result = p.getClass().getSimpleName();
			List<Stronghold.Piece> ch = tree.get(p);
			for (int j = 0; j < ch.size(); j++) {
				if (ch.get(j) == null) {
					result += " -1";
				} else {
					result += " " + index.get(ch.get(j));
				}
			}
			System.out.println(result);
		}
	}
	public void addPiece(Stronghold.Piece piece) {
		tree.get(pieces.get(pieces.size() - 1)).add(piece);
	}
	public void correctOrder5Way() {
		Stronghold.Piece piece = pieces.get(pieces.size() - 1);
		List<Stronghold.Piece> list = tree.get(piece);
		if (list.size() == 5 && piece instanceof FiveWayCrossing) {
			Direction direction = piece.getFacing();
			if (direction != null) {
				switch (direction) {
					case NORTH:
						Collections.swap(list, 1, 2);
						Collections.swap(list, 3, 4);
						break;
					case SOUTH:
						Collections.swap(list, 1, 3);
						Collections.swap(list, 2, 4);
						break;
					case WEST:
						Collections.swap(list, 1, 4);
						Collections.swap(list, 2, 3);
						break;
					default:
				}
			} else {
				System.err.println("Attempted to correct other room as if 5 Way");
			}
		}
	}

	public void correctOrderSquareAndCorridor() {
		Stronghold.Piece piece = pieces.get(pieces.size() - 1);
		List<Stronghold.Piece> list = tree.get(piece);
		if (list.size() == 3 && (piece instanceof SquareRoom || piece instanceof Corridor)) {
			Direction direction = piece.getFacing();
			if (direction != null) {
				switch (direction) {
					case WEST:
					case SOUTH:
						Collections.swap(list, 1, 2);
						break;
					default:
				}
			}
		} else {
			System.err.println("Attempted to correct other room as if square or corridor");
		}
	}
}
