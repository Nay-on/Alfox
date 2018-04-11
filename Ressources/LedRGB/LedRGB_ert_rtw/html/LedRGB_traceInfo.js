function RTW_Sid2UrlHash() {
	this.urlHashMap = new Array();
	/* <Root>/Gain */
	this.urlHashMap["LedRGB:18"] = "LedRGB.c:57&LedRGB.h:110&LedRGB_data.c:53";
	/* <Root>/Gain1 */
	this.urlHashMap["LedRGB:19"] = "LedRGB.c:78&LedRGB.h:116&LedRGB_data.c:59";
	/* <Root>/Gain2 */
	this.urlHashMap["LedRGB:20"] = "LedRGB.c:36&LedRGB.h:104&LedRGB_data.c:47";
	/* <Root>/cst_blue */
	this.urlHashMap["LedRGB:6"] = "LedRGB.c:35&LedRGB.h:101&LedRGB_data.c:44";
	/* <Root>/cst_green
 */
	this.urlHashMap["LedRGB:5"] = "LedRGB.c:77&LedRGB.h:113&LedRGB_data.c:56";
	/* <Root>/cst_red */
	this.urlHashMap["LedRGB:4"] = "LedRGB.c:56&LedRGB.h:107&LedRGB_data.c:50";
	/* <S1>/Data Type Conversion */
	this.urlHashMap["LedRGB:3:114"] = "LedRGB.c:34,50";
	/* <S1>/PWM */
	this.urlHashMap["LedRGB:3:215"] = "LedRGB.c:52,171&LedRGB.h:92&LedRGB_data.c:35";
	/* <S2>/Data Type Conversion */
	this.urlHashMap["LedRGB:1:114"] = "LedRGB.c:55,71";
	/* <S2>/PWM */
	this.urlHashMap["LedRGB:1:215"] = "LedRGB.c:73,174&LedRGB.h:95&LedRGB_data.c:38";
	/* <S3>/Data Type Conversion */
	this.urlHashMap["LedRGB:2:114"] = "LedRGB.c:76,92";
	/* <S3>/PWM */
	this.urlHashMap["LedRGB:2:215"] = "LedRGB.c:94,177&LedRGB.h:98&LedRGB_data.c:41";
	/* <S4>/Slider
Gain */
	this.urlHashMap["LedRGB:23:31"] = "LedRGB.c:37&LedRGB.h:83&LedRGB_data.c:26";
	/* <S5>/Slider
Gain */
	this.urlHashMap["LedRGB:22:31"] = "LedRGB.c:79&LedRGB.h:89&LedRGB_data.c:32";
	/* <S6>/Slider
Gain */
	this.urlHashMap["LedRGB:21:31"] = "LedRGB.c:58&LedRGB.h:86&LedRGB_data.c:29";
	this.getUrlHash = function(sid) { return this.urlHashMap[sid];}
}
RTW_Sid2UrlHash.instance = new RTW_Sid2UrlHash();
function RTW_rtwnameSIDMap() {
	this.rtwnameHashMap = new Array();
	this.sidHashMap = new Array();
	this.rtwnameHashMap["<Root>"] = {sid: "LedRGB"};
	this.sidHashMap["LedRGB"] = {rtwname: "<Root>"};
	this.rtwnameHashMap["<S1>"] = {sid: "LedRGB:3"};
	this.sidHashMap["LedRGB:3"] = {rtwname: "<S1>"};
	this.rtwnameHashMap["<S2>"] = {sid: "LedRGB:1"};
	this.sidHashMap["LedRGB:1"] = {rtwname: "<S2>"};
	this.rtwnameHashMap["<S3>"] = {sid: "LedRGB:2"};
	this.sidHashMap["LedRGB:2"] = {rtwname: "<S3>"};
	this.rtwnameHashMap["<S4>"] = {sid: "LedRGB:23"};
	this.sidHashMap["LedRGB:23"] = {rtwname: "<S4>"};
	this.rtwnameHashMap["<S5>"] = {sid: "LedRGB:22"};
	this.sidHashMap["LedRGB:22"] = {rtwname: "<S5>"};
	this.rtwnameHashMap["<S6>"] = {sid: "LedRGB:21"};
	this.sidHashMap["LedRGB:21"] = {rtwname: "<S6>"};
	this.rtwnameHashMap["<Root>/Bleu "] = {sid: "LedRGB:3"};
	this.sidHashMap["LedRGB:3"] = {rtwname: "<Root>/Bleu "};
	this.rtwnameHashMap["<Root>/Gain"] = {sid: "LedRGB:18"};
	this.sidHashMap["LedRGB:18"] = {rtwname: "<Root>/Gain"};
	this.rtwnameHashMap["<Root>/Gain1"] = {sid: "LedRGB:19"};
	this.sidHashMap["LedRGB:19"] = {rtwname: "<Root>/Gain1"};
	this.rtwnameHashMap["<Root>/Gain2"] = {sid: "LedRGB:20"};
	this.sidHashMap["LedRGB:20"] = {rtwname: "<Root>/Gain2"};
	this.rtwnameHashMap["<Root>/Rouge"] = {sid: "LedRGB:1"};
	this.sidHashMap["LedRGB:1"] = {rtwname: "<Root>/Rouge"};
	this.rtwnameHashMap["<Root>/Vert"] = {sid: "LedRGB:2"};
	this.sidHashMap["LedRGB:2"] = {rtwname: "<Root>/Vert"};
	this.rtwnameHashMap["<Root>/blue"] = {sid: "LedRGB:23"};
	this.sidHashMap["LedRGB:23"] = {rtwname: "<Root>/blue"};
	this.rtwnameHashMap["<Root>/cst_blue"] = {sid: "LedRGB:6"};
	this.sidHashMap["LedRGB:6"] = {rtwname: "<Root>/cst_blue"};
	this.rtwnameHashMap["<Root>/cst_green "] = {sid: "LedRGB:5"};
	this.sidHashMap["LedRGB:5"] = {rtwname: "<Root>/cst_green "};
	this.rtwnameHashMap["<Root>/cst_red"] = {sid: "LedRGB:4"};
	this.sidHashMap["LedRGB:4"] = {rtwname: "<Root>/cst_red"};
	this.rtwnameHashMap["<Root>/green"] = {sid: "LedRGB:22"};
	this.sidHashMap["LedRGB:22"] = {rtwname: "<Root>/green"};
	this.rtwnameHashMap["<Root>/red"] = {sid: "LedRGB:21"};
	this.sidHashMap["LedRGB:21"] = {rtwname: "<Root>/red"};
	this.rtwnameHashMap["<S1>/In1"] = {sid: "LedRGB:3:116"};
	this.sidHashMap["LedRGB:3:116"] = {rtwname: "<S1>/In1"};
	this.rtwnameHashMap["<S1>/Data Type Conversion"] = {sid: "LedRGB:3:114"};
	this.sidHashMap["LedRGB:3:114"] = {rtwname: "<S1>/Data Type Conversion"};
	this.rtwnameHashMap["<S1>/PWM"] = {sid: "LedRGB:3:215"};
	this.sidHashMap["LedRGB:3:215"] = {rtwname: "<S1>/PWM"};
	this.rtwnameHashMap["<S2>/In1"] = {sid: "LedRGB:1:116"};
	this.sidHashMap["LedRGB:1:116"] = {rtwname: "<S2>/In1"};
	this.rtwnameHashMap["<S2>/Data Type Conversion"] = {sid: "LedRGB:1:114"};
	this.sidHashMap["LedRGB:1:114"] = {rtwname: "<S2>/Data Type Conversion"};
	this.rtwnameHashMap["<S2>/PWM"] = {sid: "LedRGB:1:215"};
	this.sidHashMap["LedRGB:1:215"] = {rtwname: "<S2>/PWM"};
	this.rtwnameHashMap["<S3>/In1"] = {sid: "LedRGB:2:116"};
	this.sidHashMap["LedRGB:2:116"] = {rtwname: "<S3>/In1"};
	this.rtwnameHashMap["<S3>/Data Type Conversion"] = {sid: "LedRGB:2:114"};
	this.sidHashMap["LedRGB:2:114"] = {rtwname: "<S3>/Data Type Conversion"};
	this.rtwnameHashMap["<S3>/PWM"] = {sid: "LedRGB:2:215"};
	this.sidHashMap["LedRGB:2:215"] = {rtwname: "<S3>/PWM"};
	this.rtwnameHashMap["<S4>/u"] = {sid: "LedRGB:23:30"};
	this.sidHashMap["LedRGB:23:30"] = {rtwname: "<S4>/u"};
	this.rtwnameHashMap["<S4>/Slider Gain"] = {sid: "LedRGB:23:31"};
	this.sidHashMap["LedRGB:23:31"] = {rtwname: "<S4>/Slider Gain"};
	this.rtwnameHashMap["<S4>/y"] = {sid: "LedRGB:23:32"};
	this.sidHashMap["LedRGB:23:32"] = {rtwname: "<S4>/y"};
	this.rtwnameHashMap["<S5>/u"] = {sid: "LedRGB:22:30"};
	this.sidHashMap["LedRGB:22:30"] = {rtwname: "<S5>/u"};
	this.rtwnameHashMap["<S5>/Slider Gain"] = {sid: "LedRGB:22:31"};
	this.sidHashMap["LedRGB:22:31"] = {rtwname: "<S5>/Slider Gain"};
	this.rtwnameHashMap["<S5>/y"] = {sid: "LedRGB:22:32"};
	this.sidHashMap["LedRGB:22:32"] = {rtwname: "<S5>/y"};
	this.rtwnameHashMap["<S6>/u"] = {sid: "LedRGB:21:30"};
	this.sidHashMap["LedRGB:21:30"] = {rtwname: "<S6>/u"};
	this.rtwnameHashMap["<S6>/Slider Gain"] = {sid: "LedRGB:21:31"};
	this.sidHashMap["LedRGB:21:31"] = {rtwname: "<S6>/Slider Gain"};
	this.rtwnameHashMap["<S6>/y"] = {sid: "LedRGB:21:32"};
	this.sidHashMap["LedRGB:21:32"] = {rtwname: "<S6>/y"};
	this.getSID = function(rtwname) { return this.rtwnameHashMap[rtwname];}
	this.getRtwname = function(sid) { return this.sidHashMap[sid];}
}
RTW_rtwnameSIDMap.instance = new RTW_rtwnameSIDMap();
