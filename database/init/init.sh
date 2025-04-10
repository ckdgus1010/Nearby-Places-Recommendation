#!/bin/bash

# 환경 변수에서 사용자 및 비밀번호 가져오기
DB_USER=${MARIADB_USERNAME}
DB_PASS=${MARIADB_PASSWORD}
DB_NAME=${MARIADB_DATABASE_NAME}

echo "DB_NAME: $DB_NAME"

# SQL 명령어 실행
mysql -u root -p"$MARIADB_ROOT_PASSWORD" <<-EOSQL
    CREATE DATABASE IF NOT EXISTS $DB_NAME;
    CREATE USER IF NOT EXISTS '$DB_USER'@'%' IDENTIFIED BY '$DB_PASS';
    GRANT ALL PRIVILEGES ON $DB_NAME.* TO '$DB_USER'@'%';
    FLUSH PRIVILEGES;
EOSQL
