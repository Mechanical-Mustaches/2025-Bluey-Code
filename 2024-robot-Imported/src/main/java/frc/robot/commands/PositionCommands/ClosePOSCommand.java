package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class ClosePOSCommand extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;

    public ClosePOSCommand(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;

    }

    @Override
    public void initialize(){
        pivot.pivotSubWooferPosition();
        elevator.subWooferPosition();
    }

    @Override
    public void end(boolean i){
        pivot.pivotBasePosition();
        elevator.basePosition();
    }
}
