TOP_LOCAL_PATH:=$(call my-dir)

include $(call all-subdir-makefiles)


LOCAL_PATH:=$(TOP_LOCAL_PATH)
include $(CLEAR_VARS)

LOCAL_LDLIBS := -llog

LOCAL_SRC_FILES := neubot_wrap.cxx
		
LOCAL_MODULE := neubotjava

LOCAL_STATIC_LIBRARIES := neubot

include $(BUILD_SHARED_LIBRARY)


