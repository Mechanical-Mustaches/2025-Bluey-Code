package frc.robot.commands.FlyWheelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;

public class FarShotCommand extends Command{
    FlyWheelSubsystem flywheel;

    public FarShotCommand(FlyWheelSubsystem flywheel){
        this.flywheel = flywheel;
    }

    @Override
    public void initialize(){
        flywheel.farShot();
    }

    @Override
    public void end(boolean i){
        flywheel.rampDown();
    }
    



    
}
