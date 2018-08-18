package org.usfirst.frc.team6897.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import org.usfirst.frc.team6897.robot.commands.*;
import org.usfirst.frc.team6897.robot.subsystems.*;
import org.usfirst.frc.team6897.robot.Constants;
import org.usfirst.frc.team6897.robot.RobotMap;

public class Robot extends TimedRobot{

    private OI m_oi;
    private DriverSubsystem driverSubsystem;
    private GripperSubsystem gripperSubsystem;
    private ShooterSubsystem shooterSubsystem;
    private FeederSubsystem feederSubsystem;
    private SpeedControllerGroup leftTalons;
    private SpeedControllerGroup rightTalons;
    private DifferentialDrive differentialDrive;

    @Override
    public void robotInit() {
        m_oi = new OI();
        driverSubsystem = new DriverSubsystem(m_oi);
        gripperSubsystem = new GripperSubsystem(m_oi);
        shooterSubsystem = new ShooterSubsystem(m_oi);
        feederSubsystem = new FeederSubsystem(m_oi);
        leftTalons = new SpeedControllerGroup(new Talon(0), new Talon(1));
        rightTalons = new SpeedControllerGroup(new Talon(2), new Talon(3));
        differentialDrive = new DifferentialDrive(leftTalons, rightTalons);
    }

    @Override
    public void teleopInit() {
        driverSubsystem.startTeleop();
        gripperSubsystem.startTeleop();
        shooterSubsystem.startTeleop();
        feederSubsystem.startTeleop();
    }

    @Override
    public void teleopPeriodic() {
        differentialDrive.TankDrive(m_oi.leftJoystick.getY(), m_oi.rightJoystick.getY());
        Scheduler.getInstance().run();
    }
}
