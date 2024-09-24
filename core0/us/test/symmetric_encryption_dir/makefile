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
us:=us
javac:=$(JAVA_HOME)/bin/javac
suffix:=bc
bcv:=jdk15to18-1.78.1
flags:=-g
libs:=../../sdk/wallet/java
libspath:=${libs}:${libs}/libs_bouncy/bcprov-${bcv}.jar:${libs}/libs_bouncy/bcpkix-${bcv}.jar:${libs}/libs_bouncy/bcpg-${bcv}.jar:${libs}/libs_bouncy/bctls-${bcv}.jar:${libs}/libs_bouncy/bcmail-${bcv}.jar
depjar:=../../sdk/wallet/java/${us}-sdk_${suffix}.jar
classfile:=Main.class

all: Main.class generate_keys test_cpp

generate_keys:
	g++ -I ../../.. -L ../../gov generate_keys.cpp -lusgov -o generate_keys

test_cpp:
	g++ -I ../../.. -L ../../gov main.cpp -lusgov -lcryptopp -o test_cpp

Main.class: Main.java
	${javac} -Xlint ${flags} -cp ${depjar}:${libspath} Main.java


clean:
	$(RM) generate_keys
	$(RM) test_cpp
	$(RM) Main.class

.PHONY: all clean



