package frc.robot.commands.FloorIntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntakeSubsystem;

public class FI_IntakeReverseCommand extends Command {
    FloorIntakeSubsystem intake;

    public FI_IntakeReverseCommand(FloorIntakeSubsystem intake){
        addRequirements(intake);
        this.intake = intake;
    }

    @Override
    public void execute(){
        intake.intakeReverse();
    }

    @Override
    public void end(boolean i){
        intake.intakeStop();
    }




}



