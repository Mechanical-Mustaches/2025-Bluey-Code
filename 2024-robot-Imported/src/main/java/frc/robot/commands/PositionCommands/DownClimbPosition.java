package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class DownClimbPosition extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;

    public DownClimbPosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void initialize(){
        pivot.pivotClimbPosition();
        elevator.basePosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
    
}
