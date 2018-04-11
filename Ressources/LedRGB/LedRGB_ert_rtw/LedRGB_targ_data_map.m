  function targMap = targDataMap(),

  ;%***********************
  ;% Create Parameter Map *
  ;%***********************
      
    nTotData      = 0; %add to this count as we go
    nTotSects     = 3;
    sectIdxOffset = 0;
    
    ;%
    ;% Define dummy sections & preallocate arrays
    ;%
    dumSection.nData = -1;  
    dumSection.data  = [];
    
    dumData.logicalSrcIdx = -1;
    dumData.dtTransOffset = -1;
    
    ;%
    ;% Init/prealloc paramMap
    ;%
    paramMap.nSections           = nTotSects;
    paramMap.sectIdxOffset       = sectIdxOffset;
      paramMap.sections(nTotSects) = dumSection; %prealloc
    paramMap.nTotData            = -1;
    
    ;%
    ;% Auto data (LedRGB_P)
    ;%
      section.nData     = 3;
      section.data(3)  = dumData; %prealloc
      
	  ;% LedRGB_P.blue_gain
	  section.data(1).logicalSrcIdx = 0;
	  section.data(1).dtTransOffset = 0;
	
	  ;% LedRGB_P.red_gain
	  section.data(2).logicalSrcIdx = 1;
	  section.data(2).dtTransOffset = 1;
	
	  ;% LedRGB_P.green_gain
	  section.data(3).logicalSrcIdx = 2;
	  section.data(3).dtTransOffset = 2;
	
      nTotData = nTotData + section.nData;
      paramMap.sections(1) = section;
      clear section
      
      section.nData     = 3;
      section.data(3)  = dumData; %prealloc
      
	  ;% LedRGB_P.PWM_pinNumber
	  section.data(1).logicalSrcIdx = 3;
	  section.data(1).dtTransOffset = 0;
	
	  ;% LedRGB_P.PWM_pinNumber_m
	  section.data(2).logicalSrcIdx = 4;
	  section.data(2).dtTransOffset = 1;
	
	  ;% LedRGB_P.PWM_pinNumber_mb
	  section.data(3).logicalSrcIdx = 5;
	  section.data(3).dtTransOffset = 2;
	
      nTotData = nTotData + section.nData;
      paramMap.sections(2) = section;
      clear section
      
      section.nData     = 6;
      section.data(6)  = dumData; %prealloc
      
	  ;% LedRGB_P.cst_blue_Value
	  section.data(1).logicalSrcIdx = 6;
	  section.data(1).dtTransOffset = 0;
	
	  ;% LedRGB_P.Gain2_Gain
	  section.data(2).logicalSrcIdx = 7;
	  section.data(2).dtTransOffset = 1;
	
	  ;% LedRGB_P.cst_red_Value
	  section.data(3).logicalSrcIdx = 8;
	  section.data(3).dtTransOffset = 2;
	
	  ;% LedRGB_P.Gain_Gain
	  section.data(4).logicalSrcIdx = 9;
	  section.data(4).dtTransOffset = 3;
	
	  ;% LedRGB_P.cst_green_Value
	  section.data(5).logicalSrcIdx = 10;
	  section.data(5).dtTransOffset = 4;
	
	  ;% LedRGB_P.Gain1_Gain
	  section.data(6).logicalSrcIdx = 11;
	  section.data(6).dtTransOffset = 5;
	
      nTotData = nTotData + section.nData;
      paramMap.sections(3) = section;
      clear section
      
    
      ;%
      ;% Non-auto Data (parameter)
      ;%
    

    ;%
    ;% Add final counts to struct.
    ;%
    paramMap.nTotData = nTotData;
    


  ;%**************************
  ;% Create Block Output Map *
  ;%**************************
      
    nTotData      = 0; %add to this count as we go
    nTotSects     = 0;
    sectIdxOffset = 0;
    
    ;%
    ;% Define dummy sections & preallocate arrays
    ;%
    dumSection.nData = -1;  
    dumSection.data  = [];
    
    dumData.logicalSrcIdx = -1;
    dumData.dtTransOffset = -1;
    
    ;%
    ;% Init/prealloc sigMap
    ;%
    sigMap.nSections           = nTotSects;
    sigMap.sectIdxOffset       = sectIdxOffset;
    sigMap.nTotData            = -1;
    
    ;%
    ;% Auto data (LedRGB_B)
    ;%
    
      ;%
      ;% Non-auto Data (signal)
      ;%
    

    ;%
    ;% Add final counts to struct.
    ;%
    sigMap.nTotData = nTotData;
    


  ;%*******************
  ;% Create DWork Map *
  ;%*******************
      
    nTotData      = 0; %add to this count as we go
    nTotSects     = 0;
    sectIdxOffset = 0;
    
    ;%
    ;% Define dummy sections & preallocate arrays
    ;%
    dumSection.nData = -1;  
    dumSection.data  = [];
    
    dumData.logicalSrcIdx = -1;
    dumData.dtTransOffset = -1;
    
    ;%
    ;% Init/prealloc dworkMap
    ;%
    dworkMap.nSections           = nTotSects;
    dworkMap.sectIdxOffset       = sectIdxOffset;
    dworkMap.nTotData            = -1;
    
    ;%
    ;% Auto data (LedRGB_DW)
    ;%
    
      ;%
      ;% Non-auto Data (dwork)
      ;%
    

    ;%
    ;% Add final counts to struct.
    ;%
    dworkMap.nTotData = nTotData;
    


  ;%
  ;% Add individual maps to base struct.
  ;%

  targMap.paramMap  = paramMap;    
  targMap.signalMap = sigMap;
  targMap.dworkMap  = dworkMap;
  
  ;%
  ;% Add checksums to base struct.
  ;%


  targMap.checksum0 = 1002774782;
  targMap.checksum1 = 3270606099;
  targMap.checksum2 = 2561952057;
  targMap.checksum3 = 952281866;

