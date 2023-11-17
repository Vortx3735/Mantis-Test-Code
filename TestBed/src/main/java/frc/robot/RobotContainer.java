// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.util.VorTXController;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  
  public static VorTXController con1 = new VorTXController(0);

  // public static SparkMAXSub sparksub = new SparkMAXSub(0);
  // public static SparkMAXCom spark = new SparkMAXCom(sparksub);

  public static FalconSub leftshooterfalcon = new FalconSub(5);
  public static FalconCom leftshooter = new FalconCom(leftshooterfalcon);

  public static FalconSub rightshooterfalcon = new FalconSub(6, true);
  public static FalconCom rightshooter = new FalconCom(rightshooterfalcon);

  public static TalonSub talonsub = new TalonSub(4);
  public static TalonCom talon = new TalonCom(talonsub);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    // sparksub.setDefaultCommand(
    //   new RunCommand(
    //     spark::moveStick,
    //     sparksub
    //   )
    // );

    leftshooterfalcon.setDefaultCommand(
      new RunCommand(
        leftshooter::stop,
        leftshooterfalcon
      )
    );

    rightshooterfalcon.setDefaultCommand(
      new RunCommand(
        rightshooter::stop,
        rightshooterfalcon
      )
    );

    talonsub.setDefaultCommand(
      new RunCommand(
        talon::stop,
        talonsub
      )
    );
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    
    con1.circle.whileTrue(
      new RunCommand(
        talon::rev, 
        talonsub
      )
    );

    con1.triangle.whileTrue(
      new RunCommand(
        talon::start, 
        talonsub
      )
    );

    con1.circle.whileTrue(
      new ParallelCommandGroup(
        new RunCommand(
          leftshooter::start,
          leftshooterfalcon
        ),
        new RunCommand(
          rightshooter::start,
          rightshooterfalcon
        )
      )
      
    );

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new InstantCommand();
  }
}
