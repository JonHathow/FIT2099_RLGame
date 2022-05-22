package game.grounds;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * The WarpPipeManager class keeps track of all warp pipes across all worlds and manages the links of the warp
 * pipes (i.e. where each warp pipe transports the player to). Thus, it is a Static Factory Method
 * (ref. FIT2009 Java Bootcamp 5), as one instance of WarpPipeManager will manage all the warp pipes in all
 * maps in a world.
 *
 * @author How Yu Chern
 * @version 3.0.0
 */
public class WarpPipeManager {

    /**
     * List of warp pipes for the Warp Pipe Manager to keep track of.
     */
    private List<WarpPipe> warpPipes;

    /**
     * An instance of the Lava Zone's map to identify the warp pipe that is in the Lava Zone.
     */
    private GameMap lavaMap;

    /**
     * Variable to store the Warp Pipe which has been identified to be the Lava Zone's warp pipe.
     * Implemented as a standalone variable for this assignment as Lava Zone's Warp Pipe is unique (there is
     * only one Lava Zone warp pipe, and it can link to any of the warp pipes in overworld).
     */
    private WarpPipe lavaZonePipe;

    /**
     * Variable to keep track of the return pipe from Lava Zone.
     */
    private WarpPipe returnPipe;

    /**
     * A singleton warp pipe manager instance
     */
    private static WarpPipeManager instance;

    /**
     * Get the singleton instance of Warp Pipe Manager
     * @return WarpPipeManager singleton instance
     */
    public static WarpPipeManager getInstance(){
        if(instance == null){
            instance = new WarpPipeManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private WarpPipeManager(){
        warpPipes = new ArrayList<>();
    }

    /**
     * Registers the warp pipe for WarpPipeManager to manage.
     * @param warpPipe The Warp Pipe.
     */
    public void registerPipeInstance(WarpPipe warpPipe){
        if (warpPipe.getSource().map().equals(lavaMap)){
            setLavaZonePipe(warpPipe);
        } else{
            warpPipes.add(warpPipe);
        }
    }

    /**
     * Links a warp pipe with it's destination warp pipe, by mutually setting the warps for both
     * pipes. If there is a piranha plant on the desintation
     * warp pipe remove it.
     *
     * @param sourceWarpPipe The source warp pipe which the player enters to warp.
     */
    public void link(WarpPipe sourceWarpPipe){
        if (warpPipes.contains(sourceWarpPipe)) {
            //Remove the Piranha Plant at the lava zone warp pipe.
            if (getLavaZonePipe().getSource().getActor() != null) {
                getLavaZonePipe().getSource().map().removeActor(getLavaZonePipe().getSource().getActor());
            }

            //Set the warp and warp the player.
            sourceWarpPipe.setWarp(new MoveActorAction(getLavaZonePipe().getSource(), " to Lava Zone."));

            //Have WarpPipeManager remember the source warp pipe to return to.
            setReturnPipe(sourceWarpPipe);
        }

        if (sourceWarpPipe.equals(lavaZonePipe)) {
            //Remove the Piranha Plant at the return warp pipe.
            if(getReturnPipe().getSource().getActor() != null) {
                getReturnPipe().getSource().map().removeActor(getReturnPipe().getSource().getActor());
            }

            //Set the warp and send the player back to the overworld.
            getLavaZonePipe().setWarp(new MoveActorAction(getReturnPipe().getSource(), " to Overworld."));
        }
    }

    //Setters
    /**
     * Setter for Lava Map for Warp Pipe Manager to use to identify the Lava Zone Warp Pipe.
     * @param lavaMap The Lava Zone game map.
     */
    public void setLavaMap(GameMap lavaMap) {
        this.lavaMap = lavaMap;
    }

    /**
     * Setter for the Lava Zone Warp Pipe. Made private as Warp Pipe Manager should be the only class capable
     * of doing so.
     * @param lavaZonePipe The Lava Zone warp pipe, once identified.
     */
    private void setLavaZonePipe(WarpPipe lavaZonePipe) {
        this.lavaZonePipe = lavaZonePipe;
    }

    /**
     * Setter for the return pipe from lava zone (the source pipe).
     * @param returnPipe the source pipe which the player initially entered to get to lava zone warp pipe.
     */
    public void setReturnPipe(WarpPipe returnPipe) {
        this.returnPipe = returnPipe;
    }

    //Getters
    /**
     * Getter for the Lava Zone warp pipe.
     * @return The Lava Zone warp pipe.
     */
    private WarpPipe getLavaZonePipe(){
        return this.lavaZonePipe;
    }

    /**
     * Getter for the return pipe.
     * @return The source pipe which the player will return to when entering the Lava Zone warp pipe.
     */
    public WarpPipe getReturnPipe() {
        return returnPipe;
    }
}
