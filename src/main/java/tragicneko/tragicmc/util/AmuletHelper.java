package tragicneko.tragicmc.util;

import tragicneko.tragicmc.items.special.ItemAmulet;

public class AmuletHelper {

	/**
	 * Gets the highest amulet level of the 3 integers passed in, if par3 is 0, only checks for the first two integers passed in
	 * @param par1
	 * @param par2
	 * @param par3
	 * @return
	 */
	public static int getAmuletWithHighestLevel(int par1, int par2, int par3)
	{
		if (par3 != 0)
		{
			if (par1 > par2 && par1 > par3)
			{
				return par1;
			}

			if (par2 > par1 && par2 > par3)
			{
				return par2;
			}

			if (par3 > par1 && par3 > par2)
			{
				return par3;
			}

			if (par1 == par2 && par1 == par3)
			{
				return par1;
			}
		}
		else
		{
			if (par1 > par2)
			{
				return par1;
			}

			if (par2 > par1)
			{
				return par2;
			}

			if (par1 == par2)
			{
				return par1;
			}
		}
		return (par1 * par2 * par3) / 3;
	}

	/**
	 * For getting the highest amulet level out of 2 amulets, will always return one of them if they are equal
	 * @param par1
	 * @param par2
	 * @return
	 */
	public static int getAmuletWithHighestLevel(int par1, int par2)
	{
		return getAmuletWithHighestLevel(par1, par2, 0);
	}

	/**
	 * Returns stacked percentages if all 3 amulets equipped are of the same kind, pass in the 3 percentages of successful effect use and returns the calculated
	 * stacked efficiency of the 3, first integer should be the highest amulet level, third should be lowest
	 * @param par1
	 * @param par2
	 * @param par3
	 * @return
	 */
	public static double getStackedEfficiency(int par1, int par2, int par3)
	{
		double d0 = par1 / 100;
		double d1 = par2 / 100;
		double d2 = par3 / 100;

		double d3 = (1 - d0) * d1;
		double d4 = (1 - (d0 + d3)) * d2;

		return d0 + d3 + d4;
	}

	/**
	 * Pass in the 3 amulets to check, returns an integer based on the amulets that are the same, 123 is all of them, 12 is the first two, etc., now does null
	 * check automatically and returns proper integer
	 * @param amu1
	 * @param amu2
	 * @param amu3
	 * @return
	 */
	public static int getSameAmulets(ItemAmulet amu1, ItemAmulet amu2, ItemAmulet amu3)
	{
		if (amu1 == null)
		{
			if (amu2 != null && amu3 != null)
			{
				if (amu2.getAmuletName() == amu3.getAmuletName())
				{
					return 23;
				}
			}
			return 0;
		}

		if (amu2 == null)
		{
			if (amu1 != null && amu3 != null)
			{
				if (amu1.getAmuletName() == amu3.getAmuletName())
				{
					return 13;
				}
			}
			return 0;
		}

		if (amu3 == null)
		{
			if (amu1 != null && amu2 != null)
			{
				if (amu1.getAmuletName() == amu2.getAmuletName())
				{
					return 12;
				}
			}
			return 0;
		}

		if (amu1.getAmuletName() == amu2.getAmuletName() && amu1.getAmuletName() == amu3.getAmuletName())
		{
			return 123;
		}

		if (amu1.getAmuletName() == amu2.getAmuletName() && amu1.getAmuletName() != amu3.getAmuletName())
		{
			return 12;
		}

		if (amu1.getAmuletName() == amu3.getAmuletName() && amu1.getAmuletName() != amu2.getAmuletName())
		{
			return 13;
		}

		if (amu2.getAmuletName() == amu3.getAmuletName() && amu2.getAmuletName() != amu1.getAmuletName())
		{
			return 23;
		}

		return 0;
	}
}
