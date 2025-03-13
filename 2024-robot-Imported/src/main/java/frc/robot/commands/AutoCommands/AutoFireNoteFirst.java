package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ConveyorSubsystem;
import frc.robot.subsystems.FlyWheelSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class AutoFireNoteFirst extends Command{
    ConveyorSubsystem convey;
    FlyWheelSubsystem flywheel;
    boolean seen = false;
    boolean done = false;
    int timer = 0;
    int waitTimer = 75; //in theory a 1.5 second timer

    public AutoFireNoteFirst(ConveyorSubsystem convey, FlyWheelSubsystem flywheel, SwerveSubsystem swerve){
        this.convey = convey;
        this.flywheel = flywheel;
        addRequirements(convey, flywheel, swerve);
    }

    @Override
    public void initialize(){
        System.out.println("Fire Command:");
        timer = 0;
        flywheel.closeShot();
        seen = false;
        done = false;
    }
    // every roboRio cycle is 20ms, simply incrementing an intiger every cycle acts as a timer

    @Override
    public void execute(){
        System.out.println(timer);
            timer++;
            if(timer>50) {
                convey.conveyInward();
            }
            if(flywheel.isNoteSeen()) {
                seen = true;
            }
            if(seen==true && !flywheel.isNoteSeen()) {
                done = true;
            }
            if(timer>=waitTimer) {
                System.out.println("Fire Failed");
            }
            
    }

    @Override
    public boolean isFinished(){
        return (done || timer>waitTimer);
    } //timer function added in the event we miss a note and there is nothing in the robot
    
    @Override
    public void end(boolean i){
        convey.stopConvey();
        flywheel.rampDown();
    }

}
