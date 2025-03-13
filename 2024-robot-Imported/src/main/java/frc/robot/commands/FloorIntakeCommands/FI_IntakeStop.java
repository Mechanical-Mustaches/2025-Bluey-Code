package frc.robot.commands.FloorIntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntakeSubsystem;

public class FI_IntakeStop extends Command {
    FloorIntakeSubsystem intake;

    public FI_IntakeStop(FloorIntakeSubsystem intake){
        this.intake = intake;
    }

    @Override
    public void initialize(){
        intake.intakeStop();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}

