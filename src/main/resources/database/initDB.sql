CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,                                     -- Уникальный идентификатор пользователя
    username VARCHAR(50) UNIQUE NOT NULL,                           -- Имя пользователя (уникальное)
    email VARCHAR(100) UNIQUE NOT NULL,                             -- Адрес электронной почты (уникальный)
    password_hash CHAR(64) NOT NULL,                                -- Хэш пароля для безопасного хранения
    full_name VARCHAR(100) NOT NULL,                                -- Полное имя пользователя
    bio TEXT NOT NULL,                                              -- Описание профиля
    profile_picture_url VARCHAR(255),                               -- URL аватарки пользователя
    date_of_birth DATE NOT NULL,                                    -- Дата рождения пользователя
    followers_count INT DEFAULT 0 NOT NULL,                         -- Количество подписчиков
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL         -- Дата регистрации
);