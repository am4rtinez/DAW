from app import create_app
import config

if __name__ == '__main__':
    # Modificado para que el host sea visible en toda la red.
    app = create_app()

    # Using a production configuration
    # app.config.from_object('config.ProdConfig')

    # Using a development configuration
    app.config.from_object(config.DevConfig)

    app.run(port=5123)