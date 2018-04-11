/*
 * Primary and Secondary School License - for use in teaching and meeting
 * course requirements at primary and secondary schools only.  Not for
 * government, commercial, university, or other organizational use.
 *
 * File: LedRGB.c
 *
 * Code generated for Simulink model 'LedRGB'.
 *
 * Model version                  : 1.9
 * Simulink Coder version         : 8.12 (R2017a) 16-Feb-2017
 * C/C++ source code generated on : Sat Apr  7 17:41:21 2018
 *
 * Target selection: ert.tlc
 * Embedded hardware selection: Atmel->AVR
 * Code generation objectives: Unspecified
 * Validation result: Not run
 */

#include "LedRGB.h"
#include "LedRGB_private.h"
#include "LedRGB_dt.h"

/* Real-time model */
RT_MODEL_LedRGB_T LedRGB_M_;
RT_MODEL_LedRGB_T *const LedRGB_M = &LedRGB_M_;

/* Model step function */
void LedRGB_step(void)
{
  real_T tmp;
  uint8_T tmp_0;

  /* DataTypeConversion: '<S1>/Data Type Conversion' incorporates:
   *  Constant: '<Root>/cst_blue'
   *  Gain: '<Root>/Gain2'
   *  Gain: '<S4>/Slider Gain'
   */
  tmp = LedRGB_P.blue_gain * LedRGB_P.cst_blue_Value * LedRGB_P.Gain2_Gain;
  if (tmp < 256.0) {
    if (tmp >= 0.0) {
      tmp_0 = (uint8_T)tmp;
    } else {
      tmp_0 = 0U;
    }
  } else {
    tmp_0 = MAX_uint8_T;
  }

  /* End of DataTypeConversion: '<S1>/Data Type Conversion' */

  /* S-Function (arduinoanalogoutput_sfcn): '<S1>/PWM' */
  MW_analogWrite(LedRGB_P.PWM_pinNumber, tmp_0);

  /* DataTypeConversion: '<S2>/Data Type Conversion' incorporates:
   *  Constant: '<Root>/cst_red'
   *  Gain: '<Root>/Gain'
   *  Gain: '<S6>/Slider Gain'
   */
  tmp = LedRGB_P.red_gain * LedRGB_P.cst_red_Value * LedRGB_P.Gain_Gain;
  if (tmp < 256.0) {
    if (tmp >= 0.0) {
      tmp_0 = (uint8_T)tmp;
    } else {
      tmp_0 = 0U;
    }
  } else {
    tmp_0 = MAX_uint8_T;
  }

  /* End of DataTypeConversion: '<S2>/Data Type Conversion' */

  /* S-Function (arduinoanalogoutput_sfcn): '<S2>/PWM' */
  MW_analogWrite(LedRGB_P.PWM_pinNumber_m, tmp_0);

  /* DataTypeConversion: '<S3>/Data Type Conversion' incorporates:
   *  Constant: '<Root>/cst_green '
   *  Gain: '<Root>/Gain1'
   *  Gain: '<S5>/Slider Gain'
   */
  tmp = LedRGB_P.green_gain * LedRGB_P.cst_green_Value * LedRGB_P.Gain1_Gain;
  if (tmp < 256.0) {
    if (tmp >= 0.0) {
      tmp_0 = (uint8_T)tmp;
    } else {
      tmp_0 = 0U;
    }
  } else {
    tmp_0 = MAX_uint8_T;
  }

  /* End of DataTypeConversion: '<S3>/Data Type Conversion' */

  /* S-Function (arduinoanalogoutput_sfcn): '<S3>/PWM' */
  MW_analogWrite(LedRGB_P.PWM_pinNumber_mb, tmp_0);

  /* External mode */
  rtExtModeUploadCheckTrigger(1);

  {                                    /* Sample time: [0.01s, 0.0s] */
    rtExtModeUpload(0, LedRGB_M->Timing.taskTime0);
  }

  /* signal main to stop simulation */
  {                                    /* Sample time: [0.01s, 0.0s] */
    if ((rtmGetTFinal(LedRGB_M)!=-1) &&
        !((rtmGetTFinal(LedRGB_M)-LedRGB_M->Timing.taskTime0) >
          LedRGB_M->Timing.taskTime0 * (DBL_EPSILON))) {
      rtmSetErrorStatus(LedRGB_M, "Simulation finished");
    }

    if (rtmGetStopRequested(LedRGB_M)) {
      rtmSetErrorStatus(LedRGB_M, "Simulation finished");
    }
  }

  /* Update absolute time for base rate */
  /* The "clockTick0" counts the number of times the code of this task has
   * been executed. The absolute time is the multiplication of "clockTick0"
   * and "Timing.stepSize0". Size of "clockTick0" ensures timer will not
   * overflow during the application lifespan selected.
   */
  LedRGB_M->Timing.taskTime0 =
    (++LedRGB_M->Timing.clockTick0) * LedRGB_M->Timing.stepSize0;
}

