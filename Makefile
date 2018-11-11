# **************************************************************************** #
#                                                                              #
#                                                         :::      ::::::::    #
#    Makefile                                           :+:      :+:    :+:    #
#                                                     +:+ +:+         +:+      #
#    By: thmotaun <marvin@42.fr>                    +#+  +:+       +#+         #
#                                                 +#+#+#+#+#+   +#+            #
#    Created: 2018/07/04 12:42:53 by thmotaun          #+#    #+#              #
#    Updated: 2018/07/04 12:42:56 by thmotaun         ###   ########.fr        #
#                                                                              #
# **************************************************************************** #

CURDIR			= $(shell pwd)
JAVA_CLASSES	= $(subst /src/,/build/,$(JAVA_SRCS:.java=.class))
JFLAGS			= -g -cp
JC				= javac
MAIN			= com/thmotaun/simulator/main/AircraftSimulator
JVM				= java
TEST_LINE       = java com/thmotaun/simulator/main/AircraftSimulator scenario.txt

define errors
tput blink
tput setaf 14
@echo $1
tput sgr0
endef

define correct
tput setaf 46
@echo $1
tput sgr0
endef

define empties
tput setaf 201
@echo $1
tput sgr0
endef

define incorrect
tput setaf 124
@echo $1
tput sgr0
endef

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

default: build

build: $(JAVA_CLASSES)

source:
	find . -name "*.java" > sources.txt

compile:
	$(JC) $(JFLAGS) -sourcepath @sources.txt

git:
	git add *;
	git commit -m "update";
	git push origin master;

all: source compile

run_test:
	@$(call correct, "Testing 1, 2, 3...")
	$(JVM) -cp $(MAIN) ./scenario.txt
run_1:
	@$(call correct, "Running first correct format scenario file...")
	$(JVM) -cp $(MAIN) ./com/thmotaun/simulator/.test_folder/scenario.txt
run_2:
	@$(call correct, "Running second correct format scenario file...")
	$(JVM) $(MAIN) ./.test_folder/_scenario_success.txt
run_3:
	@$(call empties, "Running empty scenario file...")
	$(JVM) $(MAIN) ./.test_folder/_scenario_empty.txt
run_4:
	@$(call incorrect, "Running first incorrect format scenario file...")
	$(JVM) $(MAIN) ./.test_folder/_scenario_errors.txt
run_5:
	@$(call incorrect, "Running second incorrect format scenario file...")
	$(JVM) $(MAIN) ./.test_folder/_scenario_errors_warnings.txt
run_6:
	@$(call errors, "Running scenario file with warnings...")
	$(JVM) $(MAIN) ./.test_folder/_scenario_warnings.txt

clean:
	find . -type f -name "*.class" -delete
	find . -type f -name "sources.txt" -delete
	find . -type f -name "simulation.txt" -delete

fclean: clean

re: fclean all
