package game.grounds;

import edu.monash.fit2099.engine.actions.MoveActorAction;

public class WarpLink {

    private WarpPipe warpPipeS;

    private WarpPipe warpPipeD;

    private MoveActorAction warp;

    public WarpLink(WarpPipe warpPipeS, WarpPipe warpPipeD){
        setWarpPipeS(warpPipeS);
        setWarpPipeD(warpPipeD);
    }

    //Setters
    public void setWarpPipeS(WarpPipe warpPipeS) {
        this.warpPipeS = warpPipeS;
    }

    public void setWarpPipeD(WarpPipe warpPipeD) {
        this.warpPipeD = warpPipeD;
    }
    public void setWarp(MoveActorAction warp) {
        this.warp = warp;
    }

    //Getters
    public WarpPipe getWarpPipeS() {
        return warpPipeS;
    }
    public WarpPipe getWarpPipeD() {
        return warpPipeD;
    }
    public MoveActorAction getWarp() {
        return warp;
    }
}
