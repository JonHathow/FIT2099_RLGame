package game.grounds;

import edu.monash.fit2099.engine.actions.MoveActorAction;

import java.util.ArrayList;
import java.util.List;

/**
 * The WarpPipeManager class keeps track of all warp pipes across all worlds and manages the links of the warp
 * pipes (i.e. where each warp pipe transports the player to).
 *
 * @author How Yu Chern
 * @version 1.0.0
 */
public class WarpPipeManager {

    /**
     * List of warp pipes for the Warp Pipe Manager to keep track of.
     */
    private List<WarpPipe> warpPipes = new ArrayList<>();

    private WarpPipe lavaZonePipe;

    public void registerPipe(WarpPipe warpPipe){
       warpPipes.add(warpPipe);
    }

    public void findNexus(){    }

    public void link(WarpPipe warpPipe1){
        warpPipe1.setWarp(new MoveActorAction(lavaZonePipe.getSource(), " to Lava Zone."));
        getLavaZonePipe().setWarp(new MoveActorAction(warpPipe1.getSource(), " to Overworld."));
    }

    private void setLavaZonePipe(WarpPipe lavaZonePipe) {
        this.lavaZonePipe = lavaZonePipe;
    }

    private WarpPipe getLavaZonePipe(){
        return this.lavaZonePipe;
    }
}
