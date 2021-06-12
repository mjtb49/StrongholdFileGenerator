import kaptainwutax.seedutils.mc.MCVersion;
import kaptainwutax.featureutils.structure.generator.StrongholdGenerator;
import kaptainwutax.seedutils.mc.ChunkRand;
import kaptainwutax.seedutils.lcg.rand.JRand;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		StrongholdGenerator g = new StrongholdGenerator(MCVersion.v1_16_2);
		ChunkRand r = new ChunkRand();
		Random rand = new Random();
		int numStrongholds = 100000;
		for (int i = 0; i < numStrongholds; i++) {
			g.generate(rand.nextLong(), 0, 0, r);
		}
	}
}