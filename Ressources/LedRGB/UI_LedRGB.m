function varargout = UI_LedRGB(varargin)
% UI_LEDRGB MATLAB code for UI_LedRGB.fig
%      UI_LEDRGB, by itself, creates a new UI_LEDRGB or raises the existing
%      singleton*.
%
%      H = UI_LEDRGB returns the handle to a new UI_LEDRGB or the handle to
%      the existing singleton*.
%
%      UI_LEDRGB('CALLBACK',hObject,eventData,handles,...) calls the local
%      function named CALLBACK in UI_LEDRGB.M with the given input arguments.
%
%      UI_LEDRGB('Property','Value',...) creates a new UI_LEDRGB or raises the
%      existing singleton*.  Starting from the left, property value pairs are
%      applied to the GUI before UI_LedRGB_OpeningFcn gets called.  An
%      unrecognized property name or invalid value makes property application
%      stop.  All inputs are passed to UI_LedRGB_OpeningFcn via varargin.
%
%      *See GUI Options on GUIDE's Tools menu.  Choose "GUI allows only one
%      instance to run (singleton)".
%
% See also: GUIDE, GUIDATA, GUIHANDLES

% Edit the above text to modify the response to help UI_LedRGB

% Last Modified by GUIDE v2.5 07-Apr-2018 12:08:31

% Begin initialization code - DO NOT EDIT
gui_Singleton = 1;
gui_State = struct('gui_Name',       mfilename, ...
                   'gui_Singleton',  gui_Singleton, ...
                   'gui_OpeningFcn', @UI_LedRGB_OpeningFcn, ...
                   'gui_OutputFcn',  @UI_LedRGB_OutputFcn, ...
                   'gui_LayoutFcn',  [] , ...
                   'gui_Callback',   []);
if nargin && ischar(varargin{1})
    gui_State.gui_Callback = str2func(varargin{1});
end

if nargout
    [varargout{1:nargout}] = gui_mainfcn(gui_State, varargin{:});
else
    gui_mainfcn(gui_State, varargin{:});
end
% End initialization code - DO NOT EDIT


% --- Executes just before UI_LedRGB is made visible.
function UI_LedRGB_OpeningFcn(hObject, eventdata, handles, varargin)
% This function has no output args, see OutputFcn.
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)
% varargin   command line arguments to UI_LedRGB (see VARARGIN)

% Choose default command line output for UI_LedRGB
handles.output = hObject;

% Update handles structure
guidata(hObject, handles);

% UIWAIT makes UI_LedRGB wait for user response (see UIRESUME)
% uiwait(handles.figure1);


% --- Outputs from this function are returned to the command line.
function varargout = UI_LedRGB_OutputFcn(hObject, eventdata, handles) 
% varargout  cell array for returning output args (see VARARGOUT);
% hObject    handle to figure
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Get default command line output from handles structure
varargout{1} = handles.output;


% --- Executes on slider movement.
function rouge_Callback(hObject, eventdata, handles)
% hObject    handle to rouge (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'Value') returns position of slider
%        get(hObject,'Min') and get(hObject,'Max') to determine range of slider
set(handles.couleur,'BackgroundColor',[get(hObject,'Value') dot(get(handles.couleur,'BackgroundColor'),[0 1 0])  dot(get(handles.couleur,'BackgroundColor'),[0 0 1]) ]);
set_param('LedRGB/red', 'gain', num2str(get(hObject,'Value')));

% --- Executes during object creation, after setting all properties.
function rouge_CreateFcn(hObject, eventdata, handles)
% hObject    handle to rouge (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: slider controls usually have a light gray background.
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
    

end


% --- Executes on slider movement.
function vert_Callback(hObject, eventdata, handles)
% hObject    handle to vert (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'Value') returns position of slider
%        get(hObject,'Min') and get(hObject,'Max') to determine range of slider
set(handles.couleur,'BackgroundColor',[dot(get(handles.couleur,'BackgroundColor'),[1 0 0]) get(hObject,'Value') dot(get(handles.couleur,'BackgroundColor'),[0 0 1]) ]);
set_param('LedRGB/green', 'gain', num2str(get(hObject,'Value')));


% --- Executes during object creation, after setting all properties.
function vert_CreateFcn(hObject, eventdata, handles)
% hObject    handle to vert (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: slider controls usually have a light gray background.
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end


% --- Executes on slider movement.
function bleu_Callback(hObject, eventdata, handles)
% hObject    handle to bleu (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    structure with handles and user data (see GUIDATA)

% Hints: get(hObject,'Value') returns position of slider
%        get(hObject,'Min') and get(hObject,'Max') to determine range of slider
set(handles.couleur,'BackgroundColor',[dot(get(handles.couleur,'BackgroundColor'),[1 0 0]) dot(get(handles.couleur,'BackgroundColor'),[0 1 0]) get(hObject,'Value')]);
set_param('LedRGB/blue', 'gain', num2str(get(hObject,'Value')));


% --- Executes during object creation, after setting all properties.
function bleu_CreateFcn(hObject, eventdata, handles)
% hObject    handle to bleu (see GCBO)
% eventdata  reserved - to be defined in a future version of MATLAB
% handles    empty - handles not created until after all CreateFcns called

% Hint: slider controls usually have a light gray background.
if isequal(get(hObject,'BackgroundColor'), get(0,'defaultUicontrolBackgroundColor'))
    set(hObject,'BackgroundColor',[.9 .9 .9]);
end
