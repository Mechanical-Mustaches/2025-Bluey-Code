package frc.robot.commands.FlyWheelCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheelSubsystem;

public class ShootNoteCommand extends Command {
    private final FlyWheelSubsystem flyWheel;

    public ShootNoteCommand(FlyWheelSubsystem flyWheel){
        this.flyWheel = flyWheel;
    }

    @Override
    public void execute(){
        flyWheel.rampUp();
    }

    @Override
    public boolean isFinished(){
        return true;
    }

    @Override
    public void end(boolean i){
        flyWheel.rampDown();
    }

    
}
