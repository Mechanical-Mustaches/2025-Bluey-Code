package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ConveyorSubsystem extends SubsystemBase {
    SparkMax m_conveyor = new SparkMax(10, MotorType.kBrushless);
    RelativeEncoder encoder;

    public ConveyorSubsystem() {
        var configuration = new SparkMaxConfig();

        configuration.idleMode(IdleMode.kBrake);

        m_conveyor.configure(configuration, null, null);
        encoder = m_conveyor.getEncoder();
    }

    public void conveyInward() {
        m_conveyor.set(-0.7); // 0.5
    }

    public void conveyTrap() {
        m_conveyor.set(1);
    }

    public void stopConvey() {
        m_conveyor.set(0);
    }

    public void conveyFromSource() {
        m_conveyor.set(-0.5);
        // 0.2
    }

    public void conveyMoveBack() {
        m_conveyor.set(0.2);
    }
}
