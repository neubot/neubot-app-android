%module(directors="1") neubotjava;
%feature("director");
%include "./libneubot/neubot.hh"
%{
#include "./libneubot/neubot.hh"
%}
