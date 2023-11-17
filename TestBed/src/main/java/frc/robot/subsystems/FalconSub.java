// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;

public class FalconSub extends SubsystemBase {
  static TalonFX falcon;
  
  //** Creates a new ExampleSubsystem. */
  public FalconSub(int ID) {
    falcon = new TalonFX(ID);
    falcon.setNeutralMode(NeutralMode.Coast);
  } 

  public FalconSub(int ID, boolean reversed) {
    falcon = new TalonFX(ID);
    falcon.setNeutralMode(NeutralMode.Coast);
    falcon.setInverted(reversed);
  }

  public void move(double percentSpeed){
    falcon.set(TalonFXControlMode.PercentOutput, -percentSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
