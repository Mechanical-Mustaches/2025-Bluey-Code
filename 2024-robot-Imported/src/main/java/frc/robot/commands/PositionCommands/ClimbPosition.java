package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class ClimbPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;

    public ClimbPosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void initialize(){
        pivot.pivotClimbPosition();
        elevator.trapPosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
    
}
