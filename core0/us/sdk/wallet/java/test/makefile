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
build:=build_${suffix}
javafiles:=$(wildcard */*/*/*/*.java) $(wildcard */*/*/*.java) $(wildcard */*/*.java) $(wildcard */*.java) $(wildcard *.java)
classfiles:=$(patsubst %.java, ${build}/%.class, $(javafiles))
bcv:=jdk15to18-1.78.1
flags:=-g
libspath:=..:../libs_bouncy/bcprov-${bcv}.jar:../libs_bouncy/bcpkix-${bcv}.jar:../libs_bouncy/bcpg-${bcv}.jar:../libs_bouncy/bctls-${bcv}.jar:../libs_bouncy/bcmail-${bcv}.jar
depjar:=../${us}-sdk_${suffix}.jar
jarfile:=${us}-sdk-test_${suffix}.jar

all: jar

info:
	echo "javac: ${javac}"
	echo "javafiles: ${javafiles}"
	echo "classfiles ${classfiles}"

jar: ${build}/${jarfile}
	echo ${jarfile}

${build}/${jarfile}: ${depjar} ${classfiles}
	mkdir -p ${build}
	cd ${build}; jar cfe ${jarfile} * main_t*.class; echo "produced file ${jarfile}"; cd ..

${build}/%.class: %.java
	mkdir -p ${build}
	${javac} -Xlint ${flags} -d ${build} -cp ${depjar}:${libspath}:${build} $^

${build}/main_t.class: ${build}/us/gov/crypto/test/symmetric_encryption.class ${build}/us/gov/crypto/test/ec.class main_t.java
	${javac} -Xlint ${flags} -d ${build} -cp ${depjar}:${libspath}:${build} main_t.java

clean:
	$(RM) -r build_*
	$(RM) ${jarfile}

.PHONY: clean jar info
