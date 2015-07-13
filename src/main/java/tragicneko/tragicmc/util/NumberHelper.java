package tragicneko.tragicmc.util;

import java.util.Random;

public class NumberHelper {

	public static int getIntWithMinRangeAndVarSign(int min, int var, Random rand)
	{
		int i = getIntWithMinRange(min, var, rand);
		return rand.nextBoolean() ? -i : i;
	}
	
	public static int getIntWithMinRange(int min, int var, Random rand)
	{
		return (min + rand.nextInt(var));
	}
}
