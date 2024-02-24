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
OBJ:= socket_multipeer
CORE0=../../../..
INC:=-I${CORE0} -I/usr/local/include
CPPS:= $(wildcard *.cpp)
OBJS:= $(patsubst %.cpp, %.o, $(CPPS))
HS:= $(wildcard *.h)
CXXFLAGS=-O0 -g -std=c++17 -DDEBUG -Wno-psabi

all: ${OBJ}.o

%.o: %.cpp
	$(CXX) -fPIC -Wno-psabi -c ${CXXFLAGS} $^ -pthread ${INC} -o $@

${OBJ}.o: ${OBJS}
	ld -r -o ${OBJ}.o ${OBJS}

clean:
	$(RM) -f *.o

.PHONY: all clean

