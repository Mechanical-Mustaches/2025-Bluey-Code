package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyMoveNote extends Command {
    ConveyorSubsystem conveyor;

    public ConveyMoveNote(ConveyorSubsystem conveyor){
        this.conveyor = conveyor;
    }

    @Override
    public void initialize(){
        conveyor.conveyMoveBack();
    }

    @Override 
    public void end(boolean i){
        conveyor.stopConvey();
    }
    
}
