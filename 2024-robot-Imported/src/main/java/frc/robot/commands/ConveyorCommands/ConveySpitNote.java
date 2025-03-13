package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveySpitNote extends Command {
    ConveyorSubsystem conveyor;

    public ConveySpitNote(ConveyorSubsystem conveyor){
        this.conveyor = conveyor;
    }

    @Override
    public void initialize(){
        conveyor.conveyTrap();
    }

    @Override 
    public void end(boolean i){
        conveyor.stopConvey();
    }
    
}
