import os

BASE_DIR = os.path.abspath(os.path.dirname(__file__))


class Config:
    def init_app(self, app):
        pass


class DevelopmentConfig(Config):
    DEBUG = True


class TestingConfig(Config):
    TESTING = True


class ProductionConfig(Config):
    pass


config = {
    "development": DevelopmentConfig(),
    "testing": TestingConfig(),
    "production": ProductionConfig(),
    "default": DevelopmentConfig()
}
