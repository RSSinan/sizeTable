from decouple import config


class Config:
    CSRF_ENABLED = True

    USER = 'sa'
    PASSWORD = 'RealIkili2019!!'
    HOST = '37.148.209.211'
    DATABASE = 'QualityDB'

    SECRET_KEY = config('SECRET_KEY', default='S#perS3crEt_007')
    SQLALCHEMY_DATABASE_URI = 'mssql+pyodbc://' + USER + ':' + PASSWORD + '@' + HOST + '/' + DATABASE + '?driver=/opt/microsoft/msodbcsql17/lib64/libmsodbcsql-17.0.so.1.1'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
