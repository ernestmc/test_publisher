cmake_minimum_required(VERSION 2.8.3)
project(android_teleop)

find_package(catkin REQUIRED rosjava_build_tools)

catkin_android_setup(assembleDebug uploadArchives)

catkin_package()

find_package(catkin REQUIRED genjava)

# using pre-build messages as a workaround for actionlib messages
#generate_rosjava_messages(
#  PACKAGES
#  imover_msgs
#)

##############################################################################
# Installation
##############################################################################

#install(DIRECTORY ${CATKIN_DEVEL_PREFIX}/${CATKIN_GLOBAL_MAVEN_DESTINATION}/xyz/imover/android_teleop/
#        DESTINATION ${CATKIN_GLOBAL_MAVEN_DESTINATION}/xyz/imover/android_teleop)
