cmake_minimum_required(VERSION 2.8.3)
project(imover_msgs)

# Disable message generation for lisp and java
set(ENV{ROS_LANG_DISABLE} "genlisp:")

find_package(catkin REQUIRED
  COMPONENTS
    actionlib_msgs
    geometry_msgs
    message_generation
    std_msgs
    std_srvs
    uuid_msgs
)

## Generate messages in the 'msg' folder
add_message_files(
  FILES
    ActionItem.msg
    Mode.msg
    Odometry.msg
    Path.msg
    MissionActionGoal.msg
    MissionActionResult.msg
    MissionFeedback.msg
    MissionGoal.msg
    MissionResult.msg
)

## Generate added messages and services with any dependencies listed here
generate_messages(
  DEPENDENCIES
    actionlib_msgs
    geometry_msgs
    std_msgs
    uuid_msgs
)

catkin_package(
  CATKIN_DEPENDS
    actionlib_msgs
    geometry_msgs
    message_runtime
    std_msgs
    std_srvs
    uuid_msgs
)
