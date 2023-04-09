cmake_minimum_required(VERSION 3.13)

# initialize the SDK based on PICO_SDK_PATH
# note: this must happen before project()
include(pico_sdk_import.cmake)

project(QualityDeskControl C CXX ASM)

set(CMAKE_C_STANDARD 11)
set(CMAKE_CXX_STANDARD 17)

if (PICO_SDK_VERSION_STRING VERSION_LESS "1.3.0")
    message(FATAL_ERROR "Raspberry Pi Pico SDK version 1.3.0 (or later) required. Your version is ${PICO_SDK_VERSION_STRING}")
endif()

# initialize the Raspberry Pi Pico SDK
pico_sdk_init()

add_executable(QualityDeskControl main.c)

# Add pico_stdlib library which aggregates commonly used features
target_link_libraries(QualityDeskControl
        pico_stdlib
        pico_time
        hardware_clocks
        hardware_uart
        hardware_gpio
        hardware_adc)

# enable usb output, disable uart output
pico_enable_stdio_usb(QualityDeskControl 1)
pico_enable_stdio_uart(QualityDeskControl 0)

# create map/bin/hex/uf2 file in addition to ELF.
pico_add_extra_outputs(QualityDeskControl)
