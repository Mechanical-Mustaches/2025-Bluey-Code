package frc.robot.commands.ConveyorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;

public class ConveyFireCommand extends Command{
    ConveyorSubsystem conveyor; 
  
    public ConveyFireCommand(ConveyorSubsystem conveyor){
        this.conveyor = conveyor;
    }

    @Override
    public void initialize(){
        conveyor.conveyInward();
    }

    @Override
    public void end(boolean i){
        conveyor.stopConvey();
    }
    

}
