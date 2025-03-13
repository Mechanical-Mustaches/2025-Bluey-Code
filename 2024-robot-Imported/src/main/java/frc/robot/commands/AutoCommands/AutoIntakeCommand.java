package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.FloorIntakeSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.PivotSubsystem;
import frc.robot.commands.ConveyorCommands.ConveyLineBreak;
import frc.robot.commands.ConveyorCommands.ConveyorMoveBack;
import frc.robot.commands.PositionCommands.BasePosition;
import frc.robot.commands.FlyWheelCommands.CloseShotCommand;

public class AutoIntakeCommand extends SequentialCommandGroup{

    public AutoIntakeCommand(FloorIntakeSubsystem floorIntake, ConveyorSubsystem conveyor,
        ElevatorSubsystem elevator, PivotSubsystem pivot, FlyWheelSubsystem flywheel){
        
        addCommands(
                (new BasePosition(pivot, elevator)),   
                (new ConveyLineBreak(conveyor, flywheel, floorIntake)),
                (new ConveyorMoveBack(conveyor, flywheel)),
                (new CloseShotCommand(flywheel))
                
        );
       
    }

    

    
}
