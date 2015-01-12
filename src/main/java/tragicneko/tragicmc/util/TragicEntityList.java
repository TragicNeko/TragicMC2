package tragicneko.tragicmc.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLLog;

public class TragicEntityList
{
    private static final Logger logger = LogManager.getLogger();
    /**
     * Provides a mapping between entity classes and a string
     */
    public static Map<String, Class> stringToClassMapping = new HashMap();
    /**
     * Provides a mapping between a string and an entity classes
     */
    public static Map<Class, String> classToStringMapping = new HashMap();
    /**
     * provides a mapping between an entityID and an Entity Class
     */
    public static Map<Integer, Class> IDtoClassMapping = new HashMap();
    /**
     * provides a mapping between an Entity Class and an entity ID
     */
    private static Map<Class, Integer> classToIDMapping = new HashMap();
    /**
     * Maps entity names to their numeric identifiers
     */
    public static Map<String, Integer> stringToIDMapping = new HashMap();
    /**
     * This is a HashMap of the Creative Entity Eggs/Spawners.
     */
    public static HashMap entityEggs = new LinkedHashMap();
    private static final String __OBFID = "CL_00001538";

    /**
     * adds a mapping between Entity classes and both a string representation and an ID
     */
    public static void addMapping(Class par0Class, String par1Str, int par2)
    {
        if (stringToClassMapping.containsKey(par1Str))
        {
            throw new IllegalArgumentException("ID is already registered: " + par1Str);
        }
        else if (IDtoClassMapping.containsKey(Integer.valueOf(par2)))
        {
            throw new IllegalArgumentException("ID is already registered: " + par2);
        }
        else
        {
            stringToClassMapping.put(par1Str, par0Class);
            classToStringMapping.put(par0Class, par1Str);
            IDtoClassMapping.put(Integer.valueOf(par2), par0Class);
            classToIDMapping.put(par0Class, Integer.valueOf(par2));
            stringToIDMapping.put(par1Str, Integer.valueOf(par2));
        }
    }

    /**
     * Adds a entity mapping with egg info.
     */
    public static void addMapping(Class par0Class, String par1Str, int par2, int par3, int par4)
    {
        addMapping(par0Class, par1Str, par2);
        entityEggs.put(Integer.valueOf(par2), new TragicEntityList.EntityEggInfo(par2, par3, par4, EnumEggType.NORMAL));
    }
    
    public static void addMapping(Class par0Class, String par1Str, int par2, int par3, int par4, EnumEggType eggType)
    {
        addMapping(par0Class, par1Str, par2);
        entityEggs.put(Integer.valueOf(par2), new TragicEntityList.EntityEggInfo(par2, par3, par4, eggType));
    }

    /**
     * Create a new instance of an entity in the world by using the entity name.
     */
    public static Entity createEntityByName(String par0Str, World par1World)
    {
        Entity entity = null;

        try
        {
            Class oclass = stringToClassMapping.get(par0Str);

            if (oclass != null)
            {
                entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return entity;
    }

    /**
     * create a new instance of an entity from NBT store
     */
    public static Entity createEntityFromNBT(NBTTagCompound par0NBTTagCompound, World par1World)
    {
        Entity entity = null;

        if ("Minecart".equals(par0NBTTagCompound.getString("id")))
        {
            switch (par0NBTTagCompound.getInteger("Type"))
            {
                case 0:
                    par0NBTTagCompound.setString("id", "MinecartRideable");
                    break;
                case 1:
                    par0NBTTagCompound.setString("id", "MinecartChest");
                    break;
                case 2:
                    par0NBTTagCompound.setString("id", "MinecartFurnace");
            }

            par0NBTTagCompound.removeTag("Type");
        }

        Class oclass = null;
        try
        {
            oclass = stringToClassMapping.get(par0NBTTagCompound.getString("id"));

            if (oclass != null)
            {
                entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if (entity != null)
        {
            try
            {
                entity.readFromNBT(par0NBTTagCompound);
            }
            catch (Exception e)
            {
                FMLLog.log(Level.ERROR, e,
                        "An Entity %s(%s) has thrown an exception during loading, its state cannot be restored. Report this to the mod author",
                        par0NBTTagCompound.getString("id"), oclass.getName());
                entity = null;
            }
        }
        else
        {
            logger.warn("Skipping Entity with id " + par0NBTTagCompound.getString("id"));
        }

        return entity;
    }

    /**
     * Create a new instance of an entity in the world by using an entity ID.
     */
    public static Entity createEntityByID(int par0, World par1World)
    {
        Entity entity = null;

        try
        {
            Class oclass = getClassFromID(par0);

            if (oclass != null)
            {
                entity = (Entity)oclass.getConstructor(new Class[] {World.class}).newInstance(new Object[] {par1World});
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        if (entity == null)
        {
            logger.warn("Skipping Entity with id " + par0);
        }

        return entity;
    }

    /**
     * gets the entityID of a specific entity
     */
    public static int getEntityID(Entity par0Entity)
    {
        Class oclass = par0Entity.getClass();
        return classToIDMapping.containsKey(oclass) ? classToIDMapping.get(oclass).intValue() : 0;
    }

    /**
     * Return the class assigned to this entity ID.
     */
    public static Class getClassFromID(int par0)
    {
        return IDtoClassMapping.get(Integer.valueOf(par0));
    }

    /**
     * Gets the string representation of a specific entity.
     */
    public static String getEntityString(Entity par0Entity)
    {
        return classToStringMapping.get(par0Entity.getClass());
    }

    /**
     * Finds the class using IDtoClassMapping and classToStringMapping
     */
    public static String getStringFromID(int par0)
    {
        Class oclass = getClassFromID(par0);
        return oclass != null ? (String)classToStringMapping.get(oclass) : null;
    }

    public static void func_151514_a() {}

    public static Set func_151515_b()
    {
        return Collections.unmodifiableSet(stringToIDMapping.keySet());
    }

    static
    {
    	
    }

    public static class EntityEggInfo
        {
            /**
             * The entityID of the spawned mob
             */
            public final int spawnedID;
            /**
             * Base color of the egg
             */
            public final int primaryColor;
            /**
             * Color of the egg spots
             */
            public final int secondaryColor;
            
            public final EnumEggType eggType;

            public EntityEggInfo(int par1, int par2, int par3, EnumEggType eggType)
            {
                this.spawnedID = par1;
                this.primaryColor = par2;
                this.secondaryColor = par3;
                this.eggType = eggType;
            }
        }
    
    public enum EnumEggType
    {
    	NORMAL,
    	PET,
    	MINIBOSS,
    	BOSS,
    	ALPHA
    }
}