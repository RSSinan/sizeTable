from decouple import config


class Config:
    CSRF_ENABLED = True

    USER = 'sa'
    PASSWORD = 'sinan1995'
    HOST = 'NTB-97\SQLEXPRESS'
    DATABASE = 'QualityDB2'

    SECRET_KEY = config('SECRET_KEY', default='S#perS3crEt_007')
    SQLALCHEMY_DATABASE_URI = 'mssql+pyodbc://' + USER + ':' + PASSWORD + '@' + HOST + '/' + DATABASE + '?driver=SQL+Server'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
