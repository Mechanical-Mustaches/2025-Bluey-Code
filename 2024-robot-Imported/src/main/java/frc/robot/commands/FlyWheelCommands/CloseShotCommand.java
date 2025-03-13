package frc.robot.commands.FlyWheelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;

public class CloseShotCommand extends Command {
    FlyWheelSubsystem flywheel;

    public CloseShotCommand(FlyWheelSubsystem flywheel){
        this.flywheel = flywheel;
    }

    @Override
    public void initialize(){
        flywheel.closeShot();
    }
    
    @Override
    public boolean isFinished(){
        return false;
    }
    
    @Override
    public void end(boolean i){
        flywheel.rampDown();
    }



}
