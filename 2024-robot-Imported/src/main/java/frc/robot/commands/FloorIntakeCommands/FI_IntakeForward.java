package frc.robot.commands.FloorIntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntakeSubsystem;

public class FI_IntakeForward extends Command{
    FloorIntakeSubsystem floorIntake;
 

    public FI_IntakeForward(FloorIntakeSubsystem floorIntake){
        this.floorIntake = floorIntake;
    }

    @Override
    public void execute(){ 
        floorIntake.intakeForward();
    }

    @Override
    public void end(boolean i){
        floorIntake.intakeStop();
    }

    
}
