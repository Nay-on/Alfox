/*
 * Primary and Secondary School License - for use in teaching and meeting
 * course requirements at primary and secondary schools only.  Not for
 * government, commercial, university, or other organizational use.
 *
 * File: LedRGB.h
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

#ifndef RTW_HEADER_LedRGB_h_
#define RTW_HEADER_LedRGB_h_
#include <float.h>
#include <string.h>
#include <stddef.h>
#ifndef LedRGB_COMMON_INCLUDES_
# define LedRGB_COMMON_INCLUDES_
#include "rtwtypes.h"
#include "rtw_extmode.h"
#include "sysran_types.h"
#include "rtw_continuous.h"
#include "rtw_solver.h"
#include "dt_info.h"
#include "ext_work.h"
#include "arduino_analogoutput_lct.h"
#endif                                 /* LedRGB_COMMON_INCLUDES_ */

#include "LedRGB_types.h"

/* Shared type includes */
#include "multiword_types.h"
#include "MW_target_hardware_resources.h"

/* Macros for accessing real-time model data structure */
#ifndef rtmGetFinalTime
# define rtmGetFinalTime(rtm)          ((rtm)->Timing.tFinal)
#endif

#ifndef rtmGetRTWExtModeInfo
# define rtmGetRTWExtModeInfo(rtm)     ((rtm)->extModeInfo)
#endif

#ifndef rtmGetErrorStatus
# define rtmGetErrorStatus(rtm)        ((rtm)->errorStatus)
#endif

#ifndef rtmSetErrorStatus
# define rtmSetErrorStatus(rtm, val)   ((rtm)->errorStatus = (val))
#endif

#ifndef rtmGetStopRequested
# define rtmGetStopRequested(rtm)      ((rtm)->Timing.stopRequestedFlag)
#endif

#ifndef rtmSetStopRequested
# define rtmSetStopRequested(rtm, val) ((rtm)->Timing.stopRequestedFlag = (val))
#endif

#ifndef rtmGetStopRequestedPtr
# define rtmGetStopRequestedPtr(rtm)   (&((rtm)->Timing.stopRequestedFlag))
#endif

#ifndef rtmGetT
# define rtmGetT(rtm)                  ((rtm)->Timing.taskTime0)
#endif

#ifndef rtmGetTFinal
# define rtmGetTFinal(rtm)             ((rtm)->Timing.tFinal)
#endif

/* Parameters (auto storage) */
struct P_LedRGB_T_ {
  real_T blue_gain;                    /* Mask Parameter: blue_gain
                                        * Referenced by: '<S4>/Slider Gain'
                                        */
  real_T red_gain;                     /* Mask Parameter: red_gain
                                        * Referenced by: '<S6>/Slider Gain'
                                        */
  real_T green_gain;                   /* Mask Parameter: green_gain
                                        * Referenced by: '<S5>/Slider Gain'
                                        */
  uint32_T PWM_pinNumber;              /* Mask Parameter: PWM_pinNumber
                                        * Referenced by: '<S1>/PWM'
                                        */
  uint32_T PWM_pinNumber_m;            /* Mask Parameter: PWM_pinNumber_m
                                        * Referenced by: '<S2>/PWM'
                                        */
  uint32_T PWM_pinNumber_mb;           /* Mask Parameter: PWM_pinNumber_mb
                                        * Referenced by: '<S3>/PWM'
                                        */
  real_T cst_blue_Value;               /* Expression: 1
                                        * Referenced by: '<Root>/cst_blue'
                                        */
  real_T Gain2_Gain;                   /* Expression: 255
                                        * Referenced by: '<Root>/Gain2'
                                        */
  real_T cst_red_Value;                /* Expression: 1
                                        * Referenced by: '<Root>/cst_red'
                                        */
  real_T Gain_Gain;                    /* Expression: 255
                                        * Referenced by: '<Root>/Gain'
                                        */
  real_T cst_green_Value;              /* Expression: 1
                                        * Referenced by: '<Root>/cst_green '
                                        */
  real_T Gain1_Gain;                   /* Expression: 255
                                        * Referenced by: '<Root>/Gain1'
                                        */
};

/* Real-time Model Data Structure */
struct tag_RTM_LedRGB_T {
  const char_T *errorStatus;
  RTWExtModeInfo *extModeInfo;

  /*
   * Sizes:
   * The following substructure contains sizes information
   * for many of the model attributes such as inputs, outputs,
   * dwork, sample times, etc.
   */
  struct {
    uint32_T checksums[4];
  } Sizes;

  /*
   * SpecialInfo:
   * The following substructure contains special information
   * related to other components that are dependent on RTW.
   */
  struct {
    const void *mappingInfo;
  } SpecialInfo;

  /*
   * Timing:
   * The following substructure contains information regarding
   * the timing information for the model.
   */
  struct {
    time_T taskTime0;
    uint32_T clockTick0;
    time_T stepSize0;
    time_T tFinal;
    boolean_T stopRequestedFlag;
  } Timing;
};

/* Block parameters (auto storage) */
extern P_LedRGB_T LedRGB_P;

/* Model entry point functions */
extern void LedRGB_initialize(void);
extern void LedRGB_step(void);
extern void LedRGB_terminate(void);

/* Real-time Model object */
extern RT_MODEL_LedRGB_T *const LedRGB_M;

/*-
 * The generated code includes comments that allow you to trace directly
 * back to the appropriate location in the model.  The basic format
 * is <system>/block_name, where system is the system number (uniquely
 * assigned by Simulink) and block_name is the name of the block.
 *
 * Use the MATLAB hilite_system command to trace the generated code back
 * to the model.  For example,
 *
 * hilite_system('<S3>')    - opens system 3
 * hilite_system('<S3>/Kp') - opens and selects block Kp which resides in S3
 *
 * Here is the system hierarchy for this model
 *
 * '<Root>' : 'LedRGB'
 * '<S1>'   : 'LedRGB/Bleu '
 * '<S2>'   : 'LedRGB/Rouge'
 * '<S3>'   : 'LedRGB/Vert'
 * '<S4>'   : 'LedRGB/blue'
 * '<S5>'   : 'LedRGB/green'
 * '<S6>'   : 'LedRGB/red'
 */
#endif                                 /* RTW_HEADER_LedRGB_h_ */

/*
 * File trailer for generated code.
 *
 * [EOF]
 */
