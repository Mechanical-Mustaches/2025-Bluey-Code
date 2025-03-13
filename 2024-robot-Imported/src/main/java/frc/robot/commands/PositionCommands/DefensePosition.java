package frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.PivotSubsystem;

public class DefensePosition extends Command{
    private final PivotSubsystem pivot;
    private final ElevatorSubsystem elevator;

    public DefensePosition(PivotSubsystem pivot, ElevatorSubsystem elevator){
        this.pivot = pivot;
        this.elevator = elevator;
    }

    @Override
    public void initialize(){
        elevator.defensePosition();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
    
    
}
