package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class FarPOSCommand extends Command {
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;

    public FarPOSCommand(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void execute(){
        pivot.pivotPodiumPosition();
        elevator.podiumPosition();
    }

    @Override
    public void end(boolean i){
        pivot.pivotBasePosition();
        elevator.basePosition();
    }
}
