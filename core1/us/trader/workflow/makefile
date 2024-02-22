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
HS:=$(wildcard *.h)

all: consumer organization logistics

consumer: ${HS}
	$(MAKE) CXXFLAGS="${CXXFLAGS}" INC="${INC}" -C consumer;

organization: ${HS}
	$(MAKE) CXXFLAGS="${CXXFLAGS}" INC="${INC}" -C organization;

logistics: ${HS}
	$(MAKE) CXXFLAGS="${CXXFLAGS}" INC="${INC}" -C logistics;

clean:
	$(MAKE) clean -C consumer;
	$(MAKE) clean -C organization;
	$(MAKE) clean -C logistics;

.PHONY: all clean consumer organization logistics

