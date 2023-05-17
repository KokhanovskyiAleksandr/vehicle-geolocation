CREATE TABLE user
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    email      varchar(255) unique not null,
    first_name varchar(50),
    last_name  varchar(100),
    password   varchar(255)        not null,
    role       varchar(20) default 'USER',
    status     varchar(20) default 'ACTIVE'
);
