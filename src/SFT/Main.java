package SFT;

import Utils.*;
import Utils.Debug.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Debug.log("Started",DebugOutput.STDOUT);
		SFT.generateQueries(76, 1/36, 1, 0.1);
	}

}
