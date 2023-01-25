from decouple import config


class Config:
    CSRF_ENABLED = True

    USER = 'sa'
    PASSWORD = 'RealIkili2019!!'
    HOST = '37.148.209.211'
    DATABASE = 'QualityDB'

    SECRET_KEY = config('SECRET_KEY', default='S#perS3crEt_007')
    SQLALCHEMY_DATABASE_URI = 'mssql+pyodbc://' + USER + ':' + PASSWORD + '@' + HOST + '/' + DATABASE + '?driver=ODBC+Driver+17+for+SQL+Server'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
