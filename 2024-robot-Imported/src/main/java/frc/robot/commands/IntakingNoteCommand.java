package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.commands.ConveyorCommands.ConveyLineBreak;
import frc.robot.commands.ConveyorCommands.ConveyorMoveBack;
import frc.robot.commands.PositionCommands.BasePosition;

public class IntakingNoteCommand extends SequentialCommandGroup{

    public IntakingNoteCommand(FloorIntakeSubsystem floorIntake, ConveyorSubsystem conveyor,
        ElevatorSubsystem elevator, PivotSubsystem pivot, FlyWheelSubsystem flywheel){
        
        addCommands(
                (new BasePosition(pivot, elevator)),   
                (new ConveyLineBreak(conveyor, flywheel, floorIntake)),
                (new ConveyorMoveBack(conveyor, flywheel))
        );
       
    }

    

    
}