/* Model initialize function */
void LedRGB_initialize(void)
{
  /* Registration code */

  /* initialize real-time model */
  (void) memset((void *)LedRGB_M, 0,
                sizeof(RT_MODEL_LedRGB_T));
  rtmSetTFinal(LedRGB_M, -1);
  LedRGB_M->Timing.stepSize0 = 0.01;

  /* External mode info */
  LedRGB_M->Sizes.checksums[0] = (1002774782U);
  LedRGB_M->Sizes.checksums[1] = (3270606099U);
  LedRGB_M->Sizes.checksums[2] = (2561952057U);
  LedRGB_M->Sizes.checksums[3] = (952281866U);

  {
    static const sysRanDType rtAlwaysEnabled = SUBSYS_RAN_BC_ENABLE;
    static RTWExtModeInfo rt_ExtModeInfo;
    static const sysRanDType *systemRan[1];
    LedRGB_M->extModeInfo = (&rt_ExtModeInfo);
    rteiSetSubSystemActiveVectorAddresses(&rt_ExtModeInfo, systemRan);
    systemRan[0] = &rtAlwaysEnabled;
    rteiSetModelMappingInfoPtr(LedRGB_M->extModeInfo,
      &LedRGB_M->SpecialInfo.mappingInfo);
    rteiSetChecksumsPtr(LedRGB_M->extModeInfo, LedRGB_M->Sizes.checksums);
    rteiSetTPtr(LedRGB_M->extModeInfo, rtmGetTPtr(LedRGB_M));
  }

  /* data type transition information */
  {
    static DataTypeTransInfo dtInfo;
    (void) memset((char_T *) &dtInfo, 0,
                  sizeof(dtInfo));
    LedRGB_M->SpecialInfo.mappingInfo = (&dtInfo);
    dtInfo.numDataTypes = 14;
    dtInfo.dataTypeSizes = &rtDataTypeSizes[0];
    dtInfo.dataTypeNames = &rtDataTypeNames[0];

    /* Parameters transition table */
    dtInfo.PTransTable = &rtPTransTable;
  }

  /* Start for S-Function (arduinoanalogoutput_sfcn): '<S1>/PWM' */
  MW_pinModeOutput(LedRGB_P.PWM_pinNumber);

  /* Start for S-Function (arduinoanalogoutput_sfcn): '<S2>/PWM' */
  MW_pinModeOutput(LedRGB_P.PWM_pinNumber_m);

  /* Start for S-Function (arduinoanalogoutput_sfcn): '<S3>/PWM' */
  MW_pinModeOutput(LedRGB_P.PWM_pinNumber_mb);
}

/* Model terminate function */
void LedRGB_terminate(void)
{
  /* (no terminate code required) */
}

/*
 * File trailer for generated code.
 *
 * [EOF]
 */
