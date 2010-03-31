"""
Define any Glu specific exceptions we might use.

"""

class GluException(Exception):
    """
    The base class for all exceptions generated by Glu.
    
    """
    pass

class GluMethodNotAllowed(GluException):
    code = 405
    msg  = "Method not allowed"
    