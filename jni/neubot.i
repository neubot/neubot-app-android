%module(directors="1") libneubot;
%feature("director");
%include "./libneubot/neubot.hh"
%{
#include "./libneubot/neubot.hh"
%}
