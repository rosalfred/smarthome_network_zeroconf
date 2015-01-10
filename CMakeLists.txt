cmake_minimum_required(VERSION 2.8.3)
project(network_zeroconf)

find_package(catkin REQUIRED rosjava_build_tools)

catkin_rosjava_setup(publishMavenJavaPublicationToMavenRepository)

catkin_package()

install(DIRECTORY ${CATKIN_DEVEL_PREFIX}/${CATKIN_GLOBAL_MAVEN_DESTINATION}/com/alfred/ros/${PROJECT_NAME}/ 
        DESTINATION ${CATKIN_GLOBAL_MAVEN_DESTINATION}/com/alfred/ros/${PROJECT_NAME})
