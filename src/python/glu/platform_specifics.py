"""
Platform specific settings for Glu.

By modifying these, we can switch to a different Http server abstraction
or a different storage abstraction.

"""

#
# These are the three types of platforms we currently know about.
#
PLATFORM_PYTHON = "Python"
PLATFORM_JYTHON = "Jython"
PLATFORM_GAE    = "GAE"

#
# ------------------------------------------------------------------------------------------
# !!! EDIT THIS:
# ------------------------------------------------------------------------------------------
#
PLATFORM = PLATFORM_JYTHON




#
# Export the correct storage object under the generic name 'STORAGE_OBJECT'
#
if PLATFORM == PLATFORM_GAE:
    from glu.storageabstraction.gae_storage import GaeStorage
    STORAGE_OBJECT = GaeStorage()
else:
    from glu.storageabstraction.resource_storage import ResourceStorage
    STORAGE_OBJECT = ResourceStorage("resourceDB")


#
# Export the correct server class under the generic name 'HttpServer'
#
if PLATFORM == PLATFORM_JYTHON:
    from glu.httpabstraction.jython_java_server import JythonJavaHttpServer as HttpServer
elif PLATFORM == PLATFORM_PYTHON:
    from glu.httpabstraction.python_http_server import PythonHttpServer as HttpServer
else:
    from glu.httpabstraction.gae_http_server import GaeHttpServer as HttpServer


