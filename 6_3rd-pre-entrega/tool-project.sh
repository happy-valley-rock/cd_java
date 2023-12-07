#!/bin/bash
###########################################
#   Build of POS Simple
#   Author: github@happy-valley-rock
###########################################
clear
reset=`tput sgr0`
red=`tput setaf 1`
green=`tput setaf 2`
blue=`tput setaf 4`

echo "${green}"
echo '''
     .d8888b.  .d8888b.  .d8888b.
     88    88  88    88  88.
     888888P`  88    88  `88888b.
     88        88    88       .88
     dp        dp8888P`  dP8888P`

     build tool

  # Welcome!, the respository from this project is:
'''
git remove -v

echo "
${blue}
# What u wanna do?? select the option
  1 - Build project.
  2 - Run project.
  3 - Build and run project.
${red}
# Warning - Not all the option maybe work correctly:
    First of all you need to have install JDK, Maven and Node JS. You can check the versions in the /README.md doc file."
echo "${reset}
type your option:"
read input

echo "${blue}
<----// STEPS //---->"

#============================= 1
if [[ $input == "1" || $input == "3" ]]; then
pwd

echo "${red}#STEP --> Installing dependecies and build${reset}"
rm -rf bundle;
mkdir -p bundle/build/;
mvn clean install -Pprod -f ./app/pom.xml;
cp ./app/target/classes/application.properties ./bundle/build/application.properties;
cp ./app/target/*.jar ./bundle/build/;
#cp -r ./app/target/classes/static ./bundle/build/;

echo "${red}#STEP --> Compressed build files${reset}"
mkdir -p bundle/compressed;
file="$(echo ./bundle/build/*.jar)";
filename=$(basename -- "$file");
filename="${filename##*/}";
cp -r bundle/build ./;
tar -czvf ./bundle/compressed/$filename.tar.gz ./build;
rm -rf build;


echo "${blue}
   __________
 .' _ _ _ _ .|
 | |.| |.| | |
 |_|_|_|_|_|.'

Build created successfuly ! :)
"
fi


#============================= 2
if [[ $input == "2" || $input == "3" ]]; then
pwd

echo "${red}#STEP --> Running project build${reset}"

java -jar ./bundle/build/*.jar

fi
