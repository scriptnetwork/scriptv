#===-                           S C R I P T  T.V.
#===-                           https://script.tv
#===-
#===-            Copyright (C) 2017-2024 Script Network
#===-            Copyright (C) 2017-2024 manicberet@gmail.com
#===-
#===-                      GNU GENERAL PUBLIC LICENSE
#===-                       Version 3, 29 June 2007
#===-
#===-    This program is free software: you can redistribute it and/or modify
#===-    it under the terms of the GPLv3 License as published by the Free
#===-    Software Foundation.
#===-
#===-    This program is distributed in the hope that it will be useful,
#===-    but WITHOUT ANY WARRANTY; without even the implied warranty of
#===-    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
#===-
#===-    You should have received a copy of the General Public License
#===-    along with this program, see LICENCE file.
#===-    see https://www.gnu.org/licenses
#===-
#===----------------------------------------------------------------------------
#===-
STD=c++20
OBJ:=mimx
CXXFLAGS=-O3 -std=${STD} -DNDEBUG
CPPS:=$(wildcard *.cpp)
OBJS:=$(patsubst %.cpp, %.o, $(CPPS))
HS:=$(wildcard *.h)
MODE=dev
#Produce debug output
VERBOSE=1

#Run without writting files
DRYRUN=0

#run in gdb
GDB=0

#review all files based on kickoff files
REVIEW=0

#Run layer:
#opts=-maxz 0.1 -v ${VERBOSE}
#opts=-maxz 0.5 -v ${VERBOSE}
#opts=-maxz 0.61 -v ${VERBOSE}
#opts=-maxz 0.65 -v ${VERBOSE}
opts=-maxz 0.7 -v ${VERBOSE}
#opts=-maxz 0.81 -v ${VERBOSE}
#opts=-maxz 0.82 -v ${VERBOSE}
#opts=-maxz 0.83 -v ${VERBOSE}
#opts=-maxz 0.84 -v ${VERBOSE}
#opts=-maxz 0.85 -v ${VERBOSE}
#opts=-maxz 0.86 -v ${VERBOSE}
#opts=-maxz 0.9 -v ${VERBOSE}

optsclean=-v ${VERBOSE}

ifeq (${DRYRUN},1)
    opts+= -dryrun
    optsclean+= -dryrun
endif

ifeq (${REVIEW},1)
    opts+= -review
endif

ifeq (${GDB},1)
    cmd=gdb --args ./${OBJ}
else
    cmd=./${OBJ}
endif


INC:=-I../.. -I.

all: generated

debug: export CXXFLAGS:=-O0 -g -std=${STD} -DDEBUG
debug: export MODE:=dev
debug: all

release: export CXXFLAGS:=-O3 -std=${STD} -DNDEBUG
release: export MODE:=cbs
release: all

%.o: %.cpp
	${CXX} -fPIC -c ${CXXFLAGS} ${INC} $^ -o $@

${OBJ}.o: ${OBJS}
	ld -r -o ${OBJ}.o ${OBJS}

${OBJ}: ${OBJ}.o
	${CXX} ${CXXFLAGS} ${OBJ}.o -o ${OBJ}

generated: ${OBJ}
	${cmd} ${opts} configure ${MODE}

clean_conf: ${OBJ}
	${cmd} ${optsclean} clean_conf
	${RM} ${OBJ}
	${RM} *.o

debug_clean_conf: export CXXFLAGS:=-O0 -g -std=${STD} -DDEBUG
debug_clean_conf: export MODE:=dev
debug_clean_conf: clean_conf

clean:
	${RM} ${OBJ}
	${RM} *.o

.phony: debug all clean clean_conf

